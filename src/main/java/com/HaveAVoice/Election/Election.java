package com.HaveAVoice.Election;

import com.HaveAVoice.User.UserDB;
import com.HaveAVoice.Choice.Choice;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Election {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false)
    @NotBlank
    private UserDB organizer;

    @NotBlank(message = "{ELECTION.VALIDATION.NAME.NOT_BLANK}")
    @Length(min = 5, max = 200, message = "{ELECTION.VALIDATION.NAME.LENGTH}")
    protected String name;

    @OneToMany(mappedBy = "election",cascade = CascadeType.ALL, orphanRemoval = true)
    @Size(min= 2)
    protected List<Choice> choices = new ArrayList<Choice>();

}
