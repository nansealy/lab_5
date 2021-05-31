import Series.ArticlesCollection;
import Series.Seriesable;
import SeriesExceptions.NullSeriesableObjectException;
import Threads.*;

import java.util.Scanner;


import static Series.SeriesMenu.*;

class Main {
    public static void main(String[] args) {
        Seriesable[] sArr = null; // сборник серий (сборник сборников)
        Seriesable s = null;

        Scanner scan = new Scanner(System.in);
        String menuItem;

        int testingNumOfStartPages;
        final int testingNumOfEls = 500;
        Seriesable testingSer;

        do {
            System.out.print(
                    "РАБОТА С БАЗОЙ:\n" +
                    " 1 - вывести полную информацию базы\n" +
                    " 2 - создать базу\n" +
                    " 3 - задание элемента базы\n" +
                    " 4 - найти в базе объекты,\n" +
                    "     функциональный метод которых возвращают одинаковый результат,\n" +
                    "     поместить такие объекты в массив\n" +
                    " 5 - разбить базу на два массива,\n" +
                    "     в которых будут храниться однотипные элементы\n" +
                    " 6 - считать базу из байтового потока\n" +
                    " 7 - считать базу из текстового потока\n" +
                    " 8 - десериализовать базу\n" +
                    " 9 - записать базу в байтовый поток\n" +
                    "10 - записать базу в символьный поток\n" +
                    "11 - сериализовать базу\n" +
                    "РАБОТА С ОБЪЕКТОМ:\n" +
                    "12 - показать содержимое объекта\n" +
                    "13 - создать и заполнить объект Seriesable\n" +
                    "14 - считать из байтового потока\n" +
                    "15 - считать из текстового потока\n" +
                    "16 - десериализовать объект\n" +
                    "17 - записать объект в байтовый поток\n" +
                    "18 - записать объект в символьный поток\n" +
                    "19 - сериализовать объект\n" +
                    "ДЛЯ ТЕСТИРОВАНИЯ:\n" +
                    "-1 - создать и заполнить базу автоматически\n" +
                    "-2 - создать и заполнить базу автоматически так,\n" +
                    "     чтобы были элементы,\n" +
                    "     у которых функциональные методы возвращают одинаковый результат\n" +
                    "-3 - создать и заполнить объект Seriesable автоматически\n" +
                    "0 - выйти\n" +
                    "выбор ... ");
            menuItem = scan.nextLine();

            switch (menuItem) {
                // region РАБОТА С БАЗОЙ
                case "1":
                    print(" 1 - вывести полную информацию базы");
                    printSerArr(sArr);
                    break;

                case "2":
                    print(" 2 - создать базу");
                    print("задание размера базы: ");
                    sArr = printGetSerArr();
                    break;

                case "3":
                    print(" 3 - задание элемента базы");
                    printSerArrAsTitlesOfEls(sArr);
                    System.out.println();
                    printSerArrAsTitlesOfEls(sArr);
                    break;

                case "4":
                    print(" 4 - найти в базе объекты,\n" +
                            "      функциональный метод которых возвращают одинаковый результат,\n" +
                            "      поместить такие объекты в массив");
                    printGetArrWithTwoElsWithSameSumOfPagesWithoutStart(sArr);
                    break;

                case "5":
                    print(" 5 - разбить базу на два массива,\n" +
                            "      в которых будут храниться однотипные элементы");
                    printSplitArrIntoTwoArticlesAndBooksArrs(sArr);
                    break;

                case "6":
                    print(" 6 - считать базу из байтового потока");
                    try {
                        sArr = printInputBytesAsSerArr();
                    } catch (NullSeriesableObjectException exc) {
                        print(exc.getMessage());
                    }
                    break;

                case "7":
                    print(" 7 - считать базу из текстового потока");
                    try {
                        sArr = printReadTextAsSerArr();
                    } catch (NullSeriesableObjectException exc) {
                        print(exc.getMessage());
                    }
                    break;

                case "8":
                    print(" 8 - десериализовать базу");
                    try {
                        sArr = printDeserializeSerArr();
                    } catch (NullSeriesableObjectException exc) {
                        print(exc.getMessage());
                    }
                    break;

                case "9":
                    print(" 9 - записать базу в байтовый поток");
                    printOutputSerArrAsBytes(sArr);
                    break;

                case "10":
                    print("10 - записать базу в символьный поток");
                    printWriteSerArrAsText(sArr);
                    break;

                case "11":
                    print("11 - сериализовать базу");
                    printSerializeSerArr(sArr);
                    break;
                case "12":
                    print("12 - показать содержимое объекта");
                    System.out.println(s);
                    break;

                case "13":
                    print("13 - создать и заполнить объект Seriesable");
                    s = printGetAndSetSer();
                    break;

                case "14":
                    print("14 - считать из байтового потока");
                    try {
                        s = printInputBytesAsSer();

                    } catch (NullSeriesableObjectException exc) {
                        print(exc.getMessage());
                    }
                    break;

                case "15":
                    print("15 - считать из текстового потока");
                    try {
                        s = printReadTextAsSer();
                    } catch (NullSeriesableObjectException exc) {
                        print(exc.getMessage());
                    }
                    break;

                case "16":
                    print("16 - десериализовать объект");
                    try {
                        s = printDeserializeSer();
                    } catch (NullSeriesableObjectException exc) {
                        print(exc.getMessage());
                    }
                    break;

                case "17":
                    print("17 - записать объект в байтовый поток");
                    printOutputSerAsBytes(s);
                    break;

                case "18":
                    print("18 - записать объект в текстовый поток");
                    printWriteSerAsText(s);
                    break;

                case "19":
                    print("19 - сериализовать объект");
                    printSerializeSer(s);
                    break;

                case "20":
                    print("20 - заполнить нитью +\n" +
                            "      считать   нитью");

                    testingNumOfStartPages = TestingSeries.getRandInt();

                    testingSer = new ArticlesCollection("Тестовый номер", testingNumOfStartPages, testingNumOfEls);
                    WritingThread wt = new WritingThread(testingSer);
                    ReadingThread rt = new ReadingThread(testingSer);

                    wt.setPriority(Thread.MAX_PRIORITY);
                    wt.start();

                    rt.setPriority(Thread.MIN_PRIORITY);
                    rt.start();
                    break;

                case "21":
                    print("21 - write-read-write-read...");

                    testingNumOfStartPages = getRandInt();

                    testingSer = new ArticlesCollection("Тестовый номер", testingNumOfStartPages, testingNumOfEls);

                    SeriesableSynchronizer ssyncher = new SeriesableSynchronizer(testingSer);
                    WritingRunnableThread wrt = new WritingRunnableThread(ssyncher);
                    ReadingRunnableThread rrt = new ReadingRunnableThread(ssyncher);

                    new Thread(wrt).start();
                    new Thread(rrt).start();

                case "-1":
                    print("-1 -- создать и заполнить базу автоматически");
                    sArr = TestingSeries.createAndFillInDbWithFiveElsAutomatically();
                    break;

                case "-2":
                    print("-2 -- создать и заполнить базу автоматически так,\n" +
                            "      чтобы были элементы,\n" +
                            "      у которых функциональные методы возвращают одинаковый результат");
                    sArr = TestingSeries.createAndFillInDbWithFiveElsAutomatically();
                    TestingSeries.setTwoSeriesableWithSameSumOfPagesWithoutStart(sArr);
                    break;

                case "-3":
                    print("-3 -- создать и заполнить объект Seriesable автоматически");
                    s = TestingSeries.createAndFillSerAutomatically();
                    break;

                default:
                    break;
            }
            print("Выход");
            System.out.println();
        } while (!menuItem.equals("0"));
    }
}
