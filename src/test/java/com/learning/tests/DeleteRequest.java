package com.learning.tests;

import com.learning.annotation.FrameWorkAnnotation;
import com.learning.reports.ExtentLogger;
import com.learning.requestbuilder.RequestBuilders;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class DeleteRequest {

    @FrameWorkAnnotation
    @Test
    public void deleteEmployee(){
        RequestBuilders.buildRequestForDeleteCall()
            .pathParam("id", 383)
            .delete("/employees/{id}");
//        ExtentLogger.logResponse("Employee deleted successfully");
}

}
