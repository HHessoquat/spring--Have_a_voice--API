package com.HaveAVoice.User.BLL;

import com.HaveAVoice.User.DAL.UserRepository;
import com.HaveAVoice.User.UserDB;
import com.HaveAVoice.Vote.Vote;
import com.HaveAVoice.Vote.dal.VoteRepository;
import com.HaveAVoice.shared.BusinessCodes;
import com.HaveAVoice.shared.Exception.ResourceConflict;
import com.HaveAVoice.shared.Exception.ResourceNotFoundException;
import com.HaveAVoice.shared.Response.ResponseService;
import com.HaveAVoice.shared.i18n.MessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final VoteRepository voteRepo;
    private final PasswordEncoder encoder;
    private final MessageHelper message;

    public UserServiceImpl(UserRepository repo, PasswordEncoder encoder, VoteRepository voteRepo, MessageHelper message) {
        this.repo = repo;
        this.encoder = encoder;
        this.voteRepo = voteRepo;
        this.message = message;
    }

    @Override
    public ResponseService<UserDB> getUser(Long id) {
        UserDB user = this.repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", id));
        return ResponseService.build(
                BusinessCodes.USER_RETRIEVED,
                message.i18n("RESPONSE.SUCCESS"),
                user
        );
    }

    @Override
    public ResponseService<UserDB> getUserByUsername(String username) {
        UserDB user = this.repo.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("User", 0L);
        }
        return ResponseService.build(
                BusinessCodes.USER_RETRIEVED,
                message.i18n("RESPONSE.SUCCESS"),
                user
        );
    }

    @Override
    public ResponseService<List<UserDB>> getAllUsers() {

        return ResponseService.build(
                BusinessCodes.USER_RETRIEVED,
                message.i18n("RESPONSE.SUCCESS"),
                repo.findAll()
        );
    }

    @Override
    public ResponseService<List<Vote>> getVotesForUser(Long userId) {
        if (this.repo.existsById(userId)) {
            throw new ResourceNotFoundException("User", userId);
        }
        return ResponseService.build(
                BusinessCodes.VOTE_RETRIEVED,
                message.i18n("RESPONSE.SUCCESS"),
                this.voteRepo.findByVoterId(userId)
        );
    }

    @Override
    public ResponseService<UserDB> addUser(UserDB user) {
        if (repo.existsByUsername(user.getUsername())) {
            throw new ResourceConflict(BusinessCodes.USER_CONFLICT, "User");
        }
        user.setPassword(encoder.encode(user.getPassword()));

        return ResponseService.build(
                BusinessCodes.USER_CREATED,
                message.i18n("RESPONSE.SUCCESS"),
                this.repo.save(user)
        );
    }

    @Override
    public ResponseService<UserDB> updateUser(UserDB user) {
        if (!repo.existsById(user.getId())) {
            throw new ResourceNotFoundException("User", user.getId());
        }
        return ResponseService.build(
                BusinessCodes.USER_UPDATED,
                message.i18n("RESPONSE.SUCCESS"),
                this.repo.save(user)
                );
    }

    @Override
    public ResponseService<Void> deleteUser(Long id) {
        this.repo.deleteById(id);
        return ResponseService.build(
                BusinessCodes.USER_DELETED,
                message.i18n("USER.DELETED", new Object[] { id })
        );
    }
}
