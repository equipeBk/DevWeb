package web.Model;

import java.util.List;

public class Turma {

	Aluno aluno = new Aluno();

	int codigo;
	String disciplina;
	int semestre;
	List<Aluno> alunos;

	public Turma(int codigo, String disciplina, int semestre, List<Aluno> aluno) {
		this.codigo = codigo;
		this.disciplina = disciplina;
		this.semestre = semestre;
		this.alunos = aluno;
	}

	public Turma() {
	}

	// gets and sets

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

}
