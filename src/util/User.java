package util;

public class User
{
    private String userName;
    private String password;

    private int solvedWordles;
    private int helpedWordles;
    private double cheatedWordles;
    private int failedWordles;

    public User()
    {
        this.userName = "";
        this.password = "";
        this.solvedWordles = 0;
        this.helpedWordles = 0;
        this.cheatedWordles = 0.0;
        this.failedWordles = 0;
    }

    public User(String name, String password)
    {
        this.userName = name;
        this.password = password;
        this.solvedWordles = 0;
        this.helpedWordles = 0;
        this.cheatedWordles = 0.0;
    }

    public User(String name, String password, int solvedWordles, int helpedWordles, double cheatedWordles, int failedWordles)
    {
        this.userName = name;
        this.password = password;
        this.solvedWordles = solvedWordles;
        this.helpedWordles = helpedWordles;
        this.cheatedWordles = cheatedWordles;
        this.failedWordles = failedWordles;
    }


    public void incrementSolvedWordles()
    {
        this.solvedWordles += 1;
        updateCheatedWordles();
    }

    public void incrementHelpedWordles()
    {
        this.helpedWordles += 1;
        incrementSolvedWordles();
        updateCheatedWordles();
    }

    private void updateCheatedWordles()
    {
        if(solvedWordles == 0)
            cheatedWordles = 0.0;
        else
            cheatedWordles = ((double)helpedWordles / (double)solvedWordles);
    }






    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public int getSolvedWordles() {return solvedWordles;}
    public void setSolvedWordles(int solvedWordles) {this.solvedWordles = solvedWordles;}
    public int getHelpedWordles() {return helpedWordles;}
    public void setHelpedWordles(int helpedWordles) {this.helpedWordles = helpedWordles;}
    public double getCheatedWordles() {updateCheatedWordles(); return cheatedWordles;}
    public void setCheatedWordles(double cheatedWordles) {this.cheatedWordles = cheatedWordles;}
    public int getFailedWordles() { return failedWordles; }
    public void incrementFailedWordles() { this.failedWordles++; }
}
