import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

    public class Registr
{

    private static WebDriver driver;

    @BeforeMethod
        public void openbrowser()
    {
        // pre condition for all the test cases : need to open chrome browser
        System.setProperty("webdriver.chrome.driver", "src\\test\\java\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.NANOSECONDS);
        driver.manage().window().fullscreen();
        driver.get(" https://demo.nopcommerce.com/");//pre condition for all the test cases : Type URL demo.nopcommerce.com
    }

    //@AfterMethod
       // public void closebrowser()
   // {
     //   driver.quit();
   // }

    @Test(priority = 1)

        public void UserShouldBeRegister()
    {

        driver.findElement(By.linkText("Register")).click();//click on register page
        driver.findElement(By.id("gender-male")).click();//select gender
        driver.findElement(By.id("FirstName")).sendKeys("Tarun");//type firstname
        driver.findElement(By.id("LastName")).sendKeys("Patel");//type last name
        //select date of Birth
        driver.findElement(By.xpath("//div[@class=\"page registration-page\"]/div[2]/form/div[1]/div[2]/div[4]/div/select[1]/option[5]")).click();
        driver.findElement(By.xpath("//div[@class=\"page registration-page\"]/div[2]/form/div[1]/div[2]/div[4]/div/select[2]/option[3]")).click();
        driver.findElement(By.xpath("//div[@class=\"page registration-page\"]/div[2]/form/div[1]/div[2]/div[4]/div/select[3]/option[79]")).click();
        //Date format so no need to change email
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMddHHmm");
        Date date = new Date();
        String date1 = dateFormat1.format(date);
        driver.findElement(By.id("Email")).sendKeys("tkpatel22851789+" + date1 + "@gmail.com");//enter email address
        driver.findElement(By.id("Company")).sendKeys("Shreeji Ltd");
        driver.findElement(By.id("Newsletter")).click();
        driver.findElement(By.id("Password")).sendKeys("Tkpat1");//enter password
        driver.findElement(By.id("ConfirmPassword")).sendKeys("Tkpat1");//reenter password
        driver.findElement(By.id("register-button")).click();//clcik on register button
        String actualRegisterSuccessMessage = driver.findElement(By.xpath("//div[@class='result']")).getText();//expected result"Your registration completed"
        // actual result "Your registration completed"
        Assert.assertEquals("Test case : Registration senario pass", "Your registration completed", actualRegisterSuccessMessage);
        //driver.findElement(By.linkText("Log out")).click();


    }

    @Test(priority = 2)
        public void UserShouldBeSentEmailSuccessfully() {

        driver.findElement(By.linkText("Log in")).click();//click on Login page
        driver.findElement(By.id("Email")).sendKeys("tkpatel2285@gmail.com");//enter register email address
        driver.findElement(By.id("Password")).sendKeys("Tarun123");//enter valid Password
        driver.findElement(By.xpath("//form[@method=\"post\"]/div[3]/input")).click();//click on login button
        //click on any product
        driver.findElement(By.xpath("//div[@class=\"product-grid home-page-product-grid\"]/div[2]/div[2]/div/div[2]/h2/a")).click();
        driver.findElement(By.xpath("//div[@class=\"overview-buttons\"]/div[3]")).click();//click on "Email a friend" Button
        driver.findElement(By.xpath("//input[@id='FriendEmail']")).sendKeys(new CharSequence[]{"tkpatel2285@gmail.com"});//enter friend's email address
        driver.findElement(By.xpath("//form[@method=\"post\"]/div[2]/input")).click();//click on SEND EMAIL button
        String AculatMessgae = driver.findElement(By.xpath("//div[@class=\'result\']")).getText();
        Assert.assertEquals("Email address is not valid", "Your message has been sent.", AculatMessgae);//expected result "Your message has been sent"
        //actual result "Your message has been sent"
        driver.findElement(By.linkText("Log out")).click();//test case pass

    }

    @Test(priority = 3)
    public void UnregisterShouldNotbeAbleToSendEmail() {
        // clcik on any product
        driver.findElement(By.xpath("//div[@class=\"product-grid home-page-product-grid\"]/div[2]/div[2]/div/div[2]/h2/a")).click();
        driver.findElement(By.xpath("//div[@class=\"overview-buttons\"]/div[3]")).click();////click on "Email a friend" Button
        driver.findElement(By.xpath("//input[@id='FriendEmail']")).sendKeys(new CharSequence[]{"tkpatel2285@gmail.com"});//enter friend's email address
        driver.findElement(By.xpath("//input[@id='YourEmailAddress']")).sendKeys(new CharSequence[]{"tkpaagv@gmail.com"});//enter unregister email address
        driver.findElement(By.xpath("//form[@method=\"post\"]/div[2]/input")).click();//clcik on SEND EMAIL button
        String actualerrormessage = driver.findElement(By.xpath("//div[@class=\"message-error validation-summary-errors\"]")).getText();
        Assert.assertEquals("Test case : Registration senario pass", "Only registered customers can use email a friend feature", actualerrormessage);
        //expected result = "Only registered customers can use email a friend feature"
        //actual result = "Only registered customers can use email a friend feature"
        //Test case pass

    }

    @Test(priority = 4)
    public void ClickTermsCondition() {
        driver.findElement(By.xpath("//div[@class=\"product-grid home-page-product-grid\"]/div[2]/div[2]/div/div[1]")).click();
        driver.findElement(By.xpath("//input[@id=\"add-to-cart-button-4\"]")).click();
        driver.findElement(By.linkText("Shopping cart")).click();
        //driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        driver.findElement(By.xpath("//div[@class=\"cart-footer\"]/div[2]/div[4]/button")).click();
        String acualterms = driver.findElement(By.xpath("//div[@id=\"terms-of-service-warning-box\"]/p")).getText();
        Assert.assertEquals("Need to Click on terms and conditions", "Please accept the terms of service before the next step.", acualterms);
    }

    @Test(priority = 5)
    public void BuySingleProductSuccessfully() {
        driver.findElement(By.xpath("//div[@class=\"product-grid home-page-product-grid\"]/div[2]/div[2]/div/div[1]")).click();
        driver.findElement(By.xpath("//input[@id=\"add-to-cart-button-4\"]")).click();
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        driver.findElement(By.linkText("Shopping cart")).click();
        driver.findElement(By.xpath("//input[@id='termsofservice']")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("Email")).sendKeys("tkpatel2285@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("Tarun123");
        driver.findElement(By.xpath("//div[@class=\"returning-wrapper fieldset\"]/form/div[3]/input")).click();
        driver.findElement(By.xpath("//input[@id='termsofservice']")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.xpath("//div[@id=\"billing-buttons-container\"]/input")).click();
        driver.findElement(By.xpath("//div[@id=\"shipping-method-buttons-container\"]/input")).click();
        driver.findElement(By.id("paymentmethod_1")).click();
        driver.findElement(By.xpath("//div[@id=\"payment-method-buttons-container\"]/input")).click();
        // requirement of visa card exp (20/2011) is wrong so we cant go further as need to clarify with BA
    }

    @Test(priority = 6)
    public void SortThePriceHighToLow() {

        driver.findElement(By.linkText("Electronics")).click();
        driver.findElement(By.linkText("Camera & photo")).click();
        driver.findElement(By.xpath("//select[@aria-label=\"Select product sort order\"]/option[5]")).click();
        String highprice = driver.findElement(By.xpath("//div[@class='item-grid']/div[1]/div/div[2]/div[3]/div[1]/span")).getText();
        String lowprice = driver.findElement(By.xpath("//div[@class=\"item-grid\"]/div[3]/div/div[2]/div[3]/div[1]/span")).getText();
        float hp = Float.parseFloat(((highprice.replace("$", ""))));
        float lp = Float.parseFloat((lowprice.replace("$", "")));
        Assert.assertTrue("Fail",hp>lp);

        }


    }











