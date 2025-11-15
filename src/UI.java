import java.util.Scanner;

/**
 * Класс взаимодействия с пользователем
 */
public class UI {

    /** Объект класса Scanner для считывания пользовательских данных */
    private final Scanner _SC;

    /**
     * Конструктор по умолчанию
     */
    UI() {
        _SC = new Scanner(System.in);
    }

    /**
     * Выводит информационное сообщение
     */
    private void getInfo() {
        System.out.println("Проверка скобочных последовательностей");
        System.out.println("Бригада 10: Пчелинцев Д., Глебов Я.\n");
    }

    /**
     * Меню приложения
     */
    private void menu() {
        System.out.println("Выберите действие: ");
        System.out.println("1. Проверить новое выражение");
        System.out.println("2. Показать историю проверок");
        System.out.println("3. Сохранить историю в файл");
        System.out.println("Для выхода введите пустую строку...");
        System.out.print("Ваш выбор: ");
    }

    /**
     * Метод взаимодействия с пользователем
     */
    public void run() {
        getInfo();
        menu();
        String choose = _SC.nextLine();
        while (!choose.isEmpty()) {
            try {
                switch (choose) {
                    case "1":
                        checkNewExpression();
                        break;
                    case "2":
                        showAllChecks();
                        break;
                    case "3":
                        saveToFile();
                        break;
                    default:
                        System.out.println("Выбор не распознан!");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
            menu();
            choose = _SC.nextLine();
        }
        System.out.println("Вы вышли из программы!");
    }

    /**
     * Метод для проверки нового выражения
     */
    private void checkNewExpression() {
        System.out.print("Введите выражение для проверки: ");
        String expression = _SC.nextLine();
        String message = BracketChecker.pushNewExpression(expression);
        System.out.println(message);

        if (message.equals("Выражение успешно добавлено")) {
            if (!BracketChecker._EXPRESSIONS.isEmpty()) {
                BracketChecker tempChecker = new BracketChecker(expression) {};
                String result = tempChecker.checkBrackets();
                System.out.println("Результат проверки: " + result);
            }
        }
    }

    /**
     * Метод выводит историю проверок
     */
    private void showAllChecks() {
        String message = BracketChecker.showAll();
        System.out.println(message);
    }

    /**
     * Метод для сохранения истории в файл
     */
    private void saveToFile() {
        System.out.print("Введите название файла: ");
        String filename = _SC.nextLine();

        boolean success = OutputWriter.saveToFile(filename);
        if (success) {
            System.out.println("История проверок сохранена в файл: " + filename);
        } else {
            System.out.println("Ошибка сохранения в файл: " + filename);
        }
    }
}