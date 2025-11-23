package BackEnd;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;

public class StudentDB extends JsonDatabaseManager<Srudent> {

    private Gson gson = new Gson();

    public StudentDB(String fileName) {
        super(fileName, new TypeToken<ArrayList<Srudent>>() {}.getType());
    }

    @Override
public ArrayList<Srudent> load() {
    try (FileReader reader = new FileReader(fileName)) {

        ArrayList<User> allUsers = gson.fromJson(reader, typeOfT);
        ArrayList<Srudent> students = new ArrayList<>();

        if (allUsers != null) {
            for (User u : allUsers) {
                if (u instanceof Srudent || "student".equalsIgnoreCase(u.getRole())) {
                    students.add((Srudent) u);
                }
            }
        }

        return students;

    } catch (Exception e) {
        e.printStackTrace();
        return new ArrayList<>();
    }
}


    @Override
    public void save(ArrayList<Srudent> list) {
        try (java.io.FileWriter writer = new java.io.FileWriter(fileName)) {
            gson.toJson(list, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(Srudent student) {
        ArrayList<Srudent> list = load();

        // Prevent duplicate usernames
        for (Srudent s : list) {
            if (s.getUsername().trim().equalsIgnoreCase(student.getUsername().trim())) {
                System.out.println("Duplicate username. Student not added.");
                return false;
            }
        }

        // Assign sequential ID if null or duplicate
        int nextId = list.stream()
                         .mapToInt(s -> {
                             try { return Integer.parseInt(s.getUserId().substring(1)); }
                             catch (Exception e) { return 100; }
                         })
                         .max().orElse(100) + 1;
        student.userId = "U" + nextId;

        list.add(student);
        save(list);
        System.out.println("Student added: " + student.getUsername() + " with ID: " + student.getUserId());
        return true;
    }

    public boolean addStudent(Srudent s) {
       return add(s);
    }
 public Certificate generateCertificate(Srudent s, Course c) {
    String id = "CERT-" + System.currentTimeMillis();
    String date = java.time.LocalDate.now().toString();

    Certificate cert = new Certificate(id, s.getUserId(), c.getCourseId(), date);

    ArrayList<Srudent> list = load();

    for (Srudent st : list) {
        if (st.getUserId().equals(s.getUserId())) {
            st.addCertificate(cert);
            break;
        }
    }

    save(list);

    return cert;
}
  public Srudent getStudentById(String id) {
    ArrayList<Srudent> list = load();
    
    for (Srudent s : list) {
        if (s.getUserId().equals(id)) {
            return s;
        }
    }
    return null; 
}

}   