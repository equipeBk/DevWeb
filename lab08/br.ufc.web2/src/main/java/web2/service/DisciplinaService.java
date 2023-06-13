package web2.service;

import org.springframework.beans.factory.annotation.Autowired;

import web2.model.Disciplina;
import web2.repository.DisciplinaRepository;

public class DisciplinaService {
	
	@Autowired
    DisciplinaRepository disciplinaRepository;

	
	public Disciplina addDisciplina(Disciplina disciplina) {
		
		return disciplinaRepository.save(disciplina);
		
	}

}
