<#--Date:        November 11, 2009
 * Template:	PluginScreenFTLTemplateGen.ftl.ftl
 * generator:   org.molgenis.generators.ui.PluginScreenFTLTemplateGen 3.3.2-testing
 * 
 * THIS FILE IS A TEMPLATE. PLEASE EDIT :-)
-->
<#macro org_molgenis_hemodb_header_MolgenisHeader screen>

<div style="height:10px;">&nbsp;</div>

<a href="?__target=main&select=QtlFinderPublic2"><img src="clusterdemo/logos/hemodb_logo.jpg" width="600px" height="100px" /></a>

<table style="width: 100%;">
	<tr>
		<td align="right">
			<font style="font-size:14px;">
				<#-->| <a href="api/REST/">JSON api</a> | <a href="api/SOAP/">SOAP api</a> | <a href="api/REST/">REST api</a> | -->
				<a target="_blank" href="http://www.molgenis.org/wiki/xQTL">Help</a> | <a href="generated-doc/fileformat.html">Exchange format</a> | <a href="api/R/">R api</a> | <a href="api/find/">Find api</a> | ${screen.userLogin}
			</font>
		</td>
	</tr>
</table>


<div style="height:10px;">&nbsp;</div>

	
</#macro>
