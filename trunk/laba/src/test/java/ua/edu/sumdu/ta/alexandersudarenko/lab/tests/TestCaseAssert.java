package ua.edu.sumdu.ta.alexandersudarenko.lab.tests;

public class TestCaseAssert extends org.junit.Assert {

    static private ListMessage list;

    protected TestCaseAssert() {
    }
    
    static public void setListMessage(ListMessage listMessage) {
        list = listMessage;
    }

    static public void assertEquals(String message, Object expected, Object actual) {
        try {
            org.junit.Assert.assertEquals(message, expected, actual);
            TestMessage testMessage = new TestMessage();
            testMessage.setDescription(message);
            testMessage.setStatus(1);
            list.add(testMessage);
        } 
        catch (AssertionError e) {
            TestMessage testMessage = new TestMessage();
            testMessage.setDescription(message);
            testMessage.setStatus(0);
            list.add(testMessage);
        }
    }
    
    static public void assertEquals(Object expected, Object actual) {
        TestCaseAssert.assertEquals("No description" , expected, actual);
    }
    
    
    static public void assertEquals(String message, long expected, long actual) {
        try {
            org.junit.Assert.assertEquals(message, expected, actual);
            TestMessage testMessage = new TestMessage();
            testMessage.setDescription(message);
            testMessage.setStatus(1);
            list.add(testMessage);
        } 
        catch (AssertionError e) {
            TestMessage testMessage = new TestMessage();
            testMessage.setDescription(message);
            testMessage.setStatus(0);
            list.add(testMessage);
        }
    }
    
    static public void assertEquals(long expected, long actual) {
        TestCaseAssert.assertEquals("No description" , expected, actual);
    }
    
    
}
