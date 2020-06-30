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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="Jefeventas",uniqueConstraints={@UniqueConstraint(columnNames ={"nombJefeventas","apellJefeventas"})})
public class Jefeventas implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int idJefeventas;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Size(min = 2, max = 50, message = "El nombre debe tener entre 2 a 50 caracteres")
	@Column(name="nombJefeventas", nullable = false)
	private String nombJefeventas;
	
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Size(min = 2, max = 50, message = "El apellido debe tener entre 2 a 50 caracteres")
	@Column(name="apellJefeventas", nullable = false)
	private String apellJefeventas;
	
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Pattern(regexp = "[1-9][0-9]{7}",message="El dni que ha ingresado debe tener 8 números")
	@Column(name="dniJefeventas", nullable = false,unique=true)
	private String dniJefeventas;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Size(min = 3, max = 50, message = "El area debe tener entre 3 a 50 caracteres")
	@Column(name="areaJefeventas", nullable = false,unique=true)
	private String areaJefeventas;
	


	@ManyToOne
	@JoinColumn(name="idTipojefeventas", nullable=false)
	private Tipojefeventas tipojefeventas;



	public Jefeventas() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Jefeventas(int idJefeventas,
			 String nombJefeventas,
			 String apellJefeventas,
			 String dniJefeventas,
			 String areaJefeventas,
			Tipojefeventas tipojefeventas) {
		super();
		this.idJefeventas = idJefeventas;
		this.nombJefeventas = nombJefeventas;
		this.apellJefeventas = apellJefeventas;
		this.dniJefeventas = dniJefeventas;
		this.areaJefeventas = areaJefeventas;
		this.tipojefeventas = tipojefeventas;
	}



	public int getIdJefeventas() {
		return idJefeventas;
	}



	public void setIdJefeventas(int idJefeventas) {
		this.idJefeventas = idJefeventas;
	}



	public String getNombJefeventas() {
		return nombJefeventas;
	}



	public void setNombJefeventas(String nombJefeventas) {
		this.nombJefeventas = nombJefeventas;
	}



	public String getApellJefeventas() {
		return apellJefeventas;
	}



	public void setApellJefeventas(String apellJefeventas) {
		this.apellJefeventas = apellJefeventas;
	}



	public String getDniJefeventas() {
		return dniJefeventas;
	}



	public void setDniJefeventas(String dniJefeventas) {
		this.dniJefeventas = dniJefeventas;
	}



	public String getAreaJefeventas() {
		return areaJefeventas;
	}



	public void setAreaJefeventas(String areaJefeventas) {
		this.areaJefeventas = areaJefeventas;
	}



	public Tipojefeventas getTipojefeventas() {
		return tipojefeventas;
	}



	public void setTipojefeventas(Tipojefeventas tipojefeventas) {
		this.tipojefeventas = tipojefeventas;
	}



	
	
	
}
