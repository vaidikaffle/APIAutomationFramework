package com.learning.requestbuilder;

import com.learning.enums.PropertiesType;
import com.learning.utils.PropertyUtils;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public final class RequestBuilders {

    private RequestBuilders(){}

    public static RequestSpecification buildRequestForGetCall(){
        return given()
                .baseUri(PropertyUtils.getValues(PropertiesType.BASEURL))
                .log()
                .all();
    }

    public static RequestSpecification buildRequestForPostCall(){
        return buildRequestForGetCall()
                .contentType(ContentType.JSON);
    }

    public static RequestSpecification buildRequestForPutCall(){
        return buildRequestForGetCall()
                .contentType(ContentType.JSON);
    }

    public static RequestSpecification buildRequestForDeleteCall(){
        return buildRequestForGetCall();
    }
}
