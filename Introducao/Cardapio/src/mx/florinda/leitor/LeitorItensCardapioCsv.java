package mx.florinda.leitor;

import mx.florinda.model.CategoriaCardapio;
import mx.florinda.model.ItemCardapio;
import mx.florinda.model.isento.ItemCardapioIsento;

public class LeitorItensCardapioCsv extends LeitorItensCardapioBase {

    private static final String SEPARADOR = ";"; // constante

    public LeitorItensCardapioCsv(String nomeArquivo) {
        super(nomeArquivo);
    }

    @Override
    protected ItemCardapio processaLinha(String linha) {
        String[] partes = linha.split(SEPARADOR);

        long id = Long.parseLong(partes[0]);
        String nome = partes[1];
        String descricao = partes[2];
        double preco = Double.parseDouble(partes[3]);
        CategoriaCardapio categoria = CategoriaCardapio.valueOf(partes[4]);

        ItemCardapio item;
        boolean impostoIsento = Boolean.parseBoolean(partes[7]);
        if (impostoIsento) {
            item = new ItemCardapioIsento(id, nome, descricao, preco, categoria);
        } else {
            item = new ItemCardapio(id, nome, descricao, preco, categoria);
        }

        boolean emPromocao = Boolean.parseBoolean(partes[5]);
        if (emPromocao) {
            double precoDesconto = Double.parseDouble(partes[6]);
            item.setPromocao(precoDesconto);
        }

        return item;
    }

}