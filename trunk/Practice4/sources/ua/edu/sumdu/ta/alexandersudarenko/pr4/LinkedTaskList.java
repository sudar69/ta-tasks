package ua.edu.sumdu.ta.alexandersudarenko.pr4;

/**
 * class LinkedTaskList
 */
public class LinkedTaskList extends AbstractTaskList {
    
    /**
     * Variable to Task
     */
    private Task element;
    
    /**
     * Variable to next LinkedTaskList
     */
    private LinkedTaskList next;
    
    private LinkedTaskList tail;

    /**
     * Constructor
     */
    public LinkedTaskList() {
        this.element = null;
        this.next = null;
        this.tail = null;
    }
    
    /**
     * @param task Set Task
     */
    private void setElement(Task task) {
        this.element = task;
    } 
    
    /**
     * @param next Set LinkedTaskList
     */
    private void setNext(LinkedTaskList next) {
        this.next = next;
    }     
    
    /**
     * @return Returns Task
     */
    private void setTail(LinkedTaskList tail) {
        this.tail = tail;
    }     

    /**
     * @return Returns Task
     */
    private Task getElement() {
        return this.element;
    } 
        
    /**
     * @return Return next LinkedTaskList
     */
    private LinkedTaskList getNext() {
        return this.next;
    }
        
    /**
     * @return Return next LinkedTaskList
     */
    private LinkedTaskList getTail() {
        return this.tail;
    }
    
    /**
     * @param task Add new Task
     */    
    public void add(Task task) {
        if (getTail() == null) {
            setElement(task);
            setNext(new LinkedTaskList());
            setTail(getNext());
            size++;
        } else {
            getTail().setElement(task);
            getTail().setNext(new LinkedTaskList());
            setTail(getTail().getNext());
            size++;
        }  
    }
    
    /**
     * @param task Remove Task
     */    
    public void remove(Task task) {
        LinkedTaskList temp = this;
        int i = 0;
        while (i < size()) {
            if (temp.getElement().equals(task)) {
                temp.setElement(temp.getNext().getElement());
                temp.setNext(temp.getNext().getNext());
                size--;
            } else {
                temp = temp.getNext();
                i++;
            }
        }       
    }
    
    /**
     * @param index Index LinkedTaskList
     * @return Task
     */    
    public Task getTask(int index) {
        if (index >= 0 && index < size()) {
            LinkedTaskList temp = this;
            int i = 0;
            while (i < index) {
                temp = temp.getNext();
                i++;
            }
            return temp.getElement();
        }
        return null;
    }
    
    /**
     * The array of tasks from the list, alarm time which is between from (exclusive) and to (inclusive).
     */ 
    public Task[] incoming(int from, int to) {
        LinkedTaskList temp = this;
        int tempSize = 0;
        int i = 0;
        while (i < size()) {
            if ((temp.getElement().nextTimeAfter(from) != -1) && 
              (temp.getElement().nextTimeAfter(from) <= to)) tempSize++;
            temp = temp.getNext();
            i++;
        }
        Task[] tempArrayList = new Task[tempSize];
        tempSize = 0;
        temp = this;
        i = 0;
        while (i < size()) {
            if ((temp.getElement().nextTimeAfter(from) != -1) && (temp.getElement().nextTimeAfter(from) <= to)) {
                tempArrayList[tempSize] = temp.getElement();
                tempSize++;
            }    
            temp = temp.getNext();
            i++;
        }        
        return tempArrayList;
    }

}