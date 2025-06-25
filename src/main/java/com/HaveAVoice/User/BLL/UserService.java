package com.HaveAVoice.User.BLL;

import com.HaveAVoice.User.UserDB;
import com.HaveAVoice.Vote.Vote;
import com.HaveAVoice.shared.Response.ResponseService;


import java.util.List;

public interface UserService {
    ResponseService<UserDB> getUser(Long id);
    ResponseService<UserDB> getUserByUsername(String email);
    ResponseService<List<UserDB>> getAllUsers();
    ResponseService<List<Vote>> getVotesForUser(Long userId);
    ResponseService<UserDB> addUser(UserDB user);
    ResponseService<UserDB> updateUser(UserDB user);
    ResponseService<Void> deleteUser(Long id);
}
