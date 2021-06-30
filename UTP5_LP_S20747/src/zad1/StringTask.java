package zad1;

public class StringTask implements Runnable {

    public String text;
    public volatile String result = "";
    public int times;
    public volatile TaskState state;
    public Thread thread;

    public StringTask(String text, int times) {
        this.text = text;
        this.times = times;
        this.state = TaskState.CREATED;
    }

    @Override
    public void run() {

        for (int i = 0; i < times; i++) {
            try {
                if(i % 1200 == 0)
                Thread.sleep(0,1);
            } catch (InterruptedException e) {
                state = TaskState.ABORTED;
                return;
            }
            result = result + text;
        }
        state = TaskState.READY;
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
        state = TaskState.RUNNING;
    }

    public void abort() {
        thread.interrupt();
    }

    public boolean isDone() {
        if (state == TaskState.READY || isInterrupted()) return true;
        else return false;
    }

    public boolean isInterrupted() {
        if (state == TaskState.ABORTED) return true;
        else return false;
    }

    public String getResult() {
        return result;
    }

    public TaskState getState() {
        return state;
    }
}
