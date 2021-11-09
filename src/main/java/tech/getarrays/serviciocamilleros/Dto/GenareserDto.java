package tech.getarrays.serviciocamilleros.Dto;

import java.io.Serializable;

public class GenareserDto implements Serializable {

    private int oid;
    private String gascodigo;
    private String gasnombre;

    public GenareserDto() {
    }

    public GenareserDto(int oid, String gascodigo, String gasnombre) {
        this.oid = oid;
        this.gascodigo = gascodigo;
        this.gasnombre = gasnombre;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getGascodigo() {
        return gascodigo;
    }

    public void setGascodigo(String gascodigo) {
        this.gascodigo = gascodigo;
    }

    public String getGasnombre() {
        return gasnombre;
    }

    public void setGasnombre(String gasnombre) {
        this.gasnombre = gasnombre;
    }
}
