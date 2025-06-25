package com.HaveAVoice.Vote.bll;

import com.HaveAVoice.Vote.Vote;
import com.HaveAVoice.Vote.dal.VoteRepository;
import com.HaveAVoice.shared.BusinessCodes;
import com.HaveAVoice.shared.Exception.ResourceNotFoundException;
import com.HaveAVoice.shared.Response.ResponseService;
import com.HaveAVoice.shared.i18n.MessageHelper;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements VoteService {
    VoteRepository repo;
    MessageHelper message;

    VoteServiceImpl(VoteRepository repo, MessageHelper message) {
        this.repo = repo;
        this.message = message;
    }
    @Override
    public ResponseService<Vote> getVoteById(Long id) {
        Vote vote = this.repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vote", id));
        return ResponseService.build(
                BusinessCodes.VOTE_RETRIEVED,
                message.i18n("RESPONSE.SUCCESS"),
                vote
        );
    }

    @Override
    public ResponseService<Vote> addVote(Vote vote) {
        return ResponseService.build(
                BusinessCodes.VOTE_CREATED,
                message.i18n("RESPONSE.SUCCESS"),
                this.repo.save(vote)
        );
    }

    @Override
    public ResponseService<Vote> updateVote(Vote vote) {
        return ResponseService.build(
                BusinessCodes.VOTE_UPDATED,
                message.i18n("RESPONSE.SUCCESS"),
                this.repo.save(vote)
        );
    }

    @Override
    public ResponseService<Void> deleteVote(Long id) {
        this.repo.deleteById(id);
        return ResponseService.build(
                BusinessCodes.VOTE_DELETED,
                message.i18n("VOTE.DELETED", new Object[] { id })
        );
    }
}
