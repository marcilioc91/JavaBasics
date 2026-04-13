public class Veiculo {
    private String marca;
    private String cor;
    private int ano;
    private double preco;
    private int velMaxima;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getVelMaxima() {
        return velMaxima;
    }

    public void setVelMaxima(int velMaxima) {
        this.velMaxima = velMaxima;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "marca='" + marca + '\'' +
                ", cor='" + cor + '\'' +
                ", ano=" + ano +
                ", preco=" + preco +
                ", velMaxima=" + velMaxima +
                '}';
    }

    public Veiculo(String marca, String cor, int ano, double preco, int velMaxima) {
        this.marca = marca;
        this.cor = cor;
        this.ano = ano;
        this.preco = preco;
        this.velMaxima = velMaxima;
    }
}
