package BackEnd;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
//
//        // --- Students ---
//        Type studentListType = new TypeToken<ArrayList<Srudent>>() {}.getType();
//        JsonDatabaseManager<Srudent> studentDB = new JsonDatabaseManager<>("users.json", studentListType);
//        StudentDB studentDB = new StudentDB("users.json");
//        Srudent student1 = new Srudent("S100","Ahmed", "ahmed@333322.com", "A123");
//        student1.enrollCourse(101);
////student1.completeLesson(1);
//studentDB.addStudent(student1);
        
        
        InstructorDB instructorDB = new InstructorDB("users.json");
        Instructor i = new Instructor("Dr. Smith", "hamo@gmail.com" , "password");
         instructorDB.addInstructor(i);

        Course c = new Course("Math 101", "M101" , "I456");
        i.createCourse(c);
        
        
        StudentDB studentDB = new StudentDB("users.json");
        Srudent student1 = new Srudent("Ahmed", "ahmed@333322.com", "A123");
        
        Srudent student11 = new Srudent("Ahmed", "ahmed@3lm33322.com", "A123");
        Srudent student12 = new Srudent("Ahmedlol", "ahmed@3lm33322.com", "A123");
//        student1.enrollCourse(101);
//student1.completeLesson(1);
studentDB.addStudent(student1);
studentDB.addStudent(student11);
studentDB.addStudent(student12);



//        Srudent s = new Srudent("Ahmed", "A123");
//        studentDB.add(s); // adds to students.json

        // --- Instructors ---
//        Type instructorListType = new TypeToken<ArrayList<Instructor>>() {}.getType();
//        JsonDatabaseManager<Instructor> instructorDB = new JsonDatabaseManager<>("users.json", instructorListType);

//        Instructor i = new Instructor("U100" , "instructor","Dr. Smith", "hamo@gmail.com" , "password");
//        instructorDB.add(i); // adds to instructors.json

        // --- Courses ---
//        Type courseListType = new TypeToken<ArrayList<Course>>() {}.getType();
//        JsonDatabaseManager<Course> courseDB = new JsonDatabaseManager<>("courses.json", courseListType);
//

        
//        courseDB.add(c); // adds to courses.js
    }
}