import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Task {
    private int ID;
    private String name;
    private String dueDate; // Format: YYYYMMDD
    private int priority;
    private boolean isCompleted;
    private long timeLeft; // in minutes
    LinkedList<Task> taskList = new LinkedList<>();
    DateTimeFormatter format = DateTimeFormatter.BASIC_ISO_DATE;
    
// Main constructor with explicit parameters
public Task(int taskID, String taskName, String dueDate, int priority, boolean isCompleted, long timeLeft){
    this.ID = taskID;
    this.name = taskName;
    this.dueDate = dueDate;
    this.priority = priority;
    this.isCompleted = isCompleted;
    this.timeLeft = timeLeft;
}

// Simplified constructor that only takes four paraneters and assumes the other fields
public Task(int taskID, String taskName, String dueDate, int priority) {
    this.ID = getID();
    this.name = taskName;
    this.dueDate = dueDate;
    this.priority = priority;
    this.isCompleted = false;
    this.timeLeft = getTimeLeft();
}
    
    public int getID() {
        return ID;
        }
    public String getName() {
        return name;
        
    }
    public String getDueDate() {
        return dueDate;
        
    }
    public int getPriority() {
        return priority;
        
    }
    public boolean isCompleted() {
        return isCompleted;
        
    }
    public long getTimeLeft() {
        LocalDate time = LocalDate.now();
        String T = time.toString();
        timeLeft = ChronoUnit.DAYS.between(LocalDate.parse(T.replace("-", ""), format), LocalDate.parse(this.dueDate, format));
        return timeLeft;
    }

    public void setID(int id){
        ID = id;
    }

    public void setIsCompleted(boolean c){
        isCompleted = c;
    }

    public String toString(){
        String 
        taskDesc  = "\nID: " + ID;
        taskDesc += "\nTask: " + name;
        taskDesc += "\nDue: " + dueDate;
        taskDesc += "\nPriority: " + priority;
        taskDesc += "\nTime Left: " + timeLeft + " days";
        taskDesc += "\nComplete: " + isCompleted;
        return taskDesc;

    }



    

  
    
    
    
}
