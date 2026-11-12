public class Task {
    private int taskID;
    private String taskName;
    private int dueDate; // Format: YYYYMMDD
    private int priority;
    private boolean isCompleted;
    private int estimatedTime; // in minutes
    
    

public Task(int taskID, String taskName, int dueDate, int priority, boolean isCompleted, int estimatedTime) {
    this.taskID = taskID;
    this.taskName = taskName;
    this.dueDate = dueDate;
    this.priority = priority;
    this.isCompleted = isCompleted;
    this.estimatedTime = estimatedTime;
}
    
    public int getTaskID() {
        return taskID;
        }
    public String getTaskName() {
        return taskName;
        
    }
    public int getDueDate() {
        return dueDate;
        
    }
    public int getPriority() {
        return priority;
        
    }
    public boolean isCompleted() {
        return isCompleted;
        
    }
    public int getEstimatedTime() {
        return estimatedTime;
        
    }
    
    
    
}
