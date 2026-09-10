package mx.florinda.cardapio;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.time.Instant;

public class SerializadorPix {

    static void main(String[] args) throws Exception {
        var pix = new Pix(
                1L, new BigDecimal("10.99"),
                "marcilioccsilva@fac.pe.senac.br",
                Instant.now(), "quitação total das dívidas"
        );

        try (var fos = new FileOutputStream("pix.ser");
        var oos = new ObjectOutputStream(fos)){
            oos.writeObject(pix);
        }
    }
}
