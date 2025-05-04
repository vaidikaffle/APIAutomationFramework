package com.learning.tests;

import com.learning.annotation.FrameWorkAnnotation;
import com.learning.constants.FrameworkConstants;
import com.learning.listeners.TestListeners;
import com.learning.pojo.Employee;
import com.learning.reports.ExtentLogger;
import com.learning.utils.ApiUtils;
import com.learning.utils.RandomUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static com.learning.requestbuilder.RequestBuilders.*;

@Listeners(TestListeners.class)
public class AssignmentTests {
    
    @FrameWorkAnnotation(author = {"Vaidik"}, category = {"Assignment"})
    @Test
    public void assignmentTest(){

        // Get the size of the employee list
        Response response = buildRequestForGetCall()
                .get("/employees");
        int size = response.jsonPath().getList("$").size();
        System.out.println("Size before: "+ size);

        // Create a new Employee
        Employee employee = Employee.builder()
                .setFirstname(RandomUtils.getFirstName())
                .setLastname(RandomUtils.getLastName())
                .setId(RandomUtils.getId()).build();

        RequestSpecification requestSpecification = buildRequestForPostCall().body(employee);
        ExtentLogger.logRequest(requestSpecification);
        Response postResponse =  requestSpecification.post("/employees");
        ExtentLogger.logResponse(postResponse.asPrettyString());

        // Check the size of employee list after creating a new employee
        Assertions.assertThat(buildRequestForGetCall().get("/employees").jsonPath().getList("$").size()).isEqualTo(size+1);

        int id = employee.getId();
        // Update the created employee
        Employee updatedEmployee = Employee.builder()
                .setFirstname(RandomUtils.getFirstName())
                .setLastname(RandomUtils.getLastName())
                .setId(id)
                .build();
        RequestSpecification requestSpecification1 = buildRequestForPutCall().body(updatedEmployee);
        ExtentLogger.logRequest(requestSpecification1);

        Response putResponse = requestSpecification1.pathParams("id", id).put("/employees/{id}");
        putResponse.prettyPrint();
        ApiUtils.storeStringResponseAsJsonFile(FrameworkConstants.getResponseJsonFolderPath() + "Assignment_PutResponse.txt", putResponse);
        ExtentLogger.logResponse(putResponse.asPrettyString());

        // Delete the Employee
        buildRequestForDeleteCall().pathParam("id", id).delete("/employees/{id}");

        // Again check the size of the employee after deleting
        Assertions.assertThat(buildRequestForGetCall().get("/employees").jsonPath().getList("$").size()).isEqualTo(size);

    }
    
}
