package com.HaveAVoice.Election.bll;

import com.HaveAVoice.Choice.Choice;
import com.HaveAVoice.Election.Election;
import com.HaveAVoice.shared.Response.ResponseService;

import java.util.List;

public interface ElectionService {
    ResponseService<List<Election>> getAll();
    ResponseService<Election> getById(Long id);
    ResponseService<List<Choice>> getChoicesForElection(Long id);
    ResponseService<Election> add(Election election);
    ResponseService<Election> update(Election election);
    ResponseService<Void> delete(Long id);
}
