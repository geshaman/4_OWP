import java.util.Stack;
import  java.util.Iterator;

public class StackIterator<T> {
    private Iterator<T> _iterator;

    public StackIterator(Stack<T> stack) {
        this._iterator = stack.iterator();
    }

    public T nextElement() {
        return  _iterator.next();
    }

    public boolean hasNextElement() {
        return _iterator.hasNext();
    }
}
