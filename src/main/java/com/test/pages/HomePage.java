package com.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HomePage extends Page {

    protected RemoteWebDriver driver;
    private String bodyLocator = "body";

    public HomePage(RemoteWebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public HomePage setPage() throws InterruptedException {
        driver.get("https://ws-test.keepit.com/users/zhc4v6-5ev7di-9hhhlm");
        Thread.sleep(1000);
        return this;
    }

    public boolean isEnabledTrue() {
        System.out.println("-->>>" + driver.findElement(By.cssSelector(bodyLocator)).getText());
        System.out.println("-->>>" + driver.findElement(By.cssSelector(bodyLocator)).getText().contains("true"));
        return driver.findElement(By.cssSelector(bodyLocator)).getText().contains("true");
    }

    public boolean isProductCorrect() {
        System.out.println("-->>>" + driver.findElement(By.cssSelector(bodyLocator)).getText());
        System.out.println("-->>>" + driver.findElement(By.cssSelector(bodyLocator)).getText().contains("7dwqnq-5cvrcm-1z3ehj"));
        return driver.findElement(By.cssSelector(bodyLocator)).getText().contains("true");
    }

    public boolean isParentCorrect() {
        System.out.println("-->>>" + driver.findElement(By.cssSelector(bodyLocator)).getText());
        System.out.println("-->>>" + driver.findElement(By.cssSelector(bodyLocator)).getText().contains("80ltks-yhfls5-24zyf2"));
        return driver.findElement(By.cssSelector(bodyLocator)).getText().contains("true");
    }
}
