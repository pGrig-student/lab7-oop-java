package repository;

import book.Book;
import view.Printer;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryBinaryImpl implements BookRepository {
    @Override
    public void outputList(List<Book> books, File file) {
        try (var out = new ObjectOutputStream(Files.newOutputStream(file.toPath()))) {
            out.writeObject(books);
            Printer.printMessage("Дані успішно збережено у бінарний файл!");
        } catch (IOException ex) {
            Printer.printError("Помилка запису файлу: " + ex.getMessage());
        }
    }

    @Override
    public void outputList(List<Book> books, String fileName) {
        outputList(books, new File(fileName));
    }

    @Override
    public List<Book> readList(File file) {
        try (var in = new ObjectInputStream(Files.newInputStream(file.toPath()))) {
            return (List<Book>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            Printer.printError("Помилка читання файлу: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Book> readList(String fileName) {
        return readList(new File(fileName));
    }
}