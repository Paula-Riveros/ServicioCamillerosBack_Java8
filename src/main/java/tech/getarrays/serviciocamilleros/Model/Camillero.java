package tech.getarrays.serviciocamilleros.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Camillero {
    @Id
    private Integer idCamillero;
    private String nombreCamillero;
    private String emailCamillero;
    private Boolean estadoCamillero;

    public Camillero() {
    }

    public Camillero(int idCamillero, String nombreCamillero, String emailCamillero, Boolean estadoCamillero) {
        this.idCamillero = idCamillero;
        this.nombreCamillero = nombreCamillero;
        this.emailCamillero = emailCamillero;
        this.estadoCamillero = estadoCamillero;
    }

    public Integer getIdCamillero() {
        return idCamillero;
    }

    public void setIdCamillero(Integer idCamillero) {
        this.idCamillero = idCamillero;
    }

    public String getNombreCamillero() {
        return nombreCamillero;
    }

    public void setNombreCamillero(String nombreCamillero) {
        this.nombreCamillero = nombreCamillero;
    }

    public String getEmailCamillero() {
        return emailCamillero;
    }

    public void setEmailCamillero(String emailCamillero) {
        this.emailCamillero = emailCamillero;
    }

    public boolean isEstadoCamillero() {
        return estadoCamillero;
    }

    public void setEstadoCamillero(Boolean estadoCamillero) {
        this.estadoCamillero = estadoCamillero;
    }
}

