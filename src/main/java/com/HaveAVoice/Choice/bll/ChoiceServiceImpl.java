package com.HaveAVoice.Choice.bll;

import com.HaveAVoice.Choice.Choice;
import com.HaveAVoice.Choice.dal.ChoiceRepository;
import com.HaveAVoice.Vote.Vote;
import com.HaveAVoice.Vote.dal.VoteRepository;
import com.HaveAVoice.shared.Exception.ResourceNotFoundException;
import com.HaveAVoice.shared.Response.ResponseService;
import com.HaveAVoice.shared.BusinessCodes;
import com.HaveAVoice.shared.i18n.MessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChoiceServiceImpl implements ChoiceService {
    private final ChoiceRepository repo;
    private final VoteRepository voteRepo;
    private final MessageHelper message;

    public ChoiceServiceImpl(ChoiceRepository repo,VoteRepository voteRepo ,MessageHelper message) {
        this.repo = repo;
        this.voteRepo = voteRepo;
        this.message = message;
    }

    @Override
    public ResponseService<List<Vote>> getVotesForChoice(Long choiceId) {
        return ResponseService.build(
                BusinessCodes.VOTE_RETRIEVED,
                message.i18n("RESPONSE.SUCCESS"),
                this.voteRepo.findByChoiceId(choiceId)
                );
    }

    @Override
    public ResponseService<Choice> getById(Long id) {

        Choice choice = this.repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("choice", id));

        return ResponseService.build(
                BusinessCodes.SUCCESS,
                message.i18n("RESPONSE.SUCCESS"),
                choice
        );
    }

    @Override
    public ResponseService<Choice> addChoice(Choice choice) {
        return ResponseService.build(
                BusinessCodes.CHOICE_CREATED,
                message.i18n("RESPONSE.SUCCESS"),
                this.repo.save(choice)
        );
    }

    @Override
    public ResponseService<Choice> updateChoice(Choice choice) {
        return ResponseService.build(
                BusinessCodes.CHOICE_UPDATED,
                message.i18n("SUCCESS"),
                this.repo.save(choice)
        );
    }

    @Override
    public ResponseService<Void> deleteChoice(Long id) {
        repo.deleteById(id);
        return ResponseService.build(
                BusinessCodes.CHOICE_DELETED,
                message.i18n("CHOICE.DELETED", new Object[] { id })
        );
    }
}
