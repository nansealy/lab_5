package Series;

import SeriesExceptions.NullSeriesableObjectException;
import SeriesExceptions.IllegalIndexException;
import Series.ArticlesCollection;
import Series.BooksCollection;
import Series.InputAndOutputSeriesable;
import Series.Seriesable;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

import static Series.InputAndOutputSeriesable.*;
import static Series.InputAndOutputSeriesableArray.*;
import static Series.Series.*;

public class SeriesMenu {

    private static final String BYTES_FILE_WITH_SER = "serAsBytes.bin";
    private static final String TEXT_FILE_WITH_SER = "serAsText.txt";
    private static final String SERIALIZED_FILE_WITH_SER = "serSerialized.bin";

    private static final String BYTES_FILE_WITH_SER_ARR = "serArrAsBytes.bin";
    private static final String TEXT_FILE_WITH_SER_ARR = "serArrAsText.txt";
    private static final String SERIALIZED_FILE_WITH_SER_ARR = "serArrSerialized.bin";


    public static void print(String str) {
        System.out.print(str);
    }


    static void printExit() {
        System.out.print('\n' + "нажмите Enter, чтобы выйти в меню ... ");
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
    }
    // endregion

    // region вывести БД
    public static void printSerArrAsTitlesOfEls(Seriesable[] sArr) {
        System.out.print("база данных: ");
        if (sArr == null) {
            System.out.println("не задана");
        } else {
            System.out.print('\n');

            for (int i = 0; i < sArr.length; i++) {
                System.out.print("[" + i + "] ");
                if (sArr[i] == null) {
                    System.out.println("элемент не задан");
                } else {
                    System.out.println('«' + sArr[i].getTitle() + '»');
                }
            }
        }
    }

    public static void printSerArr(Seriesable[] sArr) {
        System.out.print("база данных: ");
        if (sArr == null) {
            System.out.println("не задана");
        } else {
            System.out.print('\n');
            for (int i = 0; i < sArr.length; i++) { // по элементам БД
                System.out.print("[" + i + "] ");
                printSer(sArr[i]);
                System.out.print("\n");
            }
        }
    }

    private static void printSer(Seriesable s) {
        if (s == null) {
            print("серия не задана");
        } else {
            System.out.println('«' + s.getTitle() + '»');
            System.out.println(s);
        }
    }

    private static void printElsOfSer(Seriesable s) {
        if (s == null) {
            System.out.println("серия не задана");
        } else {
            for (int i = 0; i < s.getCountOfElements(); i++) {
                System.out.print(i + ") ");

                if (s.getElement(i) == null) {
                    print("элемент на задан");
                } else {
                    System.out.println(s.getElement(i) +
                            " (кол-во страниц -- " + s.getCountPages(i) + ')');
                }
            }
        }
    }
    // endregion

    // region геты
    public static Seriesable[] printGetSerArr() {
        int len;

        do {
            len = printGetInt();


            Seriesable[] sArr = new Seriesable[len];
            print("массив размером в " + len + " элементов успешно создан");
            return sArr;
        } while (true);
    }

    private static int printGetInt() {
        int num;

        Scanner scan = new Scanner(System.in);
        String str;

        do {
            System.out.print("введите число ... ");
            str = scan.nextLine();

            try {
                num = Integer.parseInt(str);
                break;
            } catch (NumberFormatException exc) {
                print("ошибка: введённая строка не является числом");
            }
        } while (true);

        return num;
    }

