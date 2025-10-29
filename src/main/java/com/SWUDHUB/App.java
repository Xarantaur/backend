package com.SWUDHUB;
import com.SWUDHUB.controller.*;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) throws IOException {

        int port = 8080;

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);


        //routes 
        MainController mainController = new MainController();
        server.createContext("/", mainController::handleRoot);

        StoreShowdownController storeShowdownController = new StoreShowdownController();
        server.createContext("/storeshowdown", storeShowdownController::handleStoreShowdown);

        PrereleaseController eventController = new PrereleaseController();
        server.createContext("/prerelease", eventController::handlePrerelease);



        // virtual threads ( java 21 test ) 
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        server.setExecutor(executor);

        System.out.printf("Server started on port ", + port);
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                server.stop(1);
            } catch (Exception ignored) {}
            try {
                executor.shutdown();
            } catch (Exception ignored) {}
        }) );
        
    }

}
