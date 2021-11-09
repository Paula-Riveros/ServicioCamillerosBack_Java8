package tech.getarrays.serviciocamilleros.Dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CamilleroDto implements Serializable {

    @NotNull
    @Min(1)
    private int idCamillero;

    @NotBlank
    private String nombreCamillero;

    private boolean estadoCamillero;

    public CamilleroDto() {
    }

    public CamilleroDto(int idCamillero, String nombreCamillero, boolean estadoCamillero) {
        this.idCamillero = idCamillero;
        this.nombreCamillero = nombreCamillero;
        this.estadoCamillero = estadoCamillero;
    }

    public int getIdCamillero() {
        return idCamillero;
    }

    public void setIdCamillero(int idCamillero) {
        this.idCamillero = idCamillero;
    }

    public String getNombreCamillero() {
        return nombreCamillero;
    }

    public void setNombreCamillero(String nombreCamillero) {
        this.nombreCamillero = nombreCamillero;
    }

    public boolean isEstadoCamillero() {
        return estadoCamillero;
    }

    public void setEstadoCamillero(boolean estadoCamillero) {
        this.estadoCamillero = estadoCamillero;
    }
}
