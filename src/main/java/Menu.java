import java.util.Scanner;



public class Menu {
    public static void main(String[] args ) throws InterruptedException {

        GestorCoches concesionario = new GestorCoches();

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            // Mostrar el menú de opciones
            System.out.println("Menú de opciones:");
            System.out.println("1. Añadir nuevo coche");
            System.out.println("2. Borrar coche por id");
            System.out.println("3. Consulta coche por id");
            System.out.println("4. Listado de coches");
            System.out.println("5. Convertir a CSV");
            System.out.println("6. Terminar el programa");
            System.out.print("Seleccione una opción: ");

            // Leer la opción seleccionada por el usuario
            opcion = scanner.nextInt();

            // Manejar las opciones seleccionadas
            switch (opcion) {
                case 1:
                    concesionario.addNew();
                    Thread.sleep(2000);
                    break;
                case 2:
                    concesionario.deleteById();
                    Thread.sleep(2000);
                    break;
                case 3:
                    concesionario.findById();
                    Thread.sleep(2000);
                    break;
                case 4:
                    concesionario.findAll();
                    Thread.sleep(2000);
                    break;
                case 5:
                    concesionario.toCsv();
                    Thread.sleep(2000);
                    break;
                case 6:
                    System.out.println("Se ha seleccionado la opción: Terminar el programa");
                    Thread.sleep(2000);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción del 1 al 6.");
                    Thread.sleep(2000);
            }
        } while (opcion != 6);


        scanner.close();
        System.out.println("Programa terminado.");
    }
}


