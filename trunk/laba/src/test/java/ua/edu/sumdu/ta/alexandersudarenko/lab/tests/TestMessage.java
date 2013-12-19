package ua.edu.sumdu.ta.alexandersudarenko.lab.tests;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class TestMessage {
    
    private String description;
    
    private int status = 0;
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @XmlElement(name="Description")
    public String getDescription() {
        return this.description;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    @XmlElement(name="Status")
    public int getStatus() {
        return this.status;
    }

}
