package ua.edu.sumdu.ta.alexandersudarenko.pr5;

/**
 * abstract class AbstractTaskList
 */
public abstract class AbstractTaskList {

    protected int size = 0;

    /**
     * Adding not a unique the task
     */
    public abstract void add(Task task);
    
    /**
     * Delete all tasks equal input
     */
    public abstract void remove(Task task);
    
    /**
     * Returns the number of tasks in the list.
     */
    public int size() {
        return this.size;
    }
    
    /**
     * Getting the task by number.
     */
    public abstract Task getTask(int index);

    /**
     * The array of tasks from the list, alarm time which is between from (exclusive) and to (inclusive).
     */
    public abstract Task[] incoming(int from, int to);

}