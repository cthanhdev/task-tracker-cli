package cli.tasktracker;

import java.util.*;

public class RequestHandler {
    private static TaskManager taskManager;

    private RequestHandler() {
    };

    // <Yeu cau, <thamSo, body(tuyChon)>
    public static void handleRequest(String raw) {
        String[] parts = raw.trim().split("\\s+", 3); // Tách tối đa 3 phần: [action, thamSo, noiDung]
        if (parts.length == 0 || parts[0].isEmpty())
            return;
        String action = parts[0].toLowerCase();

        switch (action) {
            case "add":
                if (parts.length >= 2) {
                    if (parts[1] == null) {
                        throw new IllegalArgumentException("description cannot be null");
                    }

                    if (parts[1].isBlank()) {
                        throw new IllegalArgumentException("description cannot be blank");
                    }

                    taskManager.createTask(parts[1].replace("\"", ""));
                    System.out.println("Them Vao Thanh Cong");
                }

                break;
            case "update":
                if (parts.length <= 3) {
                    if (parts[2] == null) {
                        throw new IllegalArgumentException("description cannot be null");
                    }

                    if (parts[2].isBlank()) {
                        throw new IllegalArgumentException("description cannot be blank");
                    }

                    if (parts[1] == null) {
                        throw new IllegalArgumentException("ID cannot be null");
                    }
                    taskManager.updateTask(Integer.parseInt(parts[1]), parts[2].replace("\"", ""));
                    System.out.println("Update Vao Thanh Cong");
                } else {
                    System.out.println("Update Khong Thanh Cong");
                }

                break;

            case "delete":
                if (parts.length <= 3) {

                    if (parts[1] == null) {
                        throw new IllegalArgumentException("ID cannot be null");
                    }
                    taskManager.deleteTask(Integer.parseInt(parts[1]));
                    System.out.println("Delete Vao Thanh Cong");
                } else {
                    System.out.println("Update Khong Thanh Cong");

                }

                break;

            case "mark-in-progress":
                if (parts.length <= 2) {
                    if (parts[1] == null) {
                        throw new IllegalArgumentException("ID cannot be null");
                    }
                    taskManager.setTaskStatusInProgress(Integer.parseInt(parts[1]));

                }

                break;

            case "mark-done":
                if (parts.length <= 2) {
                    if (parts[1] == null) {
                        throw new IllegalArgumentException("ID cannot be null");
                    }
                    taskManager.setTaskStatusDone(Integer.parseInt(parts[1]));

                }
                break;

            case "list":
                Map<Integer, Task> taskMap = taskManager.getTaskMap();
                System.out.println("==========List Task==========");

                if (parts.length <= 1) {
                    for (Task task : taskMap.values()) {
                        System.out.println(task.toString());
                        System.out.println("__________________________________________________________");
                    }
                } else if (parts[1].equals("done")) {
                    for (Task task : taskMap.values()) {
                        if (task.getStatus() == TaskStatus.DONE) {
                            System.out.println(task.toString());
                            System.out.println("__________________________________________________________");
                        }
                    }
                } else if (parts[1].equals("todo")) {
                    for (Task task : taskMap.values()) {
                        if (task.getStatus() == TaskStatus.TODO) {
                            System.out.println(task.toString());
                            System.out.println("__________________________________________________________");
                        }
                    }
                } else if (parts[1].equals("in-progress")) {
                    for (Task task : taskMap.values()) {
                        if (task.getStatus() == TaskStatus.IN_PROGRESS) {
                            System.out.println(task.toString());
                            System.out.println("__________________________________________________________");
                        }
                    }
                }

                break;

            default:
                break;
        }

    }

}
