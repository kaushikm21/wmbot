import com.google.common.collect.ImmutableMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Pupeteer {

    public static void main(String[] args) {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "/Users/kaushikm/projects/code/jioCrawl/browserdriver_mac_arm64/chromedriver");

        // Set the options to launch Chrome with
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu", "--no-sandbox", "--headless","--remote-allow-origins=*");

        // Launch Chrome
        WebDriver driver = new ChromeDriver(options);

        try {
            // Navigate to the JioMart product page
            driver.get("https://www.jiomart.com/p/groceries/sunfeast-dark-fantasy-vanilla-fill-cream-biscuits-100-g/490335858");

            // Wait for the page to load
            TimeUnit.SECONDS.sleep(5);

            // Get the HTML content of the page
            String html = driver.getPageSource();

            // Parse the HTML content using JSoup
            Document doc = Jsoup.parse(html);

            // Extract the price information from the page
            Element priceElement = doc.selectFirst(".jm-heading-xs > span");
            if (priceElement != null) {
                String price = priceElement.text();
                System.out.println("Price: " + price);
            } else {
                System.out.println("Price not found.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Quit the driver
            driver.quit();
        }
    }
}
