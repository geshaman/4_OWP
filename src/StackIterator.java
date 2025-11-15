import java.util.Stack;
import java.util.Iterator;

/**
 * Класс итератор для стека
 * @param <T> тип элементов в стеке
 */
public class StackIterator<T> extends Stack<T> {
    /** Поле, хранящее объект-итератор для обхода по коллекции*/
    private Iterator<T> _iterator;

    /** Возвращает следующий элемент в итерации */
    @Override
    public T pop() {
        return _iterator.next();
    }

    /** Проверяет, есть ли еще элементы для итерации */
    @Override
    public boolean empty() {
        return !_iterator.hasNext();
    }
}
