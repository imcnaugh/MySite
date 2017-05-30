package com.mcnaughton.client.twitterModels;

public class NewSongFlag {

    private boolean isAcceptingNewSongs;
    private String reason;

    public NewSongFlag(boolean isAcceptingNewSongs, String reason) {
        this.isAcceptingNewSongs = isAcceptingNewSongs;
        this.reason = reason;
    }

    public boolean isAcceptingNewSongs() {
        return isAcceptingNewSongs;
    }

    public String getReason() {
        return reason;
    }
}
