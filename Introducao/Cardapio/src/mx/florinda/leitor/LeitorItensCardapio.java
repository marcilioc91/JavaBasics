package mx.florinda.leitor;

import mx.florinda.model.ItemCardapio;

import java.io.IOException;

public interface LeitorItensCardapio {

    ItemCardapio[] processaArquivo() throws IOException;

    static LeitorItensCardapio criaLeitor(String nomeArquivo) {
        LeitorItensCardapio leitor = null;
        if (nomeArquivo.endsWith(".csv")) {
            leitor = new LeitorItensCardapioCsv(nomeArquivo);
        } else if (nomeArquivo.endsWith(".json")) {
            leitor = new LeitorItensCardapioGSON(nomeArquivo);
        }
        return leitor;
    }
}