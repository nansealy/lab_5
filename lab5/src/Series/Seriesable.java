package Series;

import java.io.OutputStream;
import java.io.Writer;

public interface Seriesable {
    String getTitle();
    int getCountOfElements();
    int getCountStartPages();
    String getElement(int index);
    int getCountPages(int index);
    void setTitle(String title);
    void setCountStartPages(int num);
    void setElement(int index, String title);
    void setNumOfPagesOfEl(int index, int num);
    int getSumOfPagesWithoutIntro();
    void outputByte(OutputStream out);
    void writeText(Writer out);
}
