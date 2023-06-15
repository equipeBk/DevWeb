package web2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web2.model.Aluno;
import web2.model.Turma;
import web2.repository.AlunoRepository;
import web2.repository.DisciplinaRepository;
import web2.repository.TurmaRepository;

@Service
public class TurmaService {
 @Autowired
    private final AlunoRepository alunoRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final TurmaRepository turmaRepository; 

    public TurmaService(AlunoRepository alunoRepository, DisciplinaRepository disciplinaRepository, TurmaRepository turmaRepository) {
        this.alunoRepository = alunoRepository;
        this.disciplinaRepository = disciplinaRepository;
        this.turmaRepository = turmaRepository;
    }

    public void salvarTurma(Turma turma) {
        // Salvar os alunos no repositório
        if (turma.getAlunos() != null) {
            alunoRepository.saveAll(turma.getAlunos());
        }

        // Salvar a disciplina no repositório
        if (turma.getDisciplina() != null) {    
            disciplinaRepository.save(turma.getDisciplina());
        }

        // Salvar a turma no repositório
        turmaRepository.save(turma);
    }

    public Iterable<Turma> getTurmas() {
        return turmaRepository.findAll();
    }
    public void addAlunoParaTurma(int codigo, Aluno aluno) {
        Turma turma = turmaRepository.findByCodigo(codigo);
        if (turma != null) {
            List<Aluno> alunos = turma.getAlunos();
            if (alunos == null) {
                alunos = new ArrayList<>();
            }
            alunos.add(aluno);
            turma.setAlunos(alunos);
            turmaRepository.save(turma);
        }
    }
}
