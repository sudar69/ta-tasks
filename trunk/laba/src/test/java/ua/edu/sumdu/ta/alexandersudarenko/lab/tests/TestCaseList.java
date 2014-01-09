package ua.edu.sumdu.ta.alexandersudarenko.lab.tests;

import java.util.List; 
import java.util.ArrayList;
import java.util.HashMap; 
import javax.xml.bind.annotation.*;

@XmlRootElement
public class TestCaseList {
    
    private HashMap<String, ListMessage> list = new HashMap<String, ListMessage>();
    
    private String timeInfo = "";
    
    @XmlElement
    public String getTimeInfo() {
        return this.timeInfo;
    }
    
    public void setTimeInfo(String timeInfo) {
        this.timeInfo += timeInfo;
    }
    
    @XmlElement(name="size")
    public int getSize() {
        return this.list.size();
    }
    
    @XmlElement(name="checksize")
    public int getCheckSize() {
        int size = 0;
        List<ListMessage> listT = new ArrayList<ListMessage>(this.list.values());
        for (int i = 0; i < listT.size(); i++) {
            size += listT.get(i).getListMessage().size();
        }
        return size;
    }

    @XmlElementWrapper(name="ListMessage")
    public HashMap<String, ListMessage> getTestCaseList() {
        return list;
    }
    
    public void setTestCaseList(HashMap<String, ListMessage> list) {
        this.list = list;
    }
    
    public void add(String name, ListMessage listMessage) {
        if (listMessage != null) {
            list.put(name, listMessage);
        }
    }
    
    public void setStartDate(String name, String date) {
        ListMessage listMessage = list.get(name);
        listMessage.setStartDate(date);
    }
    
    public void setFinishDate(String name, String date) {
        ListMessage listMessage = list.get(name);
        listMessage.setFinishDate(date);
    }
    
    public void setErrorDescription(String name, String date) {
        ListMessage listMessage = list.get(name);
        listMessage.setErrorDescription(date);
    }
    
    public int size() {
        return list.size();
    }

}
