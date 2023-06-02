package web.Rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import web.Service.TurmaService;
import web.Model.Aluno;
import web.Model.Turma;

@RestController
public class Controller {

	@Autowired
	TurmaService turmaService;

	@GetMapping("/api/turma")
	public List<Turma> getTurmas(@RequestParam Map<String, String> params) {
		return turmaService.getTurma();
	}

	@GetMapping("api/turma/{codigo}")

	public Turma getTurmaByCodigo(@PathVariable int codigo) {
		return turmaService.getTurmaByCodigo(codigo);
	}
	
	@PostMapping("/api/turma")
	public void addTurma(@RequestBody Turma turma) {
	    turmaService.addTurma(turma.getDisciplina(), turma.getSemestre(), turma.getAlunos());
	}
	
	@PutMapping("/api/turma/{codigo}")
	public void editarTurma(@PathVariable int codigo, @RequestBody Turma turmaEditar) {
	    Turma turmaExistente = turmaService.getTurmaByCodigo(codigo);
	    
	    if (turmaExistente != null) {
	        turmaExistente.setDisciplina(turmaEditar.getDisciplina());
	        turmaExistente.setSemestre(turmaEditar.getSemestre());
	        turmaExistente.setAlunos(turmaEditar.getAlunos());
	        
	    }
	}
	
	@DeleteMapping("/api/turma/{codigo}")
	public void deletarTurma(@PathVariable int codigo) {
	    Turma turma = turmaService.getTurmaByCodigo(codigo);
	    
	    if (turma != null) {
	        turmaService.removerTurma(turma);
	        
	    }
	}
	
	@GetMapping("/api/turma/{codigo}/alunos")
	public List<Aluno> getAlunosByTurmaCodigo(@PathVariable int codigo) {
	    return turmaService.getAlunosByTurmaCodigo(codigo);
	}

}
