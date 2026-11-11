import java.util.Scanner;

public class ATLAS{
    public static void main(String[] args) {
        
       startUpSequence();
        
        
    }

    private static void startUpSequence() {
        System.out.println("Starting up A.T.L.A.S...");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name: "); // Later store this in memory for repeat visits
        String name = scanner.nextLine();
        System.out.println("Hello, " + name + "!");




        scanner.close();
    }

    private static void reset(){
        // TO BE IMPLEMENTED
    }
}