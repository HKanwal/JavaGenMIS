package src;

import src.RequestHandler;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.Executors;
import java.io.IOException;

public class StartServer {
    public static void main(String[] args) throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress("localhost", 8080);
        HttpServer server = HttpServer.create(socketAddress, 0);
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        server.createContext("/", new RequestHandler());
        server.setExecutor(threadPoolExecutor);

        server.start();
        System.out.println("Server started on port 8080.");
    }
}
