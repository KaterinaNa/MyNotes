package com.example.mynotes;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Note {
    public String title;
    public String description;
    public String  dayOfWeek;
    public int priorety;
    @PrimaryKey(autoGenerate = true) int id;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public int getPriorety() {
        return priorety;
    }

    @Ignore public Note (String title, String  description, String  dayOfWeek, int priorety) {

    }

    public Note () {}

    public String getDayAsString (int position) {
        switch (position) {
            case 1:
                return "Monday";
            case 2:
                return "Tuesday";
            case 3:
                return "Wednesday";
            case 4:
                return "Thursday";
            case 5:
                return "Friday";
            case 6:
                return "Saturday";
            default:
                return "Sunday";

        }

    }
}
