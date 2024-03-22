package news.function;

@FunctionalInterface
public interface StringUnaryOperator {
    // functional profile: String -> String
    String call(String string);
}
