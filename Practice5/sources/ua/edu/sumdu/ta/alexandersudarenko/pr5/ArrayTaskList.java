package ua.edu.sumdu.ta.alexandersudarenko.pr5;

/**
 * class ArrayTaskList
 */
public class ArrayTaskList extends AbstractTaskList {
    
    private Task[] arrayList;
    
    /**
     * Create the initial array.
     */
    public ArrayTaskList() {
        this.arrayList = new Task[10];        
    }
    
    /**
     * A method to increase the array by one third.
     */
    private void resizeArrayList() {
        Task[] tempArrayList;
        tempArrayList = new Task[arrayList.length + arrayList.length/3];
        for (int i = 0; i < size(); i++) {
            tempArrayList[i] = arrayList[i];
        }
        arrayList = tempArrayList;
    }

    /**
     * Add the item to the list.
     *
     * @param task a task that will be added to the list.
     */     
    public void add(Task task) {
        if (task == null) throw new NullPointerException("Task is null");
        if (task.getTitle() != "") {
            if (arrayList.length == size()) resizeArrayList();
            arrayList[size()] = task;
            size++;
        }
    }

    /**
     * Remove the item from the List.
     *
     * @param task a task that will be deleted from the list.
     */       
    public void remove(Task task) {
        if (task == null) throw new NullPointerException("Task is null");
        if (task.getTitle() != "") {
            boolean delete = false; 
            for (int i = 0; i < size(); i++) {
                if (arrayList[i].equals(task)) {
                    arrayList[i] = null;               
                    delete = true;
                    size--;
                }  
                if (delete) arrayList[i] = arrayList[i + 1];
            }
        }
    }
    
    /**
     * Returns the task of the index, in the case of an invalid index returns null.
     *
     * @param index index of the task.
     */ 
    public Task getTask(int index) {
        if (index < 0 || index >= size()) throw new RuntimeException("index < 0"); 
        return arrayList[index];
    }
    
    /**
     * The array of tasks from the list, alarm time which is between from (exclusive) and to (inclusive).
     */    
    public Task[] incoming(int from, int to) {        
        int tempSize = 0;
        for (int i = 0; i < size(); i++) {
            if ((arrayList[i].nextTimeAfter(from) != -1) && (arrayList[i].nextTimeAfter(from) <= to)) tempSize++;
        }
        Task[] tempArrayList = new Task[tempSize];
        tempSize = 0;
        for (int i = 0; i < size(); i++) {
            if ((arrayList[i].nextTimeAfter(from) != -1) && (arrayList[i].nextTimeAfter(from) <= to)) {
                tempArrayList[tempSize] = arrayList[i];
                tempSize++;
            }
        }        
        return tempArrayList;
    }

}