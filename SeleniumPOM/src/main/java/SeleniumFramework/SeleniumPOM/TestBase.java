package SeleniumFramework.SeleniumPOM;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class TestBase {

	public WebDriver driver;

	public TestBase() {

	}

	public Properties prop;

	public WebDriver initialization() throws IOException {
		String currentDirectory = System.getProperty("user.dir");

		prop = new Properties();

		FileInputStream fis = new FileInputStream(
				currentDirectory + "/src\\main\\java\\SeleniumFramework\\SeleniumPOM\\resources\\data.properties");

		prop.load(fis);

		String browser = prop.getProperty("browser");
		System.out.println(browser);

		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver",
					currentDirectory + "/src\\main\\java\\SeleniumFramework\\SeleniumPOM\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver", currentDirectory
					+ "/src\\main\\java\\SeleniumFramework\\SeleniumPOM\\resources\\resources/geckodriver.exe");
			driver = new FirefoxDriver();
			break;

		case "ie":
			System.setProperty("webdriver.ie.driver", currentDirectory
					+ "/src\\main\\java\\SeleniumFramework\\SeleniumPOM\\resources\\resources/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;

		default:
			System.setProperty("webdriver.chrome.driver", currentDirectory
					+ "/src\\main\\java\\SeleniumFramework\\SeleniumPOM\\resources\\resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.manage().window().maximize();

		return driver;
	}

	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\ListenersReports\\" + testCaseName + ".png";
		FileUtils.copyFile(file, new File(destinationFile));
		return destinationFile;
	}

}