    private static int printGetIndex(int maxIndex) {
        int index;

        Scanner scan = new Scanner(System.in);
        String str;

        do {
            System.out.print("введите индекс ... ");
            str = scan.nextLine();
            System.out.println();

            try {
                index = Integer.parseInt(str);
                if (index < 0 || index > maxIndex) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (IllegalIndexException exc) {
                print("ошибка: неверный индекс");
            } catch (Exception exc) {
                print("ошибка: введённая строка не является числом");
            }
        } while (true);

        return index;
    }
    // endregion

    // region геты и сеты БД + объекта
    static void printSetElOfArr(Seriesable[] db) {
        if (db == null) {
            print("операция невозможна: база данных не задана");
        } else {
            System.out.println("задайте индекс элемента,\n" +
                    "который хотите изменить\n" +
                    "(нумерация начинается с нуля):");
            int index = printGetIndex(db.length - 1);

            Scanner scan = new Scanner(System.in);
            String str;

            System.out.print("задание элемента под индексом " + index + '\n' );
            do {
                System.out.print("выберите тип элемента\n" +
                        "1 -- " + ArticlesCollection.class.getName() + "\n" +
                        "2 -- " + BooksCollection.class.getName() + "\n" +
                        "выбор ... ");
                str = scan.nextLine();
                System.out.println();

                if (str.equals("1")) {
                    db[index] = printGetAndSetArticlesSer();
                    break;
                } else if (str.equals("2")) {
                    db[index] = printGetAndSetBooksSer();
                    break;
                } else {
                    print("ошибка: неверный пункт меню");
                }
            } while (true);
        }
    }

    private static ArticlesCollection printGetAndSetArticlesSer() {
        System.out.print("введите название сборника ................................. ");
        Scanner scan = new Scanner(System.in);
        String title = scan.nextLine();

        int numOfArticles = printGetNumOfElsInSer();
        int numOfAbstractPages = printGetNumOfStartPages();
        ArticlesCollection as = new ArticlesCollection(title, numOfAbstractPages, numOfArticles);
        print("сборник успешно создан");
        System.out.println();

        System.out.print("заполните сборник названиями статей и их количеством страниц\n");
        printSetElsOfSer(as);

        return as;
    }

    private static BooksCollection printGetAndSetBooksSer() {
        System.out.print("введите название сборника ........................... ");
        Scanner scan = new Scanner(System.in);
        String title = scan.nextLine();

        int numOfBooks = printGetNumOfElsInSer();
        int numOfPrefacePages = printGetNumOfStartPages();
        BooksCollection bs = new BooksCollection(title, numOfPrefacePages, numOfBooks);
        print("сборник успешно создан");
        System.out.println();

        System.out.print("заполните сборник названиями книг и их количеством страниц\n");
        printSetElsOfSer(bs);

        return bs;
    }

    private static int printGetNumOfElsInSer() {
        int num;

        do {
            System.out.print("задание количества элементов в серии: ... ");
            num = printGetInt();
            return num;

        } while (true);
    }

    private static int printGetNumOfStartPages() {
        int num;

        do {
            System.out.print("задание количества страниц в предисловии/аннотации каждого элемента серии: ");
            num = printGetInt();
            return num;
        } while (true);
    }

    private static void printSetElsOfSer(Seriesable s) {
        if (s == null) {
            print("операция невозможна: серия не задана");
        } else {
            for (int i = 0; i < s.getSumOfPagesWithoutIntro(); i++) {
                System.out.print("элемент под индексом  " + "[" + i + "]" + '\n');
                try {
                    if (!printSetElOfSer(s, i)) {
                        i--;
                    }
                } catch (Exception exc) {
                    print(exc.getMessage());
                } finally {
                    System.out.println();
                }
            }
        }
    }

    private static boolean printSetElOfSer(Seriesable s, int index) throws Exception {
        if (s == null) {
            throw new UnsupportedOperationException("операция невозможна: серия не задана");
        } else {
            try {
                System.out.print("название ............................... ");
                Scanner scan = new Scanner(System.in);
                String title = scan.nextLine();
                s.setElement(index, title);

                System.out.print("количество страниц ... ");
                int numOfPages = printGetNumOfPages();
                s.setNumOfPagesOfEl(index, numOfPages);

                return true;
            } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException exc) {
                print(exc.getMessage());
                return false;
            } catch (Exception exc) {
                throw new Exception(exc.getMessage());
            }
        }
    }

    public static int printGetNumOfPages() {
        int num;

        do {
            num = printGetInt();
            return num;
        } while (true);
    }

    public static Seriesable printGetAndSetSer() {
        Seriesable s;

        Scanner scan = new Scanner(System.in);
        String str;

        do {
            System.out.print("выберите тип элемента\n" +
                    "1 -- " + ArticlesCollection.class.getName() + "\n" +
                    "2 -- " + BooksCollection.class.getName() + "\n" +
                    "выбор ... ");
            str = scan.nextLine();
            System.out.println();

            if (str.equals("1")) {
                s = printGetAndSetArticlesSer();
                break;
            } else if (str.equals("2")) {
                s = printGetAndSetBooksSer();
                break;
            } else {
                print("ошибка: неверный пункт меню");
            }
        } while (true);

        return s;
    }
    // endregion

