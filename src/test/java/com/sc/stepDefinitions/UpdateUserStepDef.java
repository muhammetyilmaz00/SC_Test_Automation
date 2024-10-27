package com.sc.stepDefinitions;

import com.sc.pojo.User;
import com.sc.requests.CallService;
import com.sc.utils.ContextStore;
import com.sc.utils.ReqresApiClient;
import com.sc.utils.LogUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;

import java.util.Map;

public class UpdateUserStepDef extends ReqresApiClient {

    CallService callService = new CallService(requestSpecification);
    SoftAssertions softAssertions = new SoftAssertions();

    @When("I request to update the user with the following details")
    public void iRequestToUpdateTheUserWithTheFollowingDetails(DataTable dataTable) {
        LogUtils.info("I request to update the user with the following details" + dataTable.asMaps(String.class, String.class).toString());
        Map<String, String> userDetails = dataTable.transpose().asMap(String.class, String.class);
        Response response = callService.executePutRequest(String.format("%s%s", USERS_ENDPOINT, ContextStore.get("createdUserId")), userDetails);

        LogUtils.info("Store the response for further assertions");
        ContextStore.put("response", response);
    }

    @When("I request to partially update the user with the following details")
    public void iRequestToPartiallyUpdateTheUserWithTheFollowingDetails(DataTable dataTable) {
        LogUtils.info("I request to partially update the user with the following details" + dataTable.asMaps(String.class, String.class).toString());
        Map<String, String> userDetails = dataTable.transpose().asMap(String.class, String.class);
        Response response = callService.executePatchRequest(String.format("%s%s", USERS_ENDPOINT, ContextStore.get("createdUserId")), userDetails);

        LogUtils.info("Store the response for further assertions");
        ContextStore.put("response", response);
    }

    @And("the response must contain the updated user details")
    public void theResponseMustContainTheUpdatedUserDetails(DataTable dataTable) {
        LogUtils.info("Acquire the response from the context store");
        Response response = ContextStore.get("response");

        LogUtils.info("Assert the response contains the user details");
        User user = response.as(User.class);
        Map<String, String> expectedDetails = dataTable.transpose().asMap(String.class, String.class);

        softAssertions.assertThat(user.getUpdatedAt()).isNotNull();
        softAssertions.assertThat(user.getName()).isEqualTo(expectedDetails.get("name"));
        softAssertions.assertThat(user.getJob()).isEqualTo(expectedDetails.get("job"));
        softAssertions.assertAll();
    }
}
