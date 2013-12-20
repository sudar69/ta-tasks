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


public class WebSiteTest {
    private static ChromeDriverService service;
    public static WebDriver driver;
    private static String PATH_TO_CHROMEDRIVER_EXE = "chromedriver/chromedriver.exe";
    
    private static TestCaseList testCaseList;
 
    @BeforeClass
    public static void createAndStartService() throws IOException {
        testCaseList = new TestCaseList();
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

/*
    //Test ID 10
    @Test
    public void loginTest() {        
    
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(operationForm.loginToServer("f", 
          new String[] {"j_username", "j_password"}, 
          new String[] {"AlS_03", "Alexander_1"}));
          
        navigation.setNavigation(new String[] {"Inventory"});
        
        String MyObjects[] = {"country", "city", "building", "floor", "room","rack"};
        
        HashMap<String, String[][]> createObjects = new HashMap<String, String[][]>();
        
        createObjects.put("country", 
            new String[][] {{"j_idt31:name", "j_idt31:continent", "j_idt31:language"} ,
                            {"[sudarenko_alexander]country", "Europa", "English"}} );
        createObjects.put("city",    
            new String[][] {{"j_idt31:name", "j_idt31:population", "j_idt31:isRegionalCenter"} ,   
                            {"[sudarenko_alexander]city", "1000", "Yes"}} );
        createObjects.put("building", 
            new String[][] {{"j_idt31:name", "j_idt31:streetName", "j_idt31:number", "j_idt31:square", "j_idt31:isconnected"} ,   
                            {"[sudarenko_alexander]building", "mystreet", "1", "1.0", "Lit"}} );
        createObjects.put("floor", 
            new String[][] {{"j_idt31:name", "j_idt31:number", "j_idt31:square"} ,   
                            {"[sudarenko_alexander]floor", "1", "1.0"}} );
        createObjects.put("room", 
            new String[][] {{"j_idt31:name", "j_idt31:square"} ,   
                            {"[sudarenko_alexander]room", "1.0"}} );
        createObjects.put("rack", 
            new String[][] {{"j_idt31:name", "j_idt31:width", "j_idt31:length", "j_idt31:height", "j_idt31:physicalStatus"} ,   
                            {"[sudarenko_alexander]rack", "1", "1", "1", "Planned"}} );
        createObjects.put("device", 
            new String[][] {{"j_idt31:name", "j_idt31:macAddress", "j_idt31:ram", "j_idt31:cpu", "j_idt31:ipAddress"} ,   
                            {"[sudarenko_alexander]device", "1", "1", "1", "Planned"}} );
        
        for (int i = 0; i < 6; i++){
            navigation.setNavigation(new String[] {"Create " + MyObjects[i]});
            operationForm.setInput("j_idt31", createObjects.get(MyObjects[i])[0]
                , createObjects.get(MyObjects[i])[1]);
            driver.findElement(By.name("j_idt31:j_idt33")).click();
            if (MyObjects[i].equals("floor")){
                navigation.setNavigation(new String[] {"Floor#1"});
            } else {
                navigation.setNavigation(new String[] {"[sudarenko_alexander]" + MyObjects[i]});
            }
        }
        
        navigation.setNavigation(new String[] {"[sudarenko_alexander]country", "[sudarenko_alexander]city", "[sudarenko_alexander]building", "Floor#1", "[sudarenko_alexander]room", "[sudarenko_alexander]rack"});
        navigation.setNavigation(new String[] {"Pos Terminal (s)", "Create Post Terminal"});
        operationForm.setInput("j_idt31", new String[] {"j_idt31:name", "j_idt31:width", "j_idt31:length", "j_idt31:height", "j_idt31:physicalStatus"}
                , new String[] {"[sudarenko_alexander]Post Terminal", "1", "1", "1", "Planned"});
            
            
        String originalWindow = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();
 
        driver.findElement(By.linkText("select")).click();
 
        String newWindow = (new WebDriverWait(driver, 10))
            .until(new ExpectedCondition<String>() {
                public String apply(WebDriver driver) {
                    Set<String> newWindowsSet = driver.getWindowHandles();
                    newWindowsSet.removeAll(oldWindowsSet);
                    return newWindowsSet.size() > 0 ? 
                                 newWindowsSet.iterator().next() : null;
                  }
                }
            );
 
        driver.switchTo().window(newWindow);
        
        navigation.setNavigation(new String[] {"Country: [sudarenko_alexander]country"});
        driver.findElement(By.id("OK")).click();
        
        //System.out.println("New window title: " + driver.getTitle());
        //driver.close();
 
        driver.switchTo().window(originalWindow);
        System.out.println("Old window title: " + driver.getTitle());
        driver.findElement(By.name("j_idt31:j_idt33")).click();
    }*/

    @After
    public void tearDown(){
        driver.quit();
    }

    @AfterClass
    public static void createAndStopService() throws Exception  {
        service.stop();
        MarshallerToXML.marshaller(testCaseList);
    }
}
