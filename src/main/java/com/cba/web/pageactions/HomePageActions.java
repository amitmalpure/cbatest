package com.cba.web.pageactions;

import com.cba.utils.WebDriverUtil;
import com.cba.web.pageobjects.HomePageObjects;

public class HomePageActions 
{
	/*
	 * private WebDriver webdriver = null;
	 * 
	 * public HomePageActions(WebDriver webdriver) { this.webdriver = webdriver; }
	 */

	public void clickDissmissButton()
	{
		WebDriverUtil.clickIfExists(HomePageObjects.dismissButton);
	}
	
	public void clickRepaymentsCalculatorLink()
	{
		WebDriverUtil.click(HomePageObjects.repaymentsCalculatorLink);
	}
	
	public void clickHomeLoansLink()
	{
		WebDriverUtil.click(HomePageObjects.homeLoansLink);
	}
}
