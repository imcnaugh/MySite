package com.mcnaughton.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import twitter4j.*;

@Repository
public class TwitterClient {

    @Autowired
    private Twitter twitterClient;

    public boolean acceptingNewSongs() throws TwitterException {
        return ! twitterClient.getUserTimeline("IanSongReqFlag")
                .get(0).getText().toUpperCase().equals("FALSE");
    }
}
