package ua.edu.sumdu.ta.alexandersudarenko.pr3;

/**
 * abstract class ArrayTaskList
 */
public class ArrayTaskList extends AbstractTaskList {
    
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
		tempArrayList = new Task[size() + size()/3];
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
        if (task.getTitle() != "") {
            if (arrayList.length == size()) resizeArrayList();
            arrayList[size()] = task;
        }
	}
	
    /**
     * Remove the item from the List.
     *
     * @param task a task that will be deleted from the list.
     */       
	public void remove(Task task) {
        if (task.getTitle() != "") {
            for (int i = 0; i < size(); i++) {
                if (arrayList[i].getTitle() == task.getTitle() &&
                        arrayList[i].isActive() == task.isActive() &&
                        arrayList[i].getTime() == task.getTime() &&
                        arrayList[i].getEndTime() == task.getEndTime() &&
                        arrayList[i].getRepeatInterval() == task.getRepeatInterval()) 
                arrayList[i] = null;
            }
            for (int i = 0; i < arrayList.length - 1; i++) {
                for (int j = 0; j < arrayList.length - 1 - i; j++) {
                    Task tempTask;
                    if (arrayList[j] == null) {
                        tempTask = arrayList[j + 1];
                        arrayList[j + 1] = arrayList[j];
                        arrayList[j] = tempTask;
                    }
                }
            }
        }
	}
    
    /**
     * Returns the task of the index, in the case of an invalid index returns null.
     *
     * @param index index of the task.
     */ 	
	public Task getTask(int index) {
		if (index >= 0 && index < size()) return arrayList[index];
		return null;
	}

}