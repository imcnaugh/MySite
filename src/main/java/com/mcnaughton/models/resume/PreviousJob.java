package com.mcnaughton.models.resume;

import java.time.YearMonth;
import java.util.Set;

public class PreviousJob {

    private String companyName;
    private String role;

    private Set<String> locations;

    private YearMonth startDate;
    private YearMonth endDate;
    private boolean isCurrentJob;

    private Set<String> highlights;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<String> getLocations() {
        return locations;
    }

    public void setLocations(Set<String> locations) {
        this.locations = locations;
    }

    public YearMonth getStartDate() {
        return startDate;
    }

    public void setStartDate(YearMonth startDate) {
        this.startDate = startDate;
    }

    public YearMonth getEndDate() {
        return endDate;
    }

    public void setEndDate(YearMonth endDate) {
        this.endDate = endDate;
    }

    public boolean isCurrentJob() {
        return isCurrentJob;
    }

    public void setCurrentJob(boolean currentJob) {
        isCurrentJob = currentJob;
    }

    public Set<String> getHighlights() {
        return highlights;
    }

    public void setHighlights(Set<String> highlights) {
        this.highlights = highlights;
    }
}
