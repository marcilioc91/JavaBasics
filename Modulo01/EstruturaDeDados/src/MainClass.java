import java.time.Clock;
import java.util.*;

public class MainClass {
    static void main() {
        List<Produto> lista = new ArrayList<>();
        lista.add((new Produto(1, "Computador", 1500.0)));
        lista.add((new Produto(1, "Computador", 1500.0)));
        lista.add((new Produto(1, "Computador", 1500.0)));
        lista.add((new Produto(2, "Mouse", 50.0)));
        lista.add((new Produto(3, "Teclado", 100.0)));
        IO.println(lista);

        Set<Produto> conjunto = new HashSet<>();
        conjunto.add(new Produto(1, "Computador", 1500.00));
        conjunto.add(new Produto(1, "Computador", 1500.00)); //daqui em diante é ignorado
        conjunto.add(new Produto(1, "Computador", 1500.00));
        conjunto.add(new Produto(1, "Computador", 1500.00));
        conjunto.add(new Produto(1, "Computador", 1500.00));
        conjunto.add(new Produto(1, "Computador", 1500.00));
        conjunto.add(new Produto(1, "Computador", 1500.00));
        conjunto.add(new Produto(1, "Computador", 1500.00));
        IO.println(conjunto);

        Map<Integer, Produto> mapa = new HashMap<>();
        mapa.put(1, new Produto(1, "computador", 1500.0));
        mapa.put(2, new Produto(2, "mouse", 50.0));
        mapa.put(3, new Produto(3, "Teclado", 100.0));
        IO.println(mapa);

//        benchmarkList(10000);
        benchmarkMapa(100000);
    }

    public static void benchmarkList(int tamanho) {
        List<Produto> lista = new ArrayList<>();
        for (int i = 0; i < tamanho; i++) {
            lista.add(new Produto(i + 1, "Produto " + i + 1, (i + 1) * 10));
        }
        int itemBusca = tamanho - 1;
        long ini, fim;
        ini = System.currentTimeMillis();
        for (int count = 1; count <= 1000; count++) {
            for (Produto p : lista) {
                if (p.getId() == itemBusca)
                    break;
            }

        }
        fim = System.currentTimeMillis();

        IO.println("Demorou " + (fim - ini) + " ms para a busca");
    }

    public static void benchmarkMapa(int tamanho) {
        Map<Integer, Produto> mapa = new HashMap<>();
        for (int i = 0; i <= tamanho; i++) {
            mapa.put(i + 1, new Produto(i + 1, "Produto " + i + 1, (i + 1) * 10));
        }
        int itemBusca = tamanho - 1;
        long ini, fim;
        ini = System.currentTimeMillis();
        for (int count = 1; count <= 1000; count++) {
            if (mapa.get(itemBusca) != null)
                break;
        }
        fim = System.currentTimeMillis();

        IO.println("Demorou " + (fim - ini) + " ms para a busca");
    }
}
