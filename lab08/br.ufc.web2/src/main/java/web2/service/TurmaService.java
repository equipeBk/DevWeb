package web2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web2.model.Turma;
import web2.repository.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
    TurmaRepository turmaRepository;

    public Turma addTurma(Turma turma) {	
		return turmaRepository.save(turma);	
	}


    
}
