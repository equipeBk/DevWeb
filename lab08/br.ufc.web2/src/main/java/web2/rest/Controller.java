package web2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import web2.model.Disciplina;
import web2.service.DisciplinaService;

@RestController
@RequestMapping("/api")
public class Controller {

	@Autowired
	DisciplinaService disciplinaService;

	@PostMapping("/disciplina")

	public void addDisciplina(@RequestBody Disciplina disciplina) {
		disciplinaService.addDisciplina(disciplina);

	}

}
