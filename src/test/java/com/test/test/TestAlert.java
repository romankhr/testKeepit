package com.test.test;

import com.test.BaseTest;
import com.test.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestAlert extends BaseTest {

    private HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void setupTest() {
        homePage = new HomePage(driver);
    }

    @Test(groups = "main")
    public void verifyEnabledElementTest() throws Exception {

        //Given user navigates to the web page https://ws-test.keepit.com/users/zhc4v6-5ev7di-9hhhlm

        //when user inserts Login: automation@keepitqa.com and Password: E#*b2wGIbFHz and log in
        homePage.setPage();

        //Then On the home page user makes sure that enabled element in the response are "true".
        Assert.assertTrue(homePage.isEnabledTrue(), "The enabled element in the response are not 'true'");
    }

    @Test(groups = "main")
    public void verifyProductElementTest() throws Exception {

        //Given user navigates to the web page https://ws-test.keepit.com/users/zhc4v6-5ev7di-9hhhlm

        //when user inserts Login: automation@keepitqa.com and Password: E#*b2wGIbFHz and log in
        homePage.setPage();

        //Then On the home page user makes sure that product element in the response contains "80ltks-yhfls5-24zyf2".
        Assert.assertTrue(homePage.isProductCorrect(), "The product element in the response are not correct");
    }

    @Test(groups = "main")
    public void verifyParentElementTest() throws Exception {

        //Given user navigates to the web page https://ws-test.keepit.com/users/zhc4v6-5ev7di-9hhhlm

        //when user inserts Login: automation@keepitqa.com and Password: E#*b2wGIbFHz and log in
        homePage.setPage();

        //Then On the home page user makes sure that parent element in the response contains "80ltks-yhfls5-24zyf2".
        Assert.assertTrue(homePage.isProductCorrect(), "The parent element in the response are not correct");
    }
}
