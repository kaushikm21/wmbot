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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JioMartCrawler {
    public static void main(String[] args) throws InterruptedException, IOException {

        String[] urlList = {
                "https://www.jiomart.com/p/groceries/knorr-tomato-chatpata-instant-cup-a-soup-16-g/490921888","https://www.jiomart.com/p/groceries/aashirvaad-superior-mp-whole-wheat-atta-5-kg/590150648","https://www.jiomart.com/p/groceries/bingo-achaari-masti-mad-angles-40-g/590033234","https://www.jiomart.com/p/groceries/aashirvaad-superior-mp-whole-wheat-atta-5-kg/590150648","https://www.jiomart.com/p/groceries/bru-instant-coffee-powder-200-g/590109988","https://www.jiomart.com/p/groceries/maggi-2-minute-masala-instant-noodles-560-g/490003834","https://www.jiomart.com/p/groceries/fortune-rice-bran-oil-1-l/491019561","https://www.jiomart.com/p/groceries/mtr-puliogare-powder-200-g/490000129","https://www.jiomart.com/p/groceries/aachi-sambar-powder-500g/596388613","https://www.jiomart.com/p/groceries/aachi-chicken-masala-100-g/490238695","https://www.jiomart.com/p/groceries/bournvita-1-kg/590514178","https://www.jiomart.com/p/groceries/horlicks-classic-malt-200-g/490001307","https://www.jiomart.com/p/groceries/sprite-300-ml-can/490809343","https://www.jiomart.com/p/groceries/maaza-mango-drink-600-ml/490001797","https://www.jiomart.com/p/groceries/india-gate-dubar-basmati-rice-1-kg/490005500","https://www.jiomart.com/p/groceries/saffola-creamy-peanut-butter-350-g/591222845","https://www.jiomart.com/p/groceries/britannia-classic-little-hearts-biscuits-34-5-g/490000226?source=fbads&city=Bhopal&pin=462002","https://www.jiomart.com/p/groceries/pepsi-750-ml/491208775","https://www.jiomart.com/p/groceries/aashirvaad-superior-mp-whole-wheat-atta-10-kg/590150649","https://www.jiomart.com/p/groceries/boost-500-g-pouch/591007244","https://www.jiomart.com/p/groceries/kellogg-s-moons-stars-chocos-375-g/490000301","https://www.jiomart.com/p/groceries/tata-agni-tea-500-g/490005069?source=shoppingads&city=Sagar&pin=470001","https://www.jiomart.com/p/groceries/orion-real-mango-choco-pie-28-g-pack-of-12/591768206","https://www.jiomart.com/p/groceries/daawat-biryani-basmati-rice-1-kg/490863667","https://www.jiomart.com/p/groceries/coca-cola-300-ml-can/490809341","https://www.jiomart.com/p/groceries/aachi-coriander-powder-500-g/590869289","https://www.jiomart.com/p/groceries/mtr-coriander-powder-500-gm/491439154","https://www.jiomart.com/p/groceries/mtr-roasted-vermicelli-165-g-pouch/490009179","https://www.jiomart.com/p/groceries/sakthi-coriander-powder-100-g/490007424","https://www.jiomart.com/p/groceries/sunrise-instant-coffee-powder-100-g/590140331","https://www.jiomart.com/p/groceries/tata-gold-tea-250-g/490001340","https://www.jiomart.com/p/groceries/sunfeast-dark-fantasy-choco-creme-biscuits-100-g/490335859","https://www.jiomart.com/p/groceries/parle-platina-hide-seek-chocolate-chip-cookies-33-g/590109959","https://www.jiomart.com/p/groceries/kurkure-yummy-cheese-puffcorn-28-g/590033334","https://www.jiomart.com/p/groceries/kissan-mixed-fruit-jam-500-g/490001975","https://www.jiomart.com/p/groceries/taj-mahal-leaf-tea-100-g-carton/490008814","https://www.jiomart.com/p/groceries/unibic-sugar-free-oatmeal-cookies-75-g-590008795/490794207","https://www.jiomart.com/p/groceries/boost-health-drink-powder-200-g/490000959","https://www.jiomart.com/p/groceries/mtr-puliogare-powder-200-g/490000129","https://www.jiomart.com/p/groceries/sunfeast-dark-fantasy-choco-creme-biscuits-100-g/490335859","https://www.jiomart.com/p/groceries/parle-platina-hide-seek-chocolate-chips-cookies-200-g/590033307","https://www.jiomart.com/p/groceries/coca-cola-750-ml/491085932","https://www.jiomart.com/p/groceries/lay-s-spanish-tomato-tango-potato-chips-52-g/490003815","https://www.jiomart.com/p/groceries/sprite-2-25-l/490004166","https://www.jiomart.com/p/groceries/india-gate-dubar-basmati-rice-5-kg/490007361?source=fbads&city=Raichur&pin=584102","https://www.jiomart.com/p/groceries/india-gate-dubar-basmati-rice-1-kg/490005500","https://www.jiomart.com/p/groceries/maggi-pichkoo-tomato-ketchup-90-g/490863179","https://www.jiomart.com/p/groceries/bingo-yumitos-chilli-sprinkled-potato-chips-130-g/491551827","https://www.jiomart.com/p/groceries/india-gate-dubar-basmati-rice-5-kg/490007361?source=fbads&city=Raichur&pin=584102","https://www.jiomart.com/p/groceries/maggi-rich-tomato-ketchup-200-g/490000490","https://www.jiomart.com/p/groceries/aashirvaad-superior-mp-whole-wheat-atta-1-kg/590150646","https://www.jiomart.com/p/groceries/india-gate-feast-rozzana-basmati-rice-1-kg/490427668","https://www.jiomart.com/p/groceries/aachi-chilli-powder-100-g/490238696","https://www.jiomart.com/p/groceries/nescafe-sunrise-premium-instant-coffee-200-g/590067099","https://www.jiomart.com/p/groceries/parle-g-biscuits-55-g/491538751","https://www.jiomart.com/p/groceries/india-gate-dubar-basmati-rice-5-kg/490007361?source=fbads&city=Raichur&pin=584102","https://www.jiomart.com/p/groceries/kellogg-s-corn-flakes-1-2-kg/491586426","https://www.jiomart.com/p/groceries/bingo-yumitos-chilli-sprinkled-potato-chips-130-g/491551827","https://www.jiomart.com/p/groceries/aashirvaad-superior-mp-whole-wheat-atta-5-kg/590150648","https://www.jiomart.com/groceries/m/bauli-india-bakes-sweets-pvt-ltd/8558","https://www.jiomart.com/p/groceries/maggi-2-minute-masala-instant-noodles-560-g/490003834","https://www.jiomart.com/p/groceries/3-roses-dust-tea-250-g-carton/490971007","https://www.jiomart.com/p/groceries/dabur-honey-50-g/490012807","https://www.jiomart.com/p/groceries/sunpure-refined-sunflower-oil-1-l-pouch/490016724","https://www.jiomart.com/p/groceries/freedom-refined-sunflower-oil-1-l-pouch/490768936","https://www.jiomart.com/p/groceries/sunpure-refined-sunflower-oil-15-l/590033691","https://www.jiomart.com/p/groceries/mtr-turmeric-powder-100-g/490016208?source=shoppingads&city=Salem","https://www.jiomart.com/p/groceries/fortune-premium-kachi-ghani-mustard-oil-500-ml-bottle/490012709","https://www.jiomart.com/p/groceries/sunfeast-mom-s-magic-cashew-almond-cookies-50-g/491179436","https://www.jiomart.com/p/groceries/sunrise-instant-coffee-powder-100-g/590140331","https://www.jiomart.com/p/groceries/britannia-50-50-maska-chaska-biscuits-50-g/490006829","https://www.jiomart.com/p/groceries/fortune-sunlite-refined-sunflower-oil-1-l-pouch/490000052","https://www.jiomart.com/p/groceries/freedom-refined-sunflower-oil-1-l-pouch/490768936","https://www.jiomart.com/p/groceries/bingo-achaari-masti-mad-angles-40-g/590033234","https://www.jiomart.com/p/groceries/britannia-nutrichoice-hi-fibre-digestive-biscuits-100-g/490007701","https://www.jiomart.com/p/groceries/horlicks-classic-malt-200-g/490001307","https://www.jiomart.com/p/groceries/nescafe-classic-instant-coffee-100-g-jar/490503478","https://www.jiomart.com/p/groceries/cothas-speciality-blend-filter-coffee-powder-200-g/490018416","https://www.jiomart.com/p/groceries/ruchi-gold-refined-palmolein-oil-1-l-pouch/490000056","https://www.jiomart.com/p/groceries/aashirvaad-superior-mp-whole-wheat-atta-5-kg/590150648","https://www.jiomart.com/p/groceries/sakthi-coriander-powder-100-g/490007424","https://www.jiomart.com/p/groceries/red-label-leaf-tea-250-g-carton/490001268","https://www.jiomart.com/p/groceries/mtr-turmeric-powder-100-g/490016208?source=shoppingads&city=Salem","https://www.jiomart.com/p/groceries/dabur-honey-100-g-30-g-free/490012808","https://www.jiomart.com/p/groceries/taj-mahal-tea-500-g-carton/490001325","https://www.jiomart.com/p/groceries/3-roses-dust-tea-250-g-carton/490971007","https://www.jiomart.com/p/groceries/aashirvaad-superior-mp-whole-wheat-atta-5-kg/590150648","https://www.jiomart.com/p/groceries/parle-g-biscuits-110-g-get-20-g-extra/491539619","https://www.jiomart.com/p/groceries/sakthi-coriander-powder-500-g/491187994?source=shoppingads&city=Salem","https://www.jiomart.com/p/groceries/red-label-leaf-tea-250-g-carton/490001268","https://www.jiomart.com/p/groceries/mtr-sambar-powder-100-g/490000117","https://www.jiomart.com/p/groceries/boost-health-drink-powder-200-g/490000959","https://www.jiomart.com/p/groceries/sakthi-sambar-powder-500-g/491278683","https://www.jiomart.com/p/groceries/sunfeast-yippee-mood-masala-instant-noodles-65-g-pouch/491337396","https://www.jiomart.com/p/groceries/saffola-gold-edible-vegetable-blended-oil-5-l-jar/490005648","https://www.jiomart.com/p/groceries/nescafe-sunrise-premium-instant-coffee-200-g/590067099","https://www.jiomart.com/p/groceries/kurkure-naughty-tomato-45-g/590033331?source=shoppingads&city=Alwar","https://www.jiomart.com/p/groceries/unibic-sugar-free-oatmeal-cookies-75-g-590008795/490794207","https://www.jiomart.com/p/groceries/parle-monaco-biscuits-75-4-g/490087809","https://www.jiomart.com/p/groceries/sunfeast-mom-s-magic-cashew-almond-biscuits-100-g/491179437","https://www.jiomart.com/p/groceries/fortune-sunlite-refined-sunflower-oil-1-l-pouch/490000052","https://www.jiomart.com/p/groceries/lay-s-spanish-tomato-tango-potato-chips-52-g/490003815","https://www.jiomart.com/p/groceries/keventer-disney-delights-strawberry-milkshake-180-ml-tetra-pak/591189472","https://www.jiomart.com/p/groceries/saffola-veggie-twist-instant-masala-oats-38-g/490985426","https://www.jiomart.com/p/groceries/parle-g-biscuits-110-g-get-20-g-extra/491539619","https://www.jiomart.com/p/groceries/raag-gold-refined-palm-oil-1-l-pouch/490544363","https://www.jiomart.com/p/groceries/vicks-mixed-flavours-cough-drops-190-pcs/590120629","https://www.jiomart.com/p/groceries/maggi-nutri-licious-masala-instant-atta-noodles-72-5-g/490007870","https://www.jiomart.com/p/groceries/haldiram-s-nagpur-khatta-meetha-150-g/490000278","https://www.jiomart.com/p/groceries/parle-g-biscuits-110-g-get-20-g-extra/491539619","https://www.jiomart.com/p/groceries/india-gate-dubar-basmati-rice-1-kg/490005500","https://www.jiomart.com/p/groceries/madhur-sugar-1-kg/590032514?source=fbads&city=Tanuku&pin=534211","https://www.jiomart.com/p/groceries/3-roses-dust-tea-250-g-carton/490971007","https://www.jiomart.com/p/groceries/sunfeast-mom-s-magic-cashew-almond-cookies-50-g/491179436","https://www.jiomart.com/p/groceries/snickers-chocolate-bar-28-g/590033975","https://www.jiomart.com/p/groceries/aachi-coriander-powder-500-g/590869289","https://www.jiomart.com/p/groceries/sunfeast-yippee-mood-masala-instant-noodles-65-g-pouch/491337396","https://www.jiomart.com/p/groceries/kissan-mixed-fruit-jam-100-g/490000798","https://www.jiomart.com/p/groceries/lay-s-american-style-cream-onion-potato-chips-26-g/590120561","https://www.jiomart.com/p/groceries/orion-real-mango-choco-pie-28-g-pack-of-12/591768206","https://www.jiomart.com/p/groceries/fortune-kachi-ghani-mustard-oil-1-l-bottle/490000525","https://www.jiomart.com/p/groceries/sunpure-refined-sunflower-oil-1-l-pouch/490016724","https://www.jiomart.com/p/groceries/boost-health-drink-powder-200-g/490000959","https://www.jiomart.com/p/groceries/boost-500-g-pouch/591007244","https://www.jiomart.com/p/groceries/sprite-2-25-l/490004166","https://www.jiomart.com/p/groceries/cadbury-oreo-original-vanilla-cream-biscuits-50-g/491070871","https://www.jiomart.com/p/groceries/sunfeast-yippee-magic-masala-instant-noodles-360-g/491337398","https://www.jiomart.com/p/groceries/dabur-honey-250-g-get-50-g-extra/490012809","https://www.jiomart.com/p/groceries/india-gate-jeera-rice-1-kg/595540414","https://www.jiomart.com/p/groceries/haldiram-s-nagpur-aloo-bhujia-1-kg/490009549","https://www.jiomart.com/p/groceries/bindu-fizz-jeera-masala-soda-600-ml/491299119","https://www.jiomart.com/p/groceries/saffola-veggie-twist-instant-masala-oats-500-g/491420618","https://www.jiomart.com/p/groceries/quaker-oats-1-5-kg/490850761","https://www.jiomart.com/p/groceries/britannia-tiger-krunch-choco-chips-cookies-64-g/490785991?source=shoppingads&city=Bhubaneshwar&pin=751002","https://www.jiomart.com/p/groceries/mirinda-orange-2-25-l/490004177","https://www.jiomart.com/p/groceries/kellogg-s-corn-flakes-250-g/490000303","https://www.jiomart.com/p/groceries/mtr-chilli-powder-500-gm/491439152","https://www.jiomart.com/p/groceries/parle-monaco-classic-regular-salted-biscuits-400-g/491439006","https://www.jiomart.com/p/groceries/centerfresh-spearmint-chewing-gum-210-pcs/590114721","https://www.jiomart.com/p/groceries/kellogg-s-corn-flakes-with-real-almond-honey-300-g/490000796","https://www.jiomart.com/p/groceries/runutz-2-piece-cashew-nuts-splits-250-g/593408809","https://www.jiomart.com/p/groceries/sunfeast-mom-s-magic-cashew-almond-cookies-200-g/491179438","https://www.jiomart.com/p/groceries/britannia-classic-little-hearts-biscuits-34-5-g/490000226?source=fbads&city=Bhopal&pin=462002","https://www.jiomart.com/p/groceries/mtr-sambar-powder-100-g/490000117","https://www.jiomart.com/p/groceries/nandini-pure-cow-ghee-1-l-pouch/490011327","https://www.jiomart.com/p/groceries/sakthi-chilli-powder-500-g/491187993","https://www.jiomart.com/p/groceries/quaker-oats-1-kg/490309138","https://www.jiomart.com/p/groceries/tata-iodised-salt-1-kg/490000073","https://www.jiomart.com/p/groceries/aashirvaad-pure-iodised-salt-1-kg/490000070","https://www.jiomart.com/p/groceries/sakthi-turmeric-powder-100-g/490007425"};
            String [] priceList = new String[urlList.length];

        String fileName = "example.txt";
        String content = "Hello, world!";
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);


        for (int i = 0; i < urlList.length; i++) {
            String url = urlList[i];
            System.out.println("url=" + url);
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*", "--headless");
            System.setProperty("webdriver.chrome.driver", "/Users/kaushikm/projects/code/jioCrawl/browserdriver_mac_arm64/chromedriver");
            WebDriver driver = new ChromeDriver(chromeOptions);
            priceList[i]= crawlJioMart(url,chromeOptions,driver);
            bufferedWriter.write(priceList[i]+"\n");


        }
        bufferedWriter.close();
    for (int i=0; i<priceList.length;i++){
    System.out.println(priceList[i]);
    }

    }

    private static String crawlJioMart(String url, ChromeOptions chromeOptions, WebDriver driver) throws InterruptedException {

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

       // System.out.println("before url");
        String price = null;
        driver.get(url);
        TimeUnit.SECONDS.sleep(5);
        //WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div [class=cart-list-total-price]")));

        try {
            WebElement priceElement = driver.findElement(By.cssSelector("span.jm-heading-xs"));
             price = priceElement.getText();
           // System.out.println("Price of the product: " + price);
            driver.quit();
        } catch (Exception e) {
           // System.out.println("Not found");
        }
        return price;
    }
}

