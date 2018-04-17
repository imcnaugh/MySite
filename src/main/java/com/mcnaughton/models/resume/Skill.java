package com.mcnaughton.models.resume;

public enum Skill {
    SCALA("Scala"),
    SPRING("Spring"),
    MAVEN("Maven"),
    HIBERNATE("Hibernate"),
    SQL("SQL"),
    JAVA("Java"),
    REST_SOAP("REST/SOAP"),
    JSP_JSTL("JSP's/JSTL"),
    JDBC("JDBC"),
    C_SHARP("C#"),
    JENKINS("Jenkins"),
    REGEX("Regex"),
    MOCKITO("Mockito"),
    GRADLE("Gradle"),
    C_PLUS_PLUS("C++"),
    TOMCAT("Tomcat"),
    GIT_SVN("Git/SVN"),
    HTML("HTML"),
    PERL("Perl"),
    ANT_IVY("Ant/Ivy"),
    AKKA("Akka"),
    KAFKA("Kafka"),
    PYTHON("Python"),
    MONGO_DB("Mongo"),
    ELASTIC_SEARCH("Elastic search"),
    CASSANDRA("Apache Cassandra"),
    SBT("Sbt");

    private final String formattedName;

    Skill(String formattedName){
        this.formattedName = formattedName;
    }

    public String toString(){
        return formattedName;
    }
}
