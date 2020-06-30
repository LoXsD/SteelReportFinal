package pe.edu.upc.model;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Ordencompra")
public class Ordencompra implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int idOrdencompra;
	

	
	@ManyToOne
	@JoinColumn(name="idJefeventas", nullable=false)
	private Jefeventas jefeventas;



	public Ordencompra() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Ordencompra(int idOrdencompra, Jefeventas jefeventas) {
		super();
		this.idOrdencompra = idOrdencompra;
		this.jefeventas = jefeventas;
	}



	public int getIdOrdencompra() {
		return idOrdencompra;
	}



	public void setIdOrdencompra(int idOrdencompra) {
		this.idOrdencompra = idOrdencompra;
	}



	public Jefeventas getJefeventas() {
		return jefeventas;
	}



	public void setJefeventas(Jefeventas jefeventas) {
		this.jefeventas = jefeventas;
	}




	
}
