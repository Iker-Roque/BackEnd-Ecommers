package isil.eva.pe.jpa.modelo;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "carrito")
public class Carrito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_carrito")
	private Integer id_carrito;
	
	@Column(name = "id_usuario", nullable = false, length = 100)
	private Integer id_usuario;
	
	@Column(name = "fecha_creacion", nullable = false, length = 100)
	private Date fecha_creacion;
	
	public Carrito () {
			
	}
	
	public Carrito(Integer id_usuario,Date fecha_creacion) {
		this.id_usuario = id_usuario;
		this.fecha_creacion = fecha_creacion;
	}

	public Integer getId_carrito() {
		return id_carrito;
	}

	public void setId_carrito(Integer id_carrito) {
		this.id_carrito = id_carrito;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	
	@Override
	public String toString() {
	    return "Carrito{" +
	           "id_carrito=" + id_carrito +
	           ", id_usuario=" + id_usuario +
	           ", fecha_creacion=" + fecha_creacion +
	           '}';
	}

}
