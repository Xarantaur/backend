package com.SWUDHUB.controller;

import com.SWUDHUB.model.StoreShowdownEvent;
import com.SWUDHUB.service.StoreShowdownService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;



public class StoreShowdownController {
    private final StoreShowdownService storeShowdownService = new StoreShowdownService();
    private final ObjectMapper mapper = new ObjectMapper();

    public void handleStoreShowdown(HttpExchange exchange) throws IOException {
        if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1); 
            return;
        }

        List<StoreShowdownEvent> events = storeShowdownService.getEvents();
        String json = mapper.writeValueAsString(events);

        exchange.getResponseHeaders().add("content-type", "application/json");
        exchange.sendResponseHeaders(200, json.getBytes().length);

       try(OutputStream os = exchange.getResponseBody()) {
           os.write(json.getBytes());
       }

    }
}