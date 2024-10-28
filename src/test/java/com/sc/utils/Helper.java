package com.sc.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Helper {

    private static final Duration durationTimeout = Duration.ofSeconds(10);

    public static void scrollIntoView(By locator) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), durationTimeout);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        JavascriptExecutor javascriptExecutor = ((JavascriptExecutor) Driver.getDriver());
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static WebElement waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), durationTimeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitAndClick(By locator) {
        scrollIntoView(locator);
        waitForElementToBeClickable(locator).click();
    }

    public static void waitAndSendKeys(By locator, String text) {
        scrollIntoView(locator);
        waitForElementToBeClickable(locator).sendKeys(text);
    }

    public static String getTextOfWebElement(By locator) {
        scrollIntoView(locator);
        return waitForElementToBeClickable(locator).getText();
    }

    public static List<String> getTextOfWebElementsList(By locator) {
        scrollIntoView(locator);
        return Driver.getDriver().findElements(locator).stream().map(WebElement::getText).toList();
    }

    public static boolean isNameSorted(List<String> list, boolean ascending) {
        if (list == null || list.size() <= 1) {
            return true;
        }

        for (int i = 0; i < list.size() - 1; i++) {
            int comparison = list.get(i).compareTo(list.get(i + 1));
            if ((ascending && comparison >= 0) || (!ascending && comparison <= 0)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPriceSorted(List<Float> list, boolean ascending) {
        if (list == null || list.size() <= 1) {
            return true;
        }

        for (int i = 0; i < list.size() - 1; i++) {
            if ((ascending && list.get(i) > list.get(i + 1)) ||
                    (!ascending && list.get(i) < list.get(i + 1))) {
                return false;
            }
        }
        return true;
    }

}
