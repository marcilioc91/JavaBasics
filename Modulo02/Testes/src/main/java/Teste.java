import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Teste {
    static void main(String[] args) {
//        Optional<String> nome = Optional.ofNullable(obterNome());
//        System.out.println("None: " + nome.orElse("Desconhecido"));
//        nome.filter(s -> s.startsWith("J")).ifPresent(System.out::println);

        Map<String, Integer> estoque = new HashMap<>();
        estoque.put("Café", 10);
        estoque.put("Café", 15);
        estoque.put("Chá", 5);

        System.out.println(estoque.get("Café"));
    }

    private static String obterNome() {
        return null;
    }

}
