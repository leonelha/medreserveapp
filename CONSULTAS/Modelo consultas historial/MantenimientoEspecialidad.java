/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import ConfigBD.Conexion;
import Entidades.Especialidad;
import Entidades.TiposAnalisis;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Deary
 */
public class MantenimientoEspecialidad {
    public List<Especialidad> getEspecialidades() {

        List<Especialidad> listaEsp = new ArrayList<>();

        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            String sql1 = "SELECT * FROM especialidad WHERE idEspecialidad != 0";
            cn = Conexion.getConexion();
            ps = cn.prepareStatement(sql1);
            //ps.setInt(1, id);
            rs = ps.executeQuery();
            Especialidad obj;

            if (rs != null) {
                while (rs.next()) {
                    obj = new Especialidad();

                    obj.setIdEspecialidad(rs.getInt(1));
                    obj.setNomEspecialidad(rs.getString(2));
                    obj.setCosto(rs.getDouble(3));
                    listaEsp.add(obj);
                }
            } else {
                obj = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return listaEsp;
    }
}
