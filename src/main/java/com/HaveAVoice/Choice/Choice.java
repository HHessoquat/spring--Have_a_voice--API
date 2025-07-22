package com.HaveAVoice.Choice;

import com.HaveAVoice.Election.Election;
import com.HaveAVoice.Vote.Vote;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Data
public class Choice {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    protected String name;
    protected String picture;

    @Length(max = 300, message = "{CHOICE.VALIDATION.DESCRIPTION.LENGTH}")
    private String description;

    @ManyToOne(optional = false)
    @JsonBackReference
    @JoinColumn(name = "election_id",nullable = false, foreignKey = @ForeignKey(name = "FK_choice_election"))
    protected Election election;

    @OneToMany
    @JsonManagedReference
    @JoinColumn(name = "choice_id", foreignKey = @ForeignKey(name = "FK_choice_vote"))
    protected List<Vote> votes;

}
