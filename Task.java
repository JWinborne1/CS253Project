import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Task implements Comparable<Task> {
    private int taskID;
    private String taskName;
    private String dueDate; // Format: YYYYMMDD
    private int priority;
    private boolean isCompleted;
    private long timeLeft; // in minutes
    LinkedList<Task> taskList = new LinkedList<>();
    DateTimeFormatter format = DateTimeFormatter.BASIC_ISO_DATE;
    
// Main constructor with explicit parameters
public Task(int taskID, String taskName, String dueDate, int priority, boolean isCompleted, long timeLeft){
    this.taskID = taskID;
    this.taskName = taskName;
    this.dueDate = dueDate;
    this.priority = priority;
    this.isCompleted = isCompleted;
    this.timeLeft = timeLeft;
}

// Simplified constructor that only takes four paraneters and assumes the other fields
public Task(int taskID, String taskName, String dueDate, int priority) {
    this.taskID = getTaskID();
    this.taskName = taskName;
    this.dueDate = dueDate;
    this.priority = priority;
    this.isCompleted = false;
    this.timeLeft = getTimeLeft();
}
    
    public int getTaskID() {
        return taskID;
        }
    public String getTaskName() {
        return taskName;
        
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

    /*
        Priority is ranked 1 being the highest priority and numbers after are lower
        @return 0: This task has higher priority; -1: Argument Task has higher priority; 1: They have equal priority
     */
    @Override
    public int compareTo(Task t) {
        if(this.priority > t.priority) return 0;
        if(this.priority < t.priority) return -1;
        return 1;
    }

  
    
    
    
}
