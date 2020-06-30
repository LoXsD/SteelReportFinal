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
@Table(name="Cliente", uniqueConstraints={@UniqueConstraint(columnNames ={"nombreCliente","apellidosCliente"})})
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int idCliente;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Size(min = 2, max = 50, message = "El nombre debe tener entre 2 a 50 caracteres")
	@Column(name="nombreCliente", nullable = false)
	private String nombreCliente;
	
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Size(min = 2, max = 50,message = "El apellido debe tener entre 2 a 50 caracteres" )
	@Column(name="apellidosCliente", nullable = false)
	private String apellidosCliente;
	
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Pattern(regexp = "[9][0-9]{8}",message="El numero que ha ingresado debe tener 9 números")
	@Column(name="telefonoCliente",unique=true, nullable = false )
	private String telefonoCliente;
	
	@ManyToOne
	@JoinColumn(name="idTipocliente", nullable=false)
	private Tipocliente tipocliente;
	
	@ManyToOne
	@JoinColumn(name="idJefeventas", nullable=false)
	private Jefeventas jefeventas;



	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cliente(int idCliente, String nombreCliente, String apellidosCliente,String telefonoCliente,
			Tipocliente tipocliente, Jefeventas jefeventas) {
		super();
		this.idCliente = idCliente;
		this.nombreCliente = nombreCliente;
		this.apellidosCliente = apellidosCliente;
		this.telefonoCliente = telefonoCliente;
		this.tipocliente = tipocliente;
		this.jefeventas = jefeventas;
	}
	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getApellidosCliente() {
		return apellidosCliente;
	}

	public void setApellidosCliente(String apellidosCliente) {
		this.apellidosCliente = apellidosCliente;
	}

	public String getTelefonoCliente() {
		return telefonoCliente;
	}

	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}

	public Tipocliente getTipocliente() {
		return tipocliente;
	}

	public void setTipocliente(Tipocliente tipocliente) {
		this.tipocliente = tipocliente;
	}

	public Jefeventas getJefeventas() {
		return jefeventas;
	}

	public void setJefeventas(Jefeventas jefeventas) {
		this.jefeventas = jefeventas;
	}
	
}
