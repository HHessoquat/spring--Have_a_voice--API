package com.HaveAVoice.User.BLL;

import com.HaveAVoice.User.UserDB;
import com.HaveAVoice.User.dto.UserDbReadDto;
import com.HaveAVoice.Vote.Vote;
import com.HaveAVoice.shared.Response.ResponseService;


import java.util.List;

public interface UserService {
    ResponseService<UserDbReadDto> getUser(Long id);
    ResponseService<UserDbReadDto> getUserByUsername(String email);
    ResponseService<List<UserDbReadDto>> getAllUsers();
    ResponseService<List<Vote>> getVotesForUser(Long userId);
    ResponseService<UserDB> addUser(UserDB user);
    ResponseService<UserDB> updateUser(UserDB user);
    ResponseService<Void> deleteUser(Long id);
}
