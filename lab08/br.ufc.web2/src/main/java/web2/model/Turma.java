package web2.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import web.Model.Aluno;

@Entity
public class Turma {
	
	@Id
	@GeneratedValue
	private int codigo;
	private Disciplina disciplina;
	private List<Aluno>  alunos = new ArrayList<>();
	private List<Date> horarios = new ArrayList<>();

}
