package mx.florinda.cardapio;

import java.text.NumberFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Comparator;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class TestesLocale {
    public static void main(String[] args) {
//        Stream.of(Locale.getAvailableLocales())
//                .sorted(Comparator.comparing(Locale::toString))
//                .forEach(System.out::println);

        System.out.println("Default locale: " + Locale.getDefault());

        Locale localeUS = Locale.US;
        Locale localePtBR = Locale.of("pt", "BR");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM/yyyy");

        System.out.println(formatter.format(ZonedDateTime.now()));
        System.out.println(formatter.withLocale(localeUS).format(ZonedDateTime.now()));
        System.out.println(formatter.withLocale(localePtBR).format(ZonedDateTime.now()));

        System.out.println(NumberFormat.getCurrencyInstance(localePtBR).format(2.99));
        System.out.println(NumberFormat.getCurrencyInstance(localeUS).format(2.99));

        ResourceBundle mensagens = ResourceBundle.getBundle("mensagens");
        ResourceBundle mensagensUS = ResourceBundle.getBundle("mensagens", localeUS);
        ResourceBundle mensagensBR = ResourceBundle.getBundle("mensagens", localePtBR);

        System.out.println(mensagens.getString("categoria.cardapio.entradas"));
        System.out.println(mensagensUS.getString("categoria.cardapio.entradas"));
        System.out.println(mensagensBR.getString("categoria.cardapio.entradas"));
    }
}
