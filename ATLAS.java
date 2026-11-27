import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.time.Year;
import java.time.LocalDate;
import java.util.function.Predicate;
@SuppressWarnings("unused")
public class ATLAS{
    
    private static LinkedList<Task> taskList = new LinkedList<>();
    public static void main(String[] args) {
  
        initializeMemoryTasks();
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
                    System.out.println("Adding a new task for you...");
                    System.out.print("Please Enter the task name: ");
                    String taskName = scanner.nextLine();
                    String dueDate = "";
                    do{
                        System.out.print("Enter due date (YYYYMMDD): ");
                        dueDate = scanner.next();    
                        if(!checkValidDate(dueDate)) System.out.println("Sorry, that is not a valid date...\nEnter Another");
                    }while(!checkValidDate(dueDate));
                    
                    System.out.print("How important is this task? Priority (1[High]-5[Low]): ");
                    int priority = scanner.nextInt();
                
                    Task newTask = createTask(taskName, dueDate, priority);
                    taskList.addBack(newTask);
                    System.out.println("Task has been added successfully!\n" + newTask.toString());
                    
                    break;
                case 2:
                    System.out.println("These are your current high priority tasks");
                    if(!listTasksByFilter(task -> task.getPriority() < 2, Comparator.comparing(Task::getPriority))) System.out.println("No High priority tasks, You're good for now...");
                    
                    break;
                case 3:
                    System.out.println("3. View All Tasks");
                    orderTasksByFilter(Comparator.comparing(Task::getID));
                    listAllTasks();
                    System.out.println();
                        do{
                            choice = filter(scanner);
                            switch(choice){
                                case 1:
                                    System.out.println("Sorting...");
                                    orderTasksByFilter(Comparator.comparing(Task::getID));
                                    listAllTasks();
                                    System.out.println("\nSorted by: ID");
                                    break;
                                case 2:
                                    System.out.println("Sorting...");
                                    orderTasksByFilter(Comparator.comparing(Task::getName));
                                    listAllTasks();
                                    System.out.println("\nSorted by: Name");
                                    break;
                                case 3:
                                    System.out.println("Sorting...");
                                    orderTasksByFilter(Comparator.comparing(Task::getPriority));
                                    listAllTasks();
                                    System.out.println("\nSorted by: Priority");
                                    break;
                                case 4:
                                    System.out.println("Sorting...");
                                    orderTasksByFilter(Comparator.comparing(Task::getTimeLeft));
                                    listAllTasks();
                                    System.out.println("\nSorted by: TimeLeft");
                                    break;
                                case 5:
                                    System.out.println("Returning to Main Menu...");
                                    break;
                                case 0:
                                    break;
                                default:
                                    System.out.println("Invalid Filter!");
                                    
                            }
                        }while(choice != 0 && choice != 5);
                    

                    break;
                case 4:
                    System.out.println("4. View Completed Tasks");
                    if(!listTasksByFilter(Task::isCompleted, Comparator.comparing(Task::isCompleted))) System.out.println("You have completed no tasks!, GET TO WORK!");
                    break;
                case 0:
                    System.out.println("0. Quit and Reset A.T.L.A.S");
                    reset();
                    break;
                default:
                    System.out.println("Invalid menu option.");
                    
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
        System.out.println("3. By Priority");
        System.out.println("4. By Time Left");
        System.out.println("5. Return");
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
        System.out.println("0. Reset A.T.L.A.S");
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


    private static void orderTasksByFilter(Comparator<Task> c){
        MergeSort<Task> m = new MergeSort<>();
        taskList.head = m.sort(taskList.head, c);
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
            System.out.println("\nAll Tasks:");
            for (int i = 0; i < taskList.getSize(); i++) {
                Task task = getTaskAtIndex(i);
                System.out.println(task.toString());
            }
        }
    }

    
    private static boolean listTasksByFilter(Predicate<Task> filter, Comparator<Task> c) {
        if (taskList.isEmpty()) {
            System.out.println("No tasks available.");
            return false;
        }

        ArrayList<Task> filteredTasks = new ArrayList<>();
        
        for(int i = 0; i < taskList.getSize(); i++){
            Task task = getTaskAtIndex(i);
            if(filter.test(task)){
                filteredTasks.add(task);
            }
        }

        if(filteredTasks.isEmpty()){
            return false;
        }

        filteredTasks.sort(c);

        for(Task task : filteredTasks){
            System.out.println(task.toString());
        }
        return true;
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

private static void initializeMemoryTasks(){
    // For now just initilizes random tasks to taskList for testing

    
    taskList.addBack(createTask("Go for a walk", "20261201", 3));
    taskList.addBack(createTask("Finish Java assignment", "20261125", 5));
    taskList.addBack(createTask("Study for Data Structures exam", "20261127", 5));
    taskList.addBack(createTask("Clean bedroom", "20261126", 2));
    taskList.addBack(createTask("Buy groceries", "20261124", 3));
    taskList.addBack(createTask("Read 20 pages", "20261128", 2));
    taskList.addBack(createTask("Complete geology lab", "20261129", 4));
    taskList.addBack(createTask("Review merge sort", "20261124", 5));
    taskList.addBack(createTask("Organize desktop files", "20261203", 1));
    taskList.addBack(createTask("Do laundry", "20261125", 2));
    taskList.addBack(createTask("Attend boxing practice", "20261126", 4));
    taskList.addBack(createTask("Finish research paper", "20261205", 5));
    taskList.addBack(createTask("Call mechanic", "20261130", 3));
    taskList.addBack(createTask("Reply to emails", "20261124", 2));
    taskList.addBack(createTask("Work on group presentation", "20261202", 4));
    taskList.addBack(createTask("Water plants", "20261204", 1));
    taskList.addBack(createTask("Review AVL trees", "20261128", 5));
    taskList.addBack(createTask("Clean car", "20261206", 2));
    taskList.addBack(createTask("Submit scholarship application", "20261127", 5));
    taskList.addBack(createTask("Practice coding for 1 hour", "20261129", 4));
    taskList.addBack(createTask("Watch lecture recording", "20261126", 3));
    taskList.addBack(createTask("Organize backpack", "20261201", 1));
    taskList.addBack(createTask("Finish reading assignment", "20261130", 4));
    taskList.addBack(createTask("Meal prep for the week", "20261207", 3));
    taskList.addBack(createTask("Pay phone bill", "20261128", 5));
    taskList.addBack(createTask("Go rock climbing", "20261202", 4));
    taskList.addBack(createTask("Update resume", "20261204", 3));
    taskList.addBack(createTask("Backup computer files", "20261208", 2));
    taskList.addBack(createTask("Review Java generics", "20261125", 4));
    taskList.addBack(createTask("Plan weekend activities", "20261206", 1));

}


    
    
}