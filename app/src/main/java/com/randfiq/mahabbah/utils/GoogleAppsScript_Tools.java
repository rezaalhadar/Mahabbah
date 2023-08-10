package com.randfiq.mahabbah.utils;

public class GoogleAppsScript_Tools implements Constant {

    String WebApp_Prefix = "https://script.google.com/macros/s/";
    String WebApp_Suffix = "/exec";

    public String setupSimpleWebAppURL(String WebApp_DeploymentID){
        String WebAppURL = WebApp_Prefix + WebApp_DeploymentID + WebApp_Suffix;
        return WebAppURL;
    }

    public String setSingleActionWebApp(String WebApp_DeploymentID, String action){
        String WebAppURL = setupSimpleWebAppURL(WebApp_DeploymentID);
        return WebAppURL + "?action=" + action;
    }
}
