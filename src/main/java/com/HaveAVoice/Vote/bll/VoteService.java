package com.HaveAVoice.Vote.bll;

import com.HaveAVoice.Vote.Vote;
import com.HaveAVoice.shared.Response.ResponseService;

public interface VoteService {
    ResponseService<Vote> getVoteById(Long id);
    ResponseService<Vote> addVote(Vote vote);
    ResponseService<Vote> updateVote(Vote vote);
    ResponseService<Void> deleteVote(Long id);
}
