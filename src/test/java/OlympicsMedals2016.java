	
	import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	import io.github.bonigarcia.wdm.WebDriverManager;

	public class OlympicsMedals2016 {
		String url= "https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table";
		WebDriver driver;
		List<String>silverCountries=new ArrayList<String>();
		List<String>bronzeCountries=new ArrayList<String>();
		int medalCount = 18;
		List<WebElement> data;
		
		@BeforeClass
		public void setUP() {
			System.out.println("Setting up WebDriver in BeforeClass...");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//driver.manage().window().fullscreen();
			driver.get(url);
		}
		
	    @Test(priority = 1)
	    public void TestCase1() throws InterruptedException {
	        // 1. Go to website
	        driver.get(url);
	        // 2. Verify that by default the Medal table is sorted by rank.
	        data = driver.findElements(
	        By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr"));
	        List<String> orderNum = new ArrayList<String>();
	        for (int i = 0; i < data.size() - 1; i++) {
	        String xpath = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr[" + (i + 1)+ "]/td[1]";
	        String tdData = driver.findElement(By.xpath(xpath)).getText();
	            orderNum.add(tdData);
	        }
	        data = driver.findElements(
	        By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr"));
	        List<String> orderNumDes = new ArrayList<String>();
	        for (int i = 0; i < data.size() - 1; i++) {
	            String xpath = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr[" + (i + 1) + "]/td[1]";
	            String tdData = driver.findElement(By.xpath(xpath)).getText();
	            orderNumDes.add(tdData);
	        }
	        assertEquals(orderNum, orderNumDes);
	        // 3. Click link NOC.
	        driver.findElement(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/thead/tr/th[2]")).click();
	        // 4. Now verify that the table is now sorted by the country names.
	        List<String> actualCountryOrder = new ArrayList<String>();
	        for (int i = 0; i < 10; i++) {
	            String xpath = "//*[@id='mw-content-text']//table[8]//tr[" + (i + 1) + "]/th/a";
	            String tdData = driver.findElement(By.xpath(xpath)).getText();
	            actualCountryOrder.add(tdData);
	        }
	        List<String> expectedCountryOrder = new ArrayList<String>(actualCountryOrder);
	        Collections.sort(expectedCountryOrder);
	        assertEquals(actualCountryOrder, expectedCountryOrder);
	        // 5. Verify that Rank column is not in ascending order anymore.
	        data = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr"));
	        List<String> actualOrderNum = new ArrayList<String>();
	        for (int i = 0; i < data.size() - 1; i++) {
	            String xpath = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr[" + (i + 1)+ "]/td[1]";
	            String tdData = driver.findElement(By.xpath(xpath)).getText();
	            actualOrderNum.add(tdData);
	        }
	        List<String> expectedOrderNum = new ArrayList<String>(actualOrderNum);
	        Collections.sort(expectedOrderNum);
	        assertNotEquals(actualOrderNum, expectedOrderNum);
	    }
	    @Test(priority = 2)
	    public void TestCase2() throws InterruptedException {
	        Thread.sleep(3000);
	        // 1. Go to website
	        driver.get(url);
	        // 2. Write a method that returns the name of the country with the most number
	        // of gold medals.
	        data = driver.findElements(
	        By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr"));
	        String MostGold = mostGold(data);
	        System.out.println(MostGold);
	        assertEquals(MostGold, "United States");
	        // 3. Write a method that returns the name of the country with the most numbe of
	        // silver medals.
	        String MostSilver = mostSilver(data);
	        System.out.println(MostSilver);
	        assertEquals(MostSilver, "United States");
	        // 4. Write a method that returns the name of the country with the most number
	        // of bronze medals.
	        String MostBronze = mostBronz(data);
	        System.out.println(MostBronze);
	        assertEquals(MostBronze, "United States");
	        // 5. Write a method that returns the name of the country with the most number
	        // of medals.
	        String TotalMedals = TotalMedals(data);
	        System.out.println(TotalMedals);
	        assertEquals(TotalMedals, "United States");
	    }
	    @Test(priority = 3)
	    public void TestCase3() {
	        // 1. Go to website
	        driver.get(url);
	        // 2. Write a method that returns a list of countries whose silver medal count i
	        // equal to 18.
	        
	        data = driver.findElements(
	        By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr"));
	        System.out.println(silverCountries(data));
	        // 3. Write TestNG test for your method.
	        List<String> expected=Arrays.asList(" China (CHN)"," France (FRA)");
	        assertEquals(silverCountries, expected);
	        }
	    @Test(priority = 4)
	    public void TestCase4() {
	        // 1. Go to website
	        driver.get(url);
	        // 2. Write a method that takes country name and returns the row and column number. You decide the datatype of the return.
	        String expected = "Name of the country is :JapanJapan row 7 column 1";
	        String actual = position("Japan");
	        assertEquals(actual, expected);
	    }
	    @Test(priority = 5)
	    public void TestCase5() {
	        // 1. Go to website
	                driver.get(url);
	        //2. Write a method that returns a list of two countries whose sum of bronze medals is 18.  
	                bronzeCountries=sum18(18);
	                System.out.println(bronzeCountries);
	                List<String> expected=Arrays.asList(" Australia (AUS)"," Italy (ITA)");
	                assertEquals(bronzeCountries, expected);
	                
	    }
	    @AfterClass
	    public void tearDown() {
	        driver.quit();
	    }
	    public String mostGold(List<WebElement> list) {
	        Map<String, Integer> GoldMedal = new HashMap<String, Integer>();
	        String result = "";
	        for (int i = 0; i < list.size(); i++) {
	            String MedalXpath = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr["+ (i + 1) + "]/td[2]";
	            String CountryXpath = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr["+ (i + 1) + "]/th/a";
	            String medals = driver.findElement(By.xpath(MedalXpath)).getText();
	            Integer value = Integer.parseInt(medals);
	            String country = driver.findElement(By.xpath(CountryXpath)).getText();
	            GoldMedal.put(country, value);
	        }
	        Set<Entry<String, Integer>> entries = GoldMedal.entrySet();
	        for (Entry<String, Integer> entry : entries) {
	            if (!entry.getValue().equals(Collections.max(GoldMedal.values()))) {
	                continue;
	            } else {
	                result = entry.getKey();
	            }
	        }
	        return result;
	    }
	    public String mostSilver(List<WebElement> list) {
	        Map<String, Integer> SilverMedal = new HashMap<String, Integer>();
	        String result = "";
	        for (int i = 0; i < list.size(); i++) {
	            String MedalXpath = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr["+ (i + 1) + "]/td[3]";
	            String CountryXpath = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr["+ (i + 1) + "]/th/a";
	            String medals = driver.findElement(By.xpath(MedalXpath)).getText();
	            Integer value = Integer.parseInt(medals);
	            String country = driver.findElement(By.xpath(CountryXpath)).getText();
	            SilverMedal.put(country, value);
	        }
	        Set<Entry<String, Integer>> entries = SilverMedal.entrySet();
	        for (Entry<String, Integer> entry : entries) {
	            if (!entry.getValue().equals(Collections.max(SilverMedal.values()))) {
	                continue;
	            } else {
	                result = entry.getKey();
	            }
	        }
	        return result;
	    }
	    public String mostBronz(List<WebElement> list) {
	        Map<String, Integer> BronzMedal = new HashMap<String, Integer>();
	        String result = "";
	        for (int i = 0; i < list.size(); i++) {
	            String MedalXpath = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr["+ (i + 1) + "]/td[4]";
	            String CountryXpath = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr["+ (i + 1) + "]/th/a";
	            String medals = driver.findElement(By.xpath(MedalXpath)).getText();
	            Integer value = Integer.parseInt(medals);
	            String country = driver.findElement(By.xpath(CountryXpath)).getText();
	            BronzMedal.put(country, value);
	        }
	        Set<Entry<String, Integer>> entries = BronzMedal.entrySet();
	        for (Entry<String, Integer> entry : entries) {
	            if (!entry.getValue().equals(Collections.max(BronzMedal.values()))) {
	                continue;
	            } else {
	                result = entry.getKey();
	            }
	        }
	        return result;
	    }
	    public String TotalMedals(List<WebElement> list) {
	        Map<String, Integer> Total = new HashMap<String, Integer>();
	        String result = "";
	        for (int i = 0; i < list.size(); i++) {
	            String MedalXpath = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr["+ (i + 1) + "]/td[5]";
	            String CountryXpath = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr["+ (i + 1) + "]/th/a";
	            String medals = driver.findElement(By.xpath(MedalXpath)).getText();
	            Integer value = Integer.parseInt(medals);
	            String country = driver.findElement(By.xpath(CountryXpath)).getText();
	            Total.put(country, value);
	        }
	        Set<Entry<String, Integer>> entries = Total.entrySet();
	        for (Entry<String, Integer> entry : entries) {
	            if (!entry.getValue().equals(Collections.max(Total.values()))) {
	                continue;
	            } else {
	                result = entry.getKey();
	            }
	        }
	        return result;
	    }
	    public List<String> silverCountries(List<WebElement> li) {
	        for (int i = 0; i < li.size() - 1; i++) {
	            String xpath = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr[" + (i + 1)+ "]/td[3]";
	            String medal = driver.findElement(By.xpath(xpath)).getText();
	            String country = driver.findElement(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr["+ (i + 1) + "]/th")).getText();
	            if (medal.equals("18")) {
	                silverCountries.add(country);
	            }
	        }
	        return silverCountries;
	    }
	    public String position(String name) {
	        List<WebElement> countries = driver.findElements(By.xpath("//*[@id=\"mw-content-text\"]/div/table[8]/tbody/tr/th/a"));
	        int row = 0;
	        for (int i = 0; i < countries.size(); i++) {
	            if (countries.get(i).getText().contains("Japan")) {
	                row = i + 1;
	                name += countries.get(i).getText();
	            }
	        }
	        List<WebElement> columns = driver
	                .findElements(By.xpath("//*[@id=\"mw-content-text\"]/div/table[8]/thead/tr/th"));
	        int column = 0;
	        for (int i = 0; i < columns.size(); i++) {
	            if (columns.get(i).getText().equals("NOC")) {
	                column = i;
	            }
	        }
	        return "Name of the country is :" + name + " row " + row + " column " + column;
	    }
	    public List<String> sum18(int sum) {
	        List<String> bronzeCountries = new ArrayList<String>();
	        for (int i = 0; i < data.size() - 1; i++) {
	            for (int j = i + 1; j < data.size() - 1; j++) {
	                String xpath = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr["+ (i + 1) + "]/td[4]";
	                String xpath1 = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr["+ (j + 1) + "]/td[4]";
	                int tdData = Integer.parseInt((driver.findElement(By.xpath(xpath)).getText()));
	                int tdData1 = Integer.parseInt((driver.findElement(By.xpath(xpath1)).getText()));
	                if ((tdData + tdData1) == sum) {
	                    String country = driver.findElement(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr["+ (i + 1) + "]/th")).getText();
	                    String country1 = driver.findElement(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr["+ (j + 1) + "]/th")).getText();
	                    bronzeCountries.add(country);
	                    bronzeCountries.add(country1);
	                }
	            }
	        }
	        return bronzeCountries;
	    }
	}
