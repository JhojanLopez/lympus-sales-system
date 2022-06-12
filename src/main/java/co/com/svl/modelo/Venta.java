/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package co.com.svl.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JhojanDS
 * @email jhojanlopez327@gmail.com
 */
@Entity
@Table(name = "venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v"),
    @NamedQuery(name = "Venta.findByCodigo", query = "SELECT v FROM Venta v WHERE v.codigo = :codigo"),
    @NamedQuery(name = "Venta.findByFecha", query = "SELECT v FROM Venta v WHERE v.fecha = :fecha"),
    @NamedQuery(name = "Venta.findByHora", query = "SELECT v FROM Venta v WHERE v.hora = :hora"),
    @NamedQuery(name = "Venta.findByGananciaVenta", query = "SELECT v FROM Venta v WHERE v.gananciaVenta = :gananciaVenta"),
    @NamedQuery(name = "Venta.findByTotalVenta", query = "SELECT v FROM Venta v WHERE v.totalVenta = :totalVenta")
})
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ganancia_venta")
    private Long gananciaVenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_venta")
    private Long totalVenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venta")
    private List<ProductoVenta> productoVentaList;
    @JoinColumn(name = "codigo_administrador", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Administrador codigoAdministrador;
    @JoinColumn(name = "codigo_cliente", referencedColumnName = "codigo")
    @ManyToOne
    private Cliente codigoCliente;
    @JoinColumn(name = "codigo_empleado", referencedColumnName = "codigo")
    @ManyToOne
    private Empleado codigoEmpleado;

    public Venta() {
    }

    public Venta(Long codigo) {
        this.codigo = codigo;
    }

    public Venta(Long codigo, Date fecha, Date hora, long gananciaVenta, long totalVenta) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.hora = hora;
        this.gananciaVenta = gananciaVenta;
        this.totalVenta = totalVenta;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public double getGananciaVenta() {
        return gananciaVenta;
    }

    public void setGananciaVenta(long gananciaVenta) {
        this.gananciaVenta = gananciaVenta;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(long totalVenta) {
        this.totalVenta = totalVenta;
    }

    @XmlTransient
    public List<ProductoVenta> getProductoVentaList() {
        return productoVentaList;
    }

    public void setProductoVentaList(List<ProductoVenta> productoVentaList) {
        this.productoVentaList = productoVentaList;
    }

    public Administrador getCodigoAdministrador() {
        return codigoAdministrador;
    }

    public void setCodigoAdministrador(Administrador codigoAdministrador) {
        this.codigoAdministrador = codigoAdministrador;
    }

    public Cliente getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Cliente codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Empleado getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(Empleado codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Venta{" + "codigo=" + codigo + ", fecha=" + fecha + ", hora=" + hora + ", gananciaVenta=" + gananciaVenta + ", totalVenta=" + totalVenta + ", codigoAdministrador=" + codigoAdministrador + ", codigoCliente=" + codigoCliente + ", codigoEmpleado=" + codigoEmpleado + '}';
    }

  
}
