FROM openjdk

COPY build/libs/IanMcNaughtonSite-1.0.0.war IanMcNaughtonPersonalSite.war

EXPOSE 8080/tcp

ENTRYPOINT java -jar IanMcNaughtonPersonalSite.war
