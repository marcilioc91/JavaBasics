package mx.florinda.cardapio;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    static void main(String[] args) {
        SQLDatabase sql = new SQLDatabase();
        List<ItemCardapio> listaItemCardapios = sql.listaDeItensCardapio();

        listaItemCardapios.forEach(System.out::println);

        System.out.println("==================================================");

        System.out.println("Total de itens no banco: " + sql.totalItensCardapio());

        System.out.println("==================================================");

//        ItemCardapio itemCardapio = new ItemCardapio(10L, "Pavê", "Sobremesa bastante recheado", ItemCardapio.CategoriaCardapio.SOBREMESA,
//                new BigDecimal(5.0), new BigDecimal(4.0));
//
//        sql.adicionaItemCardapio(itemCardapio);
    }
}