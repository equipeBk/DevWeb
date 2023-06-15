package web2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web2.model.Aluno;
import web2.repository.AlunoRepository;

@Service
public class AlunoService {
    	
	@Autowired
    AlunoRepository alunoRepository;
	Aluno aluno = new Aluno();

	public Iterable<Aluno> getsAluno(){
		
		return alunoRepository.findAll();
	}

	public Aluno addAluno(Aluno aluno) {		
		return alunoRepository.save(aluno);	
	}
}
