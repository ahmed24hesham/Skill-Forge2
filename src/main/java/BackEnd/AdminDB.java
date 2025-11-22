package BackEnd;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.util.ArrayList;

public class AdminDB extends JsonDatabaseManager<AdminRole> {

    private Gson gson = new Gson();

    public AdminDB(String fileName) {
        super(fileName, new TypeToken<ArrayList<AdminRole>>() {}.getType());
    }

    @Override
public ArrayList<AdminRole> load() {
    try (FileReader reader = new FileReader(fileName)) {
        ArrayList<User> allUsers = gson.fromJson(reader, typeOfT);

        ArrayList<AdminRole> admins = new ArrayList<>();

        if (allUsers != null) {
            for (User u : allUsers) {
                if (u instanceof AdminRole || "admin".equalsIgnoreCase(u.getRole())) {
                    admins.add((AdminRole) u);
                }
            }
        }

        return admins;

    } catch (Exception e) {
        e.printStackTrace();
        return new ArrayList<>();
    }
}


    @Override
    public void save(ArrayList<AdminRole> list) {
        try (java.io.FileWriter writer = new java.io.FileWriter(fileName)) {
            gson.toJson(list, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(AdminRole admin) {
        ArrayList<AdminRole> list = load();

        // Prevent duplicate usernames
        for (AdminRole a : list) {
            if (a.getUsername().trim().equalsIgnoreCase(admin.getUsername().trim())) {
                System.out.println("Duplicate username. Admin not added.");
                return false;
            }
        }

        // Assign sequential ID if null or duplicate
        int nextId = list.stream()
            .mapToInt(a -> {
                try { return Integer.parseInt(a.getUserId().substring(1)); }
                catch (Exception e) { return 100; }
            })
            .max().orElse(100) + 1;

        admin.userId = "A" + nextId;

        list.add(admin);
        save(list);
        System.out.println("Admin added: " + admin.getUsername() + " with ID: " + admin.getUserId());
        return true;
    }

    public boolean addAdmin(AdminRole a) {
        return add(a);
    }
}
