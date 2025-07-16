package isil.eva.pe.jpa.modelo; 

import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table; 

@Entity 
@Table(name = "categorias") 
public class Categoria {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id_categoria")
    private Integer idCategoria; 

    @Column(name = "nombre", nullable = false, length = 100) 
    private String nombre;

    // --- Constructores ---
    public Categoria() {
    }

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    // --- Getters y Setters ---
    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public String toString() {
        return "Categoria{" +
               "idCategoria=" + idCategoria +
               ", nombre='" + nombre + '\'' +
               '}';
    }
}