package com.HaveAVoice.Election;

import com.HaveAVoice.Choice.Choice;
import com.HaveAVoice.Election.bll.ElectionService;
import com.HaveAVoice.shared.Response.ResponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/elections")
public class ElectionController {
    private final ElectionService electionService;

    public ElectionController(ElectionService electionService) {
        this.electionService = electionService;
    }

    @GetMapping
    public ResponseEntity<ResponseService<List<Election>>> getElections() {
        var response = electionService.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseService<Election>> getElectionById(@PathVariable Long id) {
        var response = electionService.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/choices")
    public ResponseEntity<ResponseService<List<Choice>>> forElection(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.electionService.getChoicesForElection(id));
    }

    @PostMapping
    public ResponseEntity<ResponseService<Election>> addElection(@Validated @RequestBody Election election) {
        var response = electionService.add(election);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping
    public ResponseEntity<ResponseService<Election>> updateElection(@Validated @RequestBody Election election) {
        var response = electionService.update(election);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseService<Void>> deleteElection(@PathVariable Long id) {
        var response = electionService.delete(id);
        return ResponseEntity.ok(response);
    }

}
