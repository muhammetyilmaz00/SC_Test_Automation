package com.sc.pageObjects.web;

import com.sc.pageObjects.interfaces.SaucedemoPageObjectsInterface;
import com.sc.utils.Driver;
import com.sc.utils.Helper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class SaucedemoPageObjects implements SaucedemoPageObjectsInterface {

    String username = "user-name";
    String password = "password";
    String loginButton = "login-button";
    String productXpath = "//div[text()='%s']/../../..//button[text()='Add to cart']";
    String shoppingCartButton = "shopping_cart_container";
    String cartList = "//div[@class='cart_list']//div[@class='inventory_item_name']";
    String checkoutButton = "checkout";
    String firstName = "first-name";
    String lastName = "last-name";
    String postalCode = "postal-code";
    String continueButton = "continue";
    String paymentInformation = "[data-test='payment-info-value']";
    String shippingInformation = "[data-test='shipping-info-value']";
    String finishButton = "finish";
    String successMessage = "[data-test='complete-header']";
    String removeButton = "//div[text()='%s']/../../..//button[text()='Remove']";
    String emptyCartList = ".cart_list";
    String productSortContainer = ".product_sort_container";
    String productPrices = "[data-test='inventory-item-price']";
    String productNames = ".inventory_item_name";
    String burgerMenu = "react-burger-menu-btn";
    String logoutButton = "logout_sidebar_link";


    @Override
    public void login(String username, String password) {
        Helper.waitAndSendKeys(By.id(this.username), username);
        Helper.waitAndSendKeys(By.id(this.password), password);
        Helper.waitAndClick(By.id(loginButton));
    }

    @Override
    public void selectProduct(String product) {
        Helper.waitAndClick(By.xpath(String.format(productXpath, product)));
    }

    @Override
    public void clickShoppingCartButton() {
        Helper.waitAndClick(By.id(shoppingCartButton));
    }

    @Override
    public List<String> getCartList() {
        return Helper.getTextOfWebElementsList(By.xpath(cartList));
    }

    @Override
    public void clickCheckoutButton() {
        Helper.waitAndClick(By.id(checkoutButton));
    }

    @Override
    public void fillInPersonalDetails(String firstName, String lastName, String postcode) {
        Helper.waitAndSendKeys(By.id(this.firstName), firstName);
        Helper.waitAndSendKeys(By.id(this.lastName), lastName);
        Helper.waitAndSendKeys(By.id(this.postalCode), postcode);
    }

    @Override
    public void clickContinueButton() {
        Helper.waitAndClick(By.id(continueButton));
    }

    @Override
    public String checkPaymentInfo() {
        return Helper.getTextOfWebElement(By.cssSelector(paymentInformation));
    }

    @Override
    public String checkShippingInfo() {
        return Helper.getTextOfWebElement(By.cssSelector(shippingInformation));
    }

    @Override
    public void clickFinishButton() {
        Helper.waitAndClick(By.id(finishButton));
    }

    @Override
    public String getSuccessMessage() {
        return Helper.getTextOfWebElement(By.cssSelector(successMessage));
    }

    @Override
    public void removeItemFromCart(String productName) {
        Helper.waitAndClick(By.xpath(String.format(removeButton, productName)));
    }

    @Override
    public void checkEmptyCartList() {
        Assert.assertEquals("QTYDescription",Helper.getTextOfWebElement(By.cssSelector(emptyCartList)));
    }

    @Override
    public void sortTheProducts(String sortType) {
        Helper.scrollIntoView(By.cssSelector(productSortContainer));
        Select select = new Select(Driver.getDriver().findElement(By.cssSelector(productSortContainer)));
        switch (sortType) {
            case "Price (low to high)":
                select.selectByValue("lohi");
                break;
            case "Price (high to low)":
                select.selectByValue("hilo");
                break;
            case "Name (A to Z)":
                select.selectByValue("az");
                break;
            case "Name (Z to A)":
                select.selectByValue("za");
                break;
        }
    }

    @Override
    public boolean isProductSorted(String sortType) {
        List<String> priceList = Helper.getTextOfWebElementsList(By.cssSelector(productPrices));
        List<Float> floatList = new ArrayList<>();
        for (String price : priceList) {
            price = price.replace("$", "");
            floatList.add(Float.parseFloat(price));

        }
        List<String> nameList = Helper.getTextOfWebElementsList(By.cssSelector(productNames));
        return switch (sortType) {
            case "Price (low to high)" -> Helper.isPriceSorted(floatList,true);
            case "Price (high to low)" -> Helper.isPriceSorted(floatList,false);
            case "Name (A to Z)" -> Helper.isNameSorted(nameList,true);
            case "Name (Z to A)" -> Helper.isNameSorted(nameList,false);
            default -> false;
        };

    }

    @Override
    public void logout() {
        Helper.waitAndClick(By.id(burgerMenu));
        Helper.waitAndClick(By.id(logoutButton));
        Assert.assertTrue(Driver.getDriver().findElement(By.id(loginButton)).isDisplayed());
    }

}
