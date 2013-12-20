package ua.edu.sumdu.ta.alexandersudarenko.lab.tests;

import java.util.List; 
import java.util.LinkedList; 
import javax.xml.bind.annotation.*;

@XmlRootElement
public class ListMessage {
    
    private List<TestMessage> list = new LinkedList<TestMessage>(); 
    
    private String startDate;
    
    private String finishDate;
    
    private String errorDescription = "-";

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

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
    @XmlElement
    public String getStartDate() {
        return this.startDate;
    }
    
    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }
    
    @XmlElement
    public String getFinishDate() {
        return this.finishDate;
    }
    
    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
    
    @XmlElement
    public String getErrorDescription() {
        return this.errorDescription;
    }
    
    public void add(TestMessage testMessage) {
        list.add(testMessage);
    }

}
