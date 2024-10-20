/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import ConfigBD.Conexion;
import Entidades.Horario;
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
public class Horarios {
    
    public List<Horario> getHorario(int id) {

        List<Horario> listaHorarios = new ArrayList<Horario>();

        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            String sql1 = "SELECT * FROM `horario` INNER JOIN personal ON horario.idpersonal=personal.idPersonal WHERE personal.idEspecialidad=? AND horario.estadoHorario='Libre'";
            cn = Conexion.getConexion();
            ps = cn.prepareStatement(sql1);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Horario obj;

            if (rs != null) {
                while (rs.next()) {
                    obj = new Horario();
                    String nom, app ,apm;
                    nom=rs.getString("nombrePersonal");
                    app=rs.getString("apellido_pa");
                    apm=rs.getString("apellido_ma");
                    obj.setIdMedico(rs.getInt("idPersonal"));
                    obj.setIdHorario(rs.getInt("idHorario"));
                    obj.setFecha(rs.getString("fechaHorario"));
                    obj.setHoraInicio(rs.getString("horarioInicio"));
                    obj.setHoraFin(rs.getString("horarioFin"));
                    obj.setMedico(nom+" "+app+" "+apm);
                    listaHorarios.add(obj);
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
        return listaHorarios;
    }
    public List<Horario> getHorarioLaboratorio() {

        List<Horario> listaHorarios = new ArrayList<Horario>();

        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            String sql1 = "SELECT * FROM `horario` INNER JOIN personal ON horario.idpersonal=personal.idPersonal WHERE "
                    + "horario.estadoHorario='Libre' AND personal.idRol=4";
            cn = Conexion.getConexion();
            ps = cn.prepareStatement(sql1);
            //ps.setInt(1, id);
            rs = ps.executeQuery();
            Horario obj;

            if (rs != null) {
                while (rs.next()) {
                    obj = new Horario();
                    String nom, app ,apm;
                    nom=rs.getString("nombrePersonal");
                    app=rs.getString("apellido_pa");
                    apm=rs.getString("apellido_ma");
                    obj.setIdMedico(rs.getInt("idPersonal"));
                    obj.setIdHorario(rs.getInt("idHorario"));
                    obj.setFecha(rs.getString("fechaHorario"));
                    obj.setHoraInicio(rs.getString("horarioInicio"));
                    obj.setHoraFin(rs.getString("horarioFin"));
                    obj.setMedico(nom+" "+app+" "+apm);
                    listaHorarios.add(obj);
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
        return listaHorarios;
    }
    public double costoEspecialidad(int id) {
        
        double costo=0.0;
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            String sql1 = "SELECT * FROM `especialidad` WHERE idEspecialidad=?";
            cn = Conexion.getConexion();
            ps = cn.prepareStatement(sql1);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    costo=Double.parseDouble(rs.getString("costo"));
                }
            } else {
                costo=0.0;
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
        return costo;
    }
    public double costoLaboratorio(int id) {
        
        double costo=0.0;
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            String sql1 = "SELECT * FROM `tipoanalisis` WHERE idAnalisis=?";
            cn = Conexion.getConexion();
            ps = cn.prepareStatement(sql1);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    costo=Double.parseDouble(rs.getString("precio"));
                }
            } else {
                costo=0.0;
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
        return costo;
    }
}
