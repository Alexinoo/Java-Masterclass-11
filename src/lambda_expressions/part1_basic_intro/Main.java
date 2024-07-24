package lambda_expressions.part1_basic_intro;

public class Main {
    public static void main(String[] args) {
        Foo foo = String::toUpperCase;
        System.out.println(foo.convertToUpperCase("alex"));
    }
}


