package tech.getarrays.serviciocamilleros.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Servicio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @JsonIgnore
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "servicioSolicitado", referencedColumnName = "oid")
    private Genareser genareser;

    private String solicitante;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destinoServicio", referencedColumnName = "oid")
    private Genareser genareser2;

    private String transporte;
    private String insumo;
    private String familiar;
    private Boolean aislamiento;
    private String observaciones;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "oidGenpacien", referencedColumnName = "oid")
    private Genpacien genpacien;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCamillero", referencedColumnName = "idCamillero")
    private Camillero camillero;

    private LocalTime horaEnvio;
    private LocalTime horaAsignacion;
    private LocalTime horaEjecucion;
    private LocalTime horaFinalizacion;

    @Column(name = "minutosTiempoTotal")
    private Integer tiempoTotal;

    private Boolean cancelado;
    private String motivoCancelado;
    private LocalTime horaCancelacion;

    public Servicio() {
    }

    //
    public Servicio(LocalDate fecha, Genareser genareser, String solicitante, Genareser genareser2,
                    String transporte, String insumo, String familiar, Boolean aislamiento, String observaciones,
                    Genpacien genpacien, Camillero camillero, LocalTime horaEnvio, LocalTime horaAsignacion,
                    LocalTime horaEjecucion, LocalTime horaFinalizacion, Integer tiempoTotal,
                    Boolean cancelado, String motivoCancelado, LocalTime horaCancelacion) {
        this.fecha = fecha;
        this.genareser = genareser;
        this.solicitante = solicitante;
        this.genareser2 = genareser2;
        this.transporte = transporte;
        this.insumo = insumo;
        this.familiar = familiar;
        this.aislamiento = aislamiento;
        this.observaciones = observaciones;
        this.genpacien = genpacien;
        this.camillero = camillero;
        this.horaEnvio = horaEnvio;
        this.horaAsignacion = horaAsignacion;
        this.horaEjecucion = horaEjecucion;
        this.horaFinalizacion = horaFinalizacion;
        this.tiempoTotal = tiempoTotal;
        this.cancelado = cancelado;
        this.motivoCancelado = motivoCancelado;
        this.horaCancelacion = horaCancelacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Genareser getGenareser() {
        return genareser;
    }

    public void setGenareser(Genareser genareser) {
        this.genareser = genareser;
    }

    public Genareser getGenareser2() {
        return genareser2;
    }

    public void setGenareser2(Genareser genareser2) {
        this.genareser2 = genareser2;
    }

    public Genpacien getGenpacien() {
        return genpacien;
    }

    public void setGenpacien(Genpacien genpacien) {
        this.genpacien = genpacien;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getTransporte() {
        return transporte;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }

    public String getInsumo() {
        return insumo;
    }

    public void setInsumo(String insumo) {
        this.insumo = insumo;
    }

    public String getFamiliar() {
        return familiar;
    }

    public void setFamiliar(String familiar) {
        this.familiar = familiar;
    }

    public Boolean getAislamiento() {
        return aislamiento;
    }

    public void setAislamiento(Boolean aislamiento) {
        this.aislamiento = aislamiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }


    public Camillero getCamillero() {
        return camillero;
    }

    public void setCamillero(Camillero camillero) {
        this.camillero = camillero;
    }

    public LocalTime getHoraEnvio() {
        return horaEnvio;
    }

    public void setHoraEnvio(LocalTime horaEnvio) {
        this.horaEnvio = horaEnvio;
    }

    public LocalTime getHoraAsignacion() {
        return horaAsignacion;
    }

    public void setHoraAsignacion(LocalTime horaAsignacion) {
        this.horaAsignacion = horaAsignacion;
    }

    public LocalTime getHoraEjecucion() {
        return horaEjecucion;
    }

    public void setHoraEjecucion(LocalTime horaEjecucion) {
        this.horaEjecucion = horaEjecucion;
    }

    public LocalTime getHoraFinalizacion() {
        return horaFinalizacion;
    }

    public void setHoraFinalizacion(LocalTime horaFinalizacion) {
        this.horaFinalizacion = horaFinalizacion;
    }

    public Integer getTiempoTotal() {
        return tiempoTotal;
    }

    public void setTiempoTotal(Integer tiempoTotal) {
        this.tiempoTotal = tiempoTotal;
    }

    public Boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(Boolean cancelado) {
        this.cancelado = cancelado;
    }

    public String getMotivoCancelado() {
        return motivoCancelado;
    }

    public void setMotivoCancelado(String motivoCancelado) {
        this.motivoCancelado = motivoCancelado;
    }

    public LocalTime getHoraCancelacion() {
        return horaCancelacion;
    }

    public void setHoraCancelacion(LocalTime horaCancelacion) {
        this.horaCancelacion = horaCancelacion;
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", genareser=" + genareser +
                ", solicitante='" + solicitante + '\'' +
                ", genareser2=" + genareser2 +
                ", transporte='" + transporte + '\'' +
                ", insumo='" + insumo + '\'' +
                ", familiar='" + familiar + '\'' +
                ", aislamiento=" + aislamiento +
                ", observaciones='" + observaciones + '\'' +
                ", genpacien=" + genpacien +
                ", camillero=" + camillero +
                ", horaEnvio=" + horaEnvio +
                ", horaAsignacion=" + horaAsignacion +
                ", horaEjecucion=" + horaEjecucion +
                ", horaFinalizacion=" + horaFinalizacion +
                ", tiempoTotal='" + tiempoTotal + '\'' +
                ", cancelado=" + cancelado +
                ", motivoCancelado='" + motivoCancelado + '\'' +
                ", horaCancelacion=" + horaCancelacion +
                '}';
    }
}