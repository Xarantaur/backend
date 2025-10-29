package com.SWUDHUB.model;

public class StoreShowdownEvent {
    private String date;
    private String store;
    private String time;
    private String location;
    private String notes;

    public StoreShowdownEvent(String date, String store, String time, String location, String notes) {
        this.date = date;
        this.store = store;
        this.time = time;
        this.location = location;
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public String getStore() {
        return store;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getNotes() {
        return notes;
    }

}

