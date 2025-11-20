import java.io. *;

/**
 * Абстрактный класс для записи информации в файл
 */
public abstract class OutputWriter {
    /**
     * Метод сохраняет информацию в файл
     * @param filename имя файла
     * @return true - если запись успешна, false - в противном случае
     */
    public static boolean saveToFile(String filename) {

        try (FileOutputStream fos = new FileOutputStream(filename)) {
            PrintStream outputWriter = new PrintStream(fos);
            outputWriter.println(BracketChecker.showAll());
            outputWriter.close();
            return true;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод проверки существования файла
     * @param filename имя файла
     * @return true - если существует, false - в противном случае
     */
    public static boolean isFileExist(String filename) {
        File f = new File(filename);
        return f.exists();
    }

    /**
     * Метод для открытия файла на чтение
     * @param filename имя файла
     * @return BufferedReader для чтения файла
     * @throws IOException если файл не найден или ошибка доступа
     */
    public static BufferedReader openFileForReading(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            throw new FileNotFoundException("Файл не найден: " + filename);
        }
        if (!file.canRead()) {
            throw new IOException("Нет прав на чтение файла: " + filename);
        }
        return new BufferedReader(new FileReader(file));
    }

    /**
     * Метод для открытия файла на запись
     * @param filename имя файла
     * @param append true - дописывать в конец, false - перезаписать
     * @return PrintWriter для записи в файл
     * @throws IOException если ошибка создания файла или доступа
     */
    public static PrintWriter openFileForWriting(String filename, boolean append) throws IOException {
        File file = new File(filename);
        // Проверяем возможность создания файла
        if (file.exists() && !file.canWrite()) {
            throw new IOException("Нет прав на запись в файл: " + filename);
        }
        return new PrintWriter(new FileWriter(file, append));
    }

    /**
     * Метод для закрытия ресурса чтения
     * @param reader BufferedReader для закрытия
     */
    public static void closeReader(BufferedReader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                System.err.println("Ошибка при закрытии reader: " + e.getMessage());
            }
        }
    }

    /**
     * Метод для закрытия ресурса записи
     * @param writer PrintWriter для закрытия
     */
    public static void closeWriter(PrintWriter writer) {
        if (writer != null) {
            writer.close();
        }
    }

    /**
     * Метод для чтения всего содержимого файла
     * @param filename имя файла
     * @return содержимое файла в виде строки
     * @throws IOException если ошибка чтения
     */
    public static String readFile(String filename) throws IOException {
        BufferedReader reader = null;
        try {
            reader = openFileForReading(filename);
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            return content.toString().trim();
        } finally {
            closeReader(reader);
        }
    }

    /**
     * Метод для записи строки в файл
     * @param filename имя файла
     * @param content содержимое для записи
     * @param append true - дописывать в конец, false - перезаписать
     * @return true - если запись успешна
     */
    public static boolean writeToFile(String filename, String content, boolean append) {
        PrintWriter writer = null;
        try {
            writer = openFileForWriting(filename, append);
            writer.print(content);
            writer.flush();
            return true;
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
            return false;
        } finally {
            closeWriter(writer);
        }
    }

    /**
     * Метод для удаления файла
     * @param filename имя файла
     * @return true - если удаление успешно, false - в противном случае
     */
    public static boolean deleteFile(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * Метод для получения информации о файле
     * @param filename имя файла
     * @return строка с информацией о файле
     */
    public static String getFileInfo(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            return "Файл не существует: " + filename;
        }

        return String.format(
                "Информация о файле '%s':\n" +
                        "Размер: %d байт\n" +
                        "Последнее изменение: %s\n" +
                        "Путь: %s\n" +
                        "Читаемый: %s, Записываемый: %s",
                filename,
                file.length(),
                new java.util.Date(file.lastModified()),
                file.getAbsolutePath(),
                file.canRead() ? "да" : "нет",
                file.canWrite() ? "да" : "нет"
        );
    }
}
