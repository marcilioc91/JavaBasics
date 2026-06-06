package mx.florinda.cardapio;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GeradorItensCardapio {
    static void main() throws IOException {

        Database database = new InMemoryDatabase();
        List<ItemCardapio> lista = database.listaDeItensCardapio();
        Gson gson = new Gson();

        String json = gson.toJson(lista);

        Path path = Path.of("itensCardapio.json");

        Files.writeString(path, json);
    }
}
