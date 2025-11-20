package BackEnd;

import static BackEnd.PasswordHasher.sha256;
import java.util.ArrayList;

public class AuthService {
 public static boolean AuthStudent(String username,String password,String role){
                        StudentDB studentDB = new StudentDB("users.json");

            ArrayList<Srudent> list =studentDB.load();        // Prevent duplicate usernames
        for (Srudent s : list) {
            if (s.getUsername().trim().equals(username)) {
                if(s.getPasswordHash().equals(sha256(password))&&s.getRole().equals(role))
                return true;
            }
        }
     return false;
 }
  public static boolean AuthInstructor(String username,String password,String role){
                        InstructorDB instructorDB = new InstructorDB("users.json");

            ArrayList<Instructor> list =instructorDB.load();        // Prevent duplicate usernames
        for (Instructor i : list) {
            if (i.getUsername().trim().equals(username)) {
                if(i.getPasswordHash().equals(sha256(password))&&i.getRole().equals(role))
                return true;
            }
        }
     return false;
 }
}
