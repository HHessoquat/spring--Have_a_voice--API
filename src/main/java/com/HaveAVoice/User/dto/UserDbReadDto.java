package com.HaveAVoice.User.dto;

import com.HaveAVoice.Election.Election;
import com.HaveAVoice.Vote.Vote;
import lombok.Data;

import java.util.List;

@Data
public class UserDbReadDto {
    private Long id;
    private String username;
    private List<Election> elections;
    private List<Vote> votes;
    private String roles;
}
