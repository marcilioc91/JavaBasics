package mx.florinda.leitor;

import mx.florinda.model.ItemCardapio;

import java.io.IOException;

public interface LeitorItensCardapio {
    ItemCardapio[] processaArquivo(String nomeArquivo) throws IOException;
}
