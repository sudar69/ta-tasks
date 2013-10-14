import ua.edu.sumdu.ta.alexandersudarenko.pr4.LinkedTaskList;
import ua.edu.sumdu.ta.alexandersudarenko.pr4.Task;

public class Main {

    public static void main(String[] args) {
        System.out.println("Create LinkedTaskList"); 
        LinkedTaskList taskList = new LinkedTaskList();
        System.out.println("Size LinkedTaskList = " + taskList.size());
        System.out.println("Add 15 Task");
        for (int i = 0; i < 15; i++) taskList.add(new Task("sdads" + i, i));
        System.out.println("Size LinkedTaskList = " + taskList.size());
        System.out.println("Get Task by number");
        for (int i = 0; i < taskList.size(); i++) System.out.println(taskList.getTask(i));
        System.out.println("Delete 3 and 9");
        taskList.remove(taskList.getTask(3));
        taskList.remove(taskList.getTask(9));
        System.out.println("Size LinkedTaskList = " + taskList.size());
        System.out.println("View Task");
        for (int i = 0; i < taskList.size(); i++) System.out.println(taskList.getTask(i));  
    }
    
}