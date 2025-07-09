package com.HaveAVoice.Choice.dal;

import com.HaveAVoice.Choice.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {
}
