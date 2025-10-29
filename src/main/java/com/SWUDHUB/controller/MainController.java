package com.SWUDHUB.controller;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;


public class MainController {

    public void handleRoot(HttpExchange exchange) throws IOException {
    
       String json = """
       {
       "app": "SWU DHUB Backend",
       "version": "1.0.0",
       "routes": [
       {"path": "/prerelease", "description": "returns gathered prerelease event data in DK in a Json format."},
       {"path": "/storeshowdown", "description": "not yet implemented - will return store showdown event data in DK in a Json format."}
       ]
       }
       """; 

       exchange.getResponseHeaders().add("content-type", "text/plain");
       exchange.sendResponseHeaders(200, json.getBytes().length);

       try(OutputStream os = exchange.getResponseBody()) {
           os.write(json.getBytes());
       }

    }
}