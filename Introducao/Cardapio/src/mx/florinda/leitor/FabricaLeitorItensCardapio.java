package mx.florinda.leitor;

public class FabricaLeitorItensCardapio {
    public static LeitorItensCardapio criaLeitor(String nomeArquivo) throws Exception {
        if (nomeArquivo.endsWith(".csv")) {
            return new LeitorItensCardapioCsv(nomeArquivo);
        } else if (nomeArquivo.endsWith(".json")) {
            return new LeitorItensCardapioGSON(nomeArquivo);
        } else {
            throw new Exception("Extensão do arquivo é inválida: " + nomeArquivo);
        }
    }
}
