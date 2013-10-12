package ua.edu.sumdu.ta.alexandersudarenko.pr3;

/**
 * abstract class AbstractTaskList
 */
abstract class AbstractTaskList {
    
    /**
     * ���������� �� ���������� ������, ���������� ������ ������ ��������� �����������
     */
    abstract void add(Task task);
    
    /**
     * �������� ���� ����� ������ �������
     */
    abstract void remove(Task task);
    
    /**
     * ���������� ���������� ����� � ������
     */
    abstract int size();
    
    /**
     * ��������� ������ �� ������
     */
    abstract Task getTask(int index);

    /**
     * ������ ����� �� ������, ����� ���������� ������� ��������� ����� from (�������������) � to (������������)
     */
    abstract Task[] incoming(int from, int to);

}