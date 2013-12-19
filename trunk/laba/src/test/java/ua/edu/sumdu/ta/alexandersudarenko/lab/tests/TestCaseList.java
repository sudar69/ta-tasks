package ua.edu.sumdu.ta.alexandersudarenko.lab.tests;

import java.util.List; 
import java.util.HashMap; 
import javax.xml.bind.annotation.*;

@XmlRootElement
public class TestCaseList {
    
    private HashMap<String, ListMessage> list = new HashMap<String, ListMessage>();

    @XmlElementWrapper(name="ListMessage")
    public HashMap<String, ListMessage> getTestCaseList() {
        return list;
    }
    
    public void setTestCaseList(HashMap<String, ListMessage> list) {
        this.list = list;
    }
    
    /*public void add(String name, TestMessage testMessage) {
        if (testMessage == null) {
            list.put(name, null);
        } else {
            if (list.get(name) == null) {
                ListMessage listMessage = new ListMessage();
                listMessage.add(testMessage);
                list.put(name, listMessage);
            } else {
                ListMessage listMessage = list.get(name);
                listMessage.add(testMessage);
                list.put(name, listMessage);
            }
        }
    }*/
    
    public void add(String name, ListMessage listMessage) {
        if (listMessage != null) {
            list.put(name, listMessage);
        }
    }
    
    public int size() {
        return list.size();
    }

}
