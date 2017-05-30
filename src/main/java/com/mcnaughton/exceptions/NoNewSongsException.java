package com.mcnaughton.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class NoNewSongsException extends Exception {

    private String reason;

    public NoNewSongsException(String reason){
        this.reason = reason;
    }

    public String getReason(){
        return reason;
    }
}
