package com.sc.stepDefinitions;

import com.sc.pojo.User;
import com.sc.requests.CallService;
import com.sc.utils.ReqresApiClient;
import com.sc.utils.ContextStore;
import com.sc.utils.LogUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;

import java.util.Map;

public class CreateUserStepDef extends ReqresApiClient {

    CallService callService = new CallService(requestSpecification);
    SoftAssertions softAssertions = new SoftAssertions();

    @When("I request to create a new user with the following details")
    public void iRequestToCreateANewUserWithTheFollowingDetails(DataTable dataTable) {
        LogUtils.info("I request to create a new user with the following details" + dataTable.asMaps(String.class, String.class).toString());
        Map<String, String> userDetails = dataTable.transpose().asMap(String.class, String.class);
        Response response = callService.executePostRequest(USERS_ENDPOINT, userDetails);

        LogUtils.info("Store the response for further assertions");
        ContextStore.put("response", response);

        LogUtils.info("Store the user id for further assertions");
        User user = response.as(User.class);
        ContextStore.put("createdUserId", user.getId());
        ContextStore.put("createdUserName", user.getName());
    }

    @Then("the response must contain the user details")
    public void theResponseMustContainTheUserDetails(DataTable dataTable) {
        LogUtils.info("Acquire the response from the context store");
        Response response = ContextStore.get("response");

        LogUtils.info("Assert the response contains the user details");
        User user = response.as(User.class);
        Map<String, String> expectedDetails = dataTable.transpose().asMap(String.class, String.class);

        softAssertions.assertThat(user.getId()).isNotNull();
        softAssertions.assertThat(user.getCreatedAt()).isNotNull();
        softAssertions.assertThat(user.getName()).isEqualTo(expectedDetails.get("name"));
        softAssertions.assertThat(user.getJob()).isEqualTo(expectedDetails.get("job"));
        softAssertions.assertAll();
    }

    @And("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        LogUtils.info("Acquire the response from the context store");
        Response response = ContextStore.get("response");

        LogUtils.info("Assert the response status code is " + expectedStatusCode);
        Assert.assertEquals(expectedStatusCode, response.getStatusCode());
    }

}

