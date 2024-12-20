package DAO;
import database.DBConnecion;
import database.SchemaDB;
import model.Coche;
import model.Pasajero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class PasajeroDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public PasajeroDAO() {
        connection = new DBConnecion().getConnection();
    }

    //Añadir nuevo pasajero
    public void addOne(String nombre, int edad, int peso) throws SQLException {
        String query = String.format("INSERT INTO %s (%s, %s, %s) VALUES ?,?,?",
                SchemaDB.TAB_PAS,
                SchemaDB.COL_PAS_NOMBRE, SchemaDB.COL_PAS_EDAD, SchemaDB.COL_PAS_PESO
                );
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, nombre);
        preparedStatement.setInt(2, edad);
        preparedStatement.setInt(3, peso);
        preparedStatement.execute();
    }

    //Borrar pasajero por id
    public void deleteById(int id) throws SQLException {
        String query = String.format("DELETE FROM %s WHERE %s = ?",
                SchemaDB.TAB_PAS, SchemaDB.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    //Consulta pasajero por id
    public Pasajero findById(int id) throws SQLException {
        String query = String.format("SELECT * FROM %s WHERE %s = id",
                SchemaDB.TAB_PAS, SchemaDB.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        if (!getResultados(resultSet).isEmpty()){
            return getResultados(resultSet).get(0);
        } else {
            return null;
        }

    }

    //Listar todos los pasajeros
    public ArrayList<Pasajero> findAll() throws SQLException {
        String query = String.format("SELECT * FROM %s",
                SchemaDB.TAB_PAS);
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        return getResultados(resultSet);
    }

    //Añadir pasajero a coche, id de un pasajero y el id de un coche, y lo añadirá al coche DB.
    //Sería una buena opción mostrar todos los coches disponibles. ???? Y cómo se supone que entendemos que un coche está disponible?
    public void addPasToCar(int idPas, int idCoche) throws SQLException {
        String query = String.format("INSERT INTO %s WHERE %s = ? AND %s = ?",
                SchemaDB.TAB_COCHE_PAS,
                SchemaDB.COL_PAS_ID,
                SchemaDB.COL_COCHE_ID );
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idPas);
        preparedStatement.setInt(2, idCoche);
        resultSet = preparedStatement.executeQuery();
    }


    //Eliminar pasajero de coche, pedirá un id pasajero y el id coche, y lo eliminará del coche en base de datos.
    // Sería una buena opción mostrar todos los coches y sus pasajeros asociados.
    public void deletePassOnCar(int idPas, int idCoche) throws SQLException {
        String query = String.format("DELETE FROM %s WHERE %s = ? AND %s = ?",
                SchemaDB.TAB_COCHE_PAS,
                SchemaDB.COL_PAS_ID,
                SchemaDB.COL_COCHE_ID );
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idPas);
        preparedStatement.setInt(2, idCoche);
        resultSet = preparedStatement.executeQuery();
        pasajerosPorCoche();
    }

    //MOSTRAR TODOS LOS COCHES Y SUS PASAJEROS ASOCIADOS
    public void pasajerosPorCoche() throws SQLException {
        String query = String.format(
                "SELECT p.*, c.* " +
                        "FROM %s p " +
                        "INNER JOIN %s cp ON p.%s = cp.%s " +
                        "INNER JOIN %s c ON cp.%s = c.%s",
                SchemaDB.TAB_PAS,
                SchemaDB.TAB_COCHE_PAS, SchemaDB.COL_ID, SchemaDB.COL_PAS_ID,
                SchemaDB.TAB_COCHE, SchemaDB.COL_COCHE_ID, SchemaDB.COL_ID
        );
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            String nombre = resultSet.getString(SchemaDB.COL_PAS_NOMBRE);
            String matricula = resultSet.getString(SchemaDB.COL_COCHE_MATRICULA);
            String pasajeroEnCoche = nombre + " - " + matricula;
            System.out.println(pasajeroEnCoche);
        }
}
 //Listar todos los pasajeros de un coche, el programa pedirá el id de un coche, y mostrará todos los pasajeros de él.
    public ArrayList<Pasajero> findPasOnCar(int idCoche) throws SQLException {
        String query = String.format(
                "SELECT * FROM %s WHERE %s IN (SELECT %s FROM %s WHERE %s = ?)",
                SchemaDB.TAB_PAS,
                SchemaDB.COL_ID,
                SchemaDB.COL_PAS_ID,
                SchemaDB.TAB_COCHE_PAS,
                SchemaDB.COL_COCHE_ID
        );
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idCoche);
        resultSet = preparedStatement.executeQuery();
        return getResultados(resultSet);

    }

    private ArrayList<Pasajero> getResultados(ResultSet datosResultantes) throws SQLException {
        ArrayList<Pasajero> listaResultado = new ArrayList<>();
        while (datosResultantes.next()){
            String nombre = resultSet.getString(SchemaDB.COL_PAS_NOMBRE);
            int edad = resultSet.getInt(SchemaDB.COL_PAS_EDAD);
            int peso = resultSet.getInt(SchemaDB.COL_PAS_PESO);
            listaResultado.add(mapearPasajero(nombre,edad,peso));
        }
        return listaResultado;
    }

    private Pasajero mapearPasajero(String nombre, int edad, int peso){
        return new Pasajero(nombre, edad, peso);
    }

}
