package com.mcnaughton.client.spotifyModels.response;

import java.util.Set;

public class Playlist {
    private int total;
    private Set<Item> items;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
