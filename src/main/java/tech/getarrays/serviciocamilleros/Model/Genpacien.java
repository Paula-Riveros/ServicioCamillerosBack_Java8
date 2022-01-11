
package tech.getarrays.serviciocamilleros.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GENPACIEN")
public class Genpacien {

    @Id
    @Column(name = "OID")
    private int oid;

    // numero de documento
    @Column(name = "PACNUMDOC")
    private String pacnumdoc;

    // primer nombre
    @Column(name = "PACPRINOM")
    private String pacprinom;

    // segundo nombre
    @Column(name = "PACSEGNOM")
    private String pacsegnom;

    // primer apellido
    @Column(name = "PACPRIAPE")
    private String pacpriape;

    // segundo apellido
    @Column(name = "PACSEGAPE")
    private String pacsegape;

    public Genpacien() {
    }

    public Genpacien(int oid, String pacnumdoc, String pacprinom, String pacsegnom, String pacpriape, String pacsegape) {
        this.oid = oid;
        this.pacnumdoc = pacnumdoc;
        this.pacprinom = pacprinom;
        this.pacsegnom = pacsegnom;
        this.pacpriape = pacpriape;
        this.pacsegape = pacsegape;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
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

    @Override
    public String toString() {
        return "Genpacien{" +
                "oid=" + oid +
                ", pacnumdoc='" + pacnumdoc + '\'' +
                ", pacprinom='" + pacprinom + '\'' +
                ", pacsegnom='" + pacsegnom + '\'' +
                ", pacpriape='" + pacpriape + '\'' +
                ", pacsegape='" + pacsegape + '\'' +
                '}';
    }
}
