package lab25.example;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String currentDirectory = "src/TDir";
        boolean running = true;

        while (running) {
            System.out.println("\nФайл_менеджер. Выберите действие:");
            System.out.println("1. Показать содержимое папки");
            System.out.println("2. Удалить файл");
            System.out.println("3. Создать файл");
            System.out.println("4. Выход");
            System.out.print("Введите номер действия: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    showFolderContents(currentDirectory);
                    break;
                case "2":
                    System.out.print("Введите имя файла для удаления: ");
                    String deleteFileName = scanner.nextLine();
                    deleteFile(currentDirectory, deleteFileName);
                    break;
                case "3":
                    System.out.print("Введите имя файла для создания: ");
                    String createFileName = scanner.nextLine();
                    createFile(currentDirectory, createFileName);
                    break;
                case "4":
                    running = false;
                    System.out.println("Выход из программы.");
                    break;
                default:
                    System.out.println("Некорректный ввод. Пожалуйста, выберите действие от 1 до 4.");
            }
        }
        scanner.close();
    }

    public static void showFolderContents(String directory) {
        File folder = new File(directory);
        File[] files = folder.listFiles();

        if (files != null && files.length > 0) {
            System.out.println("Содержимое папки:");
            for (File file : files) {
                System.out.println((file.isDirectory() ? "[DIR] " : "[FILE] ") + file.getName());
            }
        } else {
            System.out.println("Папка пуста.");
        }
    }

    public static void deleteFile(String directory, String fileName) {
        File file = new File(directory, fileName);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("Файл успешно удалён.");
            } else {
                System.out.println("Не удалось удалить файл.");
            }
        } else {
            System.out.println("Файл не найден.");
        }
    }

    public static void createFile(String directory, String fileName) {
        File file = new File(directory, fileName);
        try {
            if (file.createNewFile()) {
                System.out.println("Файл успешно создан.");
            } else {
                System.out.println("Файл уже существует.");
            }
        } catch (IOException e) {
            System.out.println("Ошибка при создании файла: " + e.getMessage());
        }
    }
}