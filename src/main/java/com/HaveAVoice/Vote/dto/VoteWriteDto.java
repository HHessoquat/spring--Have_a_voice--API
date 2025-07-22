package com.HaveAVoice.Vote.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VoteWriteDto {
    private LocalDateTime date;
    private Long choice_id;
    private Long voter_id;
}
