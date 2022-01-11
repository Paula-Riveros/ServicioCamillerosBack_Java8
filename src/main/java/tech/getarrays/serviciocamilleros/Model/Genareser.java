package tech.getarrays.serviciocamilleros.Model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GENARESER")
public class Genareser {

    @Id
    @Column(name = "OID")
    private int oid;

    @Column(name = "GASCODIGO")
    private String gascodigo;

    @Column(name = "GASNOMBRE")
    private String gasnombre;

    public Genareser() {
    }

    public Genareser(int oid, String gascodigo, String gasnombre) {
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

    @Override
    public String toString() {
        return "Genareser{" +
                "oid=" + oid +
                ", gascodigo='" + gascodigo + '\'' +
                ", gasnombre='" + gasnombre + '\'' +
                '}';
    }
}
