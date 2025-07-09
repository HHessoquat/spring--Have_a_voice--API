package com.HaveAVoice.Election.converter;

import com.HaveAVoice.Choice.Choice;
import com.HaveAVoice.Election.Election;
import com.HaveAVoice.Election.dto.ElectionWriteDto;
import com.HaveAVoice.User.UserDB;
import org.springframework.stereotype.Component;

@Component
public class ElectionWriteConverter {
    public Election convert(ElectionWriteDto electionDto) {
        UserDB user = new UserDB();
        user.setId(electionDto.getOrganizer_id());

        Election election = new Election();
        election.setName(electionDto.getName());
        election.setOrganizer(user);
        election.setDateStart(electionDto.getDateStart());
        election.setDateEnd(electionDto.getDateEnd());
        election.setChoices(electionDto.getChoices());

        for (Choice choice : election.getChoices()) {
            choice.setElection(election);
        }
        return election;
    }
}
