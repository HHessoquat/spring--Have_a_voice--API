package com.HaveAVoice.Election.bll;

import com.HaveAVoice.Choice.dal.ChoiceRepository;
import com.HaveAVoice.Election.Election;
import com.HaveAVoice.Election.bll.converter.ElectionWriteConverter;
import com.HaveAVoice.Election.dal.ElectionRepository;
import com.HaveAVoice.Election.dto.ElectionWriteDto;
import com.HaveAVoice.shared.Exception.ResourceNotFoundException;
import com.HaveAVoice.shared.Response.ResponseService;
import com.HaveAVoice.shared.BusinessCodes;
import com.HaveAVoice.shared.i18n.MessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElectionServiceImpl implements ElectionService {
    private final ElectionRepository repo;
    private final ChoiceRepository choiceRepo;
    private final MessageHelper message;
    private final ElectionWriteConverter electionWriteConverter;

    public ElectionServiceImpl(
            ElectionRepository repo,
            MessageHelper message,
            ChoiceRepository choiceRepo,
            ElectionWriteConverter electionWriteConverter
    ) {
        this.repo = repo;
        this.message = message;
        this.choiceRepo = choiceRepo;
        this.electionWriteConverter = electionWriteConverter;
    }
    @Override
    public ResponseService<List<Election>> getAll() {

        return ResponseService.build(
                BusinessCodes.ELECTION_RETRIEVED,
                message.i18n("RESPONSE.SUCCESS"),
                repo.findAll()
        );
    }

    @Override
    public ResponseService<Election> getById(Long id) {
        Election election = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Election", id));

        return ResponseService.build(
                BusinessCodes.ELECTION_RETRIEVED,
                message.i18n("RESPONSE.SUCCESS"),
                election
        );
    }

    @Override
    public ResponseService<Election> add(ElectionWriteDto electionDto) {

        Election election = repo.save(this.electionWriteConverter.convert(electionDto));
        return ResponseService.build(
                BusinessCodes.ELECTION_CREATED,
                message.i18n("RESPONSE.SUCCESS"),
                election
        );
    }

    @Override
    public ResponseService<Election> update(Election election) {
        repo.save(election);
        return ResponseService.build(
                BusinessCodes.ELECTION_UPDATED,
                message.i18n("RESPONSE.SUCCESS"),
                election
        );
    }

    @Override
    public ResponseService<Void> delete(Long id) {
        repo.deleteById(id);
        return ResponseService.build(
                BusinessCodes.ELECTION_DELETED,
                message.i18n("ELECTION.DELETED", new Object[]{ id })
        );
    }
}
