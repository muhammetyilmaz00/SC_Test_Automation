package com.sc.stepDefinitions;

import com.sc.requests.CallService;
import com.sc.utils.ContextStore;
import com.sc.utils.LogUtils;
import com.sc.utils.ReqresApiClient;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import org.junit.Assert;

public class DeleteUserStepDef extends ReqresApiClient {
    CallService callService = new CallService(requestSpecification);

    @Given("I request to delete the user")
    public void deleteUser() {
        LogUtils.info("I request to delete the user");
        Response response = callService.executeDeleteRequest(USERS_ENDPOINT, ContextStore.get("createdUserId"));

        LogUtils.info("Store the response for further assertions");
        ContextStore.put("response", response);
    }

    @And("the response must have no body content")
    public void theResponseMustHaveNoBodyContent() {
        LogUtils.info("Acquire the response from the context store");
        Response response = ContextStore.get("response");

        LogUtils.info("Assert that the delete response is empty");
        Assert.assertTrue(response.asString().isEmpty());
    }
}
