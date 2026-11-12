import java.util.Scanner;

public class ATLAS{
    
    private static LinkedList<Task> taskList = new LinkedList<>();
    public static void main(String[] args) {
       taskList.addBack(new Task(1, "Finish CS253 Project", 20240615, 1, false, 120));
       taskList.addBack(new Task(2, "Prepare for CS253 Exam", 20240620, 2, false, 180));
        
       startUpSequence();
       
       
       
        
    }

    private static void startUpSequence() {
        System.out.println("Starting up A.T.L.A.S...");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name: "); // Later store this in memory for repeat visits
        String name = scanner.nextLine();
        System.out.println("Hello, " + name + " How may I assist a wonderful person like you today!");

        int choice = 0;
        
        do {
            System.out.println("Menu:");
            System.out.println("1. Add New Task");
            System.out.println("2. View Top Recommended Tasks");
            System.out.println("3. View All Tasks");
            System.out.println("4. View Completed Tasks");
            System.out.println("5. Reset A.T.L.A.S");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

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
                        System.out.println("Status: pending. Task left on read. It'll be waiting right here when your done pretendign you don't have a midterm tomorrow. ");
                    }
                    System.out.print("Enter estimated time (in minutes): ");
                    int estimatedTime = scanner.nextInt();
                    Task newTask = new Task(taskList.getSize() + 1, taskName, dueDate, priority, isCompleted, estimatedTime);
                    taskList.addBack(newTask);
                    System.out.println("Task added successfully!");
                    break;
                case 2:
                    System.out.println("2. View Top Recommended Tasks");
                    System.out.println("Top Recommended Tasks:");
                    break;
                case 3:
                    System.out.println("3. View All Tasks");
                    break;
                case 4:
                    System.out.println("4. View Completed Tasks");
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
}