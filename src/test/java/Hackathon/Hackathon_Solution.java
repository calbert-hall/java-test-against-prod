package Hackathon;

import com.applitools.eyes.*;
import com.applitools.eyes.selenium.*;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.rmi.UnexpectedException;


public class Hackathon_Solution {

	public final String prod_url= "https://demo.applitools.com/gridHackathonV1.html";
	public final String qa_url= "https://demo.applitools.com/gridHackathonV2.html";

	public final boolean ultrafast_Test_Cloud = false;
	/**
	 * Useful Selectors for navigating in the exercise.
	 */

	@Test
        public void test() {
		// Use Chrome browser
		WebDriver driver = new ChromeDriver();

		EyesRunner runner;
		if (ultrafast_Test_Cloud) {
			runner = new VisualGridRunner(5);
		}
		else {
			runner = new ClassicRunner();
		}

		// Initialize the eyes SDK
		Eyes eyes = new CompareEyes(runner);

		setUp(eyes);
		
		try {
			eyes.setSaveDiffs(true);
			System.out.println("Establishing prod baseline");
			TestApp(driver, eyes, prod_url, true);


			eyes.setSaveDiffs(false);
			System.out.println("Comparing to QA");
			TestApp(driver, eyes, qa_url, false);

		} finally {

			tearDown(driver, runner);
		}
	}

	private void setUp(Eyes eyes) {

		// Initialize the eyes configuration.
		Configuration config = new Configuration();
		
		// You can get your api key from the Applitools dashboard
		config.setApiKey(System.getenv("APPLITOOLS_API_KEY"));

		if (ultrafast_Test_Cloud) {
			config.addBrowser(1200, 800, BrowserType.CHROME);
			config.addDeviceEmulation(DeviceName.Pixel_2, ScreenOrientation.PORTRAIT);
		}

		// set new batch
		config.setBatch(new BatchInfo("Autozone batch"));

		// set the configuration to eyes
		eyes.setConfiguration(config);
	}

	private void TestApp(WebDriver driver, Eyes eyes, String url, boolean establishBaseline) {

		System.out.println("Eyes Open");
		eyes.open(driver, "Autozone", "Smoke Test", new RectangleSize(1200, 800));

		// Navigate the browser to the demo app.
		driver.get(url);

		System.out.println("Eyes Check");
		eyes.check(Target.window().fully().withName("Main Page - " + url));

		TestResults testResults = eyes.close(false);
		if (establishBaseline) {
			testResults.delete(); //Not working, unsure why.
			System.out.println("Comparison test deleted");
		}
	}
	
	private void tearDown(WebDriver driver, EyesRunner runner) {
		driver.quit();

		// Wait and collect all test results
		// we pass false to this method to suppress the exception that is thrown if we
		// find visual differences
		TestResultsSummary allTestResults = runner.getAllTestResults(false);

		// Print results
		System.out.println(allTestResults);
	}

}