    // region деление базы
    public static void printGetArrWithTwoElsWithSameSumOfPagesWithoutStart(Seriesable[] sArr) {
        Seriesable[] arrWithTwoElsWithSameSumOfPagesWithoutStart;

        try {
            arrWithTwoElsWithSameSumOfPagesWithoutStart = getArrWithTwoElsWithSameSumOfPagesWithoutStart(sArr);
            print("база данных успешно разделена");
            System.out.println();

            printSerArr(arrWithTwoElsWithSameSumOfPagesWithoutStart);
        } catch (Exception exc) {
            print(exc.getMessage());
        }
    }

    public static void printSplitArrIntoTwoArticlesAndBooksArrs(Seriesable[] sArr) {
        if (sArr == null) {
            print("операция невозможна: база данных не задана");
        } else {
            try {
                ArticlesCollection[] as = getArticlesSeriesArrFromSeriesableArr(sArr);
                BooksCollection[] bs = getBooksSeriesArrFromSeriesableArr(sArr);
                print("база данных разбита на два массива, в которых хранятся однотипные элементы");
                System.out.println();

                printSerArr(as);
                printSerArr(bs);
            } catch (Exception exc) {
                print(exc.getMessage());
            }
        }
    }
    // endregion

    // region запись объекта
    public static void printOutputSerAsBytes(Seriesable s) {
        if (s == null) {
            print("операция невозможна: объект не задан");
        } else {
            FileOutputStream fileOutputter;
            try {
                fileOutputter = new FileOutputStream(BYTES_FILE_WITH_SER);
                InputAndOutputSeriesable.outputSerAsBytes(s, fileOutputter);
                fileOutputter.flush();
                fileOutputter.close();

                print("объект успешно записан в байтовый поток");
            } catch (IOException exc) {
                print(exc.getMessage());
            }
        }
    }

