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
@Table(name="Detallecompra",uniqueConstraints={@UniqueConstraint(columnNames ={"descriDetallecompra","cantiDetallecompra","preciouDetallecompra","totalDetallecompra","idProducto","idOrdencompra"})})
public class Detallecompra implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int idDetallecompra;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Size(min = 5, max = 90, message = "La descripcion debe tener entre 5 a 90 caracteres")
	@Column(name="descriDetallecompra", nullable = false)
	private String descriDetallecompra;
	
	
	@Range(min = 1,max = 10000,message ="La cantidad debe de estar entre 1 y 10000" ) 
	@Column(name="cantiDetallecompra", nullable = false)
	private int cantiDetallecompra;
	

	@Range(min = 1,max =100000,message ="El precio debe de estar entre 1 y 100000") 
	@Column(name="preciouDetallecompra", nullable = false)
	private int preciouDetallecompra;
	
	@Range(min = 1,max = 1000000,message ="El total de detalle debe de estar entre 1 y 1000000") 
	@Column(name="totalDetallecompra", nullable = false)
	private int totalDetallecompra;
	

	@ManyToOne
	@JoinColumn(name="idOrdencompra", nullable=false)
	private Ordencompra ordencompra;

	@ManyToOne
	@JoinColumn(name="idProducto", nullable=false)
	private Producto producto;

	public Detallecompra() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Detallecompra(int idDetallecompra,
			 String descriDetallecompra,
			 int cantiDetallecompra,
			 int preciouDetallecompra,
			 int totalDetallecompra,
			Ordencompra ordencompra, Producto producto) {
		super();
		this.idDetallecompra = idDetallecompra;
		this.descriDetallecompra = descriDetallecompra;
		this.cantiDetallecompra = cantiDetallecompra;
		this.preciouDetallecompra = preciouDetallecompra;
		this.totalDetallecompra = totalDetallecompra;
		this.ordencompra = ordencompra;
		this.producto = producto;
	}

	public int getIdDetallecompra() {
		return idDetallecompra;
	}

	public void setIdDetallecompra(int idDetallecompra) {
		this.idDetallecompra = idDetallecompra;
	}

	public String getDescriDetallecompra() {
		return descriDetallecompra;
	}

	public void setDescriDetallecompra(String descriDetallecompra) {
		this.descriDetallecompra = descriDetallecompra;
	}

	public int getCantiDetallecompra() {
		return cantiDetallecompra;
	}

	public void setCantiDetallecompra(int cantiDetallecompra) {
		this.cantiDetallecompra = cantiDetallecompra;
	}

	public int getPreciouDetallecompra() {
		return preciouDetallecompra;
	}

	public void setPreciouDetallecompra(int preciouDetallecompra) {
		this.preciouDetallecompra = preciouDetallecompra;
	}

	public int getTotalDetallecompra() {
		return totalDetallecompra;
	}

	public void setTotalDetallecompra(int totalDetallecompra) {
		this.totalDetallecompra = totalDetallecompra;
	}

	public Ordencompra getOrdencompra() {
		return ordencompra;
	}

	public void setOrdencompra(Ordencompra ordencompra) {
		this.ordencompra = ordencompra;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	
	
	
}
