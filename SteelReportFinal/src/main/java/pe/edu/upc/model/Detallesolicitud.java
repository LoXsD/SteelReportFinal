package pe.edu.upc.model;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.Range;



@Entity
@Table(name="Detallesolicitud",uniqueConstraints={@UniqueConstraint(columnNames ={"idSolicitudcompra","idProducto"})})
public class Detallesolicitud implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int idDetallesolicitud;
	

	@Range(min = 1,max =10000,message ="La cantidad debe de estar entre 1 y 10000") 
	@Column(name="cantiDetallesolicitud", nullable = false)
	private int cantiDetallesolicitud;
	

	@ManyToOne
	@JoinColumn(name="idSolicitudcompra", nullable=false)
	private Solicitudcompra solicitudcompra;

	@ManyToOne
	@JoinColumn(name="idProducto", nullable=false)
	private Producto producto;

	public Detallesolicitud() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Detallesolicitud(int idDetallesolicitud, int cantiDetallesolicitud,Solicitudcompra solicitudcompra, Producto producto) {
		super();
		this.idDetallesolicitud = idDetallesolicitud;
		this.cantiDetallesolicitud = cantiDetallesolicitud;
		this.solicitudcompra = solicitudcompra;
		this.producto = producto;
	}







	public int getIdDetallesolicitud() {
		return idDetallesolicitud;
	}







	public void setIdDetallesolicitud(int idDetallesolicitud) {
		this.idDetallesolicitud = idDetallesolicitud;
	}







	public int getCantiDetallesolicitud() {
		return cantiDetallesolicitud;
	}







	public void setCantiDetallesolicitud(int cantiDetallesolicitud) {
		this.cantiDetallesolicitud = cantiDetallesolicitud;
	}







	public Solicitudcompra getSolicitudcompra() {
		return solicitudcompra;
	}







	public void setSolicitudcompra(Solicitudcompra solicitudcompra) {
		this.solicitudcompra = solicitudcompra;
	}







	public Producto getProducto() {
		return producto;
	}







	public void setProducto(Producto producto) {
		this.producto = producto;
	}





	
}
