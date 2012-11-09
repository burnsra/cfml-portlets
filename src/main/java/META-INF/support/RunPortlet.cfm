<cfsilent>
	<cfscript>

		if(thisTag.ExecutionMode EQ "Start")
		{//Before the portlet is called
			caller.originalPathToRunForCfmlPortlets = request.cfmlportlet_path;
			if (structKeyExists(REQUEST,'CFML_Variables') AND structKeyExists(REQUEST['CFML_Variables'],'parameters')) {
				structAppend(form,request['CFML_Variables']['parameters']);
			}
		 	structDelete(request['CFML_Variables'],"parameters");
		 	structAppend(CALLER, request['CFML_Variables']);
		 	structInsert(CALLER, "PORTLETINFO", structnew());
		 	structClear(request);
		}
		else
		{//After the portlet is called
			if(structKeyExists(caller,"PORTLETINFO"))
			{
				if(structKeyExists(caller.PORTLETINFO, "Title"))
				{
					caller.PortletHelper.setTitle(caller.PORTLETINFO.title);
				}
			}
		}
		 	
	</cfscript>
</cfsilent>