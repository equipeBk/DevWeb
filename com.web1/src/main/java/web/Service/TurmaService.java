package web.Service;

import java.util.ArrayList;
import java.util.List;

import web.Model.Aluno;

public class TurmaService {
	private List<Aluno> alunos = new ArrayList<Aluno>();
	
	 public void adicionarAluno(Aluno aluno) {
	        alunos.add(aluno);
	    }
	 
	 TurmaService turmaService = new TurmaService();

	 Aluno aluno1 = new Aluno();
	 aluno1.setMatricula(1);
	 aluno1.setNome("João");
	 aluno1.setEmail("joao@example.com");

	 turmaService.adicionarAluno(aluno1);

}
