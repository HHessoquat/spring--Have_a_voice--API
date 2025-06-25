package com.HaveAVoice.Vote.dal;

import com.HaveAVoice.Vote.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findByVoterId(Long voterId);
    List<Vote> findByChoiceId(Long choiceId);

}