    public static void printWriteSerAsText(Seriesable s) {
        if (s == null) {
            print("операция невозможна: объект не задан");
        } else {
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(TEXT_FILE_WITH_SER);
                InputAndOutputSeriesable.writeSerAsText(s, fileWriter);
                fileWriter.flush();
                fileWriter.close();

                print("объект успешно записан в текстовый поток");
            } catch (IOException exc) {
                print(exc.getMessage());
            }
        }
    }

    public static void printSerializeSer(Seriesable s) {
        if (s == null) {
            print("операция невозможна: объект не задан");
        } else {
            FileOutputStream fileOutputter;
            try {
                fileOutputter = new FileOutputStream(SERIALIZED_FILE_WITH_SER);
                InputAndOutputSeriesable.serializeSer(s, fileOutputter);
                fileOutputter.flush();
                fileOutputter.close();

                print("объект успешно сериализован");
            } catch (IOException exc) {
                print(exc.getMessage());
            }
        }
    }
    // endregion

    // region запись массива
    public static void printOutputSerArrAsBytes(Seriesable[] sArr) {
        if (sArr == null) {
            print("операция невозможна: массив не задан");
        } else {
            FileOutputStream fileOutputter;
            try {
                fileOutputter = new FileOutputStream(BYTES_FILE_WITH_SER_ARR);
                outputSerArrAsBytes(sArr, fileOutputter);
                fileOutputter.flush();
                fileOutputter.close();

                print("объект успешно записан в байтовый поток");
            } catch (IOException exc) {
                print(exc.getMessage());
            }
        }
    }

    public static void printWriteSerArrAsText(Seriesable[] sArr) {
        if (sArr == null) {
            print("операция невозможна: массив не задан");
        } else {
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(TEXT_FILE_WITH_SER_ARR);
                writeSerArrAsText(sArr, fileWriter);
                fileWriter.flush();
                fileWriter.close();

                print("массив успешно записан в текстовый поток");
            } catch (IOException exc) {
                print(exc.getMessage());
            }
        }
    }

    public static void printSerializeSerArr(Seriesable[] sArr) {
        if (sArr == null) {
            print("операция невозможна: массив не задан");
        } else {
            FileOutputStream fileOutputter;
            try {
                fileOutputter = new FileOutputStream(SERIALIZED_FILE_WITH_SER_ARR);
                serializeSerArr(sArr, fileOutputter);
                fileOutputter.flush();
                fileOutputter.close();

                print("массив успешно сериализован");
            } catch (IOException exc) {
                print(exc.getMessage());
            }
        }
    }
    // endregion

    // region считывание объекта
    public static Seriesable printInputBytesAsSer() throws NullSeriesableObjectException {
        Seriesable s = null;

        FileInputStream fileInputter;
        try {
            fileInputter = new FileInputStream(BYTES_FILE_WITH_SER);
            s = inputBytesAsSer(fileInputter);
            fileInputter.close();

            print("объект успешно считан из байтового потока (файла)");
        } catch (IOException | NullSeriesableObjectException | ClassNotFoundException exc) {
            print(exc.getMessage());
        }

        if (s == null) {
            throw new NullSeriesableObjectException("не удалось считать Seriesable");
        }

        return s;
    }

    public static Seriesable printReadTextAsSer() throws NullSeriesableObjectException {
        Seriesable s = null;

        FileReader fileReader;
        BufferedReader bufferedReader;
        try {
            fileReader = new FileReader(TEXT_FILE_WITH_SER);
            bufferedReader = new BufferedReader(fileReader);

            s = readTextAsSer(bufferedReader);

            bufferedReader.close();
            fileReader.close();

            print("объект успешно считан из тектового потока (файла)");
        } catch (IOException | NullSeriesableObjectException | ClassNotFoundException exc) {
            print(exc.getMessage());
        }

        if (s == null) {
            throw new NullSeriesableObjectException("не удалось считать Seriesable");
        }

        return s;
    }

    public static Seriesable printDeserializeSer() throws NullSeriesableObjectException {
        Seriesable s = null;

        FileInputStream fileInputter;
        try {
            fileInputter = new FileInputStream(SERIALIZED_FILE_WITH_SER);
            s = deserializeSer(fileInputter);
            fileInputter.close();

            print("объект успешно десериализован (из файла)");
        } catch (IOException | NullSeriesableObjectException exc) {
            print(exc.getMessage());
        }

        if (s == null) {
            throw new NullSeriesableObjectException("не удалось считать Seriesable");
        }

        return s;
    }
    // endregion

    // region считывание массива
    public static Seriesable[] printInputBytesAsSerArr() throws NullSeriesableObjectException {
        Seriesable[] sArr = null;

        FileInputStream fileInputter;
        try {
            fileInputter = new FileInputStream(BYTES_FILE_WITH_SER_ARR);
            sArr = inputBytesAsSerArr(fileInputter);
            fileInputter.close();

            print("массив успешно считан из байтового потока (файла)");
        } catch (IOException | NullSeriesableObjectException | ClassNotFoundException exc) {
            print(exc.getMessage());
        }

        if (sArr == null) {
            throw new NullSeriesableObjectException("не удалось считать массив Seriesable[]");
        }

        return sArr;
    }

    public static Seriesable[] printReadTextAsSerArr() throws NullSeriesableObjectException {
        Seriesable[] sArr = null;

        FileReader fileReader;
        try {
            fileReader = new FileReader(TEXT_FILE_WITH_SER_ARR);
            sArr = readTextAsSerArr(fileReader);
            fileReader.close();

            print("массив успешно считан из тектового потока (файла)");
        } catch (IOException | NullSeriesableObjectException | ClassNotFoundException exc) {
            print(exc.getMessage());
        }

        if (sArr == null) {
            throw new NullSeriesableObjectException("не удалось считать массив Seriesable[]");
        }

        return sArr;
    }

    public static Seriesable[] printDeserializeSerArr() throws NullSeriesableObjectException {
        Seriesable[] sArr = null;

        FileInputStream fileInputter;
        try {
            fileInputter = new FileInputStream(SERIALIZED_FILE_WITH_SER_ARR);
            sArr = deserializeSerArr(fileInputter);
            fileInputter.close();

            print("массив успешно десериализована (из файла)");
        } catch (IOException | NullSeriesableObjectException exc) {
            print(exc.getMessage());
        }

        if (sArr == null) {
            throw new NullSeriesableObjectException("не удалось десериализовать массив Seriesable[]");
        }

        return sArr;
    }

    public static int getRandInt() {
        int num;
        int min = 1;
        int max = 1000;
        Random rand = new Random();
        num = min + rand.nextInt(max - min + 1);
        return num;
    }
}
