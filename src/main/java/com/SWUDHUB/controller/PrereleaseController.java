package com.SWUDHUB.controller;

import com.sun.net.httpserver.HttpExchange;
import com.SWUDHUB.model.PrereleaseEvent;
import com.SWUDHUB.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


public class PrereleaseController {
    private final EventService eventService = new EventService();
    private final ObjectMapper mapper = new ObjectMapper();

    public void handlePrerelease(HttpExchange exchange) throws IOException {
        if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1); 
            return;
        }

        List<PrereleaseEvent> events = eventService.getEvents();
        String json = mapper.writeValueAsString(events);

        exchange.getResponseHeaders().add("content-type", "application/json");
        exchange.sendResponseHeaders(200, json.getBytes().length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(json.getBytes());
        }
    }
}
