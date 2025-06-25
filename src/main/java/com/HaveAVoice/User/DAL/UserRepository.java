package com.HaveAVoice.User.DAL;

import com.HaveAVoice.User.UserDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDB, Long> {
    public UserDB findByUsername(String username);
    boolean existsByUsername(String username);
}
