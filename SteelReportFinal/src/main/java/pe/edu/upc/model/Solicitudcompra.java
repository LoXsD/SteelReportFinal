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
@Table(name="Solicitudcompra")
public class Solicitudcompra implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int idSolicitudcompra;
	
	
    @ManyToOne
	@JoinColumn(name="idCliente", nullable=false)
	private Cliente cliente;



	public Solicitudcompra() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Solicitudcompra(int idSolicitudcompra, Cliente cliente) {
		super();
		this.idSolicitudcompra = idSolicitudcompra;
		this.cliente = cliente;
	}

	public int getIdSolicitudcompra() {
		return idSolicitudcompra;
	}



	public void setIdSolicitudcompra(int idSolicitudcompra) {
		this.idSolicitudcompra = idSolicitudcompra;
	}



	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
