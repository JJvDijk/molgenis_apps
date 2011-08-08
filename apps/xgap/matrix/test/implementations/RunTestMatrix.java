package matrix.test.implementations;

import java.io.File;

import matrix.test.implementations.binary.TestBinMatrix;
import matrix.test.implementations.csv.TestFileMatrix;
import matrix.test.implementations.database.TestDatabaseMatrix;
import matrix.test.implementations.general.Params;
import matrix.test.implementations.memory.TestMemoryMatrix;

import org.molgenis.framework.db.Database;
import org.molgenis.framework.db.DatabaseException;
import org.molgenis.organization.Investigation;
import org.molgenis.xgap.xqtlworkbench.ResetXgapDb;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.ExpectedExceptions;
import org.testng.annotations.Test;

import test.TestHelper;

import app.servlet.MolgenisServlet;

/**
 * Test data matrix import and export across all backends, all retrieval functions,
 * data types, and most dimensions, transpositions, sparsities, and text length variation.
 * 
 * To be used in xQTL automated test cases
 *
 */
public class RunTestMatrix {

	@DataProvider(name = "params")
	public static Object[][] data() {
		Object[][] data = new Object[][] {
				//			 DIM DIM TEXT	FIX.T?  SPARSE?	SKIPEL.?
				{ new Params(1,	 1,  0,		true,	false,	false) },
				{ new Params(1,	 1,  1,		true,	false,	false) },
				{ new Params(20, 1, 10,		true,	true,	false) },
				{ new Params(1, 20, 10,		false,	false,	false) },
				{ new Params(50, 10, 127,	false,	true,	false) },
				{ new Params(10, 50, 127,	true,	false,	false) },
				};
		return data;
	}

	@BeforeClass(alwaysRun = true)
	public void setupBeforeClass() throws Exception {
		
		TestHelper.deleteDatabase();
		
		Database db = new MolgenisServlet().getDatabase();
		
		//assert db is empty
		Assert.assertFalse(db.getFileSourceHelper().hasFilesource(false));
		try{
			db.find(Investigation.class).get(0);
			Assert.fail("DatabaseException expected");
		}catch(DatabaseException expected){
			//DatabaseException was thrown
		}
		
		//setup database
		String report = ResetXgapDb.reset(db, true);
		Assert.assertTrue(report.endsWith("SUCCESS"));
		
		//setup file storage
		String path = new File(".").getAbsolutePath() + File.separator + "tmp_matrix_test_data";
		db.getFileSourceHelper().setFilesource(path);
		db.getFileSourceHelper().validateFileSource();
		Assert.assertTrue(db.getFileSourceHelper().hasValidFileSource());
	}
	
	@AfterClass(alwaysRun = true)
	public void cleanupAfterClass() throws InterruptedException, Exception
	{
		TestHelper.deleteStorage();
	}

	@Test(dataProvider = "params")
	public void binary(Params params) throws Exception{
		Database db = new MolgenisServlet().getDatabase();
		new TestBinMatrix(db, params);
	}

	@Test(dataProvider = "params")
	public void database(Params params) throws Exception {
		Database db = new MolgenisServlet().getDatabase();
		new TestDatabaseMatrix(db, params);
	}

	@Test(dataProvider = "params")
	public void file(Params params) throws Exception {
		Database db = new MolgenisServlet().getDatabase();
		new TestFileMatrix(db, params);
	}
	
	@Test(dataProvider = "params")
	public void memory(Params params) throws Exception {
		Database db = new MolgenisServlet().getDatabase();
		new TestMemoryMatrix(db, params);
	}
	
}