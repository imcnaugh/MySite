package com.mcnaughton.models.resume;

import java.util.Set;

public class Resume {

    private String name;

    private String Summary;
    private ContactInfo contactInfo;
    private Set<Skill> skills;
    private Set<PreviousJob> previousWork;
    private Set<Degree> degrees;
    private Set<Certification> certifications;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Set<PreviousJob> getPreviousWork() {
        return previousWork;
    }

    public void setPreviousWork(Set<PreviousJob> previousWork) {
        this.previousWork = previousWork;
    }

    public Set<Degree> getDegrees() {
        return degrees;
    }

    public void setDegrees(Set<Degree> degrees) {
        this.degrees = degrees;
    }

    public Set<Certification> getCertifications() {
        return certifications;
    }

    public void setCertifications(Set<Certification> certifications) {
        this.certifications = certifications;
    }
}
