package mx.florinda.cardapio;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorItensCardapioComSocket {

    private static final Database database = new SQLDatabase();

    static void main(String[] args) throws Exception {

        try (ExecutorService executorService = Executors.newFixedThreadPool(50)) {
            try (ServerSocket serverSocket = new ServerSocket(8000)) {
                System.out.println("Subiu Servidor");

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
            System.out.println("-------------------------------------------");
            System.out.println(request);
            System.out.println("\r\n\r\nChegou um novo request");

            Thread.sleep(250);

            String[] requestChunks = getRequestLineChunks(request);

            String method = requestChunks[0];
            String requestURI = requestChunks[1];

            System.out.println("------------------------------------------------");
            System.out.println(method);
            System.out.println(requestURI);

            OutputStream clientOS = clientSocket.getOutputStream();
            PrintStream clientOut = new PrintStream(clientOS);

            if (method.equals("GET") && requestURI.equals("/itensCardapio.json")) {
                System.out.println("Chamou arquivo JSON");
                Path path = Path.of("itensCardapio.json");
                String json = Files.readString(path);

                clientOut.println("HTTP/1.1 200 OK");
                clientOut.println("Content-type: application/json; charset=UTF-8");
                clientOut.println();
                clientOut.println(json);
            } else if (method.equals("GET") && requestURI.equals("/itens-cardapio")) {
                System.out.println("Chamou listagem de itens cardapio");
                List<ItemCardapio> listaItemCardapios = database.listaDeItensCardapio();
                Gson gson = new Gson();
                String json = gson.toJson(listaItemCardapios);

                clientOut.println("HTTP/1.1 200 OK");
                clientOut.println("Content-type: application/json; charset=UTF-8");
                clientOut.println();
                clientOut.println(json);
            } else if (method.equals("GET") && requestURI.equals("/itens-cardapio/total")) {
                System.out.println("Chamou total de itens cardapio");
                List<ItemCardapio> listaItemCardapios = database.listaDeItensCardapio();
                int total = listaItemCardapios.size();

                clientOut.println("HTTP/1.1 200 OK");
                clientOut.println("Content-type: application/json; charset=UTF-8");
                clientOut.println();
                clientOut.println("Quantidade de itens: " + total);
            } else if (method.equals("POST") && requestURI.equals("/itens-cardapio")) {
                System.out.println("Chamou adição de itens cardapio");
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
            } else {
                System.out.println("URI " + requestURI + "não encontrada.");
                clientOut.println("HTTP/1.1 404 not found");
            }
        } catch (Exception e) {
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
