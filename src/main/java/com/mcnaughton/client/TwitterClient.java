package com.mcnaughton.client;

import com.mcnaughton.client.twitterModels.NewSongFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import twitter4j.*;

@Repository
public class TwitterClient {

    @Autowired
    private Twitter twitterClient;

    public NewSongFlag acceptingNewSongs() throws TwitterException {
        String latestTweet = twitterClient.getUserTimeline("IanSongReqFlag")
                .get(0).getText();
        boolean newSongs = ! latestTweet.split(" ")[0].toUpperCase().equals("FALSE");
        return new NewSongFlag(newSongs, latestTweet);
    }

    public void pingMe(String message) throws TwitterException{
        twitterClient.directMessages().sendDirectMessage(twitterClient.getId(), message);
    }
}
