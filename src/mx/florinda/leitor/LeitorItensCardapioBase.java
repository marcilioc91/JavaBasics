package mx.florinda.leitor;

import mx.florinda.model.ItemCardapio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class LeitorItensCardapioBase implements LeitorItensCardapio {

    private final String nomeArquivo;

    public LeitorItensCardapioBase(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    @Override
    public ItemCardapio[] processaArquivo() throws IOException {
        Path arquivo = Path.of(nomeArquivo);
        String conteudoArquivo = Files.readString(arquivo);
        String[] linhasArquivo = conteudoArquivo.split("\n");

        ItemCardapio[] itens = new ItemCardapio[linhasArquivo.length - 1];

        for (int i = 1; i < linhasArquivo.length; i++) {
            String linha = linhasArquivo[i];

            ItemCardapio item = processaLinha(linha);

            itens[i - 1] = item;

        }

        return itens;
    }

    protected abstract ItemCardapio processaLinha(String linha);

}