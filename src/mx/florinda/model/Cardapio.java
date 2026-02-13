package mx.florinda.model;

import mx.florinda.leitor.FabricaLeitorItensCardapio;
import mx.florinda.leitor.LeitorItensCardapio;

import java.io.IOException;

public class Cardapio {

    private final ItemCardapio[] itens;

    public Cardapio(String nomeArquivo) throws IOException {
        FabricaLeitorItensCardapio fabricaLeitor = new FabricaLeitorItensCardapio();
        LeitorItensCardapio leitor = fabricaLeitor.criaLeitor(nomeArquivo);
        if (leitor != null)
            itens = leitor.processaArquivo(nomeArquivo);
        else{
            itens = new ItemCardapio[0];
            IO.println("Tipo de arquivo ou extensão é inválida: " + nomeArquivo);
        }
    }

    public double getSomaDosPrecos() {
        double totalDePrecos = 0.0;
        for (ItemCardapio item : itens) {
            totalDePrecos += item.getPreco();
        }
        return totalDePrecos;
    }

    public int getTotalDeItensEmPromocao() {
        int totalItensEmPromocao = 0;
        for (ItemCardapio item : itens) {
            if (item.isEmPromocao()) {
                totalItensEmPromocao++;
            }
        }
        return totalItensEmPromocao;
    }

    public double getPrimeiroPrecoMaiorQueLimite(double precoLimite) {
        double precoMaiorQueLimite = -1.0;
        for (ItemCardapio item : itens) {
            if (item.getPreco() > precoLimite) {
                precoMaiorQueLimite = item.getPreco();
                break;
            }
        }
        return precoMaiorQueLimite;
    }

    public ItemCardapio getItemPorId(long idSelecionado) {
        return itens[((int) idSelecionado) - 1];
    }

    public ItemCardapio[] getItens() {
        return itens;
    }
}