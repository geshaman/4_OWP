import java.util.Stack;
import java.util.Iterator;

/**
 * Класс итератор для стека
 * @param <T> тип элементов в стеке
 */
public class StackIterator<T> extends Stack<T> {
    /** Поле, хранящее объект-итератор для обхода по коллекции*/
    private Iterator<T> _iterator;

    /**
     * Инициализирует итератор
     */
    public void initIterator() {
        this._iterator = super.iterator();
    }

    /**
     * Возвращает следующий элемент в итерации
     * Переопределяет стандартный pop() для работы через итератор
     */
    @Override
    public T pop() {
        return _iterator.next();
    }

    /**
     * Проверяет, есть ли еще элементы для итерации
     * Переопределяет стандартный empty() для работы через итератор
     */
    @Override
    public boolean empty() {
        return !_iterator.hasNext();
    }

}