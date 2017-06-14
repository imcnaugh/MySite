package com.mcnaughton.client;

import com.mcnaughton.client.twitterModels.NewSongFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import twitter4j.*;

@Repository
public class TwitterClient {

    @Autowired
    private Twitter twitterClient;

    @Value("${twitter.userId}")
    private long userId;

    public NewSongFlag acceptingNewSongs() throws TwitterException {
        String latestTweet = twitterClient.getUserTimeline("IanSongReqFlag")
                .get(0).getText();
        boolean newSongs = ! latestTweet.split(" ")[0].toUpperCase().equals("FALSE");
        return new NewSongFlag(newSongs, latestTweet);
    }

    public void pingMe(String message) throws TwitterException{
        twitterClient.sendDirectMessage(userId, message);
    }
}
