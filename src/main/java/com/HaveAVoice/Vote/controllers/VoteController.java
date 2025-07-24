package com.HaveAVoice.Vote.controllers;

import com.HaveAVoice.Vote.Vote;
import com.HaveAVoice.Vote.bll.VoteService;
import com.HaveAVoice.Vote.dto.VoteWriteDto;
import com.HaveAVoice.shared.Response.ResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/votes")
public class VoteController {
    VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseService<Vote>> getVote(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.voteService.getVoteById(id));
    }

     @PostMapping
    public ResponseEntity<ResponseService<Vote>> addVote(@RequestBody VoteWriteDto vote) {
        return ResponseEntity.status(HttpStatus.CREATED).body(voteService.addVote(vote));
     }

     @PutMapping
    public ResponseEntity<ResponseService<Vote>> updateVote(@RequestBody Vote vote) {
        return ResponseEntity.ok().body(voteService.updateVote(vote));
     }

     @DeleteMapping("/{id}")
    public ResponseEntity<ResponseService<Void>> deleteVote(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.voteService.deleteVote(id));
     }
}
