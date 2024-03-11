package factory;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseClass {

		 static WebDriver driver;
	     static Properties properties;
	     private static final Logger log = LogManager.getLogger(BaseClass.class);
	  	     
	public static WebDriver initilizeBrowser() throws IOException
	{
		if(getProperties().getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			if (getProperties().getProperty("os").equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
				log.info("Setting the desired capabilities Platform as Windows");
			} else if (getProperties().getProperty("os").equalsIgnoreCase("mac")) {
			    capabilities.setPlatform(Platform.MAC);
				log.info("Setting the desired capabilities Platform as MAC");
			} else {
			    System.out.println("No matching OS..");
			      }
			//browser
			switch (getProperties().getProperty("browser").toLowerCase()) {
				case "chrome" -> {capabilities.setBrowserName("chrome");log.info("Setting Browser Properties from the .property file as Chrome");}
				case "edge" -> {capabilities.setBrowserName("MicrosoftEdge");log.info("Setting Browser Properties from the .property file as Edge");}
				default -> System.out.println("No matching browser");
			}
	       
	        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
			
		}
		else if(getProperties().getProperty("execution_env").equalsIgnoreCase("local"))
			{

				try {


				switch (getProperties().getProperty("browser").toLowerCase()) {
					case "chrome" -> {
						driver = new ChromeDriver();
					log.info("Initializing the Chrome Driver");
					}
					case "edge" -> {
						driver = new EdgeDriver();
						log.info("Initializing the Edge Driver");
					}
					default -> {
						System.out.println("No matching browser");
						driver = null;
					}
				}
				}catch (Exception e){
					System.out.println(e.getMessage());
					log.error("Exception occurred",new Exception("Failure in initializing the driver"));
				}
			}
		assert driver != null;
		 driver.manage().deleteAllCookies();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		log.info("Implicit wait Set Complete");
		 driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		log.info("PageLoad wait Set Complete");
		 return driver;
		 
	}
	
	public static WebDriver getDriver() {
			return driver;
		}

	public static Properties getProperties() throws IOException
	{		 
        FileReader file=new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");

		properties=new Properties();
		properties.load(file);
		return properties;
	}

	
	public static String randomeString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	
	public static String randomeNumber()
	{
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	
		
	public static String randomAlphaNumeric()
	{
	String str=RandomStringUtils.randomAlphabetic(5);
	 String num=RandomStringUtils.randomNumeric(10);
	return str+num;
	}
	
	
}
