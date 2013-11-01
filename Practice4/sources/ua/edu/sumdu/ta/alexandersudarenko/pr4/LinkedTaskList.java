package ua.edu.sumdu.ta.alexandersudarenko.pr4;

/**
 * class LinkedTaskList
 */
public class LinkedTaskList extends AbstractTaskList {
    
    /**
     * Variable to Task
     */
    private Element element = null;
    private Element tail = null;
    
    /**
     * @param task Add new Task
     */    
    public void add(Task task) {
		boolean unique = true;
        for (int i = 0; i < size(); i++) {
            if (getTask(i).equals(task)) unique = false;
        }
        if (unique) {
			Element temp = new Element();
			temp.setElement(task);
			if (size() == 0) {
				temp.setNext(element);
				element = temp;
				tail = temp;
				size++;
			} else {
				tail.setNext(temp);
				tail = temp;
				size++;
			}
		}
    }
    
    /**
     * @param task Remove Task
     */    
    public void remove(Task task) {
        int i = 0;
        Element temp = element;
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
            Element temp = element;
            while (index > 0) {
                temp = temp.getNext();
                index--;
            }
            return temp.getElement();
        }
        return null;
    }
    
    /**
     * The array of tasks from the list, alarm time which is between from (exclusive) and to (inclusive).
     */ 
    public Task[] incoming(int from, int to) {
        Element temp = element;
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
        temp = element;
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

    
    private class Element {
        private Element next = null;
        private Task element = null;

        /**
         * @param task Set Task
         */
        public void setElement(Task task) {
            this.element = task;
        } 
        
        /**
         * @param next Set Element
         */
        public void setNext(Element next) {
            this.next = next;
        }         

        /**
         * @return Returns Task
         */
        public Task getElement() {
            return this.element;
        } 
            
        /**
         * @return Return next Element
         */
        public Element getNext() {
            return this.next;
        }
    }    
    
}