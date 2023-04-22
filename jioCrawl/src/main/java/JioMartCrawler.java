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
                "https://www.jiomart.com/p/groceries/aachi-coriander-powder-500-g/590869289","https://www.jiomart.com/p/groceries/mtr-coriander-powder-500-gm/491439154","https://www.jiomart.com/p/groceries/sakthi-coriander-powder-100-g/490007424","https://www.jiomart.com/p/groceries/sunfeast-dark-fantasy-choco-creme-biscuits-100-g/490335859","https://www.jiomart.com/p/groceries/mtr-puliogare-powder-200-g/490000129","https://www.jiomart.com/p/groceries/sunfeast-dark-fantasy-choco-creme-biscuits-100-g/490335859","https://www.jiomart.com/p/groceries/parle-platina-hide-seek-chocolate-chips-cookies-200-g/590033307","https://www.jiomart.com/p/groceries/coca-cola-750-ml/491085932","https://www.jiomart.com/p/groceries/lay-s-spanish-tomato-tango-potato-chips-52-g/490003815","https://www.jiomart.com/p/groceries/sprite-2-25-l/490004166","https://www.jiomart.com/p/groceries/india-gate-dubar-basmati-rice-5-kg/490007361","https://www.jiomart.com/p/groceries/india-gate-dubar-basmati-rice-1-kg/490005500","https://www.jiomart.com/p/groceries/india-gate-dubar-basmati-rice-5-kg/490007361","https://www.jiomart.com/p/groceries/maggi-rich-tomato-ketchup-200-g/490000490","https://www.jiomart.com/p/groceries/india-gate-feast-rozzana-basmati-rice-1-kg/490427668","https://www.jiomart.com/p/groceries/aachi-chilli-powder-100-g/490238696","https://www.jiomart.com/p/groceries/india-gate-dubar-basmati-rice-5-kg/490007361","https://www.jiomart.com/p/groceries/aashirvaad-superior-mp-whole-wheat-atta-5-kg/590150648","https://www.jiomart.com/p/groceries/maggi-2-minute-masala-instant-noodles-560-g/490003834","https://www.jiomart.com/p/groceries/sunpure-refined-sunflower-oil-1-l-pouch/490016724","https://www.jiomart.com/p/groceries/freedom-refined-sunflower-oil-1-l-pouch/490768936","https://www.jiomart.com/p/groceries/mtr-turmeric-powder-100-g/490016208","https://www.jiomart.com/p/groceries/fortune-sunlite-refined-sunflower-oil-1-l-pouch/490000052","https://www.jiomart.com/p/groceries/freedom-refined-sunflower-oil-1-l-pouch/490768936","https://www.jiomart.com/p/groceries/britannia-nutrichoice-hi-fibre-digestive-biscuits-100-g/490007701","https://www.jiomart.com/p/groceries/ruchi-gold-refined-palmolein-oil-1-l-pouch/490000056","https://www.jiomart.com/p/groceries/aashirvaad-superior-mp-whole-wheat-atta-5-kg/590150648","https://www.jiomart.com/p/groceries/sakthi-coriander-powder-100-g/490007424","https://www.jiomart.com/p/groceries/mtr-turmeric-powder-100-g/490016208","https://www.jiomart.com/p/groceries/aashirvaad-superior-mp-whole-wheat-atta-5-kg/590150648","https://www.jiomart.com/p/groceries/sakthi-coriander-powder-500-g/491187994","https://www.jiomart.com/p/groceries/mtr-sambar-powder-100-g/490000117","https://www.jiomart.com/p/groceries/sakthi-sambar-powder-500-g/491278683","https://www.jiomart.com/p/groceries/saffola-gold-edible-vegetable-blended-oil-5-l-jar/490005648","https://www.jiomart.com/p/groceries/fortune-sunlite-refined-sunflower-oil-1-l-pouch/490000052","https://www.jiomart.com/p/groceries/lay-s-spanish-tomato-tango-potato-chips-52-g/490003815","https://www.jiomart.com/p/groceries/raag-gold-refined-palm-oil-1-l-pouch/490544363","https://www.jiomart.com/p/groceries/haldiram-s-nagpur-khatta-meetha-150-g/490000278","https://www.jiomart.com/p/groceries/india-gate-dubar-basmati-rice-1-kg/490005500","https://www.jiomart.com/p/groceries/madhur-sugar-1-kg/590032514","https://www.jiomart.com/p/groceries/aachi-coriander-powder-500-g/590869289","https://www.jiomart.com/p/groceries/fortune-kachi-ghani-mustard-oil-1-l-bottle/490000525","https://www.jiomart.com/p/groceries/sunpure-refined-sunflower-oil-1-l-pouch/490016724","https://www.jiomart.com/p/groceries/sprite-2-25-l/490004166","https://www.jiomart.com/p/groceries/sunfeast-yippee-magic-masala-instant-noodles-360-g/491337398","https://www.jiomart.com/p/groceries/india-gate-jeera-rice-1-kg/595540414","https://www.jiomart.com/p/groceries/bindu-fizz-jeera-masala-soda-600-ml/491299119","https://www.jiomart.com/p/groceries/mirinda-orange-2-25-l/490004177","https://www.jiomart.com/p/groceries/mtr-chilli-powder-500-gm/491439152","https://www.jiomart.com/p/groceries/runutz-2-piece-cashew-nuts-splits-250-g/593408809","https://www.jiomart.com/p/groceries/sunfeast-mom-s-magic-cashew-almond-cookies-200-g/491179438","https://www.jiomart.com/p/groceries/mtr-sambar-powder-100-g/490000117","https://www.jiomart.com/p/groceries/nandini-pure-cow-ghee-1-l-pouch/490011327","https://www.jiomart.com/p/groceries/sakthi-chilli-powder-500-g/491187993","https://www.jiomart.com/p/groceries/quaker-oats-1-kg/490309138","https://www.jiomart.com/p/groceries/tata-iodised-salt-1-kg/490000073","https://www.jiomart.com/p/groceries/aashirvaad-pure-iodised-salt-1-kg/490000070","https://www.jiomart.com/p/groceries/sakthi-turmeric-powder-100-g/490007425","https://www.jiomart.com/p/groceries/fortune-kachi-ghani-mustard-oil-1-l-bottle/490000525","https://www.jiomart.com/p/groceries/maggi-rich-tomato-ketchup-200-g/490000490","https://www.jiomart.com/p/groceries/quaker-oats-1-kg/490309138","https://www.jiomart.com/p/groceries/mtr-puliogare-powder-200-g/490000129","https://www.jiomart.com/p/groceries/nandini-pure-cow-ghee-1-l-pouch/490011327","https://www.jiomart.com/p/groceries/mtr-sambar-powder-100-g/490000117","https://www.jiomart.com/p/groceries/parle-monaco-salted-biscuits-200-g/490000415","https://www.jiomart.com/p/groceries/aashirvaad-multigrain-atta-5-kg/490675817","https://www.jiomart.com/p/groceries/aashirvaad-multigrain-atta-5-kg/490675817","https://www.jiomart.com/p/groceries/kellogg-s-corn-flakes-with-real-almond-honey-1-kg/491409953","https://www.jiomart.com/p/groceries/tang-orange-instant-drink-powder-250-g/490002002","https://www.jiomart.com/p/groceries/aashirvaad-superior-mp-whole-wheat-atta-5-kg/590150648","https://www.jiomart.com/p/groceries/sakthi-chilli-powder-500-g/491187993","https://www.jiomart.com/p/groceries/sprite-2-25-l/490004166","https://www.jiomart.com/p/groceries/sakthi-chilli-powder-100-g/490007423","https://www.jiomart.com/p/groceries/freedom-refined-sunflower-oil-1-l-pouch/490768936","https://www.jiomart.com/p/groceries/thums-up-750-ml/491085935","https://www.jiomart.com/p/groceries/gold-winner-refined-sunflower-oil-5-l-jar/490007390","https://www.jiomart.com/p/groceries/saffola-gold-ricebran-based-blended-oil-1-l-pouch/490000057","https://www.jiomart.com/p/groceries/sunfeast-dark-fantasy-choco-creme-biscuits-100-g/490335859","https://www.jiomart.com/p/groceries/bingo-yumitos-chilli-sprinkled-chips-60-g/491229855","https://www.jiomart.com/p/groceries/parle-monaco-salted-biscuits-200-g/490000415","https://www.jiomart.com/p/groceries/madhur-sugar-1-kg/590032514?source=fbads&city=Tanuku&pin=534211","https://www.jiomart.com/p/groceries/mtr-coriander-powder-500-gm/491439154","https://www.jiomart.com/p/groceries/kissan-mixed-fruit-jam-200-g/490000827","https://www.jiomart.com/p/groceries/gold-winner-refined-sunflower-oil-1-l/490007389","https://www.jiomart.com/p/groceries/fortune-sun-lite-refined-sunflower-oil-5-l-jar/490005647","https://www.jiomart.com/p/groceries/sprite-2-25-l/490004166","https://www.jiomart.com/p/groceries/mtr-puliogare-powder-200-g/490000129","https://www.jiomart.com/p/groceries/quaker-oats-1-kg/490309138","https://www.jiomart.com/p/groceries/maggi-rich-tomato-ketchup-200-g/490000490","https://www.jiomart.com/p/groceries/sunfeast-yippee-magic-masala-instant-noodles-360-g/491337398","https://www.jiomart.com/p/groceries/sunfeast-dark-fantasy-choco-creme-biscuits-100-g/490335859","https://www.jiomart.com/p/groceries/sunpure-refined-sunflower-oil-1-l-pouch/490016724","https://www.jiomart.com/p/groceries/sunfeast-mom-s-magic-cashew-almond-cookies-200-g/491179438","https://www.jiomart.com/p/groceries/sunfeast-mom-s-magic-cashew-almond-cookies-200-g/491179438","https://www.jiomart.com/p/groceries/fortune-kachi-ghani-mustard-oil-1-l-bottle/490000525","https://www.jiomart.com/p/groceries/ruchi-gold-refined-palmolein-oil-1-l-pouch/490000056","https://www.jiomart.com/p/groceries/sunpure-refined-sunflower-oil-1-l-pouch/490016724","https://www.jiomart.com/p/groceries/mtr-chilli-powder-500-gm/491439152","https://www.jiomart.com/p/groceries/nandini-pure-cow-ghee-1-l-pouch/490011327","https://www.jiomart.com/p/groceries/maggi-2-minute-masala-instant-noodles-560-g/490003834","https://www.jiomart.com/p/groceries/aachi-turmeric-powder-100-g/490017450","https://www.jiomart.com/p/groceries/fortune-sun-lite-refined-sunflower-oil-5-l-jar/490005647","https://www.jiomart.com/p/groceries/sunfeast-dark-fantasy-vanilla-fill-cream-biscuits-100-g/490335858","https://www.jiomart.com/p/groceries/fortune-sunlite-refined-sunflower-oil-1-l-pouch/490000052","https://www.jiomart.com/p/groceries/aachi-chilli-powder-100-g/490238696","https://www.jiomart.com/p/groceries/kissan-mixed-fruit-jam-200-g/490000827","https://www.jiomart.com/p/groceries/veeba-eggless-mayonnaise-250-g/491335298","https://www.jiomart.com/p/groceries/britannia-good-day-cashew-cookies-200-g/490176188","https://www.jiomart.com/p/groceries/aachi-chilli-powder-500-g/590869290","https://www.jiomart.com/p/groceries/bingo-yumitos-chilli-sprinkled-chips-60-g/491229855","https://www.jiomart.com/p/groceries/quaker-oats-1-kg/490309138","https://www.jiomart.com/p/groceries/sakthi-chilli-powder-100-g/490007423","https://www.jiomart.com/p/groceries/thums-up-2-25-l/490005134","https://www.jiomart.com/p/groceries/maggi-2-minute-masala-instant-noodles-560-g/490003834","https://www.jiomart.com/p/groceries/veeba-eggless-mayonnaise-250-g/491335298","https://www.jiomart.com/p/groceries/kellogg-s-corn-flakes-with-real-almond-honey-1-kg/491409953","https://www.jiomart.com/p/groceries/india-gate-feast-rozzana-basmati-rice-1-kg/490427668","https://www.jiomart.com/p/groceries/ruchi-gold-refined-palmolein-oil-1-l-pouch/490000056","https://www.jiomart.com/p/groceries/india-gate-feast-rozzana-basmati-rice-1-kg/490427668","https://www.jiomart.com/p/groceries/tata-iodised-salt-1-kg/490000073","https://www.jiomart.com/p/groceries/aashirvaad-superior-mp-whole-wheat-atta-5-kg/590150648","https://www.jiomart.com/p/groceries/sakthi-coriander-powder-100-g/490007424","https://www.jiomart.com/p/groceries/fortune-rice-bran-oil-1-l/491019561","https://www.jiomart.com/p/groceries/runutz-2-piece-cashew-nuts-splits-250-g/490000056","https://www.jiomart.com/p/groceries/saffola-tasty-corn-based-blended-oil-5-l-jar/490005535","https://www.jiomart.com/p/groceries/parle-monaco-salted-biscuits-200-g/490000415","https://www.jiomart.com/p/groceries/gold-winner-refined-sunflower-oil-1-l/490007389","https://www.jiomart.com/p/groceries/sakthi-chilli-powder-100-g/490007423","https://www.jiomart.com/p/groceries/bingo-yumitos-chilli-sprinkled-chips-60-g/491229855","https://www.jiomart.com/p/groceries/pillsbury-chakki-fresh-whole-wheat-atta-10-kg/490006668","https://www.jiomart.com/p/groceries/mountain-dew-750-ml/491349790","https://www.jiomart.com/p/groceries/britannia-nutrichoice-hi-fibre-digestive-biscuits-100-g/490007701","https://www.jiomart.com/p/groceries/sunfeast-dark-fantasy-choco-creme-biscuits-100-g/490335859","https://www.jiomart.com/p/groceries/india-gate-jeera-rice-1-kg/595540414","https://www.jiomart.com/p/groceries/bingo-yumitos-chilli-sprinkled-chips-60-g/491229855","https://www.jiomart.com/p/groceries/shakti-sambar-powder-100-g/490007431","https://www.jiomart.com/p/groceries/thums-up-2-25-l/490005134","https://www.jiomart.com/p/groceries/bingo-yumitos-chilli-sprinkled-chips-60-g/491229855","https://www.jiomart.com/p/groceries/sakthi-turmeric-powder-100-g/490007425","https://www.jiomart.com/p/groceries/sakthi-sambar-powder-500-g/491278683","https://www.jiomart.com/p/groceries/maggi-rich-tomato-ketchup-200-g/490000490","https://www.jiomart.com/p/groceries/aachi-chicken-masala-100-g/490238695","https://www.jiomart.com/p/groceries/sakthi-coriander-powder-500-g/491187994?source=shoppingads&city=Salem","https://www.jiomart.com/p/groceries/shakti-sambar-powder-100-g/490007431","https://www.jiomart.com/p/groceries/maggi-rich-tomato-ketchup-200-g/490000490","https://www.jiomart.com/p/groceries/nandini-pure-cow-ghee-1-l-pouch/490011327","https://www.jiomart.com/p/groceries/7up-2-l/490005200","https://www.jiomart.com/p/groceries/aachi-chilli-powder-100-g/490238696","https://www.jiomart.com/p/groceries/britannia-nutrichoice-hi-fibre-digestive-biscuits-100-g/490007701","https://www.jiomart.com/p/groceries/mtr-coriander-powder-500-gm/491439154","https://www.jiomart.com/p/groceries/sakthi-coriander-powder-500-g/491187994?source=shoppingads&city=Salem","https://www.jiomart.com/p/groceries/gold-winner-refined-sunflower-oil-1-l/490007389","https://www.jiomart.com/p/groceries/aashirvaad-superior-mp-whole-wheat-atta-5-kg/590150648","https://www.jiomart.com/p/groceries/lay-s-spanish-tomato-tango-potato-chips-52-g/490003815","https://www.jiomart.com/p/groceries/sakthi-chilli-powder-100-g/490007423","https://www.jiomart.com/p/groceries/sunfeast-dark-fantasy-choco-creme-biscuits-100-g/490335859","https://www.jiomart.com/p/groceries/pepsi-750-ml/491208775","https://www.jiomart.com/p/groceries/india-gate-jeera-rice-1-kg/595540414","https://www.jiomart.com/p/groceries/aachi-sambar-powder-500g/596388613","https://www.jiomart.com/p/groceries/aachi-chilli-powder-500-g/590869290","https://www.jiomart.com/p/groceries/aachi-coriander-powder-500-g/590869289","https://www.jiomart.com/p/groceries/7up-2-l/490005200","https://www.jiomart.com/p/groceries/mtr-sambar-powder-100-g/490000117","https://www.jiomart.com/p/groceries/sakthi-chicken-masala-100-g/490007422","https://www.jiomart.com/p/groceries/aachi-sambar-powder-100-g/490307860","https://www.jiomart.com/p/groceries/sprite-750-ml/491085934","https://www.jiomart.com/p/groceries/bingo-yumitos-chilli-sprinkled-chips-60-g/491229855","https://www.jiomart.com/p/groceries/gemini-refined-sunflower-oil-1-l-pouch/490012719","https://www.jiomart.com/p/groceries/aachi-coriander-powder-500-g/590869289","https://www.jiomart.com/p/groceries/kissan-mixed-fruit-jam-200-g/490000827","https://www.jiomart.com/p/groceries/shakti-sambar-powder-100-g/490007431","https://www.jiomart.com/p/groceries/maggi-rich-tomato-ketchup-200-g/490000490","https://www.jiomart.com/p/groceries/maggi-2-minute-masala-instant-noodles-560-g/490003834","https://www.jiomart.com/p/groceries/sakthi-coriander-powder-100-g/490007424","https://www.jiomart.com/p/groceries/pillsbury-chakki-fresh-atta-flour-5-kg/490000040","https://www.jiomart.com/p/groceries/aashirvaad-superior-mp-whole-wheat-atta-5-kg/590150648","https://www.jiomart.com/p/groceries/mirinda-orange-2-25-l/490004177","https://www.jiomart.com/p/groceries/britannia-good-day-cashew-cookies-200-g/490176188","https://www.jiomart.com/p/groceries/kellogg-s-moons-stars-chocos-375-g/490000301","https://www.jiomart.com/p/groceries/india-gate-dubar-basmati-rice-5-kg/490007361","https://www.jiomart.com/p/groceries/fortune-sunlite-refined-sunflower-oil-1-l-pouch/490000052","https://www.jiomart.com/p/groceries/bingo-yumitos-chilli-sprinkled-chips-60-g/491229855","https://www.jiomart.com/p/groceries/mountain-dew-750-ml/491349790","https://www.jiomart.com/p/groceries/sunfeast-yippee-magic-masala-instant-noodles-360-g/491337398","https://www.jiomart.com/p/groceries/sunpure-refined-sunflower-oil-1-l-pouch/490016724","https://www.jiomart.com/p/groceries/sakthi-chilli-powder-100-g/490007423","https://www.jiomart.com/p/groceries/aashirvaad-superior-mp-whole-wheat-atta-5-kg/590150648","https://www.jiomart.com/p/groceries/nandini-pure-cow-ghee-1-l-pouch/490011327","https://www.jiomart.com/p/groceries/daawat-biryani-basmati-rice-1-kg/490863667","https://www.jiomart.com/p/groceries/india-gate-dubar-basmati-rice-5-kg/490007361","https://www.jiomart.com/p/groceries/thums-up-2-25-l/490005134","https://www.jiomart.com/p/groceries/aachi-turmeric-powder-100-g/490017450","https://www.jiomart.com/p/groceries/mtr-chilli-powder-200-gm/491439151","https://www.jiomart.com/p/groceries/aachi-chilli-powder-500-g/590869290","https://www.jiomart.com/p/groceries/madhur-pure-hygienic-sugar-5-kg/490861956","https://www.jiomart.com/p/groceries/maggi-2-minute-masala-instant-noodles-560-g/490003834","https://www.jiomart.com/p/groceries/aachi-turmeric-powder-500-g/590849046","https://www.jiomart.com/p/groceries/sakthi-coriander-powder-500-g/491187994","https://www.jiomart.com/p/groceries/thums-up-750-ml/491085935","https://www.jiomart.com/p/groceries/aashirvaad-pure-iodised-salt-1-kg/490000070","https://www.jiomart.com/p/groceries/britannia-good-day-cashew-cookies-200-g/490176188","https://www.jiomart.com/p/groceries/mtr-chilli-powder-500-gm/491439152","https://www.jiomart.com/p/groceries/fortune-sunlite-refined-sunflower-oil-1-l-pouch/490000052","https://www.jiomart.com/p/groceries/lay-s-spanish-tomato-tango-potato-chips-52-g/490003815","https://www.jiomart.com/p/groceries/veeba-eggless-mayonnaise-250-g/491335298","https://www.jiomart.com/p/groceries/freedom-refined-sunflower-oil-1-l-pouch/490768936","https://www.jiomart.com/p/groceries/sakthi-coriander-powder-500-g/491187994","https://www.jiomart.com/p/groceries/fanta-orange-750-ml/491085933","https://www.jiomart.com/p/groceries/india-gate-dubar-basmati-rice-5-kg/490007361","https://www.jiomart.com/p/groceries/coca-cola-2-25-l/490004164","https://www.jiomart.com/p/groceries/fortune-sun-lite-refined-sunflower-oil-5-l-jar/490005647","https://www.jiomart.com/p/groceries/nandini-pure-cow-ghee-1-l-pouch/490011327","https://www.jiomart.com/p/groceries/maggi-2-minute-masala-instant-noodles-560-g/490003834","https://www.jiomart.com/p/groceries/lay-s-spanish-tomato-tango-potato-chips-52-g/490003815","https://www.jiomart.com/p/groceries/veeba-eggless-mayonnaise-250-g/491335298","https://www.jiomart.com/p/groceries/sunfeast-dark-fantasy-vanilla-fill-cream-biscuits-100-g/490335858"};
        String [] priceList = new String[urlList.length];

        String fileName = "example.txt";
        String content = "Hello, world!";
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        ProfilesIni profiles = new ProfilesIni();
        FirefoxProfile profile = profiles.getProfile("default");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(profile)
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

