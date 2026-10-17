package cli.tasktracker;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TaskManager {
    private static final Map<Integer, Task> taskMap = new HashMap<>();
    private static int id = 0;

    private TaskManager() {
    };

    public static void createTask(String description) {
        Task task = new Task.Builder(++id, description, LocalDateTime.now()).build();
        taskMap.put(task.getId(), task);
    }

    public static void updateTask(int id, String description) {

        if (!taskMap.containsKey(id)) {
            throw new IllegalStateException("Không có Key này trong List Task");

        }

        taskMap.put(id, new Task.Builder(id, description, taskMap.get(id).getCreatedAt()).setTimeUpdate().build());
        return;
    }

    public static void setTaskStatusDone(int id) {

        if (!taskMap.containsKey(id)) {
            throw new IllegalStateException("Không có Key này trong List Task");

        }

        taskMap.put(id,
                new Task.Builder(taskMap.get(id).getId(), taskMap.get(id).getDescription(), taskMap.get(id)
                        .getCreatedAt()).setStatusDone()
                        .setTimeUpdate().build());
        return;
    }

    public static void setTaskStatusInProgress(int id) {

        if (!taskMap.containsKey(id)) {
            throw new IllegalStateException("Không có Key này trong List Task");

        }

        taskMap.put(id,
                new Task.Builder(taskMap.get(id).getId(), taskMap.get(id).getDescription(), taskMap.get(id)
                        .getCreatedAt()).setStatusInProgress()
                        .setTimeUpdate()
                        .build());
        return;
    }

    public static void deleteTask(int id) {

        if (!taskMap.containsKey(id)) {
            throw new IllegalStateException("Không có Key này trong List Task");
        }

        taskMap.remove(id);
        return;
    }

    public static Map<Integer, Task> getTaskMap() {
        return Collections.unmodifiableMap(new HashMap<>(taskMap));
    }
}
