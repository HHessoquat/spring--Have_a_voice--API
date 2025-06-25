package com.HaveAVoice.Choice.bll;

import com.HaveAVoice.Choice.Choice;
import com.HaveAVoice.Vote.Vote;
import com.HaveAVoice.shared.Response.ResponseService;

import java.util.List;

public interface ChoiceService {
    ResponseService<List<Vote>> getVotesForChoice(Long choiceId);
    ResponseService<Choice> getById(Long id);
    ResponseService<Choice> addChoice(Choice choice);
    ResponseService<Choice> updateChoice(Choice choice);
    ResponseService<Void> deleteChoice(Long id);
}
