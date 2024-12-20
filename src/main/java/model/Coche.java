package model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coche implements Serializable {


    private int id;
    private String matricula;
    private String marca;
    private String modelo;
    private String color;



    public Coche(String matricula, String marca, String modelo, String color) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
    }

    public void mostrarDatos(){
        System.out.println("matricula = " + matricula);
        System.out.println("marca = " + marca);
        System.out.println("modelo = " + modelo);
        System.out.println("color = " + color);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coche)) return false;
        Coche coche = (Coche) o;
        return id == coche.id || Objects.equals(matricula, coche.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, matricula);
    }
}
