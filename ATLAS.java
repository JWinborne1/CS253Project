import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;
import java.util.Comparator;
import java.time.Year;
import java.time.LocalDate;
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
                    String dueDate = "";
                    do{
                        System.out.print("Enter due date (YYYYMMDD): ");
                        dueDate = scanner.next();    
                        if(!checkValidDate(dueDate)) System.out.println("Invlaid Date...\n");
                    }while(!checkValidDate(dueDate));
                    
                    System.out.print("Enter priority (1-5): ");
                    int priority = scanner.nextInt();
                
    
                    Task newTask = createTask(taskName, dueDate, priority);
                    taskList.addBack(newTask);
                    System.out.println("Task added successfully!\n" + newTask.toString());
                    
                    break;
                case 2:
                    System.out.println("2. View Top Recommended Tasks");
                    listTopRecommendedTasks();
                    break;
                case 3:
                    System.out.println("3. View All Tasks");
                    listAllTasks();
                    choice = filter(scanner);
                    if(choice != 7){
                        System.out.println("Filtering...");
                    }

                    break;
                case 4:
                    System.out.println("4. View Completed Tasks");
                    listCompletedTasks();
                    break;
                case 5:
                    System.out.println("0. Quit and Reset A.T.L.A.S");
                    reset();
                    break;
                default:
                    System.out.println("Invalid menu option.");
                    break;
            }
            System.out.println();
        } while (choice != 0);

        scanner.close();
       
        }

   

    private static void reset() {
        System.out.println("Resetting A.T.L.A.S...");
        // TO BE IMPLEMENTED
    }

    private static int filter(Scanner scanner){
        System.out.println("1. By ID (Default)");
        System.out.println("2. By Name");
        System.out.println("3. By Due Date");
        System.out.println("4. By Priority");
        System.out.println("5. By Completed");
        System.out.println("6. By Time Left");
        System.out.println("7. Return");
        System.out.println("0. Quit");
        System.out.print("Choose a filter: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
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
        T.setID(taskList.getSize() + 1);
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
            System.out.println("Task ID: " + task.getID() + ", Name: " + task.getName());
        }
    }

    private static void orderTasksByPriority() {
        MergeSort<Task> m = new MergeSort<>();
        taskList.head = m.sort(taskList.head, Comparator.comparing(Task::getPriority));
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
                System.out.println("Task ID: " + task.getID() + ", Name: " + task.getName());
            }
        }
    }
private static void listCompletedTasks() {
    if (taskList.isEmpty()) {
        System.out.println("No completed tasks available.");
    }
}

private static boolean checkValidDate(String date){

    if (date == null || date.length() != 8) return false;
    for (int i = 0; i < 8; i++) {
        char c = date.charAt(i);
        if (c < '0' || c > '9') return false;
    }

    int year  = Integer.parseInt(date.substring(0, 4));
    int month = Integer.parseInt(date.substring(4, 6));
    int day   = Integer.parseInt(date.substring(6, 8));

    if (month < 1 || month > 12 || day < 1) return false;

    int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int maxDay = daysInMonth[month - 1];
    boolean leap = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    if (month == 2 && leap) maxDay = 29;

    if (day > maxDay) return false;

    LocalDate today = LocalDate.now();
    int todayNum = today.getYear() * 10000
                 + today.getMonthValue() * 100
                 + today.getDayOfMonth();

    return Integer.parseInt(date) >= todayNum;

}


    
    
}