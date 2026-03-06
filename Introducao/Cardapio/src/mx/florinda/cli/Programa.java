import mx.florinda.model.Cardapio;
import mx.florinda.model.ItemCardapio;

void main() throws Exception {
    String nomeArquivo = IO.readln("Digite um nome de arquivo de itens de cardápio: ");

    Cardapio cardapio = new Cardapio(nomeArquivo);

    String linha = IO.readln("Digite um id de um item de cardápio: ");
    long idSelecionado = Long.parseLong(linha);

    mx.florinda.model.ItemCardapio ItemCardapio = cardapio.getItemPorId(idSelecionado);

    IO.println("== Item do Cardápio ==");
    IO.println("Id: " + ItemCardapio.getId());
    IO.println("Nome: " + ItemCardapio.getNome());
    IO.println("Descrição: " + ItemCardapio.getDescricao());
    if (ItemCardapio.isEmPromocao()) {
        IO.println("Item em promoção! 🤑");
        double porcentagemDesconto = ItemCardapio.setPorcentagemDesconto();
        IO.println("Preco: de " + ItemCardapio.getPreco() + " por " + ItemCardapio.getPrecoComDesconto());
        IO.println("Porcentagem de desconto: " + porcentagemDesconto);
    } else {
        IO.println("Preco: " + ItemCardapio.getPreco());
        IO.println("Item não está em promoção");
    }
    IO.println("Categoria: " + ItemCardapio.getCategoria());
    IO.println("Imposto: " + ItemCardapio.getImposto());

    IO.println("-------");

    IO.println("Soma dos preços: " + cardapio.getSomaDosPrecos());
    IO.println("Total de itens em promoção: " + cardapio.getTotalDeItensEmPromocao());

    double precoLimite = 10.0;
    IO.println("O primeiro preço que é maior que " + precoLimite + ": " + cardapio.getPrimeiroPrecoMaiorQueLimite(precoLimite));

    IO.println("-------");

    for (ItemCardapio item : cardapio.getItens()) {
        if (item.getPreco() <= precoLimite) {
            IO.println("Preço menor que " + precoLimite + ": " + item.getPreco());
        }
    }
}