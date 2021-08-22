package com.cba.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

import com.cba.constants.AppConstants;

public class WebDriverUtil 
{
	private static WebDriver webDriver = null;
	private static WebDriverWait wait = null;
	
	public static void setup(ITestContext context)
	{
		String chromeVersion = context.getCurrentXmlTest().getParameter("chrome.version");

		if(chromeVersion == null)
		{
			chromeVersion = "80";
		}
		
		System.setProperty(AppConstants.CHROME_DRIVER, AppConstants.CHROME_DRIVER_PATH.replace("XX", chromeVersion));  
	}
	
	public static WebDriver launch()
	{
		webDriver = new ChromeDriver();
		webDriver.get(AppConstants.APP_URL);
		webDriver.manage().window().maximize();
		
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(webDriver, 10000);
		
		return webDriver;
	}
	
	public static void cleanUpWebDriver() 
	{
		if (webDriver != null) 
		{
			try
			{
				webDriver.quit();
			}catch (Exception e)
			{
				e.printStackTrace();
			}
			
			webDriver = null;
		}
	}

	public static void clickIfVisible(By element) 
	{
		if(isElementVisible(element))
		{
			webDriver.findElement(element).click();;
		}
	}
	
	public static void clickIfExists(By element) 
	{
		if(isElementPresent(element))
		{
			webDriver.findElement(element).click();
		}
	}
	
	public static void click(By element) 
	{
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
		webDriver.findElement(element).click();
	}

	public static void enterText(By element, String text, boolean isClear, boolean isCntlA) 
	{
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
		if(isClear)
		{
			webDriver.findElement(element).clear();
		}
		
		if(isCntlA)
		{
			webDriver.findElement(element).sendKeys(Keys.chord(Keys.CONTROL, "a"), text);
		}
		else
		{
			webDriver.findElement(element).sendKeys(text);
		}
	}
	
	public static void enterText(By element, String text) 
	{
		wait.until(ExpectedConditions.elementToBeClickable(element));
		webDriver.findElement(element).sendKeys(text);
	}

	public static void selectDropDown(By element, String option)
	{
		Select dropDown = new Select(webDriver.findElement(element));
		dropDown.selectByVisibleText(option);
	}
	
	public static void selectMultiLvlDropDown(By element, String option)
	{
		Select dropDown1 = new Select(webDriver.findElement(element));
		List<WebElement> optionsList = dropDown1.getOptions();
		
		for(WebElement optionElement : optionsList)
		{
			if(option.equals(optionElement.getText()))
			{
				optionElement.click();
				break;
			}
		}
	}

	public static String getLabel(By element) 
	{
		return webDriver.findElement(element).getText();
	}
	
	public static boolean isElementPresent(By element) 
	{
		List<WebElement> elementList = webDriver.findElements(element);

		if(elementList.size() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean isElementVisible(By element)
	{
	    return webDriver.findElement(element).isDisplayed();
	}

	public static void clickALink(By element)
	{
		List<WebElement> elementList = webDriver.findElements(element);

		for(WebElement element1 : elementList)
		{
			if(element1.isDisplayed())
			{
				element1.click();
				break;
			}
		}
	}
}
