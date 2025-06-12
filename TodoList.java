import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TodoList {

    private static List<String> tasks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    viewTasks();
                    break;
                case 3:
                    removeTask();
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("To-Do List Application");
        System.out.println("1. Add Task");
        System.out.println("2. View Tasks");
        System.out.println("3. Remove Task");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addTask() {
        System.out.print("Enter task description: ");
        String task = scanner.nextLine();
        tasks.add(task);
        System.out.println("Task added.");
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks in the list.");
        } else {
            System.out.println("Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private static void removeTask() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to remove.");
            return;
        }
        System.out.print("Remove by (index/name): ");
        String choice = scanner.nextLine().trim().toLowerCase();
        if (choice.equals("index")) {
            System.out.print("Enter the index of the task to remove: ");
            try {
                 int index = scanner.nextInt();
                scanner.nextLine();
                if(index > 0 && index <= tasks.size()){
                    tasks.remove(index-1);
                    System.out.println("Task removed.");
                }else{
                    System.out.println("Invalid index.");
                }

            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid index.");
            }
        } else if (choice.equals("name")) {
            System.out.print("Enter the name of the task to remove: ");
            String taskName = scanner.nextLine();
            if(tasks.remove(taskName)){
                System.out.println("Task removed.");
            } else{
                System.out.println("Task not found.");
            }
        }else{
            System.out.println("Invalid choice.");
        }
    }
}