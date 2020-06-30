package pe.edu.upc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="Tipojefeventas")
public class Tipojefeventas implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int idTipojefeventas;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Size(min = 3, max = 50, message = "El tipo de jefe debe tener entre 3 a 50 caracteres")
	@Column(name="nombreTipo", nullable = false, length=60, unique=true)
	private String nombreTipo;

	public Tipojefeventas() {
		super();
	}

	public Tipojefeventas(int idTipojefeventas, String nombreTipo) {
		super();
		this.idTipojefeventas = idTipojefeventas;
		this.nombreTipo = nombreTipo;
	}

	public int getIdTipojefeventas() {
		return idTipojefeventas;
	}

	public void setIdTipojefeventas(int idTipojefeventas) {
		this.idTipojefeventas = idTipojefeventas;
	}

	public String getNombreTipo() {
		return nombreTipo;
	}

	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}
      	
}
