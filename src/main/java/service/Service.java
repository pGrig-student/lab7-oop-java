package service;

import book.Book;
import repository.BookRepository;

import java.util.*;
import java.util.stream.Collectors;

public class Service {
    private List<Book> books = new ArrayList<>();
    private final BookRepository textRepo;
    private final BookRepository binaryRepo;

    public Service(BookRepository textRepo, BookRepository binaryRepo) {
        this.textRepo = textRepo;
        this.binaryRepo = binaryRepo;
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public void loadFromJson(String fileName) {
        this.books = textRepo.readList(fileName);
    }

    public void saveToJson(String fileName) {
        textRepo.outputList(books, fileName);
    }

    public void loadFromBinary(String fileName) {
        this.books = binaryRepo.readList(fileName);
    }

    public void saveToBinary(String fileName) {
        binaryRepo.outputList(books, fileName);
    }


    public void addBook(Book book) {
        this.books.add(book);
    }

    public void deleteBookById(int id) {
        books.removeIf(book -> book.getId() == id);
    }

    public List<Book> getMostExpensiveBooks() {
        double maxPrice = 0;
        for (Book book : books) {
            if (book.getPrice() > maxPrice) {
                maxPrice = book.getPrice();
            }
        }
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getPrice() == maxPrice) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> getBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> getBooksByPublisher(String publisher) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getPublisher().equalsIgnoreCase(publisher)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> getBooksByYearAndPages(int year, int pages) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getYear() < year && book.getPageCount() > pages) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> getBooksByGenreSorted(String genre) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getGenre().equalsIgnoreCase(genre)) {
                result.add(book);
            }
        }
        result.sort(Comparator.comparingInt(Book::getYear)
                .thenComparing(Book::getTitle));
        return result;
    }

    public List<Book> checkBook(String name) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(name)) {
                result.add(book);
            }
        }
        return result;
    }

    public Map<String, List<Book>> getBooksByGenre() {
        Map<String, List<Book>> result = new HashMap<>();

        for (Book book : books) {
            String currentGenre = book.getGenre();

            if (result.containsKey(currentGenre)) {
                result.get(currentGenre).add(book);
            } else {
                result.put(currentGenre, new ArrayList<>(List.of(book)));
            }
        }

        for (List<Book> list : result.values()) {
            list.sort(new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    return o1.getAuthor().compareTo(o2.getAuthor());
                }
            });
        }

        return result;
    }

    public Map<String, Integer> getCountBooksByPublisher() {
        Map<String, Integer> result = new HashMap<>();

        for (Book book : books) {
            String currentPublisher = book.getPublisher();
            if (result.containsKey(currentPublisher)) {
                result.compute(currentPublisher, (k, currentCount) -> currentCount + 1);
            } else {
                result.put(currentPublisher, 1);
            }
        }
        return result;
    }

}