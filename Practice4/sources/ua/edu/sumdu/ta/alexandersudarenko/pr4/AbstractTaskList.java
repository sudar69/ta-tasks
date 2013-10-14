package ua.edu.sumdu.ta.alexandersudarenko.pr4;

/**
 * abstract class AbstractTaskList
 */
abstract class AbstractTaskList {

    protected Task[] arrayList;

    /**
     * Adding not a unique the task
     */
    abstract void add(Task task);
    
    /**
     * Delete all tasks equal input
     */
    abstract void remove(Task task);
    
    /**
     * Returns the number of tasks in the list.
     */
    public int size() {
        int sum = 0;
        for (int i = 0; i < arrayList.length; i++) {
            if (arrayList[i] != null) sum++;
        }
        return sum;
    }
    
    /**
     * Getting the task by number.
     */
    abstract Task getTask(int index);

    /**
     * The array of tasks from the list, alarm time which is between from (exclusive) and to (inclusive).
     */
    abstract Task[] incoming(int from, int to);

}