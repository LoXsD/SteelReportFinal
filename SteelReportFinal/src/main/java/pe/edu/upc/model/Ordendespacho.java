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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Future;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Ordendespacho")
public class Ordendespacho implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int idOrdendespacho;
	
	@ManyToOne
	@JoinColumn(name="idJefeventas", nullable=false)
	private Jefeventas jefeventas;

	
	
	@NotNull
	@Future(message="No puedes seleccionar un dia que NO existe")
	@Temporal(TemporalType.DATE)
	@Column(name="fecDateOrdendespacho")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fecDateOrdendespacho;



	public Ordendespacho() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Ordendespacho(int idOrdendespacho, Jefeventas jefeventas,
		Date fecDateOrdendespacho) {
		super();
		this.idOrdendespacho = idOrdendespacho;
		this.jefeventas = jefeventas;
		this.fecDateOrdendespacho = fecDateOrdendespacho;
	}



	public int getIdOrdendespacho() {
		return idOrdendespacho;
	}



	public void setIdOrdendespacho(int idOrdendespacho) {
		this.idOrdendespacho = idOrdendespacho;
	}



	public Jefeventas getJefeventas() {
		return jefeventas;
	}



	public void setJefeventas(Jefeventas jefeventas) {
		this.jefeventas = jefeventas;
	}



	public Date getFecDateOrdendespacho() {
		return fecDateOrdendespacho;
	}



	public void setFecDateOrdendespacho(Date fecDateOrdendespacho) {
		this.fecDateOrdendespacho = fecDateOrdendespacho;
	}



	
	

	
	
}
