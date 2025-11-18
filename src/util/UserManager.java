package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager
{
    public UserManager UserManager;
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
        saveUsers();
        
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
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 2) {
                        String username = parts[0].trim();
                        String password = parts[1].trim();
                        users.add(new User(username, password));
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

    private void saveUsers() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(USER_FILE))) {
            for (User u : users) {
                pw.println(u.getUserName() + "," + u.getPassword());
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }
    public void saveDataToFile()
    {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE));
            for(User user : users){
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
