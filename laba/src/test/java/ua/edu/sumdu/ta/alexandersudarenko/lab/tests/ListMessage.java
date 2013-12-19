package ua.edu.sumdu.ta.alexandersudarenko.lab.tests;

import java.util.List; 
import java.util.LinkedList; 
import javax.xml.bind.annotation.*;

@XmlRootElement
public class ListMessage {
    
    private List<TestMessage> list = new LinkedList<TestMessage>(); 

    public List<TestMessage> getListMessaget() {
        return list;
    }
    
    public void setListMessage(List<TestMessage> list) {
        this.list = list;
    }

    @XmlAnyElement(lax=true)
    public List<TestMessage> getListMessage() {
        return list;
    }
 
    
    public void add(TestMessage testMessage) {
        list.add(testMessage);
    }

}
