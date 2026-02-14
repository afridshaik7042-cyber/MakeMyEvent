package com.event.Event.Model;

import java.time.LocalDateTime;

public class ErrorMessage {
    public String msg;
    public LocalDateTime time;

    public ErrorMessage(String msg) {
        this.msg = msg;
        this.time = LocalDateTime.now();
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
