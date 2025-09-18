// Last updated: 9/19/2025, 1:42:38 AM
import java.util.*;

class TaskManager {

    private final Map<Integer, Integer> taskToUser;
    private final Map<Integer, Integer> taskToPriority;
    private final PriorityQueue<int[]> pq;

    public TaskManager(List<List<Integer>> tasks) {
        taskToUser = new HashMap<>();
        taskToPriority = new HashMap<>();
        // Max-heap storing {priority, taskId}
        pq = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return b[0] - a[0]; // Sort by priority descending
            }
            return b[1] - a[1]; // Then by taskId descending
        });

        for (List<Integer> task : tasks) {
            int userId = task.get(0);
            int taskId = task.get(1);
            int priority = task.get(2);
            add(userId, taskId, priority);
        }
    }

    public void add(int userId, int taskId, int priority) {
        taskToUser.put(taskId, userId);
        taskToPriority.put(taskId, priority);
        pq.offer(new int[]{priority, taskId});
    }

    public void edit(int taskId, int newPriority) {
        taskToPriority.put(taskId, newPriority);
        // Re-adding is simpler than removing and re-adding,
        // we will handle stale entries in execTop
        pq.offer(new int[]{newPriority, taskId});
    }

    public void rmv(int taskId) {
        taskToUser.remove(taskId);
        taskToPriority.remove(taskId);
        // Stale entries will be handled in execTop
    }

    public int execTop() {
        while (!pq.isEmpty()) {
            int[] top = pq.peek();
            int priority = top[0];
            int taskId = top[1];

            if (!taskToPriority.containsKey(taskId) || taskToPriority.get(taskId) != priority) {
                // Stale entry, discard
                pq.poll();
                continue;
            }

            // Valid highest priority task
            pq.poll();
            int userId = taskToUser.get(taskId);
            taskToUser.remove(taskId);
            taskToPriority.remove(taskId);
            return userId;
        }
        return -1; // No tasks available
    }
}