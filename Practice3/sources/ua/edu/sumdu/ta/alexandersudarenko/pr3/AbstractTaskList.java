package ua.edu.sumdu.ta.alexandersudarenko.pr3;

/**
 * abstract class AbstractTaskList
 */
abstract class AbstractTaskList {
    
    /**
     * Добавление не уникальной задачи, добавление пустой задачи запретить реализацией
     */
    abstract void add(Task task);
    
    /**
     * Удаление всех задач равных входной
     */
    abstract void remove(Task task);
    
    /**
     * Возвращает количество задач в списке
     */
    abstract int size();
    
    /**
     * Получение задачи по номеру
     */
    abstract Task getTask(int index);

    /**
     * Массив задач из списка, время оповещения которых находится между from (исключительно) и to (включительно)
     */
    abstract Task[] incoming(int from, int to);

}