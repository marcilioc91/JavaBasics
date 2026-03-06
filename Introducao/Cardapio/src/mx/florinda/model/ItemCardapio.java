package mx.florinda.model;

public class ItemCardapio {
    private long id;
    private String nome;
    private String descricao;
    private boolean emPromocao;
    private double preco;
    private double precoComDesconto;
    private CategoriaCardapio categoria;

    public ItemCardapio(long id, String nome, String descricao, double preco, CategoriaCardapio categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isEmPromocao() {
        return emPromocao;
    }

    public double getPreco() {
        return preco;
    }

    public double getPrecoComDesconto() {
        return precoComDesconto;
    }

    public CategoriaCardapio getCategoria() {
        return categoria;
    }

    public double setPorcentagemDesconto() {
        return (preco - precoComDesconto) / preco * 100;
    }

    public CategoriaCardapio obtemNomeCategoria() {
        return categoria;
    }

    public void setPromocao(double precoComDesconto) {
        emPromocao = true;
        this.precoComDesconto = precoComDesconto;
    }

    public double getImposto() {
        return emPromocao ? precoComDesconto * 0.1 : preco * 0.1;
    }
}
