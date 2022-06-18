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
 * @author JHOJAN L
 */
@Entity
@Table(name = "reporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reporte.findAll", query = "SELECT r FROM Reporte r"),
    @NamedQuery(name = "Reporte.findByCodigo", query = "SELECT r FROM Reporte r WHERE r.codigo = :codigo"),
  })
public class Reporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_generacion")
    @Temporal(TemporalType.DATE)
    private Date fechaGeneracion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "desde")
    @Temporal(TemporalType.DATE)
    private Date desde;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hasta")
    @Temporal(TemporalType.DATE)
    private Date hasta;
    @Column(name = "precio_total_ventas")
    private long precioTotalVentas;
    @Column(name = "costo_total_ventas")
    private long costoTotalVentas;
    @Column(name = "ganancia_total_ventas")
    private long gananciaTotalVentas;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reporte")
    private List<ReporteVenta> reporteVentaList;

    public Reporte() {
    }

    public Reporte(Long codigo) {
        this.codigo = codigo;
    }

    public Reporte(Date fechaGeneracion, Date desde, Date hasta) {
        this.fechaGeneracion = fechaGeneracion;
        this.desde = desde;
        this.hasta = hasta;
    }

    public Reporte(Date fechaGeneracion, Date desde, Date hasta, long precioTotalVentas, long costoTotalVentas, long gananciaTotalVentas) {
        this.fechaGeneracion = fechaGeneracion;
        this.desde = desde;
        this.hasta = hasta;
        this.precioTotalVentas = precioTotalVentas;
        this.costoTotalVentas = costoTotalVentas;
        this.gananciaTotalVentas = gananciaTotalVentas;
    }

   
 
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    
    
    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public long getPrecioTotalVentas() {
        return precioTotalVentas;
    }

    public void setPrecioTotalVentas(long precioTotalVentas) {
        this.precioTotalVentas = precioTotalVentas;
    }

    public long getCostoTotalVentas() {
        return costoTotalVentas;
    }

    public void setCostoTotalVentas(long costoTotalVentas) {
        this.costoTotalVentas = costoTotalVentas;
    }

   
    public long getGananciaTotalVentas() {
        return gananciaTotalVentas;
    }

    public void setGananciaTotalVentas(long gananciaTotalVentas) {
        this.gananciaTotalVentas = gananciaTotalVentas;
    }

    

    
    @XmlTransient
    public List<ReporteVenta> getReporteVentaList() {
        return reporteVentaList;
    }

    public void setReporteVentaList(List<ReporteVenta> reporteVentaList) {
        this.reporteVentaList = reporteVentaList;
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
        if (!(object instanceof Reporte)) {
            return false;
        }
        Reporte other = (Reporte) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reporte{" + "codigo=" + codigo + ", fechaGeneracion=" + fechaGeneracion + ", desde=" + desde + ", hasta=" + hasta + ", precioTotalVentas=" + precioTotalVentas + ", costoTotalVentas=" + costoTotalVentas +", gananciaTotalVentas=" + gananciaTotalVentas + '}';
    }

    

}
