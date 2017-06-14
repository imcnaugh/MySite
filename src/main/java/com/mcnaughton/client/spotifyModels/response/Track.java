package com.mcnaughton.client.spotifyModels.response;

import java.util.Set;

public class Track{
    private String id;
    private String name;
    private Album album;
    private Set<Artist> artists;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Set<Artist> getArtists(){
        return artists;
    }

    public void setArtists(Set<Artist> artists){
        this.artists = artists;
    }
}