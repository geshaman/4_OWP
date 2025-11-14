import java.util.Stack;

public class BracketChecker {
    private String _expression;

    public final static Stack<BracketChecker> _EXPRESSIONS = new Stack<>();

    public BracketChecker() {
        this._expression = "";
    }

    public BracketChecker(String expression) {
        this._expression = expression;
    }

    public String getExpression() {
        return this._expression;
    }

    public static String pushNewExpression(String expression) {
        if (expression == null || expression.trim().isEmpty())
            return "Выражение не может быть пустым";

        BracketChecker bc = new BracketChecker(expression);
        _EXPRESSIONS.add(bc);
        return "Выражение успешно добавлено";
    }

    public String checkBrackets() {
        Stack<Character> bracketStack = new Stack<>();

        for (int i = 0; i < _expression.length(); i++) {
            char c = _expression.charAt(i);

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

        if(!bracketStack.isEmpty()) {
            return "Некорректно: имеются незакрытые скобки";
        }

        return "Строка корректна";
    }

    private boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '{' && close == '}') ||
                (open == '[' && close == ']');
    }

    public static String showAll() {
        if (_EXPRESSIONS.isEmpty())
            return "История проверок пуста! ";

        StringBuilder sb = new StringBuilder("История проверок: \n\n");

        StackIterator<BracketChecker> iterator = new StackIterator<>(_EXPRESSIONS);
        int counter = 1;

        while (iterator.hasNextElement()) {
            BracketChecker checker = iterator.nextElement();
            String result = checker.checkBrackets();
            sb.append("Проверка ").append(counter).append(":\n")
                    .append("Выражение: ").append(checker.getExpression())
                    .append("\nРезультат: ").append(result)
                    .append("\n---\n");
            counter++;
        }
        return sb.toString();
    }
}
