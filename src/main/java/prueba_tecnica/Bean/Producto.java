package prueba_tecnica.Bean;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "producto")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;

    @NotNull(message = "El costo no puede ser nulo")
    private Double costo;

    @NotNull(message = "El precio no puede ser nulo")
    private Double precio;

    @NotNull(message = "La existencia no puede ser nula")
    private Integer existencia;


}
