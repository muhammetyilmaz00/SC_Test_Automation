package com.sc.stepDefinitions;

import com.sc.requests.CallService;
import com.sc.utils.ContextStore;
import com.sc.utils.LogUtils;
import com.sc.utils.ReqresApiClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GetUserStepDef extends ReqresApiClient {

    CallService callService = new CallService(requestSpecification);

    @Given("I request to get all users")
    public void iRequestToGetAllUsers() {
        LogUtils.info("Request to get all users");
        Response response = callService.executeGetRequest(USERS_ENDPOINT);

        LogUtils.info("Store the response for further assertions");
        ContextStore.put("response", response);
    }

    @Then("the response should match the schema of a list of users")
    public void theResponseShouldMatchTheSchemaOfAListOfUsers() {
        LogUtils.info("Acquire the response from the context store");
        Response response = ContextStore.get("response");

        LogUtils.info("Validate the response against the schema");
        String schema;
        try {
            schema = new String(Files.readAllBytes(Paths.get("src/test/resources/config/schema.json")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(schema));
    }

}
