import java.util.Stack;

/**
 * Класс для проверки корректности расстановки скобок в выражении
 */
public abstract class BracketChecker {
    private String _expression;

    /** Статическое поле для хранения истории всех проверенных выражений */
    public final static Stack<String> _EXPRESSIONS = new Stack<>();

    /**
     * Статический метод для добавления нового выражения в историю
     * @param expression выражение для проверки
     * @return сообщение о результате операции
     */
    public static String pushNewExpression(String expression) {
        if (expression == null || expression.trim().isEmpty())
            return "Выражение не может быть пустым";

        _EXPRESSIONS.add(expression);
        return "Выражение успешно добавлено";
    }

    /**
     * Проверяет корректность расстановки скобок в выражении
     * @return сообщение о результате проверки
     */
    public static String checkBrackets(String expression) {
        Stack<Character> bracketStack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == '{' || c == '(' || c == '[') {
                bracketStack.push(c);
            }

            else if (c == '}' || c == ')' || c == ']') {
                if (bracketStack.isEmpty()) {
                    return "Некорректно: закрывающая скобка без открывающей";
                }

                char lastBracket = bracketStack.pop();
                if (!isMatchingPair(lastBracket, c)) {
                    return "Некорректно: несоответствие типов скобок";
                }
            }
        }

        if (!bracketStack.isEmpty()) {
            return "Некорректно: имеются незакрытые скобки";
        }

        return "Строка корректна";
    }

    /**
     * Проверяет, являются ли скобки парными
     * @param open открывающая скобка
     * @param close закрывающая скобка
     * @return true если скобки парные, false в противном случае
     */
    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '{' && close == '}') ||
                (open == '[' && close == ']');
    }

    /**
     * Показывает историю всех проверенных выражений
     * @return строка с историей проверок
     */
    public static String showAll() {
        if (_EXPRESSIONS.isEmpty())
            return "История проверок пуста! ";

        StringBuilder sb = new StringBuilder("История проверок: \n");
        int counter = 1;

        for (String expr : _EXPRESSIONS) {
            String result = checkBrackets(expr);

            sb.append("Проверка ").append(counter).append(":\n")
                    .append("Выражение: ").append(expr)
                    .append("\nРезультат: ").append(result)
                    .append("\n---\n");
            counter++;
        }
        return sb.toString();
    }
}