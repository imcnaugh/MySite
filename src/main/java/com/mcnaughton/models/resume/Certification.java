package com.mcnaughton.models.resume;

import java.time.Year;

public class Certification {
    private String name;
    private String issuer;
    private Year yearEarned;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public Year getYearEarned() {
        return yearEarned;
    }

    public void setYearEarned(Year yearEarned) {
        this.yearEarned = yearEarned;
    }
}
