package com.HaveAVoice.User.converter;

import com.HaveAVoice.User.UserDB;
import com.HaveAVoice.User.dto.UserDbReadDto;
import org.springframework.stereotype.Component;

@Component
public class UserDbReadConverter {
    public UserDbReadDto convert(UserDB user) {
        UserDbReadDto dto = new UserDbReadDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRoles(user.getRoles());
        dto.setElections(user.getElections());
        dto.setVotes(user.getVotes());
        return dto;
    }
}
