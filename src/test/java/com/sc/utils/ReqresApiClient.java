package com.sc.utils;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class ReqresApiClient {
    public static RequestSpecification requestSpecification;
    public static final String USERS_ENDPOINT = "users/";

    /**
     * Set up the base URI and request specification for API calls
     */
    public static void setUp() {
        baseURI = PropertiesFactory.getPropertyFromApplication("baseURI");
        requestSpecification = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }
}