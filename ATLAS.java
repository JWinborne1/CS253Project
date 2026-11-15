import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;
@SuppressWarnings("unused")
public class ATLAS{
    
    private static LinkedList<Task> taskList = new LinkedList<>();
    public static void main(String[] args) {
  
        
       startUpSequence();
       
       
       
        
    }

    private static void startUpSequence() {
        System.out.println("Starting up A.T.L.A.S...");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: "); // Later store this in memory for repeat visits
        String name = scanner.nextLine();
        System.out.println("Hello " + name + ", How may I assist a wonderful person like you today!");

        int choice;
        do {
            choice = ATLASMenu(scanner);
            switch (choice) {
                case 1:
                    System.out.println("1. Add New Task");
                    System.out.print("Enter task name: ");
                    String taskName = scanner.nextLine();
                    System.out.print("Enter due date (YYYYMMDD): ");
                    String dueDate = scanner.next();
                    System.out.print("Enter priority (1-5): ");
                    int priority = scanner.nextInt();
                
    
                    System.out.print("Enter estimated time (in minutes): ");
                    int estimatedTime = scanner.nextInt();
                    Task newTask = createTask(taskName, dueDate, priority);
                    taskList.addBack(newTask);
                    System.out.println("Task added successfully!");
                    break;
                case 2:
                    System.out.println("2. View Top Recommended Tasks");
                    listTopRecommendedTasks();
                    break;
                case 3:
                    System.out.println("3. View All Tasks");
                    listAllTasks();
                    break;
                case 4:
                    System.out.println("4. View Completed Tasks");
                    listCompletedTasks();
                    break;
                case 5:
                    System.out.println("5. Reset A.T.L.A.S");
                    reset();
                    break;
                default:
                    System.out.println("Invalid menu option.");
                    break;
            }
            System.out.println();
        } while (choice != 5);

        scanner.close();
       
        }

   

    private static void reset() {
        System.out.println("Resetting A.T.L.A.S...");
        // TO BE IMPLEMENTED
    }

    private static int ATLASMenu(Scanner scanner) {
        System.out.println("\nA.T.L.A.S. Menu");
        System.out.println("1. Add New Task");
        System.out.println("2. View Top Recommended Tasks");
        System.out.println("3. View All Tasks");
        System.out.println("4. View Completed Tasks");
        System.out.println("5. Reset A.T.L.A.S");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
    // Uses simple constructor to create task
    private static Task createTask(String taskName, String dueDate, int priority) {
        Task T = new Task(taskList.getSize() + 1, taskName, dueDate, priority);
        return T;
    }

    
    private static Task customTask(int taskID, String taskName, String dueDate, int priority, boolean isCompleted, long timeLeft){
        Task T = new Task(taskID, taskName, dueDate, priority, isCompleted, timeLeft);
        return T;
    }

    private static void listTopRecommendedTasks() {
        if (taskList.isEmpty()) {
            System.out.println("No recommended tasks available.");
            return;
        }

        orderTasksByPriority();
        System.out.println("Top Recommended Tasks:");
        int limit = Math.min(3, taskList.getSize());
        for (int i = 0; i < limit; i++) {
            Task task = getTaskAtIndex(i);
            System.out.println("Task ID: " + task.getTaskID() + ", Name: " + task.getTaskName());
        }
    }

    private static void orderTasksByPriority() {
        // TO BE IMPLEMENTED
    }

    private static Task getTaskAtIndex(int index) {
        if (index < 0 || index >= taskList.getSize()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + taskList.getSize());
        }

        try {
            Object head = LinkedList.class.getDeclaredField("head").get(taskList);
            Object current = head;
            int currentIndex = 0;

            while (current != null && currentIndex < index) {
                current = current.getClass().getDeclaredField("next").get(current);
                currentIndex++;
            }

            if (current == null) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + taskList.getSize());
            }

            Field dataField = current.getClass().getDeclaredField("data");
            dataField.setAccessible(true);
            return (Task) dataField.get(current);
        } catch (Exception e) {
            try {
                Method getter = taskList.getClass().getMethod("get", int.class);
                return (Task) getter.invoke(taskList, index);
            } catch (Exception ignored) {
                throw new IllegalStateException("Unable to access task at index " + index + ".", e);
            }
        }
    }

    private static void listAllTasks() {
        if (taskList.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("All Tasks:");
            for (int i = 0; i < taskList.getSize(); i++) {
                Task task = getTaskAtIndex(i);
                System.out.println("Task ID: " + task.getTaskID() + ", Name: " + task.getTaskName());
            }
        }
    }
private static void listCompletedTasks() {
    if (taskList.isEmpty()) {
        System.out.println("No completed tasks available.");
    }
}
    
    
}