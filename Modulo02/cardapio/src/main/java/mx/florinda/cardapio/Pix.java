package mx.florinda.cardapio;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

public class Pix implements Serializable {

    @Serial
    private  static final long serialVersionUID = 1L;

    private Long id;
    private BigDecimal valor;
    private String chaveDestino;
    private Instant dataHora;
    private String mensagem;

    public Pix() {
        System.out.println("Chamou construtor padrão.");
    }

    public Pix(Long id, BigDecimal valor, String chaveDestino, Instant dataHora, String mensagem) {
        this.id = id;
        this.valor = valor;
        this.chaveDestino = chaveDestino;
        this.dataHora = dataHora;
        this.mensagem = mensagem;

        System.out.println("Chamou construtor.");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getChaveDestino() {
        return chaveDestino;
    }

    public void setChaveDestino(String chaveDestino) {
        this.chaveDestino = chaveDestino;
    }

    @Override
    public String toString() {
        return "Pix{" +
                "id=" + id +
                ", valor=" + valor +
                ", chaveDestino='" + chaveDestino + '\'' +
                ", dataHora=" + dataHora +
                ", mensagem='" + mensagem + '\'' +
                '}';
    }
}

