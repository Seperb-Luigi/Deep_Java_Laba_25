package lab25.example;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainTest {

    private final String testDirectory = "testDir";

    @BeforeAll
    public void setup() throws IOException {
        // Создаем тестовую папку
        Files.createDirectory(Paths.get(testDirectory));
    }

    @AfterAll
    public void cleanup() throws IOException {
        // Удаляем тестовую папку и все её содержимое
        Files.walk(Paths.get(testDirectory))
                .map(Path::toFile)
                .forEach(File::delete);
    }

    @Test
    public void testCreateFile() throws IOException {
        String fileName = "testFile.txt";
        File file = new File(testDirectory, fileName);

        // Проверяем, что файл создается
        Main.createFile(testDirectory, fileName);
        assertTrue(file.exists(), "Файл должен быть создан.");

        // Проверяем повторное создание
        Main.createFile(testDirectory, fileName);
        assertTrue(file.exists(), "Файл не должен перезаписываться.");
    }

    @Test
    public void testDeleteFile() throws IOException {
        String fileName = "testDeleteFile.txt";
        File file = new File(testDirectory, fileName);
        Files.createFile(file.toPath());

        // Проверяем, что файл удаляется
        Main.deleteFile(testDirectory, fileName);
        assertFalse(file.exists(), "Файл должен быть удален.");

        // Проверяем удаление несуществующего файла
        Main.deleteFile(testDirectory, fileName);
        assertFalse(file.exists(), "Удаление несуществующего файла не должно вызывать ошибок.");
    }

    @Test
    public void testShowFolderContents() throws IOException {
        String fileName1 = "file1.txt";
        String fileName2 = "file2.txt";
        Files.createFile(Paths.get(testDirectory, fileName1));
        Files.createFile(Paths.get(testDirectory, fileName2));

        // Проверяем, что содержимое папки корректно отображается
        Main.showFolderContents(testDirectory);

        File[] files = new File(testDirectory).listFiles();
        assertNotNull(files, "Папка должна содержать файлы.");
        assertEquals(2, files.length, "Папка должна содержать 2 файла.");
    }
}
