package com.ruuddeenen.plannerplus.models;

import com.ruuddeenen.plannerplus.exceptions.TimeOutOfRangeException;

public class Time {
    private int hours;
    private int minutes;

    public Time(int hours, int minutes) throws TimeOutOfRangeException {
        if (hours > 23) throw new TimeOutOfRangeException(minutes + "should be less then 24");
        if (minutes > 59) throw new TimeOutOfRangeException(minutes + "should be less then 60");
        this.hours = hours;
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}
