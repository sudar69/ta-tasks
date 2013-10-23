package ua.edu.sumdu.ta.alexandersudarenko.pr4;

public class Element {
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
