package src;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.Scanner;
import src.TexFile;
import com.google.gson.Gson;
import src.Module;

public class RequestHandler implements HttpHandler {
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestMethod = httpExchange.getRequestMethod();
        if (requestMethod.equals("POST")) {
            handlePostRequest(httpExchange);
        }
        else if (requestMethod.equals("GET")) {
            handleGetRequest(httpExchange);
        }
    }

    private void handlePostRequest(HttpExchange httpExchange) throws IOException {
        InputStream inputStream = httpExchange.getRequestBody();
        OutputStream outputStream = httpExchange.getResponseBody();
        Scanner scanner = new Scanner(inputStream);
        String json = scanner.hasNext() ? scanner.next() : "";
        Gson gson = new Gson();
        TexFile texFile = new TexFile("tex/temp.tex", gson.fromJson(json, Module[].class));
        String response = "Received";
        httpExchange.sendResponseHeaders(200, response.length());
        outputStream.write(response.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    private void handleGetRequest(HttpExchange httpExchange) throws IOException {
        OutputStream outputStream = httpExchange.getResponseBody();
        httpExchange.sendResponseHeaders(404, 0);
        outputStream.write(null);
        outputStream.flush();
        outputStream.close();
    }
}
