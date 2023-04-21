import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
public class NoBrowserCrawl {
    public static void main(String[] args) {
        String url = "https://www.jiomart.com/p/groceries/maggi-2-minute-masala-instant-noodles-560-g/490003834";
        try {
            crawlJioMart(url);
        } catch (IOException e) {
            System.err.println("Error while crawling: " + e.getMessage());
        }
    }
    private static void crawlJioMart(String url) throws IOException {
        Document document = Jsoup.connect(url).get();

        Element priceElement = document.selectFirst("span.jm-heading-xs");
        String price = priceElement.text();
        System.out.println("Price of the product: " + price);

    }
}
