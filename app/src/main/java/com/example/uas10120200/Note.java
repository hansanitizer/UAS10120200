package com.example.uas10120200;

import android.os.health.TimerStat;

import java.sql.Time;
import com.google.firebase.Timestamp;

//10120200 - Mochamad Farhan Fadilah Ansori - IF5
public class Note {

    String title;
    String detail;
    Timestamp timestamp;

    public Note() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
