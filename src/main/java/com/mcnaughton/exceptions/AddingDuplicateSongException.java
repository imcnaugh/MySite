package com.mcnaughton.exceptions;

import com.mcnaughton.client.spotifyModels.response.Track;

public class AddingDuplicateSongException extends Exception{

    private Track track;

    public AddingDuplicateSongException(Track track){
        this.track = track;
    }

    public Track getTrack(){
        return track;
    }
}
