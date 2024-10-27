package com.sc.pageObjects.interfaces;

import java.util.List;

public interface SaucedemoPageObjectsInterface {

    void login(String username, String password);

    void selectProduct(String productName);

    void clickShoppingCartButton();

    List<String> getCartList();

    void clickCheckoutButton();

    void fillInPersonalDetails(String firstName, String lastName, String postcode);

    void clickContinueButton();

    String checkPaymentInfo();

    String checkShippingInfo();

    void clickFinishButton();

    String getSuccessMessage();

    void removeItemFromCart(String productName);

    void checkEmptyCartList();

    void sortTheProducts(String sortType);

    boolean isProductSorted(String sortType);

    void logout();
}
