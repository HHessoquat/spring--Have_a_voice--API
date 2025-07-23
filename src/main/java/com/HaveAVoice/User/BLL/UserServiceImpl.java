package com.HaveAVoice.User.BLL;

import com.HaveAVoice.User.DAL.UserRepository;
import com.HaveAVoice.User.UserDB;
import com.HaveAVoice.User.converter.UserDbReadConverter;
import com.HaveAVoice.User.dto.UserDbReadDto;
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
    private final UserDbReadConverter converter;

    public UserServiceImpl(
            UserRepository repo,
            PasswordEncoder encoder,
            VoteRepository voteRepo,
            MessageHelper message,
            UserDbReadConverter converter
    ) {
        this.repo = repo;
        this.encoder = encoder;
        this.voteRepo = voteRepo;
        this.message = message;
        this.converter = converter;
    }

    @Override
    public ResponseService<UserDbReadDto> getUser(Long id) {
        UserDB user = this.repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", id));
        UserDbReadDto userDto = this.converter.convert(user);
        return ResponseService.build(
                BusinessCodes.USER_RETRIEVED,
                message.i18n("RESPONSE.SUCCESS"),
                userDto
        );
    }

    @Override
    public ResponseService<UserDbReadDto> getUserByUsername(String username) {
        UserDB user = this.repo.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("User", 0L);
        }
        UserDbReadDto userDto = this.converter.convert(user);
        return ResponseService.build(
                BusinessCodes.USER_RETRIEVED,
                message.i18n("RESPONSE.SUCCESS"),
                userDto
        );
    }

    @Override
    public ResponseService<List<UserDbReadDto>> getAllUsers() {
        List<UserDB> user = this.repo.findAll();
        List<UserDbReadDto> userDto = user.stream().map(this.converter::convert).toList();
        return ResponseService.build(
                BusinessCodes.USER_RETRIEVED,
                message.i18n("RESPONSE.SUCCESS"),
                userDto
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
