import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainClass {
    static void main(String[] args) {
        List<Veiculo> lista = new ArrayList<>() {{
            add(new Veiculo("Corsa", "Cinza", 1998, 25000, 160));
            add(new Veiculo("Corolla", "Prata", 2020, 70000, 200));
            add(new Veiculo("Corolla", "Preto", 2022, 100000, 200));
            add(new Veiculo("208", "Perla Nera", 2022, 80000, 220));
            add(new Veiculo("2008", "Branco Perolizado", 2022, 134000, 260));
        }};

        List<Veiculo> listaOrdenada =
                lista.stream().sorted(Comparator.comparing(Veiculo::getPreco).reversed()).toList(); //comparando do mais caro ao mais baratoList<Veiculo> listaOrdenada =

        List<Veiculo> filtroMarca =
                lista.stream().filter(v -> v.getMarca().equalsIgnoreCase("corolla")).toList(); //filtrando por nome

        double precoMedio = lista.stream().mapToDouble(v -> v.getPreco()).average().orElse(0.0);
        System.out.println(precoMedio);
        double precoMaximo = lista.stream().mapToDouble(Veiculo::getPreco).max().orElseThrow(null);
        System.out.println(precoMaximo);
        double precoMinimo = lista.stream().mapToDouble(Veiculo::getPreco).min().orElseThrow(null);
        System.out.println(precoMinimo);

        double mediaCorollas =
                lista.stream().filter(v -> v.getMarca().equalsIgnoreCase("corolla"))
                        .mapToDouble(v -> v.getPreco()).average().orElse(0.0);
        System.out.println("Média dos Corollas: " + mediaCorollas);

        List<Veiculo> listaCorolla =
                lista.stream().filter(v -> v.getMarca().equalsIgnoreCase("corolla"))
                        .map(v -> converterParaMaiusculo(v)).toList();

        System.out.println(listaCorolla);
    }

    public static Veiculo converterParaMaiusculo(Veiculo v){
        return new Veiculo(v.getMarca().toUpperCase(), v.getCor().toUpperCase(), v.getAno(), v.getPreco(), v.getVelMaxima());
    }
}
