package pe.edu.upc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Hojaruta", uniqueConstraints={@UniqueConstraint(columnNames ={"descrHojaruta","fecDateHojaruta","presuHojaruta","obsHojaruta","idJefeventas"})})
public class Hojaruta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idHojaruta;

	@ManyToOne
	@JoinColumn(name = "idJefeventas", nullable = false)
	private Jefeventas jefeventas;

	@NotNull
	// @Past(message="No puedes seleccionar un dia que NO existe")
	@Temporal(TemporalType.DATE)
	@Column(name = "fecDateHojaruta")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecDateHojaruta;

	@NotEmpty(message = "No puede estar vacio")
	@NotBlank(message = "No puede estar en blanco")
	@Size(min = 5, max = 90, message = "La descripción debe tener entre 5 a 90 caracteres")
	@Column(name = "descrHojaruta", nullable = false)
	private String descrHojaruta;
	
	@Range(min = 100,max =1000000,message ="La cantidad debe de estar entre 100 y 1000000") 
	@Column(name = "presuHojaruta", nullable = false)
	private int presuHojaruta;

	@NotEmpty(message = "No puede estar vacio")
	@NotBlank(message = "No puede estar en blanco")
	@Size(min = 5, max = 90, message = "Las observaciones deben de tener entre 5 a 90 caracteres")
	@Column(name = "obsHojaruta", nullable = false)
	private String obsHojaruta;

	public Hojaruta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Hojaruta(int idHojaruta, Jefeventas jefeventas, Date fecDateHojaruta, String descrHojaruta,
			int presuHojaruta, String obsHojaruta) {
		super();
		this.idHojaruta = idHojaruta;
		this.jefeventas = jefeventas;
		this.fecDateHojaruta = fecDateHojaruta;
		this.descrHojaruta = descrHojaruta;
		this.presuHojaruta = presuHojaruta;
		this.obsHojaruta = obsHojaruta;
	}

	public int getIdHojaruta() {
		return idHojaruta;
	}

	public void setIdHojaruta(int idHojaruta) {
		this.idHojaruta = idHojaruta;
	}

	public Jefeventas getJefeventas() {
		return jefeventas;
	}

	public void setJefeventas(Jefeventas jefeventas) {
		this.jefeventas = jefeventas;
	}

	public Date getFecDateHojaruta() {
		return fecDateHojaruta;
	}

	public void setFecDateHojaruta(Date fecDateHojaruta) {
		this.fecDateHojaruta = fecDateHojaruta;
	}

	public String getDescrHojaruta() {
		return descrHojaruta;
	}

	public void setDescrHojaruta(String descrHojaruta) {
		this.descrHojaruta = descrHojaruta;
	}

	public int getPresuHojaruta() {
		return presuHojaruta;
	}

	public void setPresuHojaruta(int presuHojaruta) {
		this.presuHojaruta = presuHojaruta;
	}

	public String getObsHojaruta() {
		return obsHojaruta;
	}

	public void setObsHojaruta(String obsHojaruta) {
		this.obsHojaruta = obsHojaruta;
	}
}
