package view;

import book.Book;

import java.util.List;
import java.util.Map;

public class Printer {

    public static List<Book> printBooks(List<Book> books) {
        for (Book book : books) {
            System.out.println(book);
        }
        return books;
    }

    public static void printGenreAndYear(List<Book> books) {
        if (books.isEmpty()) {
            printMessage("Книг з такою назвою не знайдено.");
            return;
        }
        for (Book book : books) {
            System.out.println("Назва: '" + book.getTitle() +
                    "' | Жанр: " + book.getGenre() +
                    " | Рік видання: " + book.getYear());
        }
    }

    public static void printBookMapByGenre(Map<String, List<Book>> books) {
        for (Map.Entry<String, List<Book>> entry : books.entrySet()) {
            System.out.println(entry.getKey());
            for (Book book : entry.getValue()) {
                System.out.println("    " + book);
            }
        }
    }
    public static void printCountBooksMapByPublisher(Map <String, Integer> books) {
        for (Map.Entry<String, Integer> entry : books.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }
    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printPrompt(String message) {
        System.out.print(message);
    }

    public static void printError(String errorMessage) {
        System.err.println("ПОМИЛКА: " + errorMessage);
    }
    public static void printMenu() {
        System.out.print("""
                
                --- ГОЛОВНЕ МЕНЮ ---
                1. Знайти книги за автором
                2. Знайти книги за видавництвом
                3. Знайти книги (до року X та >= Y сторінок)
                4. Показати найдорожчі книги
                5. Показати всі книги
                6. Зберегти книги у JSON (text)
                7. Завантажити книги з JSON (text)
                8. Зберегти книги у бінарний файл
                9. Завантажити книги з бінарного файлу
                10. Показати список книг указаного жанру, упорядкований за роком видання,
                11. Перевірити, чи є задана книга у списку.
                12. Додати нову книгу
                13. Видалити книгу за ID
                14. Вивести список книг за жанрами (Map)
                15. Вивести кількість книг за видавництвами (Map)
                0. Вихід
                0. Вихід
                Введіть номер дії:\s""");
    }
}
