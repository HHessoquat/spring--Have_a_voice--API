package com.HaveAVoice.Election.bll;

import com.HaveAVoice.Choice.Choice;
import com.HaveAVoice.Election.Election;
import com.HaveAVoice.Election.dto.ElectionWriteDto;
import com.HaveAVoice.shared.Response.ResponseService;

import java.util.List;

public interface ElectionService {
    ResponseService<List<Election>> getAll();
    ResponseService<Election> getById(Long id);
    ResponseService<Election> add(ElectionWriteDto electionDto);
    ResponseService<Election> update(Election election);
    ResponseService<Void> delete(Long id);
}
