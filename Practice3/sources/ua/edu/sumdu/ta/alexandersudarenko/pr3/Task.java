package ua.edu.sumdu.ta.alexandersudarenko.pr3;

/**
 * public class Task
 */
public class Task {
    
    /**
     * Переменная для храниния имени задачи
     */
    private String title;
    
    /**
     * Переменная для храниния состояния задачи
     */
    private boolean active;

    /**
     * Переменная для храниния времени задачи или времени начала для повторяущейся задачи
     */    
    private int time;
    
    /**
     * Переменная для храниния времени окончания повторяющейся задачи
     */    
    private int end;
    
    /**
     * Переменная для храниния периода повторения
     */    
    private int repeat;
    
    /**
     * Создание неповторяющейся задачи 
     *
     * @param title Имя задачи
     * @param time Время оповещения
     */    
    public Task(String title, int time) {
        this.title = title;
        this.time = time;
        this.active = false;
    }

    /**
     * Создание повторяющейся задачи 
     *
     * @param title Имя задачи
     * @param start Время первого оповещения
     * @param end Время последнего оповещения
     * @param repeat Период повторения оповещения
     */    
    public Task(String title, int start, int end, int repeat) {
        this.title = title;
        this.time = start;
        this.end = end; 
        this.repeat = repeat; 
        this.active = false;
    }

    /**
     * @return Возврашает название задачи
     */     
    public String getTitle() {
        return this.title;
    }

    /**
     * @param title Изменяет название задачи
     */     
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return Возврашает активность задачи
     */      
    public boolean isActive() {
        return this.active;
    }

    /**
     * @param active Изменяет состояние задачи
     */       
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @param time Изменяет время оповещения
     */     
    public void setTime(int time) {
        this.time = time;
        this.end = time;
        this.repeat = 0;
    }

    /**
     * @param start Изменяет время первого оповещения
     * @param end Изменяет время последнего оповещения
     * @param repeat Изменяет период повторения оповещения
     */     
    public void setTime(int start, int end, int repeat) {
        this.time = start;
        this.end = end;
        this.repeat = repeat;
    }

    /**
     * @return Возврашает время первого или единственного оповещения
     */      
    public int getTime() {
        return this.time;
    }

    /**
     * @return Возврашает время первого или единственного оповещения
     */      
    public int getStartTime() {
        return this.time;
    }

    /**
     * @return Возврашает время последнего или единственного оповещения
     */      
    public int getEndTime() {
        if (this.repeat == 0) {
            return this.time;
        } else {
            return this.end;
        }    
    }

    /**
     * @return Возврашает период повторения оповещения
     */      
    public int getRepeatInterval() {
        if (this.repeat == 0) {
            return 0;
        } else {
            return this.repeat;
        }      
    }

    /**
     * @return true - событие повторяется, false - нет
     */      
    public boolean isRepeated() {
        if (this.repeat == 0) {
            return false;
        } else {
            return true;
        }     
    }

    /**
     * @return Возвращает полное название задачи
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
     * @return Возвращающий время следующего оповещения, после указанного времени
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