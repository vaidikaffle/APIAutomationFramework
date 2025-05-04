package com.learning.tests;

import com.learning.annotation.FrameWorkAnnotation;
import com.learning.listeners.TestListeners;
import com.learning.reports.ExtentLogger;
import com.learning.requestbuilder.RequestBuilders;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListeners.class)
public class DataProviderTest {

    @DataProvider
    public static Object[][] getData() {
        return new Object[][]{
                {5, "Shivam"},
                {2, "Jane"},
                {3, "Vaidik"}
        };
    }

    @FrameWorkAnnotation(author = {"Vaidik"}, category = {"Regression"})
    @Test(dataProvider = "getData")
    public void getEmployeeDetails(int id, String lastname){
        Response response = RequestBuilders.buildRequestForGetCall()
                .pathParam("id", id)
                .get("/employees/{id}");
        response.prettyPrint();

        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(response.getHeader("Content-Type")).isEqualTo("application/json");
        Assertions.assertThat(response.getBody().jsonPath().getMap("$"))
                .as("Checking if last_name is present in the JSON")
                .containsKey("last_name");
        ExtentLogger.logResponse(response.asPrettyString());
        System.out.println("lastname = " + lastname);

    }

}
