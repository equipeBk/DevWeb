package br.ufc.web.springrest01.model;

import javax.persistence.Embeddable;

@Embeddable
public class Horarios {

    private String dataHora;

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

}
