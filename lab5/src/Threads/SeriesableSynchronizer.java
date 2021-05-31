package Threads;

import Series.Seriesable;

public class SeriesableSynchronizer {
    private final Seriesable s;
    private volatile int currIndex = 0;
    private volatile boolean isElSet = false;

    public SeriesableSynchronizer(Seriesable i) {
        this.s = i;
    }

    void write(int val) throws InterruptedException {
        synchronized (s) {
            if (!canWrite()) {
                throw new InterruptedException();
            }
            while (isElSet) {
                s.wait();
            }

            s.setNumOfPagesOfEl(currIndex, val);
            isElSet = true;
            System.out.println("WRITE " + val + " to   position " + currIndex);

            s.notifyAll();
        }
    }

    private boolean canWrite() {
        return (!isElSet && currIndex < s.getCountOfElements() || (isElSet && currIndex < s.getCountOfElements() - 1));
    }

    void read() throws InterruptedException {
        int val;
        synchronized (s) {
            if (!canRead()) {
                throw new InterruptedException();
            }
            while (!isElSet) {
                s.wait();
            }

            val = s.getCountPages(currIndex);
            isElSet = false;
            System.out.println("READ  " + val + " from position " + currIndex);
            currIndex++;

            s.notifyAll();
        }
    }

    private boolean canRead() {
        return currIndex < s.getCountOfElements();
    }

    int getSerNumOfEls() {
        return s.getCountOfElements();
    }
}
