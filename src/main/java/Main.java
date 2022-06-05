import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File JSONFile = new File("new_data.json");

        readString(JSONFile.getAbsolutePath());

        List<Employee> employeeList = jsonToList(readString(JSONFile.getAbsolutePath()));

        for (Employee employee : employeeList) {
            System.out.println(employee);
        }

    }

    public static String readString(String file) {
        StringBuilder sb = new StringBuilder();
        try (FileReader reader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                sb.append(str);
            }
            String jsonString = sb.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }

    public static List<Employee> jsonToList(String json) {
        List<Employee> employeeList = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            JSONArray jsArr = (JSONArray) parser.parse(json);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            for (Object o : jsArr) {
                String str = gson.toJson(o);
                employeeList.add(gson.fromJson(str, Employee.class));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return employeeList;
    }
}
