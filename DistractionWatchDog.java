import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner; 

public class DistractionWatchDog {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    ArrayList<String> flaggedTargets = new ArrayList<>();

  }

  public addFlaggedTargets(String rawInput) {
    System.out.println("Oi mate,What sites are we wanting to flag?");
    System.out.println("Enter domains seperated by commas: ");
    
    String input = scanner.nextLine();
    String[] domains = input.split(",");

    if(domains.isEmpty() ) {
      System.out.println("Bit tough to watch for distractions with zero domains-pop at least one in.");
      return;
    }
    scanner.close();
    System.out.println("Flagged domain: " + domain)
    
  }
  public addTarget(String target) {
    
    }

  public findMatch(String currentTabOrTopic) {
    if(currentTabOrTopic.isEmpty() || flaggedTargets.isEmpty()) {
      return;

      regularText = currentTabOrTopic.lowerCase().trim();

    for( String = domains in flaggedTargets) {
      if (domains != isEmpty() ) {
    }
    public isDistraction(currentTabOrTopic) {
      matched = findMatch(currentTabOrTopic);
      if(matched != null) {
        return true;
      } else {
        return false;
      }
    }

  public buildUKCallout(String matchedTarget, String activeTask) {
    if(matchedTarget.isEmpty()) {
      imaginaryBreakTime = "that distraction";
    } else {
      imaginaryBreakTime = matchedTarget.trim();
    }
    if(activeTask.isEmpty() ) {
      taskLabel = "Your current Task"
        } else {
      taskLabel = activeTask.trim();
    }
    System.out.println("Oi, " + imaginaryBreakTime + "?! Bit cheeky while your supposed to be on" + taskLabel + " get back to work!!!!"
    
  public canTriggerAlert(String matchedTarget, long currentTimeMs) {
      int cooldownDurationMs = 15000;
      String[][] lastAlertByTarget = [8][9];
      if(matchedTarget.isEmpty()) {
        return false;
      } 
      key = matchedTarget.lowercase().trim();
      previousAlertTime = 
      
  }
}

  
