package ua.edu.sumdu.ta.alexandersudarenko.lab.tests;

import java.io.File;
import java.io.IOException;

import java.util.Set;
import java.util.List; 
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
 
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
 
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

import org.apache.commons.io.FileUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(OrderedTestRunner.class)
public class WebSiteTest {
    private static ChromeDriverService service;
    public static WebDriver driver;
    private static String PATH_TO_CHROMEDRIVER_EXE = "chromedriver/chromedriver.exe";
    
    private static TestCaseList testCaseList;
 
    @BeforeClass
    public static void createAndStartService() throws IOException {
        testCaseList = new TestCaseList();
        testCaseList.setTimeInfo("Start tests: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));
        service = new ChromeDriverService.Builder()
            .usingDriverExecutable(new File(PATH_TO_CHROMEDRIVER_EXE))
            .usingAnyFreePort()
            .build();
        service.start();
    }
 
    @Before
    public void setUp(){
        driver = new ChromeDriver(service);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get("http://s1.web.sumdu.edu.ua");
    }
    
    @Rule
      public TestWatcher testWatcher = new TestWatcher() {

        protected void succeeded(Description description) {
            super.succeeded(description);
        }
      
        @Override
        protected void failed(Throwable e, Description description) {
          testCaseList.setErrorDescription(description.getDisplayName(), e.getMessage());
          super.failed(e, description);
        }
        
        @Override
        protected void starting(Description description) {
            //Start TestCase
            ListMessage listMessage = new ListMessage();
            testCaseList.add(description.getDisplayName(), listMessage);
            testCaseList.setStartDate(description.getDisplayName(), new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));
            TestCaseAssert.setListMessage(listMessage);
            
            super.starting(description);
        }
        
        @Override
        protected void finished(Description description) {
            testCaseList.setFinishDate(description.getDisplayName(), new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));
            
            super.finished(description);
        }
      };
    
    
    private boolean loginToServer(OperationForm operationForm) {
        return operationForm.loginToServer("f", new String[] {"j_username", "j_password"}, new String[] {"AlS_03", "Alexander_1"});
    }
    
    private boolean validParameter(String name, String eValue) {
        if (driver.findElement(By.xpath("//*[@class=\"objects\"]//th[text()=\"" + name + "\"]/parent::tr/td[@class=\"parameter\"]")).getText().equals(eValue)) return true;
        return false;
    }
    
    private void validParameters(String[] name, String[] eValue) {
        for (int i = 0; i < name.length; i++) {
            TestCaseAssert.assertTrue("validParameter " + name[i], validParameter(name[i], eValue[i]));
        }
    }
    
    private void deleteObject(String name) {
        WebElement checkBox = driver.findElement(By.xpath("//*[@class=\"objects\"]//a[text()=\"" + name + "\"]/parent::td/input[@type=\"checkbox\"]"));
        if(!checkBox.isSelected()){
            checkBox.click();
            driver.findElement(By.xpath("//input[@type=\"submit\" and @value=\"Delete\"]")).click();
        }
    }
    
    
    
  
    //Test ID 1
    @Test
    public void navigationRegisterPageTest() {   
        Navigation navigation = new Navigation(driver);
        navigation.setNavigation(new String[] {"Register"});
        TestCaseAssert.assertEquals("Navigation to 'Registration' page", "Registration", driver.getTitle());        
    }

    //Test ID 2
    @Test
    public void emptyFieldsRegisterTest() {
        Navigation navigation = new Navigation(driver);
        navigation.setNavigation(new String[] {"Register"});
        assertEquals("Registration", driver.getTitle());
        driver.findElement(By.name("registerForm:j_idt27")).click();
        TestCaseAssert.assertEquals("Popytka registracii s pustymi poljami", 4, driver.findElements(By.cssSelector(".error")).size());
    }    

    //Test ID 3
    @Test
    public void validationRegisterFormTest() {
        Navigation navigation = new Navigation(driver);
        navigation.setNavigation(new String[] {"Register"});
        assertEquals("Registration", driver.getTitle());
        
        OperationForm operationForm = new OperationForm(driver);    
        TestCaseAssert.assertTrue("validation username Alexander2", operationForm.validInput("registerForm", "registerForm:username", "Alexander2"));
        TestCaseAssert.assertFalse("validation username Alexander", operationForm.validInput("registerForm", "registerForm:username", "Alexander"));
        TestCaseAssert.assertFalse("validation username 11111111", operationForm.validInput("registerForm", "registerForm:username", "11111111"));
        TestCaseAssert.assertFalse("validation username a1", operationForm.validInput("registerForm", "registerForm:username", "a1"));

        
        TestCaseAssert.assertTrue("validation password/confirmPassword Alexander_1 Alexander_1", operationForm.validInput("registerForm", 
          new String[] {"registerForm:password", "registerForm:confirmPassword"}, 
          new String[] {"Alexander_1", "Alexander_1"}));
        TestCaseAssert.assertFalse("validation password/confirmPassword Alexander_1 Alexander_2", operationForm.validInput("registerForm", 
          new String[] {"registerForm:password", "registerForm:confirmPassword"}, 
          new String[] {"Alexander_1", "Alexander_2"}));
        TestCaseAssert.assertFalse("validation password/confirmPassword alex alex", operationForm.validInput("registerForm", 
          new String[] {"registerForm:password", "registerForm:confirmPassword"}, 
          new String[] {"alex", "alex"}));
        TestCaseAssert.assertFalse("validation password/confirmPassword Alexander_ Alexander_", operationForm.validInput("registerForm", 
          new String[] {"registerForm:password", "registerForm:confirmPassword"}, 
          new String[] {"Alexander_", "Alexander_"}));
        TestCaseAssert.assertFalse("validation password/confirmPassword ALEXABDER_1 ALEXABDER_1", operationForm.validInput("registerForm", 
          new String[] {"registerForm:password", "registerForm:confirmPassword"}, 
          new String[] {"ALEXABDER_1", "ALEXABDER_1"}));
        TestCaseAssert.assertFalse("validation password/confirmPassword alexander_1 alexander_1", operationForm.validInput("registerForm", 
          new String[] {"registerForm:password", "registerForm:confirmPassword"}, 
          new String[] {"alexander_1", "alexander_1"}));
        TestCaseAssert.assertFalse("validation password/confirmPassword Alexander1 Alexander1", operationForm.validInput("registerForm", 
          new String[] {"registerForm:password", "registerForm:confirmPassword"}, 
          new String[] {"Alexander1", "Alexander1"}));       

        TestCaseAssert.assertTrue("validation email alexander@nc.com", operationForm.validInput("registerForm", "registerForm:email", "alexander@nc.com"));
        TestCaseAssert.assertFalse("validation email alexandernc.com", operationForm.validInput("registerForm", "registerForm:email", "alexandernc.com"));
        TestCaseAssert.assertFalse("validation email alexander@@nc.com", operationForm.validInput("registerForm", "registerForm:email", "alexander@@nc.com"));
        TestCaseAssert.assertFalse("validation email alexander@nc", operationForm.validInput("registerForm", "registerForm:email", "alexander@nc"));
        TestCaseAssert.assertFalse("validation email alexander@nc.COM", operationForm.validInput("registerForm", "registerForm:email", "alexander@nc.COM"));
    }

    //Test ID 4
    @Test
    public void createNewUserTest() {
        DateFormat df = new SimpleDateFormat("ddMMyyyy_HHmmss");
        Date today = Calendar.getInstance().getTime();        
        String reportDate = "AlS" + df.format(today);
        
        Navigation navigation = new Navigation(driver);
        navigation.setNavigation(new String[] {"Register"});
        assertEquals("Registration", driver.getTitle());
        
        OperationForm operationForm = new OperationForm(driver); 
        operationForm.setInput("registerForm", 
          new String[] {"registerForm:username", "registerForm:password", 
            "registerForm:confirmPassword", "registerForm:email"}, 
          new String[] {reportDate, "Alexander_1", "Alexander_1", "alexander@nc.com"});
        driver.findElement(By.name("registerForm:j_idt27")).click();
        
        TestCaseAssert.assertEquals("create New User", 1, driver.findElements(By.xpath("//div[@class=\"justRegisteredBlock\"]")).size());        
    }  

    //Test ID 5
    @Test
    public void navigationLoginPageTest() {   
        Navigation navigation = new Navigation(driver);
        navigation.setNavigation(new String[] {"Register", "Login"});
        TestCaseAssert.assertEquals("Navigation to 'Login Page' page", "Login Page", driver.getTitle());        
    }

    //Test ID 6
    @Test
    public void validationLoginFormTest() {   
        Navigation navigation = new Navigation(driver);
        TestCaseAssert.assertEquals("Navigation to 'Login Page' page", "Login Page", driver.getTitle());      
    }

    //Test ID 7
    @Test
    public void emptyFieldsLoginTest() {   
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        driver.findElement(By.name("submit")).click();
        TestCaseAssert.assertEquals("empty fields login", 1, driver.findElements(By.xpath("//div[@class=\"errorblock\"]")).size());         
    }
    
    //Test ID 8
    @Test
    public void incorrectFieldsLoginTest() {   
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        TestCaseAssert.assertFalse("incorrect fields login AlS_003/Alexander_1", operationForm.loginToServer("f", 
          new String[] {"j_username", "j_password"}, 
          new String[] {"AlS_003", "Alexander_1"}));   
        TestCaseAssert.assertFalse("incorrect fields login AlS_03/Alexander_2", operationForm.loginToServer("f", 
          new String[] {"j_username", "j_password"}, 
          new String[] {"AlS_03", "Alexander_2"}));
    }   

    //Test ID 9
    @Test
    public void loginTest() {   
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        TestCaseAssert.assertTrue("login AlS_03/Alexander_1", operationForm.loginToServer("f", 
          new String[] {"j_username", "j_password"}, 
          new String[] {"AlS_03", "Alexander_1"}));
    }

    //Test ID 10
    @Test
    @Order(2)
    public void createCountryTest() { 
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(loginToServer(operationForm));
          
        navigation.setNavigation(new String[] {"Inventory", "Create country"});
        operationForm.setInput("j_idt31", new String[] {"j_idt31:name", "j_idt31:continent", "j_idt31:language"}, new String[] {"[sudarenko_alexander]country", "Europa", "English"});
        driver.findElement(By.name("j_idt31:j_idt33")).click();
        TestCaseAssert.assertEquals("--", "[sudarenko_alexander]country", driver.getTitle());
    }
    
    //Test ID 11
    @Test
    @Order(3)
    public void navigationCountryTest() { 
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(loginToServer(operationForm));
          
        navigation.setNavigation(new String[] {"Inventory", "[sudarenko_alexander]country"});
        TestCaseAssert.assertEquals("--", "[sudarenko_alexander]country", driver.getTitle());
    }
   
    //Test ID 12
    @Test
    @Order(3)
    public void createCityTest() { 
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(loginToServer(operationForm));
          
        navigation.setNavigation(new String[] {"Inventory", "[sudarenko_alexander]country", "Create city"});
        operationForm.setInput("j_idt31", new String[] {"j_idt31:name", "j_idt31:population", "j_idt31:isRegionalCenter"}, new String[] {"[sudarenko_alexander]city", "1000", "Yes"});
        driver.findElement(By.name("j_idt31:j_idt33")).click();
        TestCaseAssert.assertEquals("--", "[sudarenko_alexander]city", driver.getTitle());
    }
    
    //Test ID 13
    @Test
    @Order(4)
    public void navigationCityTest() { 
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(loginToServer(operationForm));
          
        navigation.setNavigation(new String[] {"Inventory", "[sudarenko_alexander]country", "[sudarenko_alexander]city"});
        TestCaseAssert.assertEquals("--", "[sudarenko_alexander]city", driver.getTitle());
    }    
    
    //Test ID 14
    @Test
    @Order(4)
    public void createBuildingTest() { 
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(loginToServer(operationForm));
          
        navigation.setNavigation(new String[] {"Inventory", "[sudarenko_alexander]country", "[sudarenko_alexander]city", "Create building"});
        operationForm.setInput("j_idt31", new String[] {"j_idt31:name", "j_idt31:streetName", "j_idt31:number", "j_idt31:square", "j_idt31:isconnected"}, new String[] {"[sudarenko_alexander]building", "mystreet", "1", "1.0", "Lit"});
        driver.findElement(By.name("j_idt31:j_idt33")).click();
        TestCaseAssert.assertEquals("--", "[sudarenko_alexander]building", driver.getTitle());
    }
    
    //Test ID 15
    @Test
    @Order(5)
    public void navigationBuildingTest() { 
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(loginToServer(operationForm));
          
        navigation.setNavigation(new String[] {"Inventory", "[sudarenko_alexander]country", "[sudarenko_alexander]city", "[sudarenko_alexander]building"});
        TestCaseAssert.assertEquals("--", "[sudarenko_alexander]building", driver.getTitle());
    }    

    //Test ID 16
    @Test
    @Order(5)
    public void createFloorTest() { 
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(loginToServer(operationForm));
          
        navigation.setNavigation(new String[] {"Inventory", "[sudarenko_alexander]country", "[sudarenko_alexander]city", "[sudarenko_alexander]building", "Create floor"});
        operationForm.setInput("j_idt31", new String[] {"j_idt31:name", "j_idt31:number", "j_idt31:square"}, new String[] {"[sudarenko_alexander]floor", "1", "1.0"});
        driver.findElement(By.name("j_idt31:j_idt33")).click();
        TestCaseAssert.assertEquals("--", "Floor#1", driver.getTitle());
    }
    
    //Test ID 17
    @Test
    @Order(6)
    public void navigationFloorTest() { 
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(loginToServer(operationForm));
          
        navigation.setNavigation(new String[] {"Inventory", "[sudarenko_alexander]country", "[sudarenko_alexander]city", "[sudarenko_alexander]building", "Floor#1"});
        TestCaseAssert.assertEquals("--", "Floor#1", driver.getTitle());
    }
    
    //Test ID 18
    @Test
    @Order(6)
    public void createRoomTest() { 
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(loginToServer(operationForm));
          
        navigation.setNavigation(new String[] {"Inventory", "[sudarenko_alexander]country", "[sudarenko_alexander]city", "[sudarenko_alexander]building", "Floor#1", "Create room"});
        operationForm.setInput("j_idt31", new String[] {"j_idt31:name", "j_idt31:square"}, new String[] {"[sudarenko_alexander]room", "1.0"});
        driver.findElement(By.name("j_idt31:j_idt33")).click();
        TestCaseAssert.assertEquals("--", "[sudarenko_alexander]room", driver.getTitle());
    }
    
    //Test ID 19
    @Test
    @Order(7)
    public void navigationRoomTest() { 
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(loginToServer(operationForm));
          
        navigation.setNavigation(new String[] {"Inventory", "[sudarenko_alexander]country", "[sudarenko_alexander]city", "[sudarenko_alexander]building", "Floor#1", "[sudarenko_alexander]room"});
        TestCaseAssert.assertEquals("--", "[sudarenko_alexander]room", driver.getTitle());
    }  

    //Test ID 20
    @Test
    @Order(7)
    public void createRackTest() { 
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(loginToServer(operationForm));
          
        navigation.setNavigation(new String[] {"Inventory", "[sudarenko_alexander]country", "[sudarenko_alexander]city", "[sudarenko_alexander]building", "Floor#1", "[sudarenko_alexander]room", "Create rack"});
        operationForm.setInput("j_idt31", new String[] {"j_idt31:name", "j_idt31:width", "j_idt31:length", "j_idt31:height", "j_idt31:physicalStatus"}, new String[] {"[sudarenko_alexander]rack", "1", "1", "1", "Planned"});
        driver.findElement(By.name("j_idt31:j_idt33")).click();
        TestCaseAssert.assertEquals("--", "[sudarenko_alexander]rack", driver.getTitle());
    }
    
    //Test ID 21
    @Test
    @Order(8)
    public void navigationRackTest() { 
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(loginToServer(operationForm));
          
        navigation.setNavigation(new String[] {"Inventory", "[sudarenko_alexander]country", "[sudarenko_alexander]city", "[sudarenko_alexander]building", "Floor#1", "[sudarenko_alexander]room", "[sudarenko_alexander]rack"});
        TestCaseAssert.assertEquals("--", "[sudarenko_alexander]rack", driver.getTitle());
    }    
    
    //Test ID 22
    @Test
    @Order(8)
    public void createDeviceTest() { 
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(loginToServer(operationForm));
          
        navigation.setNavigation(new String[] {"Inventory", "[sudarenko_alexander]country", "[sudarenko_alexander]city", "[sudarenko_alexander]building", "Floor#1", "[sudarenko_alexander]room", "[sudarenko_alexander]rack", "Create device"});
        operationForm.setInput("j_idt31", 
          new String[] {"j_idt31:name", "j_idt31:macAddress", "j_idt31:ram", "j_idt31:cpu", "j_idt31:ipAddress", "j_idt31:physicalStatus", "j_idt31:width", "j_idt31:length", "j_idt31:height", "j_idt31:locatedIn", "j_idt31:networkElementName"}, 
          new String[] {"[sudarenko_alexander]device", "11:11:11:11:11:11", "1", "1", "127.0.0.1", "Planned", "1", "1", "1", "Country: [sudarenko_alexander]country", "Country: [sudarenko_alexander]country"});
        driver.findElement(By.name("j_idt31:j_idt33")).click();
        TestCaseAssert.assertEquals("--", "[sudarenko_alexander]device", driver.getTitle());
    }    

    @Test
    @Order(8)
    public void createDeviceTest2() { 
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(loginToServer(operationForm));
          
        navigation.setNavigation(new String[] {"Inventory", "[sudarenko_alexander]country", "[sudarenko_alexander]city", "[sudarenko_alexander]building", "Devices", "Create device"});
        operationForm.setInput("j_idt31", 
          new String[] {"j_idt31:name", "j_idt31:macAddress", "j_idt31:ram", "j_idt31:cpu", "j_idt31:ipAddress", "j_idt31:physicalStatus", "j_idt31:width", "j_idt31:length", "j_idt31:height", "j_idt31:locatedIn", "j_idt31:networkElementName"}, 
          new String[] {"[sudarenko_alexander]device2", "11:11:11:11:11:11", "1", "1", "127.0.0.1", "Planned", "1", "1", "1", "Country: [sudarenko_alexander]country", "Country: [sudarenko_alexander]country"});
        driver.findElement(By.name("j_idt31:j_idt33")).click();
        TestCaseAssert.assertEquals("--", "[sudarenko_alexander]device2", driver.getTitle());
    }

    @Test
    @Order(8)
    public void createDeviceTest3() { 
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(loginToServer(operationForm));
          
        navigation.setNavigation(new String[] {"Inventory", "[sudarenko_alexander]country", "[sudarenko_alexander]city", "[sudarenko_alexander]building", "Floor#1", "Devices", "Create device"});
        operationForm.setInput("j_idt31", 
          new String[] {"j_idt31:name", "j_idt31:macAddress", "j_idt31:ram", "j_idt31:cpu", "j_idt31:ipAddress", "j_idt31:physicalStatus", "j_idt31:width", "j_idt31:length", "j_idt31:height", "j_idt31:locatedIn", "j_idt31:networkElementName"}, 
          new String[] {"[sudarenko_alexander]device3", "11:11:11:11:11:11", "1", "1", "127.0.0.1", "Planned", "1", "1", "1", "Country: [sudarenko_alexander]country", "Country: [sudarenko_alexander]country"});
        driver.findElement(By.name("j_idt31:j_idt33")).click();
        TestCaseAssert.assertEquals("--", "[sudarenko_alexander]device3", driver.getTitle());
    }

    //Test ID 23
    @Test
    @Order(8)
    public void createPostTerminalTest() { 
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(loginToServer(operationForm));
          
        navigation.setNavigation(new String[] {"Inventory", "[sudarenko_alexander]country", "[sudarenko_alexander]city", "[sudarenko_alexander]building", "Floor#1", "[sudarenko_alexander]room", "[sudarenko_alexander]rack", "Pos Terminal (s)", "Create Post Terminal"});
        operationForm.setInput("j_idt31", 
          new String[] {"j_idt31:name", "j_idt31:width", "j_idt31:length", "j_idt31:height", "j_idt31:physicalStatus", "j_idt31:locatedIn"}, 
          new String[] {"[sudarenko_alexander]Post Terminal", "1", "1", "1", "Planned", "Country: [sudarenko_alexander]country"});
        driver.findElement(By.name("j_idt31:j_idt33")).click();
        TestCaseAssert.assertEquals("--", "[sudarenko_alexander]Post Terminal", driver.getTitle());
    }   
    
    //Button
    @Test
    @Order(8)
    public void createPostTerminalTest2() { 
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(loginToServer(operationForm));
          
        navigation.setNavigation(new String[] {"Inventory", "[sudarenko_alexander]country", "[sudarenko_alexander]city", "[sudarenko_alexander]building", "Pos Terminal (s)", "Create "});
        operationForm.setInput("j_idt31", 
          new String[] {"j_idt31:name", "j_idt31:width", "j_idt31:length", "j_idt31:height", "j_idt31:physicalStatus", "j_idt31:locatedIn"}, 
          new String[] {"[sudarenko_alexander]Post Terminal2", "1", "1", "1", "Planned", "Country: [sudarenko_alexander]country"});
        driver.findElement(By.name("j_idt31:j_idt33")).click();
        TestCaseAssert.assertEquals("--", "[sudarenko_alexander]Post Terminal2", driver.getTitle());
    }
    
    @Test
    @Order(8)
    public void createPostTerminalTest3() { 
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(loginToServer(operationForm));
          
        navigation.setNavigation(new String[] {"Inventory", "[sudarenko_alexander]country", "[sudarenko_alexander]city", "[sudarenko_alexander]building", "Floor#1", "Pos Terminal (s)", "Create Post Terminal"});
        operationForm.setInput("j_idt31", 
          new String[] {"j_idt31:name", "j_idt31:width", "j_idt31:length", "j_idt31:height", "j_idt31:physicalStatus", "j_idt31:locatedIn"}, 
          new String[] {"[sudarenko_alexander]Post Terminal3", "1", "1", "1", "Planned", "Country: [sudarenko_alexander]country"});
        driver.findElement(By.name("j_idt31:j_idt33")).click();
        TestCaseAssert.assertEquals("--", "[sudarenko_alexander]Post Terminal3", driver.getTitle());
    }

    //Test ID 24
    @Test
    @Order(8)
    public void createPayBoxTest() { 
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(loginToServer(operationForm));
          
        navigation.setNavigation(new String[] {"Inventory", "[sudarenko_alexander]country", "[sudarenko_alexander]city", "[sudarenko_alexander]building", "Floor#1", "[sudarenko_alexander]room", "[sudarenko_alexander]rack", "Pay Box (s)", "Create Pay Box"});
        operationForm.setInput("j_idt31", 
          new String[] {"j_idt31:name", "j_idt31:secureProtocol", "j_idt31:displaySize", "j_idt31:physicalStatus", "j_idt31:locatedIn"}, 
          new String[] {"[sudarenko_alexander]Pay Box", "1", "1", "Planned", "Country: [sudarenko_alexander]country"});
        driver.findElement(By.name("j_idt31:j_idt33")).click();
        TestCaseAssert.assertEquals("--", "[sudarenko_alexander]Pay Box", driver.getTitle());
    }    
    
    @Test
    @Order(8)
    public void createPayBoxTest2() { 
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(loginToServer(operationForm));
          
        navigation.setNavigation(new String[] {"Inventory", "[sudarenko_alexander]country", "[sudarenko_alexander]city", "[sudarenko_alexander]building", "Pay Box (s)", "Create Pay Box"});
        operationForm.setInput("j_idt31", 
          new String[] {"j_idt31:name", "j_idt31:secureProtocol", "j_idt31:displaySize", "j_idt31:physicalStatus", "j_idt31:locatedIn"}, 
          new String[] {"[sudarenko_alexander]Pay Box2", "1", "1", "Planned", "Country: [sudarenko_alexander]country"});
        driver.findElement(By.name("j_idt31:j_idt33")).click();
        TestCaseAssert.assertEquals("--", "[sudarenko_alexander]Pay Box2", driver.getTitle());
    }
    
    @Test
    @Order(8)
    public void createPayBoxTest3() { 
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(loginToServer(operationForm));
          
        navigation.setNavigation(new String[] {"Inventory", "[sudarenko_alexander]country", "[sudarenko_alexander]city", "[sudarenko_alexander]building", "Floor#1", "Pay Box (s)", "Create Pay Box"});
        operationForm.setInput("j_idt31", 
          new String[] {"j_idt31:name", "j_idt31:secureProtocol", "j_idt31:displaySize", "j_idt31:physicalStatus", "j_idt31:locatedIn"}, 
          new String[] {"[sudarenko_alexander]Pay Box3", "1", "1", "Planned", "Country: [sudarenko_alexander]country"});
        driver.findElement(By.name("j_idt31:j_idt33")).click();
        TestCaseAssert.assertEquals("--", "[sudarenko_alexander]Pay Box3", driver.getTitle());
    }

    //Test ID 25
    @Test
    @Order(8)
    public void createATMTest() { 
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(loginToServer(operationForm));
          
        navigation.setNavigation(new String[] {"Inventory", "[sudarenko_alexander]country", "[sudarenko_alexander]city", "[sudarenko_alexander]building", "Floor#1", "[sudarenko_alexander]room", "[sudarenko_alexander]rack", "ATM (s)", "Create ATM"});
        operationForm.setInput("j_idt31", 
          new String[] {"j_idt31:name", "j_idt31:connectionType", "j_idt31:extraSecurity", "j_idt31:physicalStatus", "j_idt31:locatedIn"}, 
          new String[] {"[sudarenko_alexander]ATM", "1", "1", "Planned", "Country: [sudarenko_alexander]country"});
        driver.findElement(By.name("j_idt31:j_idt33")).click();
        TestCaseAssert.assertEquals("--", "[sudarenko_alexander]ATM", driver.getTitle());
    }
    
    @Test
    @Order(8)
    public void createATMTest2() { 
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(loginToServer(operationForm));
          
        navigation.setNavigation(new String[] {"Inventory", "[sudarenko_alexander]country", "[sudarenko_alexander]city", "[sudarenko_alexander]building", "ATM (s)", "Create ATM"});
        operationForm.setInput("j_idt31", 
          new String[] {"j_idt31:name", "j_idt31:connectionType", "j_idt31:extraSecurity", "j_idt31:physicalStatus", "j_idt31:locatedIn"}, 
          new String[] {"[sudarenko_alexander]ATM2", "1", "1", "Planned", "Country: [sudarenko_alexander]country"});
        driver.findElement(By.name("j_idt31:j_idt33")).click();
        TestCaseAssert.assertEquals("--", "[sudarenko_alexander]ATM2", driver.getTitle());
    }
    
    @Test
    @Order(8)
    public void createATMTest3() { 
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(loginToServer(operationForm));
          
        navigation.setNavigation(new String[] {"Inventory", "[sudarenko_alexander]country", "[sudarenko_alexander]city", "[sudarenko_alexander]building", "Floor#1", "ATM (s)", "Create ATM"});
        operationForm.setInput("j_idt31", 
          new String[] {"j_idt31:name", "j_idt31:connectionType", "j_idt31:extraSecurity", "j_idt31:physicalStatus", "j_idt31:locatedIn"}, 
          new String[] {"[sudarenko_alexander]ATM3", "1", "1", "Planned", "Country: [sudarenko_alexander]country"});
        driver.findElement(By.name("j_idt31:j_idt33")).click();
        TestCaseAssert.assertEquals("--", "[sudarenko_alexander]ATM3", driver.getTitle());
    }


    //Test ID 26-27
    @Test
    @Order(3)
    public void editCountryTest() { 
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(loginToServer(operationForm));
          
        navigation.setNavigation(new String[] {"Inventory", "[sudarenko_alexander]country", "Parameters", "Edit"});
        operationForm.setInput("j_idt36", new String[] {"j_idt36:name", "j_idt36:continent", "j_idt36:language"}, new String[] {"[sudarenko_alexander]country", "Europa2", "English2"});
        driver.findElement(By.name("j_idt36:j_idt38")).click();
        TestCaseAssert.assertEquals("--", "[sudarenko_alexander]country", driver.getTitle());
    }

    //Test ID 28
    @Test
    @Order(4)
    public void valueCountryTest() { 
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(loginToServer(operationForm));
          
        navigation.setNavigation(new String[] {"Inventory", "[sudarenko_alexander]country", "Parameters"});
        validParameters(new String[] {"Name", "Continent", "Language", "Object Type"}, new String[] {"[sudarenko_alexander]country", "Europa2", "English2", "Country"});
    }

    //Test ID 49-50
    @Test
    @Order(9)
    public void deleteCountryTest() { 
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(loginToServer(operationForm));
          
        navigation.setNavigation(new String[] {"Inventory"});
        deleteObject("[sudarenko_alexander]country");
        
    }
    
    @After
    public void tearDown(){
        driver.quit();
    }

    @AfterClass
    public static void createAndStopService() throws Exception  {
        service.stop();
        testCaseList.setTimeInfo(" - End tests: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));
        MarshallerToXML.marshaller(testCaseList);
    }
}
