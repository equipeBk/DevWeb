package web2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import web2.model.Disciplina;
import web2.model.Turma;
import web2.service.DisciplinaService;
import web2.service.TurmaService;

@RestController
@RequestMapping("/api")
public class Controller {

	//Disciplina

	@Autowired
	DisciplinaService disciplinaService;

	@GetMapping("/disciplina")

	public Iterable<Disciplina> getDisciplina(){
		return disciplinaService.getsDisciplina();
	}

	@PostMapping("/disciplina")

	public void addDisciplina(@RequestBody Disciplina disciplina) {
		disciplinaService.addDisciplina(disciplina);

	}

	//Turma
	@Autowired
	TurmaService turmaService;

	@PostMapping("/disciplina")

	public void addTurma(@RequestBody Turma turma) {
		turmaService.addTurma(turma);
	}
}
