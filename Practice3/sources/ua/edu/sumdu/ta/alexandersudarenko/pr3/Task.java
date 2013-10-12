package ua.edu.sumdu.ta.alexandersudarenko.pr3;

/**
 * public class Task
 */
public class Task {
    
    /**
     * ���������� ��� �������� ����� ������
     */
    private String title;
    
    /**
     * ���������� ��� �������� ��������� ������
     */
    private boolean active;

    /**
     * ���������� ��� �������� ������� ������ ��� ������� ������ ��� ������������� ������
     */    
    private int time;
    
    /**
     * ���������� ��� �������� ������� ��������� ������������� ������
     */    
    private int end;
    
    /**
     * ���������� ��� �������� ������� ����������
     */    
    private int repeat;
    
    /**
     * �������� ��������������� ������ 
     *
     * @param title ��� ������
     * @param time ����� ����������
     */    
    public Task(String title, int time) {
        this.title = title;
        this.time = time;
        this.active = false;
    }

    /**
     * �������� ������������� ������ 
     *
     * @param title ��� ������
     * @param start ����� ������� ����������
     * @param end ����� ���������� ����������
     * @param repeat ������ ���������� ����������
     */    
    public Task(String title, int start, int end, int repeat) {
        this.title = title;
        this.time = start;
        this.end = end; 
        this.repeat = repeat; 
        this.active = false;
    }

    /**
     * @return ���������� �������� ������
     */     
    public String getTitle() {
        return this.title;
    }

    /**
     * @param title �������� �������� ������
     */     
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return ���������� ���������� ������
     */      
    public boolean isActive() {
        return this.active;
    }

    /**
     * @param active �������� ��������� ������
     */       
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @param time �������� ����� ����������
     */     
    public void setTime(int time) {
        this.time = time;
        this.end = time;
        this.repeat = 0;
    }

    /**
     * @param start �������� ����� ������� ����������
     * @param end �������� ����� ���������� ����������
     * @param repeat �������� ������ ���������� ����������
     */     
    public void setTime(int start, int end, int repeat) {
        this.time = start;
        this.end = end;
        this.repeat = repeat;
    }

    /**
     * @return ���������� ����� ������� ��� ������������� ����������
     */      
    public int getTime() {
        return this.time;
    }

    /**
     * @return ���������� ����� ������� ��� ������������� ����������
     */      
    public int getStartTime() {
        return this.time;
    }

    /**
     * @return ���������� ����� ���������� ��� ������������� ����������
     */      
    public int getEndTime() {
        if (this.repeat == 0) {
            return this.time;
        } else {
            return this.end;
        }    
    }

    /**
     * @return ���������� ������ ���������� ����������
     */      
    public int getRepeatInterval() {
        if (this.repeat == 0) {
            return 0;
        } else {
            return this.repeat;
        }      
    }

    /**
     * @return true - ������� �����������, false - ���
     */      
    public boolean isRepeated() {
        if (this.repeat == 0) {
            return false;
        } else {
            return true;
        }     
    }

    /**
     * @return ���������� ������ �������� ������
     */      
    public String toString() {
        if (this.repeat == 0) {
            return "Task \"" + this.title + "\" " + this.active + " at " + this.time;
        } else {
            return "Task \"" + this.title + "\" from " + this.time + " to " + this.end +
                " every " + this.repeat + " s";
        }         
    }
    
    /**
     * @return ������������ ����� ���������� ����������, ����� ���������� �������
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