package LinesCounter;

import java.io.File;
import java.util.Scanner;

public class LinesCounter {

    public static void start() {
        System.out.println("Введите путь до папки...");

        File file;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            file = new File(scanner.nextLine());

            if (file.isDirectory() && file.exists())
                break;
            else
                System.out.println("Ошибка: путь не существует или ведёт не к папке");
        }

        int totalLinesOfCode;

        System.out.println("Хотите считать файлы конкретного расширения? (y/n)");
        if (scanner.nextLine().equals("y")) {
            System.out.println("Введите расширение файлов без точки");
            totalLinesOfCode = LinesCounterUtils.getTotalLinesOfCode(file, scanner.nextLine());
        }
        else {
            totalLinesOfCode = LinesCounterUtils.getTotalLinesOfCode(file);
        }

        System.out.printf("Всего %d строк кода", totalLinesOfCode);
    }
}