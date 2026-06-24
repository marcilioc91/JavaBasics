package mx.florinda.cardapio;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mysql.cj.conf.PropertyKey.logger;

public class ServidorItensCardapioComSocket {

    private static final Logger logger = Logger.getLogger(ServidorItensCardapioComSocket.class.getName());

    private static final Database database = new SQLDatabase();

    static void main(String[] args) throws Exception {

        try (ExecutorService executorService = Executors.newFixedThreadPool(50)) {
            try (ServerSocket serverSocket = new ServerSocket(8000)) {
                logger.info("Subiu Servidor");

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    executorService.execute(() -> trataRequisicao(clientSocket));
                }
            }
        }
    }

    private static void trataRequisicao(Socket clientSocket) {

        try (clientSocket) {
            InputStream clientIS = clientSocket.getInputStream();

            StringBuilder requestBuilder = new StringBuilder();

            int data;
            do {
                data = clientIS.read();
                requestBuilder.append((char) data);
            } while (clientIS.available() > 0);

            String request = requestBuilder.toString();
            logger.finest(request);
            logger.fine("\r\n\r\nChegou um novo request");

            Thread.sleep(250);

            String[] requestChunks = getRequestLineChunks(request);

            String method = requestChunks[0];
            String requestURI = requestChunks[1];
            String httpVersion = requestChunks[2];

            logger.finer(() -> "Method:" + method);
            logger.finer(() -> "Request URI" + requestURI);
            logger.finer(() -> "HTTP Version" + httpVersion);

            OutputStream clientOS = clientSocket.getOutputStream();
            PrintStream clientOut = new PrintStream(clientOS);

            if (method.equals("GET") && requestURI.equals("/itensCardapio.json")) {
                logger.fine("Chamou arquivo JSON");
                Path path = Path.of("itensCardapio.json");
                String json = Files.readString(path);

                clientOut.println("HTTP/1.1 200 OK");
                clientOut.println("Content-type: application/json; charset=UTF-8");
                clientOut.println();
                clientOut.println(json);
            } else if (method.equals("GET") && requestURI.equals("/itens-cardapio")) {
                logger.fine("Chamou listagem de itens cardapio");
                List<ItemCardapio> listaItemCardapios = database.listaDeItensCardapio();
                Gson gson = new Gson();
                String json = gson.toJson(listaItemCardapios);

                clientOut.println("HTTP/1.1 200 OK");
                clientOut.println("Content-type: application/json; charset=UTF-8");
                clientOut.println();
                clientOut.println(json);
            } else if (method.equals("GET") && requestURI.equals("/itens-cardapio/total")) {
                logger.fine("Chamou total de itens cardapio");
                List<ItemCardapio> listaItemCardapios = database.listaDeItensCardapio();
                int total = listaItemCardapios.size();

                clientOut.println("HTTP/1.1 200 OK");
                clientOut.println("Content-type: application/json; charset=UTF-8");
                clientOut.println();
                clientOut.println("Quantidade de itens: " + total);
            } else if (method.equals("POST") && requestURI.equals("/itens-cardapio")) {
                logger.fine("Chamou adição de itens cardapio");
                String[] requestBody = request.split("\r\n\r\n");
                if (requestBody.length == 1) {
                    clientOut.println("HTTP/1.1 400 bad request");
                    return;
                }
                String body = requestBody[1];
                Gson gson = new Gson();
                ItemCardapio novoItemCardapio = gson.fromJson(body, ItemCardapio.class);

                database.adicionaItemCardapio(novoItemCardapio);

                clientOut.println("HTTP/1.1 201 Created");
                clientOut.println("Content-type: application/json; charset=UTF-8");
                clientOut.println();
            } else if ("GET".equals(method) && "/".equals(requestURI)) {
                List<ItemCardapio> listaItensCardapio = database.listaDeItensCardapio();

                Locale locale = "/en".equals(requestURI) ? Locale.US : Locale.of("pt", "BR");
                NumberFormat formatadorMoeda = NumberFormat.getCurrencyInstance(locale);
                ResourceBundle mensagens = ResourceBundle.getBundle("mensagens", locale);
                DateTimeFormatter formatadorDataHora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(locale);
                DateTimeFormatter formatadorMA = DateTimeFormatter.ofPattern("MMMM/yyyy").withLocale(locale);

                StringBuilder htmlTodosItens = new StringBuilder();

                for (ItemCardapio item : listaItensCardapio) {
                    String precoItem;
                    if (item.precoComDesconto() == null)
                        precoItem = "<strong>" + formatadorMoeda.format(item.preco()) + "</strong>";
                    else
                        precoItem = "<mark>Em promoção</mark> <strong>" +
                                formatadorMoeda.format(item.precoComDesconto()) + "</strong> <s>R$ " + formatadorMoeda.format(item.preco()) + "</s>";

                    String categoria = mensagens.getString("categoria.cardapio." + item.categoria().name().toLowerCase());

                    String htmlItem = """
                                <article>
                                    <kbd>%s</kbd>
                                    <h3>%s</h3>
                                    <p>%s</p>
                                    %s
                                </article>
                            """.formatted(categoria, item.nome(), item.descricao(), precoItem);
                    htmlTodosItens.append(htmlItem);
                }
                String html = """
                        <!DOCTYPE html>
                        <html lang="en">
                        <head>
                            <meta charset="UTF-8">
                            <title>Florinda Eats - Cardápio</title>
                            <link rel="stylesheet"
                            href="https://cdn.jsdelivr.net/npm/@picocss/pico@2.1.1/css/pico.min.css">
                        </head>
                        <body>
                            <header class="container">
                                <hgroup>
                                    <h1>Florinda Eats</h1>
                                    <p>O sabor da Vila direto pra você</p>
                                </hgroup>
                            </header>

                            %s

                            <footer class="container">
                                <p><small><em>Preços de acordo com %s</em></small></p>
                                <p><strong>Florinda Eats</strong> Todos os direitos reservados - %s</p>
                            </footer>
                        </body>
                        </html>
                        """.formatted(htmlTodosItens.toString(), formatadorDataHora.format(ZonedDateTime.now()), formatadorMA.format(ZonedDateTime.now()));

                clientOut.print("HTTP/1.1 200 OK\r\n");
                clientOut.print("Content-type: text/html; charset=UTF-8\r\n\r\n");
                clientOut.print(html);
                clientOut.print("\r\n");
            } else {
                logger.warning(() -> "URI " + requestURI + "não encontrada.");
                clientOut.println("HTTP/1.1 404 not found");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro no servidor", e);
            throw new RuntimeException(e);
        }
    }

    private static String[] getRequestLineChunks(String request) {
        String[] requestChunks = request.split("\r\n\r\n");
        String requestLineAndHeaders = requestChunks[0];
        String[] requestLineAndHeadersChunks = requestLineAndHeaders.split("\r\n");
        String requestLine = requestLineAndHeadersChunks[0];
        String[] requestLineChunks = requestLine.split(" ");
        return requestLineChunks;
    }
}
