public class Reward {
    private int rewardID;
    private String rewardName;
    private int pointsRequired;
    private int rewardAccount;
    
    public Reward(int rewardID, String rewardName, int pointsRequired) {
        this.rewardID = rewardID;
        this.rewardName = rewardName;
        this.pointsRequired = pointsRequired;
    }
    
    public int getRewardID() {
        return rewardID;
        
    }
    public String getRewardName() {
        return rewardName;
        
    }
    public int getPointsRequired() {
        return pointsRequired;
}

    public int getRewardAccount() {
        return rewardAccount;
    }

    public void setRewardAccount(int rewardAccount) {
        this.rewardAccount = rewardAccount;
    }

}
