package org.tasks;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.tasks.TaskManagement.*;

public class Main {


    public static void main(String[] args) {
        TaskManagement taskManage = new TaskManagement();
        //Gson gson = new Gson();
        //String jsonString = gson.toJson(tasks);


        Scanner leitura = new Scanner(System.in);

        System.out.println();

        int option = 1;
        do{
            menu();
            option = leitura.nextInt();
            switch (option) {
                // Add Task
                case 1:
                    System.out.println("Adicionar");
                    System.out.println("Digite o ID da tarefa: ");
                    int id = leitura.nextInt();

                    System.out.println("Digite o nome da tarefa: ");
                    String name = leitura.nextLine();
                    if(name == "") {
                        System.out.println("O nome da tarefa não pode ser vazio.");
                        name = leitura.nextLine();
                    }
                    System.out.println("Digite a descricao da tarefa: ");
                    String description = leitura.nextLine();

                    System.out.println("Tarefa criada com sucesso!");
                    Task task = new Task(id,name,description);
                    tasks.add(task);
                    System.out.println(tasks.size());
                    break;
                // Update TASK
                case 2:
                    System.out.println("Atualizar tarefa");
                    System.out.println("Digite o ID da tarefa: ");
                    int idUp = leitura.nextInt();



                    System.out.println("Digite o nome da tarefa: ");
                    String nameTask = leitura.nextLine();
                    if(nameTask == "") {
                        nameTask = leitura.nextLine();
                    }
                    System.out.println("Digite a descricao da tarefa: ");
                    String nameDescription = leitura.nextLine();
                    updateTask(idUp,nameTask,nameDescription);
                    System.out.println(tasks.size());
                    break;
                // Delete Task
                case 3:
                    System.out.println("Excluir tarefa");
                    System.out.println("Digite o ID da tarefa: ");
                    int idFind = leitura.nextInt();


                    taskManage.deleteTaskById(idFind);
                    System.out.println("Tarefa excluida com sucesso!");
                    System.out.println(tasks.size());
            }
        } while (option !=4);


    }
    public static void menu() {
        System.out.println("MENU");
        System.out.println("1: Add Task");
        System.out.println("2: Update Task");
        System.out.println("3: Delete Task");
        System.out.println("4: Exit program");
        System.out.print("Enter your selection : ");
    }

}