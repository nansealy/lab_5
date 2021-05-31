package Series;

import SeriesExceptions.DatabaseNotSetException;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Series {
    public static ArticlesCollection[] getArticlesSeriesArrFromSeriesableArr(Seriesable[] s) throws DatabaseNotSetException {
        if (s == null) {
            throw new DatabaseNotSetException("операция невозможна: база данных не задана");
        } else {
            LinkedList<Integer> indexesOfArticles = getIndexesOfArticles(s);
            ArticlesCollection[] as = new ArticlesCollection[indexesOfArticles.size()];

            for (int i = 0; i < as.length; i++) {
                as[i] = (ArticlesCollection) s[indexesOfArticles.get(i)];
            }

            return as;
        }
    }

    private static LinkedList<Integer> getIndexesOfArticles(Seriesable[] s) throws DatabaseNotSetException {
        if (s == null) {
            throw new DatabaseNotSetException("операция невозможна: сборник статей не задан");
        } else {
            LinkedList<Integer> indexesOfArticles = new LinkedList<>();

            for (int i = 0; i < s.length; i++) {
                if (s[i] instanceof ArticlesCollection) {
                    indexesOfArticles.add(i);
                }
            }

            return indexesOfArticles;
        }
    }

    public static BooksCollection[] getBooksSeriesArrFromSeriesableArr(Seriesable[] s) throws DatabaseNotSetException {
        if (s == null) {
            throw new DatabaseNotSetException("операция невозможна: сборник книг не задан");
        } else {
            LinkedList<Integer> indexesOfBooks = getIndexesOfBooks(s);
            BooksCollection[] bs = new BooksCollection[indexesOfBooks.size()];

            for (int i = 0; i < bs.length; i++) {
                bs[i] = (BooksCollection) s[indexesOfBooks.get(i)];
            }

            return bs;
        }
    }

    private static LinkedList<Integer> getIndexesOfBooks(Seriesable[] s) throws DatabaseNotSetException {
        if (s == null) {
            throw new DatabaseNotSetException("операция невозможна: сборник книг не задан");
        } else {
            LinkedList<Integer> indexesOfBooks = new LinkedList<>();

            for (int i = 0; i < s.length; i++) {
                if (s[i] instanceof BooksCollection) {
                    indexesOfBooks.add(i);
                }
            }

            return indexesOfBooks;
        }
    }

    public static Seriesable[] getArrWithTwoElsWithSameSumOfPagesWithoutStart(Seriesable[] s) throws DatabaseNotSetException {
        if (s == null) {
            throw new DatabaseNotSetException("операция невозможна: база данных не задана");
        } else {
            int[] sumsOfPagesWithoutStart = getSumsOfPagesWithoutStart(s);

            int currIndexOfSum;
            int indexToCompareWith;
            int len = sumsOfPagesWithoutStart.length;

            for (currIndexOfSum = 0; currIndexOfSum < len; currIndexOfSum++) {
                for (indexToCompareWith = currIndexOfSum + 1; indexToCompareWith < len; indexToCompareWith++) {
                    if (sumsOfPagesWithoutStart[currIndexOfSum] == sumsOfPagesWithoutStart[indexToCompareWith]) {
                        Seriesable[] twoSeriesable = new Seriesable[2];
                        twoSeriesable[0] = s[currIndexOfSum];
                        twoSeriesable[1] = s[indexToCompareWith];

                        return twoSeriesable;
                    }
                }
            }

            throw new NoSuchElementException("нет таких элементов");
        }
    }

    private static int[] getSumsOfPagesWithoutStart(Seriesable[] s) throws DatabaseNotSetException {
        if (s == null) {
            throw new DatabaseNotSetException("операция невозможна: база данных не задана");
        } else {
            int[] sumsOfPagesWithoutStart = new int[s.length];

            for (int i = 0; i < sumsOfPagesWithoutStart.length; i++) {
                sumsOfPagesWithoutStart[i] = s[i].getSumOfPagesWithoutIntro();
            }

            return sumsOfPagesWithoutStart;
        }
    }
}
