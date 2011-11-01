
package plugins.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.Map;

import org.molgenis.framework.db.Database;
import org.molgenis.framework.ui.PluginModel;
import org.molgenis.framework.ui.ScreenController;
import org.molgenis.util.Entity;
import org.molgenis.util.Tuple;

	
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.*;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.util.SimpleIRIMapper;


public class importOwlToPheno extends PluginModel<Entity>
{
	private String Status = "";

	private static final long serialVersionUID = 6149846107377048848L;
	
	public importOwlToPheno(String name, ScreenController<?> parent)
	{
		super(name, parent);
	}
	
	
	@Override
	public String getViewName()
	{
		return "plugins_data_importOwlToPheno";
	}

	@Override
	public String getViewTemplate()
	{
		return "plugins/data/importOwlToPheno.ftl";
	}

	@Override
	public void handleRequest(Database db, Tuple request) throws Exception	{
		if ("ImportOwlToPheno".equals(request.getAction())) {
			try {
					System.out.println("Starting importing owl");
					this.setStatus("Starting importing owl");
					
					//get hold of ontology manager
		            OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		
		            //if ontology available from the web 
		            IRI iri = IRI.create("http://www.datashaper.org/datashaper/owl/2009/10/generic.owl");
		            OWLOntology dataShaperOntologyWeb = manager.loadOntology(iri);
		            System.out.println("Loaded ontology: " + dataShaperOntologyWeb);
		            this.setStatus("Loaded ontology: " + dataShaperOntologyWeb);
		            // Remove the ontology so that we can load a local copy.
			        manager.removeOntology(dataShaperOntologyWeb);
			        
			        //load a local copy 
		            File file = new File("/Users/despoina/Documents/Datashaper/generic.owl");
		
			            if (file.exists()) {
				            //load local copy 
				            // Now load the local copy
				            OWLOntology dataShaperOntology = manager.loadOntologyFromOntologyDocument(file);
				            System.out.println("Loaded ontology: " + dataShaperOntology);
				            this.setStatus("Loaded ontology: " + dataShaperOntology);
				            
				            IRI documentIRI = manager.getOntologyDocumentIRI(dataShaperOntology);
				            System.out.println("    from: " + documentIRI);
				            this.setStatus("     from: " + documentIRI);
				            
				            // Remove the ontology again so we can reload it later
				            manager.removeOntology(dataShaperOntology);
				            
				            /////////////////////////////////////////////////
				            //obtain references to entities (classes, properties, individuals etc.)
				            // We can get a reference to a data factory from an OWLOntologyManager.
				            OWLDataFactory factory = manager.getOWLDataFactory();
				            
				            // The first is by specifying the full IRI.  First we create an IRI object:
				            OWLClass clsAMethodA = factory.getOWLClass(iri); //TODO : recheck the argument 
				            
				            // The first is by specifying the full IRI.  First we create an IRI object:
				            // Now we create the class
				            
				            //we''ll need to build a prefix manager . 
				            PrefixManager pm = new DefaultPrefixManager("http://www.semanticweb.org/owlapi/ontologies/ontology#");
				            OWLClass clsAMethodB = factory.getOWLClass(":A", pm);
				          
				            OWLOntology ontology = manager.createOntology(IRI.create("http://www.semanticweb.org/owlapi/ontologies/ontology"));
				            OWLDeclarationAxiom declarationAxiom = factory.getOWLDeclarationAxiom(clsAMethodA);
				            manager.addAxiom(ontology, declarationAxiom);
				            
				            //////////////////////////////////////////////
				            //work with literals
				            OWLLiteral literal1 = factory.getOWLLiteral("My string literal", "");
				            OWLLiteral literal2 = factory.getOWLLiteral("My string literal", "en");

				            System.out.println(">>>>>test " + literal1 + ">>>>>>"+ literal2);	
				            
				            
			            } else {
			            	System.out.println("The ontology file is not available!");
			            	this.setStatus("The ontology file is not available!");
			            }
		            } catch (OWLOntologyCreationIOException e) {
            		            // IOExceptions during loading get wrapped in an OWLOntologyCreationIOException
            		            IOException ioException = e.getCause();
            		            if (ioException instanceof FileNotFoundException) {
            		                System.out.println("Could not load ontology. File not found: " + ioException.getMessage());
            		            }
            		            else if (ioException instanceof UnknownHostException) {
            		                System.out.println("Could not load ontology. Unknown host: " + ioException.getMessage());
            		            }
            		            else {
            		                System.out.println("Could not load ontology: " + ioException.getClass().getSimpleName() + " " + ioException.getMessage());
            		            }
            		        }
            		   catch (UnparsableOntologyException e) {
            		            // If there was a problem loading an ontology because there are syntax errors in the document (file) that
            		            // represents the ontology then an UnparsableOntologyException is thrown
            		            System.out.println("Could not parse the ontology: " + e.getMessage());
            		            // A map of errors can be obtained from the exception
            		            Map<OWLParser, OWLParserException> exceptions = e.getExceptions();
            		            // The map describes which parsers were tried and what the errors were
            		            for (OWLParser parser : exceptions.keySet()) {
            		                System.out.println("Tried to parse the ontology with the " + parser.getClass().getSimpleName() + " parser");
            		                System.out.println("Failed because: " + exceptions.get(parser).getMessage());
            		            }
            		        }
            		   catch (UnloadableImportException e) {
            		            // If our ontology contains imports and one or more of the imports could not be loaded then an
            		            // UnloadableImportException will be thrown (depending on the missing imports handling policy)
            		            System.out.println("Could not load import: " + e.getImportsDeclaration());
            		            // The reason for this is specified and an OWLOntologyCreationException
            		            OWLOntologyCreationException cause = e.getOntologyCreationException();
            		            System.out.println("Reason: " + cause.getMessage());
            		        }
            		   catch (OWLOntologyCreationException e) {
            		            System.out.println("Could not load ontology: " + e.getMessage());
            		        }
		}
		
	}

	@Override
	public void reload(Database db)	{
	}
	
	@Override
	public boolean isVisible() {
		//you can use this to hide this plugin, e.g. based on user rights.
		//e.g.
		//if(!this.getLogin().hasEditPermission(myEntity)) return false;
		return true;
	}


	public void setStatus(String status) {
		Status = status;
	}


	public String getStatus() {
		return Status;
	}
}