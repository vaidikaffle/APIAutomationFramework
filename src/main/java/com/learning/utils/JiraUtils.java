package com.learning.utils;
import com.learning.constants.FrameworkConstants;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class JiraUtils {

    private JiraUtils(){}

    // Jira Doc: https://developer.atlassian.com/server/jira/platform/jira-rest-api-examples/

    // Commented this code in TestListeners. Need to uncomment to use this code
    public static String createIssueInJira(String errorMessage) {
        if (PropertyUtils.getPropertyValue("createIssueInJira").equalsIgnoreCase("yes")) {
            String body = ApiUtils.readJsonAndGetAsString(FrameworkConstants.getRequestJsonFolderPath() + "jiraRequest")
                    .replace("KEY", "OOBEE")
                    .replace("DESCRIPTION", errorMessage);

            Response response = given()
                    .contentType(ContentType.JSON)
                    .auth()
                    .basic("Username", "Password") // Jira username and password
                    .log()
                    .all()
                    .body(body)
                    .post("http://localhost:8080/rest/api/2/issue/"); // Jira base URL

            response.prettyPrint();
            return response.jsonPath().getString("key");
        } else {
            System.out.println("Skipping Jira issue creation as overrideReports is set to 'no'.");
            return null;
        }
    }

}
