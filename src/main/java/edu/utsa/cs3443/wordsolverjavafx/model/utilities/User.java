package edu.utsa.cs3443.wordsolverjavafx.model.utilities;


public class User
{
    private String userName;
    private String password;

    private int accAge;
    private int solvedWordles;
    private int helpedWordles;
    private double cheatedWordles;

    public User()
    {
        this.userName = "";
        this.password = "";
        this.accAge = 0;
        this.solvedWordles = 0;
        this.helpedWordles = 0;
        this.cheatedWordles = 0.0;
    }

    public User(String name, String password)
    {
        this.userName = name;
        this.password = password;
        this.accAge = 0;
        this.solvedWordles = 0;
        this.helpedWordles = 0;
        this.cheatedWordles = 0.0;
    }

    public User(String name, String password, int accAge, int solvedWordles, int helpedWordles, double cheatedWordles)
    {
        this.userName = name;
        this.password = password;
        this.accAge = accAge;
        this.solvedWordles = solvedWordles;
        this.helpedWordles = helpedWordles;
        this.cheatedWordles = cheatedWordles;
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
    public void setPassword(String name) {this.password = password;}
    public int getAccAge() {return accAge;}
    public void setAccAge(int accAge) {this.accAge = accAge;}
    public int getSolvedWordles() {return solvedWordles;}
    public void setSolvedWordles(int solvedWordles) {this.solvedWordles = solvedWordles;}
    public int getHelpedWordles() {return helpedWordles;}
    public void setHelpedWordles(int helpedWordles) {this.helpedWordles = helpedWordles;}
    public double getCheatedWordles() {updateCheatedWordles(); return cheatedWordles;}
    public void setCheatedWordles(double cheatedWordles) {this.cheatedWordles = cheatedWordles;}
}
