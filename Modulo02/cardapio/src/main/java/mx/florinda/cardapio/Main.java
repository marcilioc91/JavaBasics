package mx.florinda.cardapio;

import java.util.*;

import static mx.florinda.cardapio.ItemCardapio.CategoriaCardapio.*;

public class Main {
    static void main() {
        Database database = new Database();
//        List<ItemCardapio> itens = database.listaDeItensCardapio();

//        Set<ItemCardapio.CategoriaCardapio> categorias = new HashSet<>();
//        for (ItemCardapio item : itens) {
//            ItemCardapio.CategoriaCardapio categoria = item.categoria();
//            categorias.add(categoria);
//        }
//        for (ItemCardapio.CategoriaCardapio categoria : categorias) {
//            System.out.println(categoria);
//        }

        /*Optional<ItemCardapio> optionalItem = database.itemCardapioPorId(1L);
        String mensagem = optionalItem.map(ItemCardapio::toString).orElse("Não encontrado");
        System.out.println(mensagem);

        System.out.println("===========================");

        Set<ItemCardapio.CategoriaCardapio> categoriaPromocoes = new TreeSet<>();
        categoriaPromocoes.add(SOBREMESA);
        categoriaPromocoes.add(ENTRADAS);
        categoriaPromocoes.forEach(System.out::println);

        System.out.println("-----------------------");

        Set<ItemCardapio.CategoriaCardapio> categoriaPromocoes2 = Set.of(SOBREMESA, ENTRADAS);
        categoriaPromocoes2.forEach(System.out::println);
//        categoriaPromocoes2.add(PRATOS_PRINCIPAIS); formato não permite adição

        System.out.println("-----------------------");

        Set<ItemCardapio.CategoriaCardapio> categoriaPromocoes3 = EnumSet.of(SOBREMESA, ENTRADAS);
        categoriaPromocoes3.add(PRATOS_PRINCIPAIS);
        categoriaPromocoes3.forEach(System.out::println);

        System.out.println("-----------------------");

        //Descrições associadas as categorias em promoção.
        Map<ItemCardapio.CategoriaCardapio, String> promocoes = new EnumMap<>(ItemCardapio.CategoriaCardapio.class);
        promocoes.put(SOBREMESA, "O doce perfeito para você!");
        promocoes.put(ENTRADAS, "Comece a sua refeição com um grande toque de sabor!");
        System.out.println(promocoes);*/

        //Criar histórico de visualização
        HistoricoVisualizacao historico = new HistoricoVisualizacao(database);
        historico.registrarVisualizacao(1L);
        historico.registrarVisualizacao(2L);
        historico.registrarVisualizacao(5L);
        historico.registrarVisualizacao(6L);

        historico.mostrarItensVisualizados();
        historico.listarItensVisualizados();
    }
}