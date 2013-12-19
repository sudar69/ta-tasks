package ua.edu.sumdu.ta.alexandersudarenko.lab.tests;

import java.io.File;
import java.io.IOException;
 
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
            System.out.println("" + description.getDisplayName() + " succeeded ");
            super.succeeded(description);
        }
      
        @Override
        protected void failed(Throwable e, Description description) {
          System.out.println("" + description.getDisplayName() + " failed " + e.getMessage());
          super.failed(e, description);
        }
        
        @Override
        protected void starting(Description description) {
            
            //Start TestCase
            ListMessage listMessage = new ListMessage();
            testCaseList.add(description.getDisplayName(), listMessage);
            TestCaseAssert.setListMessage(listMessage);
            
            System.out.println("starting" + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").
              format(Calendar.getInstance().getTime()));
            super.starting(description);
        }
        
        @Override
        protected void finished(Description description) {
            System.out.println("finished");
            super.finished(description);
        }
      };
    
    //Test ID 1
    @Test
    public void navigationRegisterPageTest() {   
        Navigation navigation = new Navigation(driver);
        navigation.setNavigation(new String[] {"Register"});
        TestCaseAssert.assertEquals("Registration", driver.getTitle());        
    }

    //Test ID 2
    @Test
    public void emptyFieldsRegisterTest() {
        Navigation navigation = new Navigation(driver);
        navigation.setNavigation(new String[] {"Register"});
        TestCaseAssert.assertEquals("Registration", driver.getTitle());
        driver.findElement(By.name("registerForm:j_idt27")).click();
        TestCaseAssert.assertEquals(4, driver.findElements(By.cssSelector(".error")).size());        
    }    
/*    
    //Test ID 3
    @Test
    public void validationRegisterFormTest() {
        Navigation navigation = new Navigation(driver);
        navigation.setNavigation(new String[] {"Register"});
        assertEquals("Registration", driver.getTitle());
        
        OperationForm operationForm = new OperationForm(driver);    
        assertTrue(operationForm.validInput("registerForm", "registerForm:username", "Alexander2"));
        //assertFalse(operationForm.validInput("registerForm", "registerForm:username", "Alexander"));
        //assertFalse(operationForm.validInput("registerForm", "registerForm:username", "11111111"));
        //assertFalse(operationForm.validInput("registerForm", "registerForm:username", "a1"));

        
        assertTrue(operationForm.validInput("registerForm", 
          new String[] {"registerForm:password", "registerForm:confirmPassword"}, 
          new String[] {"Alexander_1", "Alexander_1"}));
        assertFalse(operationForm.validInput("registerForm", 
          new String[] {"registerForm:password", "registerForm:confirmPassword"}, 
          new String[] {"Alexander_1", "Alexander_2"}));
        assertFalse(operationForm.validInput("registerForm", 
          new String[] {"registerForm:password", "registerForm:confirmPassword"}, 
          new String[] {"alex", "alex"}));
        assertFalse(operationForm.validInput("registerForm", 
          new String[] {"registerForm:password", "registerForm:confirmPassword"}, 
          new String[] {"Alexander_", "Alexander_"}));
        assertFalse(operationForm.validInput("registerForm", 
          new String[] {"registerForm:password", "registerForm:confirmPassword"}, 
          new String[] {"ALEXABDER_1", "ALEXABDER_1"}));
        assertFalse(operationForm.validInput("registerForm", 
          new String[] {"registerForm:password", "registerForm:confirmPassword"}, 
          new String[] {"alexander_1", "alexander_1"}));
        assertFalse(operationForm.validInput("registerForm", 
          new String[] {"registerForm:password", "registerForm:confirmPassword"}, 
          new String[] {"Alexander1", "Alexander1"}));       

        assertTrue(operationForm.validInput("registerForm", "registerForm:email", "alexander@nc.com"));
        assertFalse(operationForm.validInput("registerForm", "registerForm:email", "alexandernc.com"));
        assertFalse(operationForm.validInput("registerForm", "registerForm:email", "alexander@@nc.com"));
        assertFalse(operationForm.validInput("registerForm", "registerForm:email", "alexander@nc"));
        assertFalse(operationForm.validInput("registerForm", "registerForm:email", "alexander@nc.COM"));
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
        
        assertEquals(1, driver.findElements(By.xpath("//div[@class=\"justRegisteredBlock\"]")).size());        
    }  

    //Test ID 5
    @Test
    public void navigationLoginPageTest() {   
        Navigation navigation = new Navigation(driver);
        navigation.setNavigation(new String[] {"Register", "Login"});
        assertEquals("Login Page", driver.getTitle());        
    }

    //Test ID 6
    @Test
    public void validationLoginFormTest() {   
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle());      
    }

    //Test ID 7
    @Test
    public void emptyFieldsLoginTest() {   
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        driver.findElement(By.name("submit")).click();
        assertEquals(1, driver.findElements(By.xpath("//div[@class=\"errorblock\"]")).size());         
    }
    
    //Test ID 8
    @Test
    public void incorrectFieldsLoginTest() {   
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertFalse(operationForm.loginToServer("f", 
          new String[] {"j_username", "j_password"}, 
          new String[] {"AlS_003", "Alexander_1"}));   
        assertFalse(operationForm.loginToServer("f", 
          new String[] {"j_username", "j_password"}, 
          new String[] {"AlS_03", "Alexander_2"}));
    }   

    //Test ID 9
    @Test
    public void loginTest() {   
        Navigation navigation = new Navigation(driver);
        assertEquals("Login Page", driver.getTitle()); 
        
        OperationForm operationForm = new OperationForm(driver); 
        assertTrue(operationForm.loginToServer("f", 
          new String[] {"j_username", "j_password"}, 
          new String[] {"AlS_03", "Alexander_1"}));
    }
*/ 
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
        
        String MyObjects[] = {"country", "city", "building", "floor", "room", "rack"};
        
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
