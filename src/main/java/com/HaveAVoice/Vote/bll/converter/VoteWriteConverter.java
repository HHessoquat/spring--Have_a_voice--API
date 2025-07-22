package com.HaveAVoice.Vote.bll.converter;

import com.HaveAVoice.Choice.Choice;
import com.HaveAVoice.User.UserDB;
import com.HaveAVoice.Vote.Vote;
import com.HaveAVoice.Vote.dto.VoteWriteDto;
import org.springframework.stereotype.Component;

@Component
public class VoteWriteConverter {
    public Vote convert(VoteWriteDto voteDto) {
        Choice choice = new Choice();
        choice.setId(voteDto.getChoice_id());

        Vote vote = new Vote();
        vote.setDate(voteDto.getDate());
        vote.setChoice(choice);
        if (voteDto.getVoter_id() != null) {
            UserDB voter = new UserDB();
            voter.setId(voteDto.getVoter_id());
            vote.setVoter(voter);
        }

        return vote;
    }
}
