import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Task implements Comparable<Task> {
    private int taskID;
    private String taskName;
    private String dueDate; // Format: YYYYMMDD
    private int priority;
    private boolean isCompleted;
    private int estimatedTime; // in minutes
    LinkedList<Task> taskList = new LinkedList<>();
    DateTimeFormatter format = DateTimeFormatter.BASIC_ISO_DATE;
    

    

public Task(String taskName, String dueDate) {
    this.taskID = getTaskID();
    this.taskName = taskName;
    this.dueDate = dueDate;
    this.priority = getPriority();
    this.isCompleted = false;
    this.timeLeft = getTimeLeft();
}
    
    public int getTaskID() {
        // TO DO: MAKE FORMULA TO GENERATE UNUSED TASK ID
        return taskID;
        }
    public String getTaskName() {
        return taskName;
        
    }
    public String getDueDate() {
        return dueDate;
        
    }
    public int getPriority() {
        // TO DO: MAKE FORUMLA TO CALCULATE PRIORITY
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
        Priority is ranked 0 being the highest priority and numbers after are lower
        @return 0: This task has higher priority; 1: Argument Task has higher priority; 2: They have equal priority
     */
    @Override
    public int compareTo(Task t) {
        if(this.priority < t.priority) return 0;
        if(this.priority == t.priority) return 2;
        return 1;
    }

  
    
    
    
}
