package web2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web2.model.Aluno;
import web2.model.Disciplina;
import web2.model.Turma;
import web2.service.AlunoService;
import web2.service.DisciplinaService;
import web2.service.TurmaService;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping("/disciplina")
    public Iterable<Disciplina> getDisciplina() {
        return disciplinaService.getsDisciplina();
    }

    @PostMapping("/disciplina")
    public void addDisciplina(@RequestBody Disciplina disciplina) {
        disciplinaService.addDisciplina(disciplina);
    }

    @Autowired
    private TurmaService turmaService;

    @PostMapping("/turma")
    public void criarTurma(@RequestBody Turma turma) {
        turmaService.salvarTurma(turma);
    }

    @GetMapping("/turma")
    public Iterable<Turma> getTurma() {
        return turmaService.getTurmas();
    }
    
    @PostMapping("/turma/{id}/aluno")
    public void addAlunoParaTurma(@PathVariable("id") int id, @RequestBody Aluno aluno) {
        turmaService.addAlunoParaTurma(id, aluno);
    }

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/aluno")
    public Iterable<Aluno> getAluno() {
        return alunoService.getsAluno();
    }

    @PostMapping("/aluno")
    public void addAluno(@RequestBody Aluno aluno) {
        alunoService.addAluno(aluno);
    }
}
