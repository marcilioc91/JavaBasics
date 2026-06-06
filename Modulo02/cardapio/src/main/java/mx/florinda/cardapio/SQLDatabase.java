package mx.florinda.cardapio;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SQLDatabase implements Database {

    @Override
    public List<ItemCardapio> listaDeItensCardapio() {

        ArrayList<ItemCardapio> itens = new ArrayList<>();

        String sql = "SELECT ic.id, ic.nome, ic.descricao, ic.categoria, ic.preco, ic.preco_promocional FROM ITEM_CARDAPIO ic";
        try (Connection connection =
                     DriverManager.getConnection("jdbc:mysql://localhost/cardapio", "root", "senha123");
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                String categoriaStr = rs.getString("categoria");
                BigDecimal preco = rs.getBigDecimal("preco");
                BigDecimal precoPromocional = rs.getBigDecimal("preco_promocional");

                ItemCardapio.CategoriaCardapio categoria = ItemCardapio.CategoriaCardapio.valueOf(categoriaStr);

                ItemCardapio itemCardapio = new ItemCardapio(id, nome, descricao, categoria, preco, precoPromocional);
                itens.add(itemCardapio);
            }

            return itens;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int totalItensCardapio() {
        String sql = "SELECT COUNT(*) total FROM ITEM_CARDAPIO";
        try (Connection connection =
                     DriverManager.getConnection("jdbc:mysql://localhost/cardapio", "root", "senha123");
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            int total = 0;
            if (rs.next())
                total = rs.getInt(1);
            return total;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<ItemCardapio> itemCardapioPorId(Long itemId) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public void adicionaItemCardapio(ItemCardapio itemCardapio) {
        String sql = "INSERT INTO ITEM_CARDAPIO (ID, NOME, DESCRICAO, CATEGORIA, PRECO, PRECO_PROMOCIONAL) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection =
                     DriverManager.getConnection("jdbc:mysql://localhost/cardapio", "root", "senha123");
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, itemCardapio.id());
            ps.setString(2, itemCardapio.nome());
            ps.setString(3, itemCardapio.descricao());
            ps.setString(4, itemCardapio.categoria().name());
            ps.setBigDecimal(5, itemCardapio.preco());
            ps.setBigDecimal(6, itemCardapio.precoComDesconto());

            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
