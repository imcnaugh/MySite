package com.mcnaughton.exceptions;

public class SpotifyException extends Exception {
    private int responseCode;

    public SpotifyException(int responseCode) {
        this.responseCode = responseCode;
    }

    public int getResponseCode(){
        return responseCode;
    }
}
