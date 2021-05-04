import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TestOne {
    private WebDriver driver;
    private String baseUrl;


    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Ловянова О.С\\oplata\\src\\chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://sandbox.cardpay.com/MI/cardpayment2.html?orderXml=PE9SREVSIFdBTExFVF9JRD0nODI5OScgT1JERVJfTlVNQkVSPSc0NTgyMTEnIEFNT1VOVD0nMjkxLjg2JyBDVVJSRU5DWT0nRVVSJyAgRU1BSUw9J2N1c3RvbWVyQGV4YW1wbGUuY29tJz4KPEFERFJFU1MgQ09VTlRSWT0nVVNBJyBTVEFURT0nTlknIFpJUD0nMTAwMDEnIENJVFk9J05ZJyBTVFJFRVQ9JzY3NyBTVFJFRVQnIFBIT05FPSc4NzY5OTA5MCcgVFlQRT0nQklMTElORycvPgo8L09SREVSPg==&sha512=998150a2b27484b776a1628bfe7505a9cb430f276dfa35b14315c1c8f03381a90490f6608f0dcff789273e05926cd782e1bb941418a9673f43c47595aa7b8b0d";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
    }

    @Test
    public void test1_1()  {
        driver.get(baseUrl);
        driver.findElement(By.id("input-card-number")).click();

        driver.findElement(By.id("input-card-number")).sendKeys("1");
        driver.findElement(By.xpath("//*[@id=\"card-number-field\"]/label")).click();
        assertEquals("Card number is not valid", driver.findElement(By.xpath("//div[@id='card-number-field']/div/label")).getText());

            }
    @Test
    public void test1_2()  {
        driver.get(baseUrl);
        driver.findElement(By.id("input-card-holder")).click();
        driver.findElement(By.id("input-card-holder")).sendKeys("3");
        driver.findElement(By.xpath("//*[@id=\"card-holder-field\"]/label")).click();
        assertEquals("Cardholder name is not valid", driver.findElement(By.xpath("//*[@id=\"card-holder-field\"]/div/label")).getText());

            }
    @Test
    public void test1_3()  {
        driver.get(baseUrl);
        driver.findElement(By.id("input-card-holder")).click();
        driver.findElement(By.id("input-card-holder")).sendKeys("@");
        driver.findElement(By.xpath("//*[@id=\"card-holder-field\"]/label")).click();
        assertEquals("Cardholder name is not valid", driver.findElement(By.xpath("//*[@id=\"card-holder-field\"]/div/label")).getText());

    }

    @Test
    public void test1_4()  {
        driver.get(baseUrl);
        driver.findElement(By.id("input-card-holder")).click();
        driver.findElement(By.id("input-card-holder")).sendKeys("ABC");
        driver.findElement(By.xpath("//*[@id=\"card-holder-field\"]/label")).click();
        assertEquals("Allowed from 4 to 50 characters", driver.findElement(By.xpath("//*[@id=\"card-holder-field\"]/div/label")).getText());

    }

    @Test
    public void test1_5()  {
        driver.get(baseUrl);
        driver.findElement(By.id("action-submit")).click();
        driver.findElement(By.xpath("//*[@id=\"card-number-field\"]/label")).click();
        assertEquals("Card number is required", driver.findElement(By.xpath("//*[@id=\"card-number-field\"]/div/label")).getText());
        driver.findElement(By.xpath("//*[@id=\"card-holder-field\"]/label")).click();
        assertEquals("Cardholder name is required", driver.findElement(By.xpath("//*[@id=\"card-holder-field\"]/div/label")).getText());
        driver.findElement(By.xpath("//*[@id=\"card-expires-field\"]/label")).click();
        assertEquals("Expiration Date is required", driver.findElement(By.xpath("//*[@id=\"card-expires-field\"]/div/label")).getText());
        driver.findElement(By.xpath("//*[@id=\"card-cvc-field\"]/label")).click();
        assertEquals("CVV2/CVC2/CAV2 is required", driver.findElement(By.xpath("//*[@id=\"card-cvc-field\"]/div/label")).getText());
    }

    @Test
    public void test2_1()  {
        driver.get(baseUrl);
        String Order_number = driver.findElement(By.xpath("//*[@id=\"order-number\"]")).getText();
        String Total = driver.findElement(By.xpath("//*[@id=\"total-amount\"]")).getText();
        driver.findElement(By.id("input-card-number")).click();
        driver.findElement(By.id("input-card-number")).sendKeys("4000 0000 0000 0002");
        driver.findElement(By.id("input-card-holder")).click();
        driver.findElement(By.id("input-card-holder")).sendKeys("ABC ABC");
        driver.findElement(By.id("card-expires-month")).click();
        new Select(driver.findElement(By.id("card-expires-month"))).selectByVisibleText("05");
        driver.findElement(By.id("card-expires-month")).click();
        driver.findElement(By.id("card-expires-year")).click();
        new Select(driver.findElement(By.id("card-expires-year"))).selectByVisibleText("2021");
        driver.findElement(By.id("card-expires-year")).click();
        driver.findElement(By.id("input-card-cvc")).click();
        driver.findElement(By.id("input-card-cvc")).sendKeys("123");
        driver.findElement(By.xpath("//div[@id='card-cvc-field']/div")).click();
        driver.findElement(By.id("action-submit")).click();
        driver.findElement(By.id("success")).click();
        assertEquals(Order_number, driver.findElement(By.xpath("//*[@id=\"payment-item-ordernumber\"]/div[2]")).getText());
        assertEquals(Total, driver.findElement(By.xpath("//*[@id=\"payment-item-total-amount\"]")).getText());
    }
    @Test
    public void test3_1() {

        driver.get(baseUrl);
        driver.findElement(By.id("input-card-number")).click();
        driver.findElement(By.id("input-card-number")).sendKeys("5555 5555 5555 4444");
        driver.findElement(By.id("input-card-holder")).click();
        driver.findElement(By.id("input-card-holder")).sendKeys("ABC ABC");
        driver.findElement(By.id("card-expires-month")).click();
        new Select(driver.findElement(By.id("card-expires-month"))).selectByVisibleText("05");
        driver.findElement(By.id("card-expires-month")).click();
        driver.findElement(By.id("card-expires-year")).click();
        new Select(driver.findElement(By.id("card-expires-year"))).selectByVisibleText("2021");
        driver.findElement(By.id("card-expires-year")).click();
        driver.findElement(By.id("input-card-cvc")).click();
        driver.findElement(By.id("input-card-cvc")).sendKeys("123");
        driver.findElement(By.id("action-submit")).click();
        driver.findElement(By.id("success")).click();
        driver.findElement(By.xpath("//*[@id=\"payment-item-status\"]/div[2]")).getText();
        assertEquals("Payment status Declined by issuing bank", driver.findElement(By.xpath("//*[@id=\"payment-item-status\"]")).getText());
    }
    @Test
    public void test3_2()  {

        driver.get(baseUrl);
        driver.findElement(By.id("input-card-number")).click();
        driver.findElement(By.id("input-card-number")).sendKeys("4000 0000 0000 0044");
        driver.findElement(By.id("input-card-holder")).click();
        driver.findElement(By.id("input-card-holder")).sendKeys("ABC ABC");
        driver.findElement(By.id("card-expires-month")).click();
        new Select(driver.findElement(By.id("card-expires-month"))).selectByVisibleText("05");
        driver.findElement(By.id("card-expires-month")).click();
        driver.findElement(By.id("card-expires-year")).click();
        new Select(driver.findElement(By.id("card-expires-year"))).selectByVisibleText("2021");
        driver.findElement(By.id("card-expires-year")).click();
        driver.findElement(By.id("input-card-cvc")).click();
        driver.findElement(By.id("input-card-cvc")).sendKeys("123");
        driver.findElement(By.id("action-submit")).click();
        driver.findElement(By.id("success")).click();
        assertEquals("Payment status CONFIRMED", driver.findElement(By.xpath("//*[@id=\"payment-item-status\"]")).getText());

    }

    @Test
    public void test3_3()  {

        driver.get(baseUrl);
        driver.findElement(By.id("input-card-number")).click();
        driver.findElement(By.id("input-card-number")).sendKeys("4000 0000 0000 0002");
        driver.findElement(By.id("input-card-holder")).click();
        driver.findElement(By.id("input-card-holder")).sendKeys("ABC ABC");
        driver.findElement(By.id("card-expires-month")).click();
        new Select(driver.findElement(By.id("card-expires-month"))).selectByVisibleText("05");
        driver.findElement(By.id("card-expires-month")).click();
        driver.findElement(By.id("card-expires-year")).click();
        new Select(driver.findElement(By.id("card-expires-year"))).selectByVisibleText("2021");
        driver.findElement(By.id("card-expires-year")).click();
        driver.findElement(By.id("input-card-cvc")).click();
        driver.findElement(By.id("input-card-cvc")).sendKeys("123");
        driver.findElement(By.id("action-submit")).click();
        driver.findElement(By.id("success")).click();
        assertEquals("Payment status Confirmed", driver.findElement(By.xpath("//*[@id=\"payment-item-status\"]")).getText());

    }



    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}