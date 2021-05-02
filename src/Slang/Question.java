package Slang;

public class Question {
    private String ques;
    private String[] ans;
    private int rightAns;
    public String getQues() {
        return ques;
    }
    public void setQues(String ques) {
        this.ques = ques;
    }
    public String[] getAns() {
        return ans;
    }
    public void setAns(String[] ans) {
        this.ans = ans;
    }
    public int getRightAns() {
        return rightAns;
    }
    public void setRightAns(int rightAns) {
        this.rightAns = rightAns;
    }
    
    public Question(String ques, String[] ans, int rightAns) {
        this.ques = ques;
        this.ans = ans;
        this.rightAns = rightAns;
    }
}
