import java.util.Scanner;

public class ATLAS{
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

        int choice = ATLASMenu(scanner);


        switch (choice) {
            case 1:
                System.out.println("Add New Task");
                break;
            case 2:
                System.out.println("View Top Recommended Tasks");
                break;
            case 3:
                System.out.println("View All Tasks");
                break;
            case 4:
                System.out.println("View Completed Tasks");
                break;
            case 5:
                reset();
                break;
            default:
                System.out.println("Invalid menu option.");
                break;
        }

        scanner.close();
    }

    private static int ATLASMenu(Scanner scanner) {
        int option;

        do {
            System.out.println("Choose an option:");
            System.out.println("1. Add New Task");
            System.out.println("2. View Top Recommended Tasks");
            System.out.println("3. View All Tasks");
            System.out.println("4. View Completed Tasks");
            System.out.println("5. Reset");
            System.out.print("Selection: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                System.out.print("Selection: ");
            }

            option = scanner.nextInt();
            scanner.nextLine();
        } while (option < 1 || option > 5);

        return option;
    }

    private static void reset() {
        System.out.println("Resetting A.T.L.A.S...");
        // TO BE IMPLEMENTED
    }
}