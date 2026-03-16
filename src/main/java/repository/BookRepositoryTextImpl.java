package repository;

import book.Book;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import view.Printer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryTextImpl implements BookRepository {
    @Override
    public void outputList(List<Book> books, File file) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonText = gson.toJson(books);

        try {
            Files.writeString(file.toPath(), jsonText);
            Printer.printMessage("Дані успішно збережено у JSON!");
        } catch (IOException e) {
            Printer.printError("Помилка запису файлу: " + e.getMessage());
        }
    }

    @Override
    public void outputList(List<Book> books, String fileName) {
        outputList(books, new File(fileName));
    }

    @Override
    public List<Book> readList(File file) {
        try {
            String jsonText = Files.readString(file.toPath());
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Book>>(){}.getType();
            return gson.fromJson(jsonText, listType);

        } catch (IOException e) {
            Printer.printError("Помилка читання файлу: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Book> readList(String fileName) {
        return readList(new File(fileName));
    }
}