package BackEnd;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
        try (java.io.FileReader reader = new java.io.FileReader(fileName)) {
            ArrayList<Srudent> list = gson.fromJson(reader, typeOfT);
            
            return list != null ? list : new ArrayList<>();
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
}