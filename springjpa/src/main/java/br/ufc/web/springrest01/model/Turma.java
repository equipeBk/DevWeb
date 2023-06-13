package br.ufc.web.springrest01.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Turma {
    @Id
    @GeneratedValue
    private int codigo;

    @ManyToMany
    private List<Alunos> alunos;

    @ManyToOne
    private Disciplinas disciplinas;

    @ElementCollection
    private List<Horarios> horarios;
    

    
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public List<Alunos> getAlunos() {
        return alunos;
    }
    public void setAlunos(List<Alunos> alunos) {
        this.alunos = alunos;
    }
    public Disciplinas getDisciplinas() {
        return disciplinas;
    }
    public void setDisciplinas(Disciplinas disciplinas) {
        this.disciplinas = disciplinas;
    }

    public List<Horarios> getHorarios() {
        return horarios;
    }
    public void setHorarios(List<Horarios> horarios) {
        this.horarios = horarios;
    }
    
    
    
}
