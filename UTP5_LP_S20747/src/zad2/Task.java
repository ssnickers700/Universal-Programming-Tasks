package zad2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Task implements Callable<Integer> {

    public int result;
    public Future<Integer> task;
    public ExecutorService exec = Executors.newFixedThreadPool(6);
    public int id;
    public static int objNum;

    public Task() {
        objNum++;
        id = objNum;
    }

    @Override
    public Integer call() {

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                result += 1;
            } catch (InterruptedException e) {
                break;
            }
        }
        return result;
    }

    public void start() {
        task = exec.submit(this);
    }

    public void cancel() {
        task.cancel(true);
    }

    public String getStatus() {
        if (task.isCancelled()) {
            return "Cancelled";
        } else if (task.isDone()) {
            return "Done";
        } else {
            return "Running";
        }
    }

    @Override
    public String toString() {
        return "Task " + id;
    }
}
