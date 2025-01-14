package org.tasks;

import db.src.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws SQLException {



        Scanner leitura = new Scanner(System.in);

        int option = 1;


        try (Connection conexao =  ConnectionDB.obterConexao()) {

                menu();
                option = leitura.nextInt();
                if(option > 5) {
                    System.out.println("tente novamente");
                    option = leitura.nextInt();
                }
                switch (option) {
                    // Add Task
                    case 1:
                        String sqlAdd = "INSERT into tasks (taskName, taskDescription) values (?,?)";
                        try(PreparedStatement stmt = conexao.prepareStatement(sqlAdd)) {
                            System.out.println("Digite o nome do Task: ");
                            String name = leitura.nextLine();
                            if (name.isEmpty()) {
                                System.out.println("Nome da tarefa não pode ser vazio.");
                                name = leitura.nextLine();
                            }
                            stmt.setString(1, name);

                            System.out.println("Digite a descricação da tarefa ");
                            String description = leitura.nextLine();
                            if (description.isEmpty()) {
                                System.out.println("Descrição da tarefa não pode ser vazia.");
                                return;
                            }
                            stmt.setString(2, description);

                            stmt.executeUpdate();
                            System.out.println("tarefa adicionada com sucesos! ");
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    // Update TASK
                    case 2:
                        String sqlUpdate = "UPDATE tasks SET taskName = ?, taskDescription = ? WHERE id = ? ";
                        try(PreparedStatement stmt = conexao.prepareStatement(sqlUpdate)) {
                            System.out.println("Digite o id da task:");
                            int taskId = leitura.nextInt();
                            stmt.setInt(3, taskId);

                            System.out.println("Digite o nome do Task: ");
                            String name = leitura.nextLine();
                            if (name.isEmpty()) {
                                System.out.println("task não pode ser vazio.");
                                name = leitura.nextLine();
                            }
                            stmt.setString(1, name);


                            System.out.println("Digite a descrição da task: ");
                            String description = leitura.nextLine();
                            if (description.isEmpty()) {
                                System.out.println("task não pode ser vazio.");
                                description = leitura.nextLine();
                            }
                            stmt.setString(2, description);
                            stmt.executeUpdate();
                            System.out.println("Tarefa de id: " + taskId + " atualizada com sucesso!");
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    // Delete Task
                    case 3:
                        String sqlDelete = "DELETE FROM tasks WHERE id = ? ";
                        try(PreparedStatement stmt = conexao.prepareStatement(sqlDelete)) {
                            System.out.println("Type the ID of task:");
                            int taskId = leitura.nextInt();
                            stmt.setInt(1, taskId);

                            stmt.executeUpdate();
                            System.out.println("Task with ID:" + taskId + "deleted!");
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 4:
                        String sqlShows = "SELECT * FROM tasks";
                        try(PreparedStatement stmt = conexao.prepareStatement(sqlShows)) {
                            ResultSet rs = stmt.executeQuery(sqlShows);

                            while(rs.next()) {
                                int id = rs.getInt("id");
                                String name = rs.getString("taskName");
                                String description = rs.getString("taskDescription");
                                System.out.println("ID:" +id + " - " + "Name: " + name + " - "+ "Description: " + description);
                            }
                        }
                        break;

            }

            leitura.close();
        }
        catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }

        }




    public static void menu() {
        System.out.println("MENU");
        System.out.println("1: Add Task");
        System.out.println("2: Update Task");
        System.out.println("3: Delete Task");
        System.out.println("4: List all tasks");
        System.out.println("5: Exit program");
        System.out.print("Enter your selection : ");
    }
}
