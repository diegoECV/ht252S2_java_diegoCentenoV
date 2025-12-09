package diegoCenteno.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Producto {
    private Integer idProducto;
    private String codigo;
    private String tipoEquipo;
    private String marca;
    private String modelo;
    private String sistemaOperativo;
    private Integer almacenamiento; // en GB
    private Integer ram; // en GB
    private String estado;
    private LocalDate fechaMantenimiento;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaActualizacion;

    public Producto() {
    }

    public Producto(Integer idProducto, String codigo, String tipoEquipo, String marca,
                    String modelo, String sistemaOperativo, Integer almacenamiento,
                    Integer ram, String estado, LocalDate fechaMantenimiento,
                    LocalDateTime fechaRegistro, LocalDateTime fechaActualizacion) {
        this.idProducto = idProducto;
        this.codigo = codigo;
        this.tipoEquipo = tipoEquipo;
        this.marca = marca;
        this.modelo = modelo;
        this.sistemaOperativo = sistemaOperativo;
        this.almacenamiento = almacenamiento;
        this.ram = ram;
        this.estado = estado;
        this.fechaMantenimiento = fechaMantenimiento;
        this.fechaRegistro = fechaRegistro;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(String tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public Integer getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(Integer almacenamiento) {
        this.almacenamiento = almacenamiento;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isActivo() {
        return "Activo".equalsIgnoreCase(estado);
    }

    public void setActivo(boolean activo) {
        this.estado = activo ? "Activo" : "Inactivo";
    }

    public LocalDate getFechaMantenimiento() {
        return fechaMantenimiento;
    }

    public void setFechaMantenimiento(LocalDate fechaMantenimiento) {
        this.fechaMantenimiento = fechaMantenimiento;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + idProducto +
                ", codigo='" + codigo + '\'' +
                ", tipo='" + tipoEquipo + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", SO='" + sistemaOperativo + '\'' +
                ", almacenamiento=" + almacenamiento + "GB" +
                ", ram=" + ram + "GB" +
                ", estado='" + estado + '\'' +
                '}';
    }
}

