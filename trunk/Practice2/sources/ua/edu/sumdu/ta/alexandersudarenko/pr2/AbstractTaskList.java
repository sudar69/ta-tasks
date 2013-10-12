package ua.edu.sumdu.ta.alexandersudarenko.pr2;

/**
 * abstract class AbstractTaskList
 */
abstract class AbstractTaskList {
    
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
    abstract int size();
    
    /**
     * Getting the task by number.
     */
    abstract Task getTask(int index);

    /**
     * The array of tasks from the list, alarm time which is between from (exclusive) and to (inclusive).
     */
    abstract Task[] incoming(int from, int to);

}