package Threads;

import Series.Seriesable;
import static Series.SeriesMenu.getRandInt;

public class WritingThread extends Thread {
    private Seriesable s;

    public WritingThread(Seriesable s) {
        this.s = s;
    }

    @Override
    public void run() {
        if (s == null) {
            System.out.println("операция невозможна: объект не задан");
            return;
        }
        int numOfPages;
        for (int index = 0; index < s.getCountOfElements(); index++) {
                numOfPages = getRandInt();
                s.setNumOfPagesOfEl(index, numOfPages);
                System.out.println("WRITE " + numOfPages + " to   position " + index);
        }
    }
}
