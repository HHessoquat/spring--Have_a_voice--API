package com.HaveAVoice.Election.dto;

import com.HaveAVoice.Choice.Choice;
import com.HaveAVoice.User.UserDB;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ElectionWriteDto {
    protected Long id;

    @NotBlank(message = "{ELECTION.VALIDATION.NAME.NOT_BLANK}")
    protected String name;
    @NotNull
    private Long organizer_id;
    @FutureOrPresent
    private LocalDateTime dateStart;
    @Future
    private LocalDateTime dateEnd;
    @Size(min= 2)
    protected List<Choice> choices = new ArrayList<Choice>();

}
