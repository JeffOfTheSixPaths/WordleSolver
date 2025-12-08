package util;

import javafx.scene.control.Alert;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager
{
//    public UserManager UserManager; //Not necessary
    private List<User> users = new ArrayList<>();
    private static final String USER_FILE = "/data/Users.csv";
    private static UserManager instance;
    private User currentUser;


    public User getCurrentUser() {
        return currentUser;
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public UserManager() {
        loadUsers();
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public boolean addUser(String username, String password) {
        for (User u : users) {
            if (u.getUserName().equals(username)) return false;
        }

        User newUser = new User(username, password);
        users.add(newUser);
        saveDataToFile();
        
        return true;
    }

    public boolean addUser(User u) {
        return addUser(u.getUserName(), u.getPassword());
    }

    public void loadUsers() {
        users.clear();

        try (InputStream is = getClass().getResourceAsStream(USER_FILE)) {
            if (is == null) {
                System.out.println("USER FILE NOT FOUND: " + USER_FILE);
                return;
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String line;
                User u;
                while ((line = br.readLine()) != null) {
                    u = convertLineToUser(line);
                    if(u != null){
                        users.add(u);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading users: " + e.getMessage());
        }

        System.out.println("Loaded users: " + users.size());
        for (User u : users) {
            System.out.println("User: " + u.getUserName() + ", " + u.getPassword());
        }
    }

        public boolean validateLogin(String username, String password) {
            for (User u : users) {
                if (u.getUserName().equals(username) &&
                        u.getPassword().equals(password)) {
                    currentUser = u;
                    return true;
                }
            }
            return false;
        }
    public User findUser(String userName)
    {
        for(User user : users)
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
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources" + USER_FILE));
            for(User user : users){
                bw.write(convertUserToLine(user));
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error saving User data to file: " + e.getMessage());
        }
//
//
//        try (PrintWriter pw = new PrintWriter(new FileWriter("src/main/resources" + USER_FILE))) {
//            for (User u : users) {
//                pw.println(convertUserToLine(u));
//            }
//        } catch (IOException e) {
//            System.out.println("Error saving users: " + e.getMessage());
//        }

    }


    // -------------------------
    //   CONVERSION HELPERS
    // -------------------------
    private User convertLineToUser(String line) {
        String[] fields = line.split(",");

        if (fields.length != 6) {
            System.out.println("Skipping invalid user line: " + line);
            return null;
        }

        return new User(
                fields[0],                          // username
                fields[1],                          // password
                Integer.parseInt(fields[2]),        // solved
                Integer.parseInt(fields[3]),        // helped
                Double.parseDouble(fields[4]),      // cheated
                Integer.parseInt(fields[5])         // failed
        );
    }

    private String convertUserToLine(User user) {
        return user.getUserName() + "," +
                user.getPassword() + "," +
                user.getSolvedWordles() + "," +
                user.getHelpedWordles() + "," +
                user.getCheatedWordles() + "," +
                user.getFailedWordles();
    }
}

