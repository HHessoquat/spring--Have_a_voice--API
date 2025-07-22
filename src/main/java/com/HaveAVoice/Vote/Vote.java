package com.HaveAVoice.Vote;

import com.HaveAVoice.Choice.Choice;
import com.HaveAVoice.User.UserDB;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Vote {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private LocalDateTime date = LocalDateTime.now();

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "voter_id", foreignKey = @ForeignKey(name = "FK_vote_user"))
    private UserDB voter;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "choice_id", foreignKey = @ForeignKey(name = "FK_vote_choice"))
    private Choice choice;

}
