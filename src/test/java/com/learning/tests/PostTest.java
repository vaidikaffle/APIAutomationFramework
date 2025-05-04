package com.learning.tests;

import com.learning.annotation.FrameWorkAnnotation;
import com.learning.constants.FrameworkConstants;
import com.learning.listeners.TestListeners;
import com.learning.pojo.Employee;
import com.learning.reports.ExtentLogger;
import com.learning.requestbuilder.RequestBuilders;
import com.learning.utils.ApiUtils;
import com.learning.utils.RandomUtils;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.File;
import java.lang.reflect.Method;


@Listeners(TestListeners.class)
public class PostTest {

    @FrameWorkAnnotation(author = {"Vaidik"}, category = {"Regression"})
    @Test
    public void postCallTests(){

        // Create and employee in the DB using post calls
        // Construct using pojo and lombok builder

        Employee employee =  Employee.builder()
                .setId(RandomUtils.getId())
                .setFirstname(RandomUtils.getFirstName())
                .setLastname(RandomUtils.getLastName())
                .build(); // Arrange

        // Print also the request in the Extent Report
        RequestSpecification requestSpecification = RequestBuilders
                .buildRequestForPostCall()
                .body(employee);
        ExtentLogger.logRequest(requestSpecification);
        Response response = requestSpecification
                .post("/employees");
        response.prettyPrint(); //Actions

        Assertions.assertThat(response.getStatusCode()).isEqualTo(201); //Assertions
        ExtentLogger.logResponse(response.asPrettyString());
    }

    @FrameWorkAnnotation(author = {"Vaidik"}, category = {"Regression"})
    @Test
    public void postRequestUsingExternalFile(Method method){

        // Read req body from an external file and convert is to String.

        String resource =  ApiUtils
                .readJsonAndGetAsString(FrameworkConstants.getRequestJsonFolderPath()+ "request.json")
                .replace("f_name", RandomUtils.getFirstName())
                .replace("userID", String.valueOf(RandomUtils.getId()))
                .replace("l_name", RandomUtils.getLastName()); // Arrange

        Response response = RequestBuilders
                .buildRequestForPostCall()
                .body(resource)
                .post("/employees");
        response.prettyPrint(); // Actions

        // To save the response in an external file and save the file
        ApiUtils.storeStringResponseAsJsonFile(FrameworkConstants.getResponseJsonFolderPath() + method.getName() + "Response.json", response);

        // Validate Schema of the JSON
        response.then().body(JsonSchemaValidator.matchesJsonSchema(
                new File(FrameworkConstants.getJsonSchemaFilePath() + "employeeSchema.json")));

        Assertions.assertThat(response.getStatusCode()).isEqualTo(201); // Assertions

        ExtentLogger.logResponse(response.asPrettyString());

    }


}
