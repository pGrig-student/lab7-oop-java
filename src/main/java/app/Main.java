package app;

import controller.BookController;
import repository.BookRepository;
import repository.BookRepositoryBinaryImpl;
import repository.BookRepositoryTextImpl;
import service.Service;
import view.Printer;

public class Main {
    public static void main(String[] args) {

        BookRepository textRepo = new BookRepositoryTextImpl();
        BookRepository binaryRepo = new BookRepositoryBinaryImpl();

        Service bookService = new Service(textRepo, binaryRepo);

        bookService.loadFromJson("books.json");
        Printer.printMessage("Початкові дані успішно завантажено з JSON.");

        BookController controller = new BookController(bookService);

        controller.start();
    }
}
