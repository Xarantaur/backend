package com.SWUDHUB.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.SWUDHUB.model.StoreShowdownEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class StoreShowdownService {
    
    private static final String FILE_PATH = "data/storeshowdown.csv";

    public List<StoreShowdownEvent> getEvents() throws IOException {
        Path path = Path.of(FILE_PATH);
        System.out.println("[evenst] fil findes ikke");

        if (!Files.exists(path)) {
            System.err.println("[events] fil findes ikke");
            return List.of();
        }

        try {
            return readCsv(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("[events] fejl ved læsning af fil: (" + e.getMessage() + "), prøver windows-1252");
            return readCsv(path, Charset.forName("windows-1252"));
        }

    }


    private List<StoreShowdownEvent> readCsv(Path path, Charset charset) throws IOException {
        List<StoreShowdownEvent> list = new ArrayList<>();

        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setSkipHeaderRecord(true)
                .setIgnoreEmptyLines(true)
                .setTrim(true)
                .build();

        try (BufferedReader reader = Files.newBufferedReader(path, charset);
             CSVParser parser = new CSVParser(reader, format)) {

            for (CSVRecord record : parser) {
               String date     = get(record, 0);
                String store    = get(record, 1);
                String time     = get(record, 2);
                String location = get(record, 3);
                String notes    = get(record, 4);
                
                list.add(new StoreShowdownEvent(date, store, time, location, notes));
            }
    }
        System.out.println("[storeShowdown] læst " + list.size() + " events fra filen.");
        return list;
  }
 private String get(CSVRecord r, int idx) {
        return idx < r.size() ? r.get(idx).trim() : "";
    }
 }
    
