package ua.edu.sumdu.ta.alexandersudarenko.pr2;

/**
 * public class Task
 */
public class Task {
    
    /**
     * Variable to store the name of the task
     */
    private String title;
    
    /**
     * Variable to store the state the task
     */
    private boolean active;

    /**
     * Variable to store the time of the task or the start time for the recurring task
     */    
    private int time;
    
    /**
     * Variable to hold the end of a recurring task
     */    
    private int end;
    
    /**
     * Variable to store the return period
     */    
    private int repeat;
    
    /**
     * Creating a recurring the task
     *
     * @param title Name of the task
     * @param time time alerts
     */    
    public Task(String title, int time) {
        this.setTitle(title);
        this.setTime(time);
        this.setActive(false);
    }

    /**
     * Creating a recurring task
     *
     * @param title Name of the task
     * @param start Time of first alert
     * @param end The last time the alert
     * @param repeat The repetition period of alert
     */    
    public Task(String title, int start, int end, int repeat) {
        this.setTitle(title);
        this.setTime(start, end, repeat);
        this.setActive(false);
    }

    /**
     * @return Returns the name of the task
     */     
    public String getTitle() {
        return this.title;
    }

    /**
     * @param title Changes the name of the task
     */     
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return Returns the active the task
     */      
    public boolean isActive() {
        return this.active;
    }

    /**
     * @param active Changes the status of a task
     */       
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @param time Changes the alarm time
     */     
    public void setTime(int time) {
        this.time = time;
        this.end = time;
        this.repeat = 0;
    }

    /**
     * @param start Changes the time of the first alert
     * @param end Changes the time of the last alert
     * @param repeat Changes the repeat period alert
     */     
    public void setTime(int start, int end, int repeat) {
        this.time = start;
        this.end = end;
        this.repeat = repeat;
    }

    /**
     * @return Returns the time the first and only alert
     */      
    public int getTime() {
        return this.time;
    }

    /**
     * @return Returns the time the first and only alert
     */      
    public int getStartTime() {
        return this.time;
    }

    /**
     * @return Returns the time of the last or only alert
     */      
    public int getEndTime() {
        if (this.repeat == 0) {
            return this.time;
        } else {
            return this.end;
        }    
    }

    /**
     * @return Returns the repetition period of alert
     */      
    public int getRepeatInterval() {
        if (this.repeat == 0) {
            return 0;
        } else {
            return this.repeat;
        }      
    }

    /**
     * @return true - event repeats, false - no
     */      
    public boolean isRepeated() {
        if (this.repeat == 0) {
            return false;
        } else {
            return true;
        }     
    }

    /**
     * @return Returns the full name of the task
     */ 
    @Override     
    public String toString() {
        if (this.repeat == 0) {
            return "Task \"" + this.title + "\" " + this.active + " at " + this.time;
        } else {
            return "Task \"" + this.title + "\" from " + this.time + " to " + this.end +
                " every " + this.repeat + " s";
        }         
    }
    
    /**
     * @return Returns the next time the alert after a specified time
     */ 
    public int nextTimeAfter(int time) {
        if (this.active) {
            if (time < this.time) {
                return this.time;
            } else {
                if (time < this.end) {
                    int temp = this.time;
                    while (temp <= time) {
                        temp += this.repeat;
                    }
                    if (temp > this.end) {
                        return -1;
                    }
                    return temp;
                } else {
                    return -1;
                } 
            }
        } else {
            return -1;
        }
    }
}