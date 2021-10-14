package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SauceDemo 
{
	static ChromeDriver driver;


	By username = By.name("user-name"); // Page object model all required elements are declared
	By passwd = By.name("password");
	By login = By.name("login-button");
	By errmsg = By.xpath("//h3[@data-test='error']");
	By products = By.xpath("//span[text()='Products']");

	By noofproducts = By.xpath("//div[@class='inventory_item_name']");
	By productsort = By.xpath("//select[@class='product_sort_container']");
	By invprice = By.xpath("//div[@class='inventory_item_price']");
	By addtocart = By.xpath("//button[text()='Add to cart']");

	By cart = By.xpath("//a[@class='shopping_cart_link']");
	By remove = By.xpath("//button[text()='Remove']");

	// ***********************************************************************************************
	public void setUp() // to initialize the firefox driver object ,maximize and implicitwait 20 sec
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public void openUrl() {
		driver.get("https://www.saucedemo.com/");
	}
	public void validateTitle()
	{
		if(driver.getTitle().contains("Swag Labs"))
		{
			System.out.println("Saucedmo url is working");
		}
		else
		{
			System.out.println("Saucedemo url Not working");
		}
	}

	// ************************************************************************************************
	public void invalidLogin() // for invalid passwd login verify there is error message displayed or not
	{

		driver.findElement(username).sendKeys("standard_user");
		driver.findElement(passwd).sendKeys("dfkdsfd"); // input invalid pwd
		driver.findElement(login).click();
		try { Thread.sleep(3000); } catch(Exception e){}

	}
	public void validateErrormsg() {
		try {
			if (driver.findElement(errmsg).isDisplayed()) // check the errmessage element is displayed, if errmsg
															// displayed it is pass else failed
			{
				System.out.println("Passed :For invalid login, user not able to login");
			}
		} catch (Exception e) {
			System.out.println("Failed :For invalid login, there is not error message");
		}
	}

	// *************************************************************************************************
	public void validLogin() // For valid login , valid username, valid password
	{
		driver.get("https://www.saucedemo.com/");
		driver.findElement(username).sendKeys("standard_user");
		driver.findElement(passwd).sendKeys("secret_sauce");
		driver.findElement(login).click();
		try { Thread.sleep(3000); } catch(Exception e){}
	}

	public void validateHomepage() {
		try {
			if (driver.findElement(products).isDisplayed()) // check the errmessage element is displayed, if errmsg
															// displayed it is pass else failed
			{
				System.out.println("Passed :After valid Login user is on Products page");
			}
		} catch (Exception e) {
			System.out.println("Failed :After valid login user is NOT on Products page");
		}
	}

	// **************************************************************************************************
	public void validatenoofProducts() // get the number of products and and check the number of products are 6
	{
		int cnt = driver.findElements(noofproducts).size();
		if (cnt == 6) {
			System.out.println("Passed:  The total number of products are 6");
		} else {
			System.out.println("Failed:  The total number of products are NOT 6");
		}
	}

	// ***************************************************************************************************
	public void sortProducts() // sort the products price from Low to High
	{
		new Select(driver.findElement(productsort)).selectByValue("lohi");
		try { Thread.sleep(3000); } catch(Exception e){}
	}

	public void validateProductssorted() {
		List<WebElement> lst = driver.findElements(invprice);
		double arrPrice[] = new double[lst.size()];
		for (int i = 0; i < lst.size(); i++) {
			arrPrice[i] = Double.parseDouble(lst.get(i).getText().substring(1)); // After sort get all products prices
																					// and store in array
		}
		int flag = 0; // initial flag=0
		for (int i = 0; i < arrPrice.length - 1; i++) // check the prices are Low to high else assign flag=1 and exit
														// loop
		{
			if (arrPrice[i] > arrPrice[i + 1]) {
				flag = 1;
				break;
			}
		}
		if (flag == 0) // check the flag is 0 means the prices are from Low to high else Not
		{
			System.out.println("passed: All Prices are in sorting order Low to High");
		} else {
			System.out.println("Failed: Prices are NOT in sorting order Low to High");
		}
	}

	// **************************************************************************************************
	public void addTocart() // add products to cart and check how many, remove 1 product and check how many
							// are now in cart
	{
		List<WebElement> lst = driver.findElements(addtocart);
		for (int i = 0; i < 3; i++) // add 3 products to cart
		{
			lst.get(i).click();
		}
		try { Thread.sleep(3000); } catch(Exception e){}
	}

	public void validateaddedcart() {
		driver.findElement(cart).click();
		int cnt = driver.findElements(remove).size(); // get how many products are in cart and check there are 3
														// products in cart
		if (cnt == 3) {
			System.out.println("Passed: number of items in cart match number of items added");
		} else {
			System.out.println("Failed: number of items in cart match NOT number of items added");
		}
	}

	public void removeFromCart() {
		driver.findElements(remove).get(2).click(); // remove 1 product from the cart and check now there are 2 products
													// in cart
		try { Thread.sleep(3000); } catch(Exception e){}
	}

	public void validateRemovedCart() {
		int cnt = driver.findElements(remove).size();
		if (cnt == 2) {
			System.out.println("Passed: After remove 1 item count is matching -1");
		} else {
			System.out.println("Failed: After remove 1 item count NOT matching");
		}

	}

	public void tearDown() {
		driver.quit(); // close the browser
	}

}
