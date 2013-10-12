import ua.edu.sumdu.ta.alexandersudarenko.pr3.ArrayTaskList;
import ua.edu.sumdu.ta.alexandersudarenko.pr3.Task;

public class Main {

    public static void main(String[] args) {
        ArrayTaskList task = new ArrayTaskList();
        System.out.println(task.size());
		for (int i = 0; i < 15; i++) {
			task.add(new Task("fdsgfd", i));
			System.out.println("size " + task.size() + "_" + task.getTask(i));
		}	
		for (int i = 0; i < task.size(); i++) {
			System.out.println("size " + task.size() + "_" + task.getTask(i));
		}		
		task.remove(new Task("fdsgfd", 10));
        task.remove(new Task("fdsgfd", 5));
		for (int i = 0; i < task.size(); i++) {
			System.out.println("size " + task.size() + "_" + task.getTask(i));
		}
		System.out.println(new Task("fds" + 10, 10));
		System.out.println(task.getTask(10));
		
    }
    
}