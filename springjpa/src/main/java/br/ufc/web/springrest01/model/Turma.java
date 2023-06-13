package br.ufc.web.springrest01.model;

import java.util.Date;
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

    @ElementCollection
    private List<Date> horarios;

    @ManyToMany
    private List<Alunos> alunos;

    @ManyToOne
    private Disciplinas disciplinas;

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

    public List<Date> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Date> horarios) {
        this.horarios = horarios;
    }
}
