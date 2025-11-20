package BackEnd;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class InstructorDB extends JsonDatabaseManager<Instructor> {

    private com.google.gson.Gson gson = new com.google.gson.Gson();

    public InstructorDB(String fileName) {
        super(fileName, new TypeToken<ArrayList<Instructor>>() {}.getType());
    }

    @Override
    public ArrayList<Instructor> load() {
        try (java.io.FileReader reader = new java.io.FileReader(fileName)) {
            ArrayList<Instructor> list = gson.fromJson(reader, typeOfT);
            return list != null ? list : new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void save(ArrayList<Instructor> list) {
        try (java.io.FileWriter writer = new java.io.FileWriter(fileName)) {
            gson.toJson(list, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(Instructor instructor) {
        ArrayList<Instructor> list = load();
        // Prevent duplicate usernames
        for (Instructor i : list) {
            if (i.getUsername().trim().equalsIgnoreCase(instructor.getUsername().trim())) {
                System.out.println("Duplicate username. Instructor not added.");
                return false;
            }
        }

        list.add(instructor);
        save(list);
        return true;
    }

    // Optional semantic helper
    public boolean addInstructor(Instructor instructor) {
      return  add(instructor);
    }
}