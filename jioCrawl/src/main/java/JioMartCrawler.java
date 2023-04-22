import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JioMartCrawler {
    public static void main(String[] args) throws InterruptedException, IOException {

        String[] urlList = {
                "https://www.jiomart.com/p/groceries/maggi-2-minute-masala-instant-noodles-560-g/490003834"
        };
            String [] priceList = new String[urlList.length];

        String fileName = "example.txt";
        String content = "Hello, world!";
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String profilePath = "/Users/kaushikm/Library/Application Support/Firefox/Profiles/zuzmlenh.default";
        ProfilesIni profiles = new ProfilesIni();
        FirefoxProfile profile = profiles.getProfile("/Users/kaushikm/Library/Application Support/Firefox/Profiles/zuzmlenh.default");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(profile)
               // .setProfile(new FirefoxProfile(new File(profilePath)))
                .addArguments("--headless")
                .addPreference("network.proxy.type", 0)
                .addPreference("browser.tabs.remote.autostart", false);
        System.setProperty("webdriver.gecko.driver", "/Users/kaushikm/projects/code/jioCrawl/browserdriver_mac_arm64/geckodriver");
        WebDriver driver = new FirefoxDriver(firefoxOptions);
        try {
            for (int i = 0; i < urlList.length; i++) {
                String url = urlList[i];
                System.out.println("url=" + url);

                priceList[i] = crawlJioMart(url, firefoxOptions, driver);
                bufferedWriter.write(priceList[i] + "\n");


            }
        }
        catch (IOException ie)
        {

        }
        finally {
            // Close files
            try {

                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        driver.quit();
    for (int i=0; i<priceList.length;i++){
    System.out.println(priceList[i]);
    }

    }

    private static String crawlJioMart(String url, FirefoxOptions firefoxOptions, WebDriver driver) throws InterruptedException {

       // ChromeOptions chromeOptions = new ChromeOptions();
        //  chromeOptions.addArguments("--disable-gpu", "--no-sandbox", "--headless");
        //chromeOptions.addArguments("--remote-allow-origins=*", "--headless");
        //List<String> arguments = new ArrayList<>();
        //  arguments.add("--whitelisted-ips=0.0.0.0/0");
        // arguments.add("--headless");
        //chromeOptions.addArguments(arguments);
        //System.setProperty("webdriver.chrome.driver", "/Users/kaushikm/projects/code/jioCrawl/browserdriver_mac_arm64/chromedriver");
        //WebDriver driver = new ChromeDriver(chromeOptions);

        // Configure Chrome to use the proxy server

        System.out.println("entered");
        String price = null;
        driver.navigate().to(url);
        System.out.println("got url");
        TimeUnit.SECONDS.sleep(5);
        //WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div [class=cart-list-total-price]")));
        System.out.println("before url");
        try {
            WebElement priceElement = driver.findElement(By.cssSelector("span.jm-heading-xs"));
             price = priceElement.getText();
           // System.out.println("Price of the product: " + price);

        } catch (Exception e) {
         System.out.println("Not found"+e.getMessage());

        }
        return price;
    }
}

