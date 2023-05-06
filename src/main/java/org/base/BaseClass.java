package org.base;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;

//	public static WebDriver launchBrowser(String browsername) {
//		switch (browsername) {
//		case "Chrome":
//			WebDriverManager.chromedriver().version("106").setup();
//			driver = new ChromeDriver();
//			break;
//		case "Firefox":
//			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver();
//			break;
//		case "Edge":
//			System.setProperty("webdriver.edge.driver", "D:\\eclipse-workspace\\TestNG08.00PM_Sep_22\\target\\msedgedriver.exe");
//			driver = new EdgeDriver();
//			break;
//
//		default:
//			System.err.println("Invalid BrowSer Name");
//			throw new WebDriverException();
//		}
//
//		return driver;
//	}
	
	
	public WebDriver launchBrowser(String browserName) {

		if (browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			System.err.println("Invalid Browser name");
		}
		return driver;
	}

	public static void launchUrl(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}

	public static void implicitWait(long sec) {
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);

	}

	public static void sendkeys(WebElement e, String value) {
		e.sendKeys(value);
	}

	public static void Click(WebElement e) {
		e.click();
	}

	public static void closeBrowser() {
		driver.quit();

	}

	public static String getCurrentUrl() {
		String url = driver.getCurrentUrl();
		return url;

	}

	public static String getTitle() {
		return driver.getTitle();

	}

	public static String getAttribute(WebElement e) {
		return e.getAttribute("value");

	}

	public static void moveToElement(WebElement target) {
		Actions a = new Actions(driver);
		a.moveToElement(target).perform();
	}

	public static void dragAndDrop(WebElement source, WebElement target) {
		Actions a = new Actions(driver);
		a.dragAndDrop(source, target).perform();
	}

	public static void selectByIndex(WebElement element, int index) {
		Select s = new Select(element);
		s.selectByIndex(index);
	}

	public static WebElement findElement(String locatorName, String locValue) {
		WebElement e = null;
		if (locatorName.equals("id")) {
			e = driver.findElement(By.id(locValue));
		} else if (locatorName.equals("name")) {
			e = driver.findElement(By.name(locValue));
		} else if (locatorName.equals("xpath")) {
			e = driver.findElement(By.xpath(locValue));
		}
		return e;

	}

	public static void takeScreenShot(String imagename) throws IOException {
		TakesScreenshot tk = (TakesScreenshot) driver;
		File src = tk.getScreenshotAs(OutputType.FILE);
		File des = new File(
				System.getProperty("user.dir") + "\\src\\test\\resources\\ScreenShots\\" + imagename + ".png");
		FileUtils.copyFile(src, des);

	}

	public static void jsSendKeys(WebElement e, String data) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','" + data + "')", e);

	}
	
	public static boolean waitForUrl(long sec,String partialUrl) {
		WebDriverWait w= new WebDriverWait(driver, sec);
		return w.until(ExpectedConditions.urlContains(partialUrl));

	}
	
//	public static String getDataFromExcel(String filename,String sheetname,int rowno, int cellno) throws IOException {
//		File loc = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Excel\\"+filename+".xlsx");
//		FileInputStream st = new FileInputStream(loc);
//		// workbook
//		Workbook w = new XSSFWorkbook(st);
//		// cell
//		Cell cell =  w.getSheet(sheetname).getRow(rowno).getCell(cellno);
//		// get cell type
//		int type = cell.getCellType();
////		type 1--->String
////		type 0--->Numbers, Date
//		String value=null;
//		if(type==1) {
//			 value = cell.getStringCellValue();
//		}
//		else {
//			if(DateUtil.isCellDateFormatted(cell)) {
//				 value = new SimpleDateFormat("dd-MMM-yyyy").format(cell.getDateCellValue());
//
//			}
//			else {
//				 value = String.valueOf((long)cell.getNumericCellValue());
//
//			}
//		}
//		return value;
//
//	
//
//	}
	
	//Ō
}
