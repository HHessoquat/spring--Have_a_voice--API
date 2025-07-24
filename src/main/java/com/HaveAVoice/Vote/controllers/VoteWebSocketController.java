package com.HaveAVoice.Vote.controllers;

import com.HaveAVoice.Vote.Vote;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class VoteWebSocketController {

    @MessageMapping("/vote")
    @SendTo("/topic/vote")
    public Vote vote(Vote vote) {
        return vote;
    }
}
