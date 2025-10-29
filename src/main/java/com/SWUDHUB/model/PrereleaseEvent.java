package com.SWUDHUB.model;

public class PrereleaseEvent {
    private String eventName;
    private String location;
    private String date;
    private String organizer;
    private String eventLink;

    public PrereleaseEvent(String eventName, String location, String date, String organizer, String eventLink) {
        this.eventName = eventName;
        this.location = location;
        this.date = date;
        this.organizer = organizer;
        this.eventLink = eventLink;
    }

    public String getEventName() {
        return eventName;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getOrganizer() {
        return organizer;
    }

    public String getEventLink() {
        return eventLink;
    }




}

