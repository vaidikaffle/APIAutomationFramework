package com.learning.tests;

import com.learning.annotation.FrameWorkAnnotation;
import com.learning.listeners.TestListeners;
import com.learning.reports.ExtentLogger;
import com.learning.requestbuilder.RequestBuilders;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(TestListeners.class)
public class GetTests{

    @FrameWorkAnnotation(author = {"Vaidik"}, category = {"Regression"})
    @Test
    public void getEmployeesDetails(){
        Response response = RequestBuilders.buildRequestForGetCall()
                .get("/employees");
        response.prettyPrint();

        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(response.getHeader("Content-Type")).isEqualTo("application/json");
        Assertions.assertThat(response.getBody().jsonPath().getList("$").size())
                .as("validating the size of the Employee Array ").isGreaterThan(30);
        ExtentLogger.logResponse(response.asPrettyString());
    }

    @FrameWorkAnnotation
    @Test
    public void getEmployeeDetails(){
        Response response = RequestBuilders.buildRequestForGetCall()
                .pathParam("id", 2)
                .get("/employees/{id}");
        response.prettyPrint();

        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(response.getHeader("Content-Type")).isEqualTo("application/json");
        Assertions.assertThat(response.getBody().jsonPath().getString("first_name"))
                .as("validating the first name of the Employee ")
                .isEqualTo("Jane")
                .hasSizeBetween(3, 10);
        Assertions.assertThat(response.getBody().jsonPath().getMap("$"))
                .as("Checking if last_name is present in the JSON")
                .containsKey("last_name");
        ExtentLogger.logResponse(response.asPrettyString());
    }

}
