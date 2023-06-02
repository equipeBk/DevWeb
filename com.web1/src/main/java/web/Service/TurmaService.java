package web.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import web.Model.Aluno;
import web.Model.Turma;

@Service
public class TurmaService {
	private List<Turma> turmas = new ArrayList<Turma>();
	private int codigo = 4;

	Aluno jose = new Aluno(1, "José", "jose@example.com");
	Aluno lucas = new Aluno(2, "Lucas", "lucas@example.com");
	Aluno gab = new Aluno(3, "Gabriel", "gab@example.com");

	public TurmaService() {
		turmas.addAll(Arrays.asList(new Turma(1, "discreta", 2, Arrays.asList(jose)),
				new Turma(2, "web", 3, Arrays.asList(lucas)), new Turma(3, "ihc", 4, Arrays.asList(gab))));

	}

	public List<Turma> getTurma() {
		return turmas;
	}

	public Turma getTurmaByCodigo(int codigo) {
		for (Turma turma : turmas) {
			if (turma.getCodigo() == codigo) {
				return turma;
			}
		}
		return null;
	}

	public void addTurma(String disciplina, int semestre, List<Aluno> alunos) {
		Turma novaTurma = new Turma(codigo++, disciplina, semestre, alunos);
		turmas.add(novaTurma);
	}

	public void removerTurma(Turma turma) {
		turmas.remove(turma);
	}

	public List<Aluno> getAlunosByTurmaCodigo(int codigo) {
		Turma turma = getTurmaByCodigo(codigo);
		if (turma != null) {
			return turma.getAlunos();
		} else {
			return new ArrayList<>();
		}
	}

}
