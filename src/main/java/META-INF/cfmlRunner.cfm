<cfmodule template="/WEB-INF/support/RunPortlet.cfm" >
    <cftry>
        <cfinclude template="#originalPathToRunForCfmlPortlets#" />
        <cfcatch type="any">
            <cfoutput>Method not implemented.</cfoutput>
        </cfcatch>
    </cftry>
</cfmodule>