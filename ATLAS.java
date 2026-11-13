import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

public class ATLAS{
    
    private static LinkedList<Task> taskList = new LinkedList<>();
    public static void main(String[] args) {
  
        
       startUpSequence();
       
       
       
        
    }

    private static void startUpSequence() {
        System.out.println("Starting up A.T.L.A.S...");
        Scanner scanner = new Scanner(System.in);
        Task task = new Task("Hi", "20261201");
        System.out.println(task.getTimeLeft());
        System.out.println("Enter your name: "); // Later store this in memory for repeat visits
        String name = scanner.nextLine();
        System.out.println("Hello, " + name + " How may I assist a wonderful person like you today!");

        int choice;
        do {
            choice = ATLASMenu(scanner);
            switch (choice) {
                case 1:
                    System.out.println("1. Add New Task");
                    System.out.print("Enter task name: ");
                    String taskName = scanner.nextLine();
                    System.out.print("Enter due date (YYYYMMDD): ");
                    int dueDate = scanner.nextInt();
                    System.out.print("Enter priority (1-5): ");
                    int priority = scanner.nextInt();
                    System.out.print("Is the task completed? (true/false): ");
                    boolean isCompleted = scanner.nextBoolean();
                    if (isCompleted) {
                        System.out.println("Status: Complete and removed from queue. Congrats, makes us proud!");
                    } else {
                        System.out.println("Status: pending. Task left on read. It'll be waiting right here when your done pretending you don't have a midterm tomorrow. ");
                    }
                    System.out.print("Enter estimated time (in minutes): ");
                    int estimatedTime = scanner.nextInt();
                    Task newTask = createTask(taskList.getSize() + 1, taskName, dueDate, priority, isCompleted, estimatedTime);
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

    private static Task createTask(int taskID, String taskName, int dueDate, int priority, boolean isCompleted, int estimatedTime) {
        try {
            return Task.class.getDeclaredConstructor(int.class, String.class, int.class, int.class, boolean.class, int.class)
                    .newInstance(taskID, taskName, dueDate, priority, isCompleted, estimatedTime);
        } catch (Exception e) {
            try {
                return Task.class.getDeclaredConstructor(String.class, String.class)
                        .newInstance(taskName, String.valueOf(dueDate));
            } catch (Exception ignored) {
                System.out.println("Unable to create Task with the expected constructor. Falling back to a basic task instance.");
                return new Task(taskName, String.valueOf(dueDate));
            }
        }
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