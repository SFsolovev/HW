package org.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        // Создаем сервер на порту 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Обработчик для корневого пути
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                // Проверяем метод запроса
                if ("GET".equals(exchange.getRequestMethod())) {
                    String response = "Hello, World!\n";

                    // Устанавливаем заголовки
                    exchange.getResponseHeaders().set("Content-Type", "text/plain");
                    exchange.sendResponseHeaders(200, response.length());

                    // Пишем ответ
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(response.getBytes());
                    }
                } else {
                    // Метод не разрешен
                    exchange.sendResponseHeaders(405, -1);
                }
            }
        });

        // Запускаем сервер
        server.start();
        System.out.println("Server started on http://localhost:8080");
        System.out.println("Press Ctrl+C to stop");
    }
}