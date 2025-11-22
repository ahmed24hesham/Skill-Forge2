package BackEnd;

import static BackEnd.PasswordHasher.sha256;
import java.util.ArrayList;

public class AdminRole extends User {
        private ArrayList<Course> pendingCourses ;
    public AdminRole() {
    }
public AdminRole(String username, String email, String passwordHash) {
        super(new IDGenerator().generateUserId(),
              "Admin",
              validateUsername(username),
              validateEmail(email),
              sha256(passwordHash));
    }

    private static String validateUsername(String username) {
        if (username == null) return null;
        username = username.trim();
        return username.length() >= 3 ? username : null;
    }

    private static String validateEmail(String email) {
        if (email == null) return null;
        email = email.trim();
        int atIndex = email.indexOf("@");
        if (atIndex <= 0 || atIndex == email.length() - 1) return null;
        String domain = email.substring(atIndex + 1);
        if (!domain.contains(".") || domain.startsWith(".") || domain.endsWith(".")) return null;
        return email;
    }
    
    
    
    public AdminRole(String userId, String role, String username, String email, String passwordHash) {
        super(userId, role, username, email, passwordHash);
    }
    public void addCourse(Course c){
        pendingCourses.add(c);
    }
    public void CourseAprroval(Course c ,boolean approved){
        if(approved){
            c.setStatus("APPROVED");
        }
        else
            c.setStatus("REJECTED");
    }
    
}
