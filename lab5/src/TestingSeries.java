import Series.ArticlesCollection;
import Series.BooksCollection;
import Series.Seriesable;

import java.util.Random;

public class TestingSeries {

    private static final String[] TITLES = new String[] {
            "Каталог лучших услуг в географическом регионе",
            "Список лучших рассказов 1913 года",
            "Книга больших новостных фотографий",
            "Академический журнал, содержащий статьи по определенной теме",
            "Каталог состоит из текстов и фотографий"
    };

    private static final String[] ELS = new String[] {
            "Мастер и Маргарита", "Преступление и наказание", "Война и мир", "Собачье сердце",
            "Идиот", "Братья Карамазовы", "Двенадцать стульев", "Мёртвые души", "Отцы и дети",
            "Анна Каренина", "Три товарища", "Граф Монте-Кристо", "Евгений Онегин",
            "Отверженные", "Горе от ума", "Золотой теленок", "Бесы", "Ревизор", "Капитанская дочка",
            "Триумфальная арка", "Униженные и оскорблённые", "Село Степанчиково и его обитатели",
            "Повести Белкина", "Приключения Шерлока Холмса", "Подросток"
    };


    public static Seriesable[] createAndFillInDbWithFiveElsAutomatically() {
        Seriesable[] db = createSeriesableArrWithFiveEls();
        setElsInSeriesableArrWithFiveEls(db);

        return db;
    }

    public static Seriesable[] createSeriesableArrWithFiveEls() {
        final int countElements  = 5;

        Seriesable[] s = new Seriesable[countElements];

        for(int i = 0; i < 5; i++){
            s[i] = getSeriesableWithRandGeneratedType(TITLES[i], getRandInt(), countElements);
        }


        return s;
    }

    public static Seriesable getSeriesableWithRandGeneratedType(String title, int numOfStartPages, int countElements) {
        Seriesable s;
        int choice = getRandInt(1, 2);
        if (choice == 1) {
            s = new ArticlesCollection(title, numOfStartPages, countElements);
        } else {
            s = new BooksCollection(title, numOfStartPages, countElements);
        }

        return s;
    }

    public static int getRandInt() {
        int num;
        int min = 1;
        int max = 1000;
        Random rand = new Random();
        num = min + rand.nextInt(max - min + 1);
        return num;
    }

    public static int getRandInt(int min , int max) {
        int num;
        Random rand = new Random();
        num = min + rand.nextInt(max - min + 1);

        return num;
    }


    static Seriesable createAndFillSerAutomatically() {
        Seriesable s;

        s = getSeriesableWithRandGeneratedType(TITLES[0], getRandInt(), 5);

        for(int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++){
                s.setElement(j, ELS[i*5 + j]);
                s.setNumOfPagesOfEl(j, getRandInt());
            }


        System.out.println("объект успешно создан и заполнен");

        return s;
    }

    public static void setElsInSeriesableArrWithFiveEls(Seriesable[] db){

        for(int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++){
                db[i].setElement(j, ELS[i*5 + j]);
            }

        for(int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++){
                db[i].setNumOfPagesOfEl(j, getRandInt());
            }
    }


    public static void setTwoSeriesableWithSameSumOfPagesWithoutStart(Seriesable[] s) {
        int lastIndex = s.length - 1;

        int firstIndex = getRandInt(0, lastIndex);
        int secondIndex = getRandInt(0, lastIndex);

        Seriesable firstSeriesable = s[firstIndex];
        Seriesable secondSeriesable = s[secondIndex];

        int sameNumOfPages;

        for (int i = 0; i < firstSeriesable.getCountOfElements(); i++) {
            sameNumOfPages = firstSeriesable.getCountPages(i);
            secondSeriesable.setNumOfPagesOfEl(i, sameNumOfPages);
        }
    }
}
