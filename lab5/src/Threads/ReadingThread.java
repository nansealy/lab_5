package Threads;

import Series.Seriesable;

public class ReadingThread extends Thread {
    private Seriesable s;

    public ReadingThread(Seriesable s) {
        this.s = s;
    }

    @Override
    public void run() {
        if (s == null) {
            System.out.println("операция невозможна: объект не задан");
            return;
        }

        for (int index = 0; index < s.getCountOfElements(); index++) {
                System.out.println("READ  " + s.getCountPages(index) + " from position " + index);
        }
    }
}
