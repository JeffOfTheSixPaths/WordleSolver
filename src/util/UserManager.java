package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserManager
{
    private ArrayList<User> groupOfUsers;
    private final String usersFileName = "data/Users.csv";

    public UserManager() { groupOfUsers = new ArrayList<>(); }


    public void addUser(User user) { groupOfUsers.add(user); }

    public void loadUsers() throws IOException
    {
        Scanner scan = null;
        try {
            String line;
            User user;
            scan = new Scanner(new File(usersFileName));
            while (scan.hasNextLine()) {
                line = scan.nextLine();
                user = convertLineToUser(line);
                if(user != null){
                    groupOfUsers.add(user);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading Users file: " + e.getMessage());
        } finally {
            scan.close();
        }
    }


    public User findUser(String userName)
    {
        for(User user : groupOfUsers)
        {
            if(user.getUserName().equals(userName))
            {
                return user;
            }
        }
        return null;
    }

    public boolean isUserExists(String registrationNumber)
    {
        return findUser(registrationNumber) != null;
    }

    public void saveDataToFile()
    {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(usersFileName));
            for(User user : groupOfUsers){
                bw.write(convertUserToLine(user));
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error saving User data to file: " + e.getMessage());
        }
    }

    private User convertLineToUser(String line)
    {
        String[] fields = line.split(",");
        if(fields.length != 6){
            return null;
        }
        return new User(fields[0], fields[1], Integer.parseInt(fields[2]), Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), Double.parseDouble(fields[5]));
    }

    private String convertUserToLine(User user)
    {
        return user.getUserName() + "," + user.getPassword() + "," + user.getAccAge() + "," + user.getSolvedWordles() + "," + user.getHelpedWordles() + "," + user.getCheatedWordles();
    }
}
