package ua.edu.sumdu.ta.alexandersudarenko.pr4.tests;

import org.junit.Before;
import ua.edu.sumdu.ta.alexandersudarenko.pr4.*;

public class LinkedTaskListTest extends TaskListTest {

    @Before
    public void before()
    {
        tasks = new LinkedTaskList();
    }
}
