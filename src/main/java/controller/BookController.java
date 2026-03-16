package controller;

import book.Book;
import service.Service;
import view.Printer;

import java.util.Scanner;

public class BookController {
    private final Service bookService;
    private final Scanner scanner;

    public BookController(Service bookService) {
        this.bookService = bookService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean isRunning = true;

        while (isRunning) {
            Printer.printMenu();

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        Printer.printPrompt("Введіть ім'я або частину імені автора: ");
                        String author = scanner.nextLine();
                        Printer.printBooks(bookService.getBooksByAuthor(author));
                        break;
                    case 2:
                        Printer.printPrompt("Введіть назву або частину назви видавництва: ");
                        String publisher = scanner.nextLine();
                        Printer.printBooks(bookService.getBooksByPublisher(publisher));
                        break;
                    case 3:
                        Printer.printPrompt("Введіть граничний рік (до якого року): ");
                        int year = scanner.nextInt();
                        Printer.printPrompt("Введіть мінімальну к-сть сторінок: ");
                        int pages = scanner.nextInt();
                        Printer.printBooks(bookService.getBooksByYearAndPages(year, pages));
                        break;
                    case 4:
                        Printer.printMessage("Список найдорожчих книг:");
                        Printer.printBooks(bookService.getMostExpensiveBooks());
                        break;
                    case 5:
                        Printer.printBooks(bookService.getAllBooks());
                        break;
                    case 6:
                        bookService.saveToJson("books.json");
                        break;
                    case 7:
                        bookService.loadFromJson("books.json");
                        Printer.printMessage("Дані успішно завантажено з JSON.");
                        Printer.printBooks(bookService.getAllBooks());
                        break;
                    case 8:
                        bookService.saveToBinary("books.bin");
                        break;
                    case 9:
                        bookService.loadFromBinary("books.bin");
                        Printer.printMessage("Дані успішно завантажено з бінарного файлу.");
                        break;
                    case 10:
                        Printer.printPrompt("Введіть жанр: ");
                        String genre = scanner.nextLine();
                        Printer.printMessage("Список книг указаного жанру, упорядкований за роком видання: ");
                        Printer.printBooks(bookService.getBooksByGenreSorted(genre));
                        break;
                    case 11:
                        Printer.printPrompt("Введіть назву книги рік якої та жанр ви хочете вивести: ");
                        String title = scanner.nextLine();
                        Printer.printMessage("Список знайдених книг: ");
                        Printer.printGenreAndYear(bookService.checkBook(title));
                        break;
                    case 12:
                        Printer.printMessage("--- Додавання нової книги ---");

                        int newId = 1;
                        for (Book book : bookService.getAllBooks()) {
                            if (book.getId() >= newId) {
                                newId = book.getId() + 1;
                            }
                        }

                        Printer.printPrompt("Введіть назву книги: ");
                        String newTitle = scanner.nextLine();

                        Printer.printPrompt("Введіть автора: ");
                        String newAuthor = scanner.nextLine();

                        Printer.printPrompt("Введіть видавництво: ");
                        String newPublisher = scanner.nextLine();

                        Printer.printPrompt("Введіть рік видання: ");
                        int newYear = Integer.parseInt(scanner.nextLine());

                        Printer.printPrompt("Введіть кількість сторінок: ");
                        int newPageCount = Integer.parseInt(scanner.nextLine());

                        Printer.printPrompt("Введіть ціну: ");
                        double newPrice = Double.parseDouble(scanner.nextLine());

                        Printer.printPrompt("Введіть жанр: ");
                        String newGenre = scanner.nextLine();

                        Book newBook = new Book(newId, newTitle, newAuthor, newPublisher, newYear, newPageCount, newPrice, newGenre);
                        bookService.addBook(newBook);
                        bookService.saveToJson("books.json");
                        Printer.printMessage("Книгу успішно додано! Автоматично призначений ID: " + newId);
                        break;
                    case 13:
                        Printer.printPrompt("Видалити книгу за ID: ");
                        bookService.deleteBookById(scanner.nextInt());
                        bookService.saveToJson("books.json");
                        break;
                    case 14:
                        Printer.printMessage("Вивести за жанром відсортований по автору");
                        Printer.printBookMapByGenre(bookService.getBooksByGenre());
                        break;
                    case 15:
                        Printer.printMessage("Вивести кілкість книг за видавництвом");
                        Printer.printCountBooksMapByPublisher(bookService.getCountBooksByPublisher());
                        break;
                    case 0:
                        Printer.printMessage("Роботу завершено.");
                        isRunning = false;
                        break;
                    default:
                        Printer.printError("Невірний вибір. Спробуйте ще раз.");
                }
            } else {
                Printer.printError("Будь ласка, введіть число.");
                scanner.next();
            }
        }
        scanner.close();
    }
}