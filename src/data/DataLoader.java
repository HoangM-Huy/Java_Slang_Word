package data;
import model.SlangDictionary;

public class DataLoader {
    public static void loadData() {
        SlangDictionary.loadFromFile("my_slang.txt");
    }
}
