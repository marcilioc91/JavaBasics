package mx.florinda.leitor;

import com.google.gson.Gson;
import mx.florinda.model.ItemCardapio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LeitorItensCardapioGSON implements LeitorItensCardapio {

    private final String nomeArquivo;

    public LeitorItensCardapioGSON(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    @Override
    public ItemCardapio[] processaArquivo() throws IOException {
        String conteudoArquivo = Files.readString(Path.of(nomeArquivo));
        return new Gson().fromJson(conteudoArquivo, ItemCardapio[].class);
    }
}
