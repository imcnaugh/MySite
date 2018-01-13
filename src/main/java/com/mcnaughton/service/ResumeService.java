package com.mcnaughton.service;

import com.mcnaughton.models.resume.*;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

@Service
public class ResumeService {

    public Resume getMyResume(){
        Resume myResume = new Resume();

        myResume.setName("Ian Louis McNaughton");
        myResume.setSummary(getSummary());
        myResume.setContactInfo(getContactInfo());
        myResume.setSkills(getSkills());
        myResume.setWorkExperience(getWorkExperience());
        myResume.setDegrees(getDegrees());
        myResume.setCertifications(getCertifications());

        return myResume;
    }

    private String getSummary(){
        return "Professional developer creating highly successful web applications, using several different" +
                " technologies and development methodologies. Has proven the ability to learn quickly and produce" +
                " code that is easy to maintain and test.";
    }

    private Set<Degree> getDegrees() {
        Degree occ = new Degree();
        occ.setCollege("Oakland Community College");
        occ.setDegreeName("Associates in Computer Science");
        occ.setLocation("Royal Oak, MI");
        occ.setYearEarned(Year.of(2011));

        Set<Degree> degrees = new HashSet<>();
        degrees.add(occ);
        return degrees;
    }

    private Set<Certification> getCertifications(){
        Certification scjp = new Certification();
        scjp.setName("Sun Certified Java Programmer");
        scjp.setIssuer("Sun Microsystems");
        scjp.setYearEarned(Year.of(2010));

        Set<Certification> certs = new HashSet<>();
        certs.add(scjp);
        return certs;
    }

    private Set<WorkExperience> getWorkExperience(){
        Set<WorkExperience> workExperience = new HashSet<>();

        workExperience.add(getSystemInMotionInfo());
        workExperience.add(getSlalomInfo());
        workExperience.add(getExpediaInfo());
        workExperience.add(getUptakeInfo());

        return workExperience;
    }

    private WorkExperience getSystemInMotionInfo() {
        WorkExperience job = new WorkExperience();
        job.setCompanyName("Systems in Motion");
        job.setRole("Software Developer");
        job.setCurrentJob(false);
        Set<String> jobLoc = new HashSet<>();
        jobLoc.add("Ann Arbor, MI");
        job.setLocations(jobLoc);
        job.setStartDate(YearMonth.of(2012, 7));
        job.setEndDate(YearMonth.of(2014, 5));
        Set<String> highlights = new HashSet<>();
        highlights.add("Collaborated closely with developers, and product owners/clients to design" +
                " client-centered application.properties");
        highlights.add("Developed and maintained code for a legacy system");
        highlights.add("Trained new team members");
        highlights.add("Documented existing legacy projects, and new development work");
        job.setHighlights(highlights);
        return job;
    }

    private WorkExperience getSlalomInfo() {
        WorkExperience job = new WorkExperience();
        job.setCompanyName("Slalom Consulting");
        job.setRole("Software Developer");
        job.setCurrentJob(false);
        Set<String> jobLoc = new HashSet<>();
        jobLoc.add("Seattle, WA");
        jobLoc.add("Chicago, IL");
        job.setLocations(jobLoc);
        job.setStartDate(YearMonth.of(2014, 6));
        job.setEndDate(YearMonth.of(2015, 9));
        Set<String> highlights = new HashSet<>();
        highlights.add("Organized, lead, and implemented changes to help remove more than 100 redundant/unused fields" +
                " in a data model bringing things to a simple, manageable state.");
        highlights.add("Replaced a PHP application.properties and its Node.js wrapper with a much simpler Scala service");
        highlights.add("Learned new technologies, and systems quickly to create production ready code within weeks");
        job.setHighlights(highlights);
        return job;
    }

    private WorkExperience getExpediaInfo() {
        WorkExperience job = new WorkExperience();
        job.setCompanyName("Expedia");
        job.setRole("Developer");
        job.setCurrentJob(false);
        Set<String> jobLoc = new HashSet<>();
        jobLoc.add("San Francisco, CA");
        job.setLocations(jobLoc);
        job.setStartDate(YearMonth.of(2015, 9));
        job.setEndDate(YearMonth.of(2017, 6));
        Set<String> highlights = new HashSet<>();
        highlights.add("Tasked with and succeeded at designing/implementing from scratch, a job to read, transform," +
                " and serve data for better sorting of activities.");
        highlights.add("Collaborated with my team and offsite resources to drastically reduce the complexity of our" +
                " database schema.");
        highlights.add("Wrote stories for team members and reviewed their pull requests to keep quality high, and to " +
                "teach cleaner, more maintainable patters in code.");
        highlights.add("Volunteered to support Expedia hosted hack-a-thons.");
        highlights.add("Spearheaded conversations to better support activity search using the search anything tool.");
        job.setHighlights(highlights);
        return job;
    }

    private WorkExperience getUptakeInfo() {
        WorkExperience job = new WorkExperience();
        job.setCompanyName("Uptake");
        job.setRole("Software Developer");
        job.setCurrentJob(true);
        Set<String> jobLoc = new HashSet<>();
        jobLoc.add("Chicago, IL");
        job.setLocations(jobLoc);
        job.setStartDate(YearMonth.of(2017, 8));
        Set<String> highlights = new HashSet<>();
        //TODO come up with highlights for this job, ugh how do i market this...
        job.setHighlights(highlights);
        return job;
    }

    private ContactInfo getContactInfo() {
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setAddressLine1("1100 N. Dearborn, Apt: 1509");
        contactInfo.setAddressLine2("Chicago, IL 60610");
        contactInfo.setEmailAddress("IanMcNaugh@gmail.com");
        contactInfo.setPhoneNumber("1 (248) 494-3748");
        return contactInfo;
    }

    private Set<Skill> getSkills(){
        Set<Skill> skills = new HashSet<>();
        skills.add(Skill.SCALA);
        skills.add(Skill.SPRING);
        skills.add(Skill.MAVEN);
        skills.add(Skill.HIBERNATE);
        skills.add(Skill.SQL);
        skills.add(Skill.JAVA);
        skills.add(Skill.REST_SOAP);
        skills.add(Skill.JSP_JSTL);
        skills.add(Skill.JDBC);
        skills.add(Skill.C_SHARP);
        skills.add(Skill.JENKINS);
        skills.add(Skill.REGEX);
        skills.add(Skill.MOCKITO);
        skills.add(Skill.GRADLE);
        skills.add(Skill.C_PLUS_PLUS);
        skills.add(Skill.TOMCAT);
        skills.add(Skill.GIT_SVN);
        skills.add(Skill.HTML);
        skills.add(Skill.PERL);
        skills.add(Skill.ANT_IVY);
        return skills;
    }
}
