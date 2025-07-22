package com.HaveAVoice.User;

import com.HaveAVoice.Election.Election;
import com.HaveAVoice.Vote.Vote;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


import java.util.List;

@Entity
@Data
public class UserDB {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(unique = true)
    @NotBlank
    private String username;
    @Column(unique = true)
    @NotBlank
    private String email;
    private String password;
    @NotBlank
    private String roles = "USER";
    @OneToMany(mappedBy = "organizer", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Election> elections;
    @OneToMany(mappedBy = "voter", orphanRemoval = true, cascade = CascadeType.REMOVE)
    @JsonBackReference
    private List<Vote> votes;

    public UserDB() {}
    public UserDB(String username, String password, String roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
