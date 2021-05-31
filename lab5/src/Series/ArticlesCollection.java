package Series;

import SeriesExceptions.IllegalIndexException;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class ArticlesCollection implements Seriesable {
    private String title;
    private int numOfAbstractPages;
    private int numOfPrefacePages = 5;
    private int countArticles = 25;
    private String[] articles;
    private int[] numsOfPages;

    public ArticlesCollection() {
        title = "";
        numOfAbstractPages = numOfPrefacePages;
        articles = new String[countArticles];
        numsOfPages = new int[articles.length];
    }

    public ArticlesCollection(String title, int numOfAbstractPages, int numOfArticles) {
        this.title = title;
        this.numOfAbstractPages = numOfAbstractPages;
        articles = new String[numOfArticles];
        numsOfPages = new int[articles.length];
    }

    public String getTitle() {
        return title;
    }

    public int getCountStartPages() {
        return numOfAbstractPages;
    }

    public int getCountOfElements() {
        return articles.length;
    }

    public String getElement(int index) {
        if (index < 0 || index >= articles.length) {
            throw new IllegalIndexException("неверный индекс");
        }

        return articles[index];
    }

    public int getCountPages(int index) {
        if (index < 0 || index >= numsOfPages.length) {
            throw new IllegalIndexException("неверный индекс");
        }

        return numsOfPages[index];
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setCountStartPages(int num) {
        numOfAbstractPages = num;
    }

    public void setElement(int index, String title) {
        if (index < 0 || index >= articles.length) {
            throw new IllegalIndexException("неверный индекс");
        }

        articles[index] = title;
    }

    public void setNumOfPagesOfEl(int index, int num) {
        if (index < 0 || index >= articles.length) {
            throw new IllegalIndexException("неверный индекс");
        }

        numsOfPages[index] = num;
    }
    public int getSumOfPagesWithoutIntro() {
        int sum = 0;
        for (int num : numsOfPages) {
            sum += num;
        }
        return sum;
    }

    @Override
    public void outputByte(OutputStream out) {
        DataOutputStream dataOutputter = new DataOutputStream(out);

        try {
            dataOutputter.writeUTF(getClass().getName());
            dataOutputter.writeUTF(title);
            dataOutputter.writeInt(numOfAbstractPages);
            dataOutputter.writeInt(articles.length);

            for (int index = 0; index < articles.length; index++) {
                dataOutputter.writeUTF(articles[index]);
                dataOutputter.writeInt(numsOfPages[index]);
            }

            dataOutputter.flush();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }

    @Override
    public void writeText(Writer out) {
        PrintWriter printer = new PrintWriter(out);

        printer.println(getClass().getName());
        printer.println(title);
        printer.println(numOfAbstractPages);
        printer.println(articles.length);

        for (int index = 0; index < articles.length; index++) {
            printer.println(articles[index]);
            printer.println(numsOfPages[index]);
        }

        printer.flush();
    }



    @Override
    public String toString() {
        StringBuilder outputStr = new StringBuilder();
        outputStr.append("Название сборника:").append(title).append('\n');
        outputStr.append("Кол-во страниц в аннотации:").append(numOfPrefacePages).append('\n');
        outputStr.append("Суммарное кол-во страниц (без аннотаций):").append(getSumOfPagesWithoutIntro()).append('\n');
        outputStr.append("Кол-во статей в сборнике").append(articles.length).append('\n');
        outputStr.append("Тип объекта").append(getClass()).append('\n').append("\n");
        return outputStr.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticlesCollection that = (ArticlesCollection) o;
        return numOfAbstractPages == that.numOfAbstractPages && title.equals(that.title) && Arrays.equals(articles, that.articles) && Arrays.equals(numsOfPages, that.numsOfPages);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(title, numOfAbstractPages);
        result = 31 * result + Arrays.hashCode(articles);
        result = 31 * result + Arrays.hashCode(numsOfPages);
        return result;
    }


}
