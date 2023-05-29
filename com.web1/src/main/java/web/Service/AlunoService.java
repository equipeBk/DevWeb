package web.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import web.Model.Aluno;

@Service
public class AlunoService {
	
	private List<Aluno> alunos = new ArrayList<Aluno>();
	
	public AlunoService() {

		Aluno aluno1 = new Aluno();
		aluno1.setMatricula(1);
		aluno1.setNome("João");
		aluno1.setEmail("joao@example.com");
		alunos.add(aluno1);
		
		Aluno aluno2 = new Aluno();
		aluno2.setMatricula(2);
		aluno2.setNome("Maria");
		aluno2.setEmail("maria@example.com");
		alunos.add(aluno2);
	}
	
	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	

}

