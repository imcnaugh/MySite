package com.mcnaughton.controllers;

import com.mcnaughton.client.spotifyModels.response.Playlist;
import com.mcnaughton.exceptions.AddingDuplicateSongException;
import com.mcnaughton.exceptions.NoNewSongsException;
import com.mcnaughton.exceptions.ValidationException;
import com.mcnaughton.service.MusicService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/music/")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String getAbout(){
        return "This is a quick example of interacting with other services. Behind the endpoint, the service interacts with \n" +
                "both twitter and Spotify. Spotify to add songs to a playlist, and return that playlist. and Twitter to act as a \n" +
                "feature flag, Let's say somebody starts spamming bad music. I can tweet to an account, this service will pick that up \n" +
                "and either shut down or enable the feature.";
    }

    @ApiOperation(value = "Returns the songs currently in the playlist")
    @RequestMapping(method = RequestMethod.GET)
    public Playlist getPlaylist() throws Exception {
        return musicService.getPlaylist();
    }

    @ApiOperation(value = "Adds a new song to the playlist")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addSongToPlaylist(
            @ApiParam(value = "the spotify uri of the song", required = true, defaultValue = "spotify:track:6SmPPtaMnfxgz5duA6t9Cu")
            @RequestParam String spotifyUri) throws Exception {
        try{
            musicService.addSongToPlaylist(spotifyUri);
            return ResponseEntity.status(HttpStatus.ACCEPTED_202).body("Song added to playlist, Thanks!");
        } catch (NoNewSongsException nnse){
            return ResponseEntity.status(HttpStatus.FORBIDDEN_403).body(nnse.getReason());
        } catch (AddingDuplicateSongException adse) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST_400).body(
                    String.format("Song %s (%s) is already in the playlist", adse.getTrack().getId(), adse.getTrack().getName()));
        } catch (ValidationException ve){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST_400).body("spotifyURI is not in the correct format");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body("Something fucked up");
        }
    }
}
