package tech.getarrays.serviciocamilleros.Dto;

import java.io.Serializable;

public class GenpacienDto implements Serializable {

    private String pacnumdoc;
    private String pacprinom;
    private String pacsegnom;
    private String pacpriape;
    private String pacsegape;

    public GenpacienDto() {
    }

    public GenpacienDto(String pacnumdoc, String pacprinom, String pacsegnom, String pacpriape, String pacsegape) {
        this.pacnumdoc = pacnumdoc;
        this.pacprinom = pacprinom;
        this.pacsegnom = pacsegnom;
        this.pacpriape = pacpriape;
        this.pacsegape = pacsegape;
    }

    public String getPacnumdoc() {
        return pacnumdoc;
    }

    public void setPacnumdoc(String pacnumdoc) {
        this.pacnumdoc = pacnumdoc;
    }

    public String getPacprinom() {
        return pacprinom;
    }

    public void setPacprinom(String pacprinom) {
        this.pacprinom = pacprinom;
    }

    public String getPacsegnom() {
        return pacsegnom;
    }

    public void setPacsegnom(String pacsegnom) {
        this.pacsegnom = pacsegnom;
    }

    public String getPacpriape() {
        return pacpriape;
    }

    public void setPacpriape(String pacpriape) {
        this.pacpriape = pacpriape;
    }

    public String getPacsegape() {
        return pacsegape;
    }

    public void setPacsegape(String pacsegape) {
        this.pacsegape = pacsegape;
    }
}