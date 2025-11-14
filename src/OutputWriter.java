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

}
