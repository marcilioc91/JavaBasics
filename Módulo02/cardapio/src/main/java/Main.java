import com.google.gson.Gson;
import mx.florinda.cardapio.Cardapio;

import java.math.BigDecimal;

import static mx.florinda.cardapio.Cardapio.CategoriaCardapio.*;

public class Main {
    static void main() {
        Cardapio refresco = new Cardapio(1, "Refresco", """
                Suco de limão que parece tamarindo mas tem gosto de groselha""",
                BEBIDAS,
                new BigDecimal("2.99"),
                null
        );

        Gson gson = new Gson();
        String json = gson.toJson(refresco);

        System.out.println(json);
    }
}
