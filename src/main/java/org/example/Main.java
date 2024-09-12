package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args){
try {
    File file = new File("tasks.txt");
    if (!file.exists()) {
        file.createNewFile();
        System.out.println("File created: " + file.getName());
    }

    BufferedReader reader = new BufferedReader(new FileReader("tasks.txt"));


    Scanner scanner = new Scanner(System.in);
    String input;

    List<Task> tasks = new ArrayList<>();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    String line;
    while ((line = reader.readLine()) != null) {
        int nameStart = line.indexOf("name=") + 5;
        int descStart = line.indexOf("description=") + 12;
        int isCompletedStart = line.indexOf("isCompleted=") + 12;
        int dueDateStart = line.indexOf("dueDate=") + 8;

        int nameEnd = line.indexOf(',', nameStart);
        int descEnd = line.indexOf(',', descStart);
        int isCompletedEnd = line.indexOf('}', isCompletedStart);
        int dueDateEnd = line.indexOf(',', dueDateStart);

        tasks.add(new Task(line.substring(nameStart, nameEnd),
                line.substring(descStart, descEnd),
                LocalDate.parse(line.substring(dueDateStart, dueDateEnd), formatter),
                Boolean.parseBoolean(line.substring(isCompletedStart, isCompletedEnd))));
        System.out.println(nameStart + " " + nameEnd + " " + descStart + " " + descEnd);
    }

    System.out.println(
            """
                    Hello! Welcome to the ToDo list app!
                    To use the app you type commands on the console!
                    To see what commands are available type 'help'.
                    IMPORTANT! To safely exit the app type the 'exit command!'"""
    );
    while (true) {
        System.out.print(">>");
        input = scanner.nextLine();

        if (input.equals("exit")) {
            System.out.println("Exiting...");
            break;
        }

        switch (input) {
            case "list":
                for (Task task : tasks) {
                    System.out.println(task);
                }
                break;

            case "add":
                while (true) {
                    try {
                        System.out.println("To add a new task you must follow the syntax: [name],[description],[Due date]");
                        System.out.print(">>");
                        String[] asd = scanner.nextLine().split(",");
                        Task createdTask = new Task(asd[0], asd[1].strip(), LocalDate.parse(asd[2].strip(), formatter), false);
                        tasks.add(createdTask);
                        System.out.println("New task added: " + createdTask);
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Wrong syntax!");
                    }
                }
                break;

            case "help":
                System.out.println(
                        """
                                list     -  List out all of your tasks.
                                add      -  Add a new task.
                                delete   -  Delete a task.
                                toggle   -  Toggle a tasks completed status
                                exit     -  Exit the app."""
                );
                break;

            case "toggle":
                while (true) {
                    System.out.println("To toggle a task you must enter the tasks name:");
                    System.out.print(">>");
                    String taskName = scanner.nextLine().strip();
                    for (Task task : tasks) {
                        if (task.getName().equals(taskName)) {
                            task.toggleIsCompleted();
                            System.out.println(task);
                        }
                    }
                    break;

                }
                break;

            case "delete":
                while (true) {
                    System.out.println("To delete a task you must enter the tasks name:");
                    System.out.print(">>");
                    String taskName = scanner.nextLine().strip();
                    for (Task task : tasks) {
                        if (task.getName().equals(taskName)) {
                            tasks.remove(task);
                            System.out.println("The task is deleted!");
                            break;
                        }
                    }
                    break;

                }
                break;


            default:
                System.out.println("Wrong command!");
                break;
        }
    }
    PrintWriter writer = new PrintWriter("tasks.txt");
    for (Task task : tasks) {
        writer.println(task);
    }
    writer.close();


    }catch(IOException error){
        System.out.println("Something went wrong!");
    }
}
}