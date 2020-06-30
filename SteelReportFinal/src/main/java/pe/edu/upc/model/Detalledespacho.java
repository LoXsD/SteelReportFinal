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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name="Detalledespacho",uniqueConstraints={@UniqueConstraint(columnNames ={"lugardDespacho","nombdDespacho","cantidaddDespacho","idProducto","idOrdendespacho"})})
public class Detalledespacho implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int idDetalledespacho;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Size(min = 2, max = 90, message = "El lugar debe tener entre 2 a 90 caracteres")
	@Column(name="lugardDespacho", nullable = false)
	private String lugardDespacho;
	
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Size(min = 2, max = 90, message = "El nombre del lugar debe tener entre 2 a 90 caracteres")
	@Column(name="nombdDespacho", nullable = false)
	private String nombdDespacho;
	
	@Range(min = 1,max =10000,message ="La cantidad debe de estar entre 1 y 10000") 
	@Column(name="cantidaddDespacho", nullable = false)
	private int cantidaddDespacho;
	
	@ManyToOne
	@JoinColumn(name="idProducto", nullable=false)
	private Producto producto;
	
	@ManyToOne
	@JoinColumn(name="idOrdendespacho", nullable=false)
	private Ordendespacho ordendespacho;

	public Detalledespacho() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Detalledespacho(int idDetalledespacho,
			 String lugardDespacho,
			 String nombdDespacho,
			 int cantidaddDespacho,
			Producto producto, Ordendespacho ordendespacho) {
		super();
		this.idDetalledespacho = idDetalledespacho;
		this.lugardDespacho = lugardDespacho;
		this.nombdDespacho = nombdDespacho;
		this.cantidaddDespacho = cantidaddDespacho;
		this.producto = producto;
		this.ordendespacho = ordendespacho;
	}

	public int getIdDetalledespacho() {
		return idDetalledespacho;
	}

	public void setIdDetalledespacho(int idDetalledespacho) {
		this.idDetalledespacho = idDetalledespacho;
	}

	public String getLugardDespacho() {
		return lugardDespacho;
	}

	public void setLugardDespacho(String lugardDespacho) {
		this.lugardDespacho = lugardDespacho;
	}

	public String getNombdDespacho() {
		return nombdDespacho;
	}

	public void setNombdDespacho(String nombdDespacho) {
		this.nombdDespacho = nombdDespacho;
	}

	public int getCantidaddDespacho() {
		return cantidaddDespacho;
	}

	public void setCantidaddDespacho(int cantidaddDespacho) {
		this.cantidaddDespacho = cantidaddDespacho;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Ordendespacho getOrdendespacho() {
		return ordendespacho;
	}

	public void setOrdendespacho(Ordendespacho ordendespacho) {
		this.ordendespacho = ordendespacho;
	}	
	
}
