package mx.florinda.cardapio;

import java.math.BigDecimal;

public record Cardapio(long id, String nome, String descricao, CategoriaCardapio categoria, BigDecimal preco,
                       BigDecimal precoComDesconto) {
    public enum CategoriaCardapio {
        ENTRADAS, PRATOS_PRINCIPAIS, BEBIDAS, SOBREMESA;
    }

}
