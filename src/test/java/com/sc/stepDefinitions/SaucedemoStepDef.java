package com.sc.stepDefinitions;

import com.sc.pageObjects.web.SaucedemoPageObjects;
import com.sc.utils.LogUtils;
import com.sc.utils.PropertiesFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class SaucedemoStepDef {

    SaucedemoPageObjects saucedemoPageObjects = new SaucedemoPageObjects();

    @Given("I login to the saucedemo.com")
    public void iLoginToTheSaucedemoCom() {
        LogUtils.info("I login to the saucedemo.com");
        saucedemoPageObjects.login(PropertiesFactory.getPropertyFromConfiguration("username"),
                PropertiesFactory.getPropertyFromConfiguration("password"));
    }

    @When("I add to chart the item {string}")
    public void iAddToChartTheItem(String productName) {
        LogUtils.info("I select the item " + productName);
        saucedemoPageObjects.selectProduct(productName);
    }

    @And("I click the shopping cart button")
    public void iClickTheShoppingCartButton() {
        LogUtils.info("I click the shopping cart button");
        saucedemoPageObjects.clickShoppingCartButton();
    }

    @And("I check the item {string} in the cart")
    public void iCheckTheItemInTheCart(String productName) {
        LogUtils.info("I check the item " + productName + " in the cart");
        Assert.assertTrue(saucedemoPageObjects.getCartList().contains(productName));
    }

    @And("I click the checkout button")
    public void iClickTheCheckoutButton() {
        LogUtils.info("I click the checkout button");
        saucedemoPageObjects.clickCheckoutButton();
    }

    @And("I fill in first name with {string} and last name with {string} and postcode with {string}")
    public void iFillInFirstNameWithAndLastNameWithAndPostcodeWith(String firstName, String lastName, String postcode) {
        LogUtils.info("I fill in first name with " + firstName + " and last name with " + lastName + " and postcode with " + postcode);
        saucedemoPageObjects.fillInPersonalDetails(firstName, lastName, postcode);
    }

    @And("I click the continue button")
    public void iClickTheContinueButton() {
        LogUtils.info("I click the continue button");
        saucedemoPageObjects.clickContinueButton();
    }

    @And("I check the payment information and shipping information")
    public void iCheckThePaymentInformationAndShippingInformation() {
        LogUtils.info("I check the payment information and shipping information");
        Assert.assertEquals("SauceCard #31337", saucedemoPageObjects.checkPaymentInfo());
        Assert.assertEquals("Free Pony Express Delivery!", saucedemoPageObjects.checkShippingInfo());
    }

    @And("I click the finish button")
    public void iClickTheFinishButton() {
        LogUtils.info("I click the finish button");
        saucedemoPageObjects.clickFinishButton();

    }

    @Then("I should see the {string} message")
    public void iShouldSeeTheMessage(String message) {
        LogUtils.info("I should see the " + message + " message");
        Assert.assertEquals(message, saucedemoPageObjects.getSuccessMessage());
    }

    @And("I click the remove button of the item {string}")
    public void iClickTheRemoveButtonOfTheItem(String productName) {
        LogUtils.info("I click the remove button of the item " + productName);
        saucedemoPageObjects.removeItemFromCart(productName);
    }

    @And("I check the item {string} is not in the cart")
    public void iCheckTheItemIsNotInTheCart(String productName) {
        LogUtils.info("I check the item " + productName + " is not in the cart");
        saucedemoPageObjects.checkEmptyCartList();
    }

    @And("I log out from the saucedemo.com")
    public void iLogOutFromTheSaucedemoCom() {
        LogUtils.info("I log out from the saucedemo.com");
        saucedemoPageObjects.logout();
    }

    @When("I sort the products with {string}")
    public void iSortTheProductsWith(String sortType) {
        LogUtils.info("I sort the products with " + sortType);
        saucedemoPageObjects.sortTheProducts(sortType);
    }

    @Then("I should see the products sorted by {string}")
    public void iShouldSeeTheProductsSortedBy(String sortType) {
        LogUtils.info("I should see the products sorted by " + sortType);
        Assert.assertTrue(saucedemoPageObjects.isProductSorted(sortType));
    }

}
