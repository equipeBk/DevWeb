package web2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import web2.model.Disciplina;
import web2.repository.DisciplinaRepository;

@Service
public class DisciplinaService {
	
	@Autowired
    DisciplinaRepository disciplinaRepository;

	public Iterable<Disciplina> getsDisciplina(){
		
		return disciplinaRepository.findAll();
	}

	public Disciplina addDisciplina(Disciplina disciplina) {
		
		return disciplinaRepository.save(disciplina);
		
	}

}
