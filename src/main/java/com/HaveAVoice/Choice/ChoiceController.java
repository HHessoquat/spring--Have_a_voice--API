package com.HaveAVoice.Choice;

import com.HaveAVoice.Choice.bll.ChoiceService;
import com.HaveAVoice.Vote.Vote;
import com.HaveAVoice.shared.Response.ResponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/choices")
public class ChoiceController {
    private final ChoiceService choiceService;

    public ChoiceController(ChoiceService choiceService) {
        this.choiceService = choiceService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseService<Choice>> getElection(@PathVariable Long id) {
        var response = this.choiceService.getById(id);

        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/choice/{id}/votes")
    public ResponseEntity<ResponseService<List<Vote>>> getVoteForChoice(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.choiceService.getVotesForChoice(id));
    }

    @PostMapping
    public ResponseEntity<ResponseService<Choice>> addChoice(@Validated @RequestBody Choice choice) {
        return ResponseEntity.status(201).body(this.choiceService.addChoice(choice));
    }

    @PutMapping
    public ResponseEntity<ResponseService<Choice>> updateChoice(@Validated @RequestBody Choice choice) {
        return ResponseEntity.ok().body(this.choiceService.updateChoice(choice));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseService<Void>> deleteChoice(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.choiceService.deleteChoice(id));
    }
}
