package mx.florinda.cardapio;

import java.util.List;
import java.util.Optional;

public interface Database {
    List<ItemCardapio> listaDeItensCardapio();

    Optional<ItemCardapio> itemCardapioPorId(Long itemId);

    void adicionaItemCardapio(ItemCardapio itemCardapio);
}
