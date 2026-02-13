package mx.florinda.leitor;

public class FabricaLeitorItensCardapio {
    public LeitorItensCardapio criaLeitor(String nomeArquivo) {
        LeitorItensCardapio leitor = null;
        if (nomeArquivo.endsWith(".csv")) {
            return new LeitorItensCardapioCsv();
        } else if (nomeArquivo.endsWith(".json")) {
            return new LeitorItensCardapioJson();
        } else {
            return leitor;
        }
    }
}
