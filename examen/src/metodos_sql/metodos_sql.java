/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metodos_sql;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author israel
 */
public class metodos_sql {

    public static conexionBD conexion = new conexionBD();

    public static PreparedStatement sentencia_lista;
    public static ResultSet resultado;
    public static String sql;
    public static int resultado_numero = 0;

    public int guardar(String nombre, String apellidos, String correo, String contraseña) {

        int resultado = 0;
        Connection conexion = null;

        String sentencia_guardar = ("INSERT INTO usuarios(nombre,apellidos,correo,contraseña) VALUES (?,?,?,?)");

        try {
            conexion = conexionBD.conectar();
            sentencia_lista = (PreparedStatement) conexion.prepareStatement(sentencia_guardar);
            sentencia_lista.setString(1, nombre);
            sentencia_lista.setString(2, apellidos);
            sentencia_lista.setString(3, correo);
            sentencia_lista.setString(4, contraseña);

            resultado = sentencia_lista.executeUpdate();
            sentencia_lista.close();

            conexion.close();

        } catch (Exception e) {

            System.out.println(e);
        }
        return resultado;
    }

    public static String buscarnombre(String correo) {

        String busqueda_nombre = null;
        Connection conexion = null;

        try {
            conexion = conexionBD.conectar();
            String sentencia_buscar = ("SELECT nombre,apellidos FROM usuarios WHERE correo = '" + correo + "'");
            sentencia_lista = (PreparedStatement) conexion.prepareStatement(sentencia_buscar);
            resultado = sentencia_lista.executeQuery();
            if (resultado.next()) {
                String nombre = resultado.getString("nombre");
                String apellidos = resultado.getString("apellidos");
                busqueda_nombre = (nombre + " " + apellidos);

            }

            conexion.close();

        } catch (Exception e) {

            System.out.println(e);
        }
        return busqueda_nombre;
    }

    public static String buscarusuarioregistrado(String correo, String contraseña) {
        String busqueda_usuario = null;
        Connection conexion = null;

        try {
            conexion = conexionBD.conectar();
            String sentencia_buscar_usuario = ("SELECT nombre,correo,contraseña FROM usuarios WHERE correo = '" + correo + "' && contraseña = '" + contraseña + "'");
            sentencia_lista = (PreparedStatement) conexion.prepareStatement(sentencia_buscar_usuario);
            resultado = sentencia_lista.executeQuery();
            if (resultado.next()) {
                busqueda_usuario = "Usuario encontrado";
            } else {
                busqueda_usuario = "Usuario no encontrado";
            }

            conexion.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return busqueda_usuario;

    }

}
