package ua.edu.sumdu.ta.alexandersudarenko.pr5.tests;

import org.junit.Before;
import ua.edu.sumdu.ta.alexandersudarenko.pr5.*;

public class LinkedTaskListTest extends TaskListTest {
    @Before 
    public void createTaskList() {
        tasks = new LinkedTaskList();
    }
}

