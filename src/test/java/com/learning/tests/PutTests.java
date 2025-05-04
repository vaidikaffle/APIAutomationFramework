package com.learning.tests;
import com.learning.annotation.FrameWorkAnnotation;
import com.learning.listeners.TestListeners;
import com.learning.pojo.Employee;
import com.learning.reports.ExtentLogger;
import com.learning.requestbuilder.RequestBuilders;
import com.learning.utils.RandomUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListeners.class)
public class PutTests {

    @FrameWorkAnnotation
    @Test
    public void updateEmployee() {
        Employee employee = Employee.builder()
                .setFirstname(RandomUtils.getFirstName())
                .setLastname(RandomUtils.getLastName())
                .setId(15)
                .build();

        RequestSpecification requestSpecification = RequestBuilders
                .buildRequestForPutCall()
                .body(employee);
        ExtentLogger.logRequest(requestSpecification);

        Response response = requestSpecification
                .pathParam("id", 15)
                .put("/employees/{id}");
        response.prettyPrint();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
        ExtentLogger.logResponse(response.asPrettyString());
    }
}
