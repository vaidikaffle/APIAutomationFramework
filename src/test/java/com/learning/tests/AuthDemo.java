package com.learning.tests;

import com.learning.constants.FrameworkConstants;
import com.learning.utils.ApiUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;

public class AuthDemo {
    
    @Test
    public void basicAuthTest(){
        // Need to search how to blacklist headers
        Response response = given()
                .header("Authorization","Basic cG9zdG1hbjpwYXNzd29yZA==") // Base64 encoded basic auth
//                .auth()
//                .basic("postman", "password")
                .log()
                .all()
                .get("https://postman-echo.com/basic-auth");
        response.prettyPrint();
    }

    @Test
    public void getAllTheWorkspaces(){
        given()
                .header("X-API-Key", "PMAK-67d115e548cab30001b58121-0650b3c0cff6062725a413591619fab786_needToRemoveAtTheTimeOFExecution")
                .log()
                .all()
                .get("https://api.getpostman.com/workspaces")
                .prettyPrint();
    }

    @Test
    public void createPostmanCollection(){
        String resource = ApiUtils.readJsonAndGetAsString(FrameworkConstants.getRequestJsonFolderPath() + "CreateNewCollectionRequest.json")
                .replace("Sample Collection", "Sample Collection 001");

        Response response = given()
                .header("X-API-Key", "PMAK-67d115e548cab30001b58121-0650b3c0cff6062725a413591619fab786_needToRemoveAtTheTimeOFExecution")
                .contentType(ContentType.JSON)
                .log()
                .all()
                .body(resource)
                .post("https://api.getpostman.com/collections");
        response.prettyPrint();

        // Extracting UID and storing it in the variable
        String collectionUid = response.jsonPath().getString("collection.uid");
        System.out.println("Stored Collection UID: " + collectionUid);

        //Delete the created collection
        given()
                .header("X-API-Key", "PMAK-67d115e548cab30001b58121-0650b3c0cff6062725a413591619fab786_needToRemoveAtTheTimeOFExecution")
                .log()
                .all()
                .delete("https://api.getpostman.com/collections/"+ collectionUid)
                .prettyPrint();
    }
}
