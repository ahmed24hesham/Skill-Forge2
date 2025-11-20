package BackEnd;

import java.util.ArrayList;
import java.lang.reflect.Type;

public abstract class JsonDatabaseManager<T> {

    protected String fileName;
    protected Type typeOfT;

    public JsonDatabaseManager(String fileName, Type typeOfT) {
        this.fileName = fileName;
        this.typeOfT = typeOfT;
    }

    public abstract ArrayList<T> load();
    public abstract void save(ArrayList<T> list);
    public abstract boolean add(T obj);
}