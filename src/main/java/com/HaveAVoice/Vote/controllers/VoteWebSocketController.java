package com.HaveAVoice.Vote.controllers;

import com.HaveAVoice.Vote.Vote;
import com.HaveAVoice.Vote.bll.VoteService;
import com.HaveAVoice.Vote.dto.VoteWriteDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class VoteWebSocketController {
    VoteService voteService;

    public VoteWebSocketController(VoteService voteService) {
        this.voteService = voteService;
    }

    @MessageMapping("/vote")
    @SendTo("/topic/vote")
    public Vote vote(VoteWriteDto vote) {
        return this.voteService.convertDtoToVote(vote);
    }
}
