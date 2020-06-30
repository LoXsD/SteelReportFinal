package pe.edu.upc.model;
//js
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name="Producto",uniqueConstraints={@UniqueConstraint(columnNames ={"medidProducto","nomProducto"})})
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int idProducto;
	

	@Range(min = 1,max = 10000,message ="La medida del producto de estar entre 1 ml y 10000 ml" ) 
	@Column(name="medidProducto", nullable = false)
	private int medidProducto;
	
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Size(min = 3, max = 50, message = "El producto debe tener entre 3 a 50 caracteres")
	@Column(name="nomProducto", nullable = false)
	private String nomProducto;
	
	//LE C AMBIOE LE NOMBRE A LA TABLA NOMA
	
	@Range(min = 0,max = 99999,message ="El stock puede tener los valores entre 0 a 99999")
	@Column(name="stockProducto", nullable = false) //cambio a int
	private int stockProducto;


	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}


	


	public Producto(int idProducto,
			 int medidProducto,
			 String nomProducto,
			 int stockProducto) {
		super();
		this.idProducto = idProducto;
		this.medidProducto = medidProducto;
		this.nomProducto = nomProducto;
		this.stockProducto = stockProducto;
	}





	public int getIdProducto() {
		return idProducto;
	}





	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}





	public int getMedidProducto() {
		return medidProducto;
	}





	public void setMedidProducto(int medidProducto) {
		this.medidProducto = medidProducto;
	}





	public String getNomProducto() {
		return nomProducto;
	}





	public void setNomProducto(String nomProducto) {
		this.nomProducto = nomProducto;
	}





	public int getStockProducto() {
		return stockProducto;
	}





	public void setStockProducto(int stockProducto) {
		this.stockProducto = stockProducto;
	}





	
	

	
	
	
	
}
