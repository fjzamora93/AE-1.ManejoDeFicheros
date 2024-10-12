package model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coche implements Serializable {
    private static final long serialVersionUID =  1L;
    private int id;
    private String matricula;
    private String marca;
    private String modelo;
    private String color;

    public void mostrarDatos(){
        System.out.println("serialVersionUID = " + serialVersionUID);
        System.out.println("id = " + id);
        System.out.println("title = " + matricula);
        System.out.println("price = " + marca);
        System.out.println("stock = " + modelo);
        System.out.println("stock = " + color);
    }

}
