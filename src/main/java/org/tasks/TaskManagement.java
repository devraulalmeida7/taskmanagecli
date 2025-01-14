package org.tasks;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class TaskManagement {

    static List<Task> tasks = new ArrayList<>();
    static Gson gson = new Gson();
    static String jsonToString = gson.toJson(tasks);

    public static  void addTask(String sql) {


    }
    public static void deleteTask(Task task){
        tasks.remove(task);
    }
    public static void updateTask(String id, String newName, String newDescription) {
        for(Task task :tasks) {
            if(task.id == id) {
                if(!newName.isEmpty()){
                    task.name = newName;
                }
                if(!newDescription.isEmpty()){
                    task.description = newDescription;
                }
                return;
            }
        }

    }
    public  List<Task> getTasks() {
        return tasks;
    }
    // Delete a task by  ID
    public boolean deleteTaskById(String id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).id == id) {
                tasks.remove(i); // Remove the element at the current index
                return true;
            }
        }
        return false;
    }
}
