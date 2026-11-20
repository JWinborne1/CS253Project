import java.util.scanner; 

public class DistractionWatchDog {
  String[] flaggedTarget = {};
  Scanner scanner = new scanner(System.in);

  System.out.println("Oi mate, What sites are we wanting to flag?");
  String domain = scanner.nextLine();
  
