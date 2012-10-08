<#macro org_molgenis_hemodb_header_HomePage screen>
<!-- normally you make one big form for the whole plugin-->
<form method="post" enctype="multipart/form-data" name="${screen.name}" action="">
	<!--needed in every form: to redirect the request to the right screen-->
	<input type="hidden" name="__target" value="${screen.name}">
	<!--needed in every form: to define the action. This can be set by the submit button-->
	<input type="hidden" name="__action">
	
<!-- this shows a title and border -->
	<div class="formscreen">
		<div class="form_header" id="${screen.getName()}">
		${screen.label}
		</div>
		
		<#--optional: mechanism to show messages-->
		<#list screen.getMessages() as message>
			<#if message.success>
		<p class="successmessage">${message.text}</p>
			<#else>
		<p class="errormessage">${message.text}</p>
			</#if>
		</#list>
		
<div align="center">
	<table>
		<tr>
			<td colspan="7">
				<div style="height: 10px">&nbsp;</div>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="7" >
				<div align="left">
				<!-- <font style='font-size:24px; font-weight:bold;'>xQTL workbench</font>-->
				<#if screen.userIsAdminAndDatabaseIsEmpty == true>
					<table bgcolor="white" border="3" bordercolor="red">
						<tr>
							<td>
								<br><i><font color="red">You are logged in as admin, and the database does not contain any investigations or other users. Automated setup is now possible. Database additions will disable this notice.</font></i><br><br>
								Enter your preferred file storage location, and press 'Load' to validate this path and load the example dataset here. Unvalidated paths are overwritten. In addition, the demo users and permissions are loaded.<br><br>
								The default shown is ./data - consider changing this before continuing. Be aware of permissions your OS grants you on this directory, depending on which user started up the application.<br><br>
								<#if screen.validpath?exists>
									<b>A valid path is present and cannot be overwritten here. To do so, use Settings -> File storage.</b><br><br>
									Path: <font style="font-size:medium; font-family: Courier, 'Courier New', monospace">${screen.validpath}</font>
								<#else>
									Path: <input type="text" size="30" style="border:2px solid black; color:blue; display:inline; font-size:medium; font-family: Courier, 'Courier New', monospace" id="inputBox" name="fileDirPath" value="./data" onkeypress="if(window.event.keyCode==13){document.forms.${screen.name}.__action.value = 'setPathAndLoad';}">
								</#if>
								<input type="submit" value="Load" id="loadExamples" onclick="document.forms.${screen.name}.__action.value = 'setPathAndLoad'; document.forms.${screen.name}.submit();"/>
								<br><br>
							</td>
						</tr>
					</table>
				</#if>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="7">
				<div style="height: 10px">&nbsp;</div>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="7" >
				<div align="left">
					Welcome to the <b>Human leukemia database</b>, a gene expression workbench for the storage and analysis of human leukemia and stem cell geno- and phenotypic data. The  human leukemia database provides the following features: 
					<ul>
					<li>Extensible computation interface
						<ul>
					 		<li>Biologists can run <b>pre-defined analyses</b></li>
					     	<li>Bio-informaticians can <b>add their own (R) scripts</b></li>
					 	</ul>
					</li>
					<li>Three levels of users:
						<ul>
							<li><b>Biologists</b> - Import, browse and analyze data</li>
				        	<li><b>Bio-informaticians</b> - Add new analysis and tools</li>
						 	<li><b>Administrators</b> - User and database management</li>
						</ul>
					</li>
					<li>Fully <b>configurable</b> user management and permission system</li>
			</div>
			</td>
		</tr>
		<tr>
			<td colspan="7">
				<div style="height: 20px">&nbsp;</div>
			</td>
		</tr>
		<tr>
			<td colspan="7" width="675">
				<i>References:</i><br>
				<a href="http://www.ncbi.nlm.nih.gov/pubmed/15059831">Molecular Genetics Information System (MOLGENIS): alternatives in developing local experimental genomics databases</a> - Swertz MA, De Brock EO, Van Hijum SA, De Jong A, Buist G, Baerends RJ, Kok J, Kuipers OP, Jansen RC.
				<br><br>
				<a href="http://www.biomedcentral.com/1471-2105/11/S12/S12">The MOLGENIS toolkit: rapid prototyping of biosoftware at the push of a button</a> - Morris A Swertz, Martijn Dijkstra, Tomasz Adamusiak, Joeri K van der Velde, Alexandros Kanterakis, Erik T Roos, Joris Lops, Gudmundur A Thorisson, Danny Arends, George Byelas, Juha Muilu, Anthony J Brookes, Engbert O de Brock, Ritsert C Jansen and Helen Parkinson
			</td>
		</tr>	
		<tr>
			<td align="center" colspan="7" >
				<div style="height: 25px">&nbsp;</div>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="7" >
				<table style="background: #FFFFFF;" cellpadding="10" cellspacing="10" border="2" width="750px">
					<tr>
						<td align="center">
							<a target="_blank" href="http://www.molgenis.org/"><img src="clusterdemo/logos/molgenis_logo.png" width="75px" height="50px" alt="logo Molgenis"></a>
							<a target="_blank" href="http://wiki.gcc.rug.nl/"><img src="clusterdemo/logos/gcc_logo.png" width="300px" height="50px" alt="logo GCC"></a>
							<a target="_blank" href="http://www.rug.nl/"><img src="clusterdemo/logos/rug_logo.png" width="150px" height="50px" alt="logo RUG"></a>
							<a target="_blank" href="http://www.umcg.nl/"><img src="clusterdemo/logos/umcg_logo.png" width="150px" height="50px" alt="logo UMCG"></a>
						</td>
					</tr>
				</table>
				<font size=1>(c) 2012 GBIC Groningen</font>
			</td>
		</tr>
	</table>
</div>


</div>
</form>
</#macro>
