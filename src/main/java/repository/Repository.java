package repository;

import java.io.File;
import java.util.List;

public interface Repository<T> {
    void outputList(List<T> items, File file);
    void outputList(List<T> items, String fileName);
    List<T> readList(File file);
    List<T> readList(String fileName);
}