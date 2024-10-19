package resources;
import Model.Coche;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class GestionDelAlmacen {

    private ArrayList<Coche> coches;

    public GestionDelAlmacen() {
        coches = new ArrayList();
        cargarDatos();
    }
    /*Creo una file e igualo el objectInputStream para poder cerrarlo luego  creo un objectInputStream para leer los datos dentro
    creo un FileInput y dentro de el meto el file creado anteriormente, ahora al usar la funcion readObject me pide que castee el
    objectInputStream a ArrayList de tipo Coche y creo las excepciones, finalmente cierro el flujo.*/
    public void cargarDatos() {
        File file = new File("src/main/java/resources/coches.dat");
        ObjectInputStream objectInputStream = null;
        if (file.exists()) {
            try {
                objectInputStream = new ObjectInputStream(new FileInputStream(file));
                coches = (ArrayList<Coche>) objectInputStream.readObject();
            } catch (IOException e) {
                System.out.println("Error al cargar los datos");
                e.getMessage();
            } catch (ClassNotFoundException e) {
                System.out.println("Clase no encontrada");
                e.getMessage();

            } finally {
                if(objectInputStream != null){
                    try {
                        objectInputStream.close();
                    } catch (IOException e) {
                        System.out.println("Error al cerrar el flujo de datos");
                    }
                }else{
                    System.out.println("El archivo no existe");
                }
            }
        }
    }

    public void guardarDatos(){
        File file = new File("src/main/java/resources/coches.dat");
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(coches);
        } catch (IOException e) {
            System.out.println("Error al guardar");
        }

    }

    //MENU
    public void metodoMenu(){
    int opcion = 0;
    Scanner leer = new Scanner(System.in);

    do{
        System.out.println("1.Añadir nuevo coche");
        System.out.println("2. Borrar coche por id");
        System.out.println("3. Consulta coche por id");
        System.out.println("4.Listado de coches");
        System.out.println("5.Exportar archivo");
        System.out.println("6.Terminar programa");
        System.out.println("Que opcion desea");
        opcion = leer.nextInt();
        switch(opcion){
            case 1:
                añadirCoche(leer);
                break;
            case 2:
                borrarCoche();
                break;
            case 3:
                consultarCoche(leer);
                break;
            case 4:
                listarCoches();
                break;
            case 5:
                exportarDatos();
                break;
            case 6:
                System.out.println("Fin de Programa !");
                break;
        }
    }while (opcion !=6);

    }
    private void añadirCoche(Scanner leer){
        System.out.println("Introduce el id");
        int id = leer.nextInt();
        System.out.println("Introduce la matricula");
        String matricula = leer.next();
        System.out.println("Introduce la marca");
        String marca = leer.next();
        System.out.println("Introduce el modelo");
        String modelo = leer.next();
        System.out.println("Introduce el color");
        String color = leer.next();

        Coche coche = new Coche();
        coche.setId(id);
        coche.setMatricula(matricula);
        coche.setMarca(marca);
        coche.setModelo(modelo);
        coche.setColor(color);

        for(Coche item:coches){
            if(coche.getId() == item.getId() || coche.getMatricula().equals(item.getMatricula())){
                System.out.println("El coche ya existe porfavor reviselo");
                return;
            }
        }
        coches.add(coche);
        guardarDatos();

        System.out.println("El coche ha sido añadido con exito!");

    }

    private void borrarCoche(){
        Scanner leer = new Scanner(System.in);
        System.out.println("Introduce el id");
        int id = leer.nextInt();
        Coche coche = new Coche();
        coche.setId(id);
        for(Coche item : coches){
            if(coche.getId() == item.getId()){
                coches.remove(item);
                guardarDatos();
                return;
            }

        }
    }

    private void consultarCoche(Scanner leer){
        System.out.println("Introduce el id");
        int id = leer.nextInt();
        Coche coche = new Coche();
        coche.setId(id);
        for(Coche item:coches){
            if(coche.getId() == item.getId()){
                System.out.println(item);
                return;
            }
        }
        System.out.println("Coche no encontrado, porfavor intentalo de nuevo");
    }

    private void listarCoches(){
        if(coches.isEmpty()){
            System.out.println("no hay coches en el almace");
        }else{
            for (Coche item: coches){
                System.out.println(item);
            }
        }

    }
    private void exportarDatos(){
        File file = new File("src/main/java/resources/coches.dat");
        ObjectInputStream objectInputStream = null;
        ArrayList<Coche>datosex = new ArrayList<>();
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
             datosex = (ArrayList<Coche>) objectInputStream.readObject();

        } catch (IOException e) {
            System.out.println("Error en la lectura");
        } catch (ClassNotFoundException e) {
            System.out.println("El archivo no se a encontrado");
        }
        finally {
            if(objectInputStream != null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar el flujo de datos");
                }
            }
        }
        File file1 = new File("src/main/java/resources/coches.txt");
        PrintWriter printWriter =null;
        try {
            printWriter = new PrintWriter(file1);
            for(Coche item: datosex){
                printWriter.println(item);
            }
            System.out.println("La exportación ha sido completada con éxito");
        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
        }finally {
            if(printWriter != null){
                printWriter.close();
            }
        }
    }
}


