package com.HaveAVoice.Election;

import com.HaveAVoice.User.UserDB;
import com.HaveAVoice.Choice.Choice;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Election {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    protected Long id;

    @NotBlank(message = "{ELECTION.VALIDATION.NAME.NOT_BLANK}")
    @Length(min = 5, max = 200, message = "{ELECTION.VALIDATION.NAME.LENGTH}")
    protected String name;


    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false)
    @JsonManagedReference
    @Valid
    private UserDB organizer;

    private LocalDateTime created = LocalDateTime.now();

    @FutureOrPresent
    private LocalDateTime dateStart;
    @Future
    private LocalDateTime dateEnd;


    @OneToMany(mappedBy = "election",cascade = CascadeType.ALL, orphanRemoval = true)
    @Size(min= 2)
    protected List<Choice> choices = new ArrayList<Choice>();

}
