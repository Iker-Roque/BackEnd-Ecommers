package isil.eva.pe.jpa.modelo;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordenes")
public class Orden {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id_orden")
	private Integer id_orden;
	
	@Column(name = "id_usuario", nullable = false, length = 100)
	private Integer id_usuario;
	
	@Column(name = "fecha", nullable = false, length = 100)
	private Date fecha;
	
	@Column(name = "total", nullable = false, length = 100)
	private Float total;
	
	public Orden() {
    }
	
	public Orden(Integer id_usuario,  Date fecha, Float total ) {
		this.id_usuario = id_usuario;
		this.fecha = fecha;
		this.total = total;	
					
		}

	public Integer getId_orden() {
		return id_orden;
	}

	public void setId_orden(Integer id_orden) {
		this.id_orden = id_orden;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	@Override
	public String toString() {
		
		return "Ordenes{" + 
		" id_orden = " + id_orden+
		", id_usuario = " + id_usuario+
		", fecha = " + fecha+
		", total = " + total + '}';
	}
		
	
	}



