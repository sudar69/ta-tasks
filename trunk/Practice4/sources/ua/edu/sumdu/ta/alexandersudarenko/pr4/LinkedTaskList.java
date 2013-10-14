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

    /**
     * Constructor
     */
    public LinkedTaskList() {
        this.element = null;
        this.next = null;
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
     * @return Return size
     */
    public int size() {
        LinkedTaskList temp = this;
        int i = 0;
        while (temp.getElement()!=null) {
            temp = temp.getNext();
            i++;
        }
        return i;
    }
    
    /**
     * @param task Add new Task
     */    
    public void add(Task task) {
        LinkedTaskList temp = this;
        int i = 0;
        while (i < size()) {
            temp = temp.getNext();
            i++;
        }
        temp.setElement(task);
        temp.setNext(new LinkedTaskList());
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
    
    public Task[] incoming(int from, int to) {return null;}

}