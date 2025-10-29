package com.SWUDHUB.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


import com.SWUDHUB.model.PrereleaseEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class EventService {
    
    private static final String FILE_PATH = "data/prerelease.csv";

    public List<PrereleaseEvent> getEvents() throws IOException {
        Path path = Path.of(FILE_PATH);
        System.out.println("[Prerelease] fil findes ikke");

        if (!Files.exists(path)) {
            System.err.println("[Prerelease] fil findes ikke");
            return List.of();
        }

        try {
            return readCsv(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("[Prerelease] fejl ved læsning af fil: (" + e.getMessage() + "), prøver windows-1252");
            return readCsv(path, Charset.forName("windows-1252"));
        }

    }


    private List<PrereleaseEvent> readCsv(Path path, Charset charset) throws IOException {
        List<PrereleaseEvent> list = new ArrayList<>();

        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setSkipHeaderRecord(true)
                .setIgnoreEmptyLines(true)
                .setTrim(true)
                .build();

        try (BufferedReader reader = Files.newBufferedReader(path, charset);
             CSVParser parser = new CSVParser(reader, format)) {

            for (CSVRecord record : parser) {
                String organizer = get(record, 3);
                String eventName = get(record, 0);
                String location = get(record, 1);
                String date = get(record, 2);
                String eventLink = get(record,4);
                

                if(eventName.isBlank() && location.isBlank() && date.isBlank() && organizer.isBlank() && eventLink.isBlank()) continue; 

                list.add(new PrereleaseEvent(eventName, location, date, organizer, eventLink));
            }
    }
        System.out.println("[Prerelease] Indlæste rækker: " + list.size());
        return list;
  }
    private String get(CSVRecord r, int idx) {
        return idx < r.size() ? r.get(idx).trim() : "";
    }
}