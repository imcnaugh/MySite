package com.mcnaughton.models.resume;


import java.time.Year;

public class Degree {
    private String degreeName;
    private String college;
    private String location;
    private Year yearEarned;

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Year getYearEarned() {
        return yearEarned;
    }

    public void setYearEarned(Year yearEarned) {
        this.yearEarned = yearEarned;
    }
}
