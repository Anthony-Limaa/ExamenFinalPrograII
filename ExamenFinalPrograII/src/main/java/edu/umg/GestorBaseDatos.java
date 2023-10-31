package edu.umg;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GestorBaseDatos {

    public static String obtenerFechaActual() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.now();
        return fecha.format(formatter);
    }


    // Método para insertar un estudiante en la tabla de estudiantes
    public static void insertarEstudiante(Connection conexion, String nombre, String apellido, String email) {
        String sql = "INSERT INTO estudiantes (nombre, apellido, email) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);
            pstmt.setString(3, email);
            pstmt.executeUpdate();
            System.out.println("Estudiante insertado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al insertar estudiante");
            e.printStackTrace();
        }
    }

    // Método para actualizar un estudiante en la tabla de estudiantes
    public static void actualizarEstudiante(Connection conexion, String nombre, String apellido, String email) {
        String sql = "UPDATE estudiantes SET apellido = ?, email = ? WHERE nombre = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, apellido);
            pstmt.setString(2, email);
            pstmt.setString(3, nombre);
            pstmt.executeUpdate();
            System.out.println("Estudiante actualizado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al actualizar estudiante");
            e.printStackTrace();
        }
    }

    // Método para eliminar un estudiante en la tabla de estudiantes
    public static void eliminarEstudiante(Connection conexion, String nombre) {
        String sql = "DELETE FROM estudiantes WHERE nombre = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.executeUpdate();

            // Actualizamos el índice de la tabla
            String sqlReindex = "REINDEX TABLE estudiantes";
            try (Statement stmt = conexion.createStatement()) {
                stmt.execute(sqlReindex);
            } catch (SQLException e) {
                System.out.println("Error al actualizar el índice");
                e.printStackTrace();
            }

            System.out.println("Estudiante eliminado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al eliminar estudiante");
            e.printStackTrace();
        }
    }






}
