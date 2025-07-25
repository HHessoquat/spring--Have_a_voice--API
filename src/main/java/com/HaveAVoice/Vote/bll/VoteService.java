package com.HaveAVoice.Vote.bll;

import com.HaveAVoice.Vote.Vote;
import com.HaveAVoice.Vote.dto.VoteWriteDto;
import com.HaveAVoice.shared.Response.ResponseService;

public interface VoteService {
    ResponseService<Vote> getVoteById(Long id);
    ResponseService<Vote> addVote(VoteWriteDto voteDto);
    ResponseService<Vote> updateVote(Vote vote);
    ResponseService<Void> deleteVote(Long id);
    Vote convertDtoToVote(VoteWriteDto voteDto);
}
