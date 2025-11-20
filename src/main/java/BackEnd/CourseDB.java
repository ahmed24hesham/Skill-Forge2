package BackEnd;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class CourseDB extends JsonDatabaseManager<Course> {

    private com.google.gson.Gson gson = new com.google.gson.Gson();

    public CourseDB(String fileName) {
        super(fileName, new TypeToken<ArrayList<Course>>() {}.getType());
    }

    @Override
    public ArrayList<Course> load() {
        try (java.io.FileReader reader = new java.io.FileReader(fileName)) {
            ArrayList<Course> list = gson.fromJson(reader, typeOfT);
            return list != null ? list : new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void save(ArrayList<Course> list) {
        try (java.io.FileWriter writer = new java.io.FileWriter(fileName)) {
            gson.toJson(list, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(Course obj) {
        ArrayList<Course> list = load();
        for (Course i : list) {
            if (i.getTitle().trim().equalsIgnoreCase(obj.getTitle().trim())) {
                System.out.println("Duplicate username. Instructor not added.");
                return false;
            }
        }

        list.add(obj);
        save(list);
        return true;
    }

       
    }
