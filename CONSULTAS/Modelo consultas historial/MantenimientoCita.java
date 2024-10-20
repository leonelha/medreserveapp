/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import ConfigBD.Conexion;
import Entidades.Solicitud;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class MantenimientoCita {

    public boolean RegistrarSolicitud(Solicitud obj) {

        boolean rp = false;
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;

        try {
            String sql1 = "INSERT INTO solicitud (idPaciente, idPersonal, idServicio, idHorario, costo, modalidad, Fecha, estadoServicio) VALUES (?,?,?,?,?,?,?,?)";
            String sql2 = "update horario SET estadoHorario='Ocupado' where idHorario =?";
            cn = Conexion.getConexion();
            
            cn.setAutoCommit(false);    //iniciamos transaccion
            ps = cn.prepareStatement(sql1);
            ps.setInt(1, obj.getIdPaciente());
            ps.setInt(2, obj.getIdPersonal());
            ps.setInt(3, obj.getIdServicio());
            ps.setInt(4, obj.getIdHorario());
            ps.setDouble(5, obj.getCosto());
            ps.setString(6, obj.getModalidad());
            ps.setString(7, obj.getFecha());
            ps.setString(8, obj.getEstadoServicio());
            ps.execute();
            //para la segunda consulta
            ps2 = cn.prepareStatement(sql2);
            ps2.setInt(1, obj.getIdHorario());
            ps2.execute();
            cn.commit();    //ejecutamos todos si todo salio bien
            rp = true;
        } catch (SQLException e) {
            try {
                cn.rollback();  //si algo sale mal hacemos rollback
            } catch (SQLException ex) {
                Logger.getLogger(MantenimientoCita.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        return rp;
    }
    public boolean ActualizarSolicitud(Solicitud obj, String idsol) {

        boolean rp = false;
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;

        try {
            String sql1 = "UPDATE solicitud SET idPersonal=?, idHorario=?, costo=?, modalidad=?, Fecha=?, estadoServicio=? WHERE idSolicitud=?";
            String sql2 = "update horario SET estadoHorario='Ocupado' where idHorario =?";
            cn = Conexion.getConexion();
            
            cn.setAutoCommit(false);    //iniciamos transaccion
            ps = cn.prepareStatement(sql1);
            ps.setInt(1, obj.getIdPersonal());
            ps.setInt(2, obj.getIdHorario());
            ps.setDouble(3, obj.getCosto());
            ps.setString(4, obj.getModalidad());
            ps.setString(5, obj.getFecha());
            ps.setString(6, obj.getEstadoServicio());
            ps.setString(7, idsol);
            ps.execute();
            //para la segunda consulta
            ps2 = cn.prepareStatement(sql2);
            ps2.setInt(1, obj.getIdHorario());
            ps2.execute();
            cn.commit();    //ejecutamos todos si todo salio bien
            rp = true;
        } catch (SQLException e) {
            try {
                cn.rollback();  //si algo sale mal hacemos rollback
            } catch (SQLException ex) {
                Logger.getLogger(MantenimientoCita.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        return rp;
    }
    public boolean RegistrarCitaLaboratorio(Solicitud obj) {

        boolean rp = false;
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        String prioridad= "Normal";
        try {
            String sql1 = "INSERT INTO solicitud (idPaciente, idPersonal, idServicio, idAnalisis, historiaXanalisis, idHorario, prioridad ,"
                    + "costo, modalidad, Fecha, estadoServicio) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            String sql2 = "update horario SET estadoHorario='Ocupado' where idHorario =?";
            cn = Conexion.getConexion();
            
            cn.setAutoCommit(false);    //iniciamos transaccion
            ps = cn.prepareStatement(sql1);
            ps.setInt(1, obj.getIdPaciente());
            ps.setInt(2, obj.getIdPersonal());
            ps.setInt(3, obj.getIdServicio());
            ps.setInt(4, obj.getIdAnalisis());
            ps.setInt(5, obj.getHistoriaXanalisis());
            ps.setInt(6, obj.getIdHorario());
            ps.setString(7, prioridad);
            ps.setDouble(8, obj.getCosto());
            ps.setString(9, obj.getModalidad());
            ps.setString(10, obj.getFecha());
            ps.setString(11, obj.getEstadoServicio());
            ps.execute();
            //para la segunda consulta
            ps2 = cn.prepareStatement(sql2);
            ps2.setInt(1, obj.getIdHorario());
            ps2.execute();
            cn.commit();    //ejecutamos todos si todo salio bien
            rp = true;
        } catch (SQLException e) {
            try {
                cn.rollback();  //si algo sale mal hacemos rollback
            } catch (SQLException ex) {
                Logger.getLogger(MantenimientoCita.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        return rp;
    }
    
    public Solicitud buscarCitas(int id) {

        Solicitud obj = new Solicitud();

        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            String sql1 = "SELECT * FROM `solicitud` INNER JOIN personal ON solicitud.idPersonal=personal.idPersonal INNER JOIN "
                    + "especialidad ON especialidad.idEspecialidad=personal.idEspecialidad INNER JOIN horario ON horario.idHorario=solicitud.idHorario "
                    + "WHERE solicitud.idSolicitud = ? AND solicitud.idServicio = '1'";
            cn = Conexion.getConexion();
            ps = cn.prepareStatement(sql1);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs != null) {
                if (rs.next()) {
                    obj = new Solicitud();
                    String nom;
                    String app;
                    String apm;
                    obj.setIdSolicitud(rs.getInt("idSolicitud"));
                    obj.setIdPersonal(rs.getInt("idPersonal"));
                    obj.setIdHorario(rs.getInt("idHorario"));
                    obj.setFecha(rs.getString("Fecha"));
                    obj.setIdEspecialidad(rs.getInt("idEspecialidad"));
                    obj.setEspecialidad(rs.getString("tipoEspecialidad"));
                    obj.setCosto(rs.getDouble("costo"));
                    obj.setModalidad(rs.getString("modalidad"));
                    obj.setEstadoServicio(rs.getString("estadoServicio"));
                    nom = rs.getString("nombrePersonal");
                    app = rs.getString("apellido_pa");
                    apm = rs.getString("apellido_ma");
                    obj.setPersonal(nom + " " + app + " " + apm);
                    obj.setHorarioInicio(rs.getString("horarioInicio"));
                    obj.setHorarioFin(rs.getString("horarioFin"));
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
        return obj;
    }
    public List<Solicitud> getCitas(int id) {

        List<Solicitud> listaCitas = new ArrayList<Solicitud>();

        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            String sql1 = "SELECT * FROM `solicitud` INNER JOIN personal ON solicitud.idPersonal=personal.idPersonal INNER JOIN "
                    + "especialidad ON especialidad.idEspecialidad=personal.idEspecialidad INNER JOIN horario ON horario.idHorario=solicitud.idHorario "
                    + "WHERE solicitud.idPaciente =? AND solicitud.idServicio = '1' ORDER BY solicitud.idSolicitud DESC";
            
            cn = Conexion.getConexion();
            ps = cn.prepareStatement(sql1);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Solicitud obj;

            if (rs != null) {
                while (rs.next()) {
                    obj = new Solicitud();
                    String nom;
                    String app;
                    String apm;
                    obj.setIdSolicitud(rs.getInt("idSolicitud"));
                    obj.setIdEspecialidad(rs.getInt("idEspecialidad"));
                    obj.setEspecialidad(rs.getString("tipoEspecialidad"));
                    obj.setCosto(rs.getDouble("costo"));
                    obj.setModalidad(rs.getString("modalidad"));
                    obj.setEstadoServicio(rs.getString("estadoServicio"));
                    nom = rs.getString("nombrePersonal");
                    app = rs.getString("apellido_pa");
                    apm = rs.getString("apellido_ma");
                    obj.setPersonal(nom + " " + app + " " + apm);
                    obj.setHorarioInicio(rs.getString("horarioInicio"));
                    obj.setHorarioFin(rs.getString("horarioFin"));
                    obj.setFecha(rs.getString("fechaHorario"));
                    listaCitas.add(obj);

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
        return listaCitas;
    }

    public List<Solicitud> getCitasLaboratorio(int id) {

        List<Solicitud> listaCitas = new ArrayList<Solicitud>();

        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            String sql1 = "SELECT * FROM `solicitud` INNER JOIN personal ON solicitud.idPersonal=personal.idPersonal INNER JOIN tipoanalisis ON "
                    + "tipoanalisis.idAnalisis=solicitud.idAnalisis INNER JOIN horario ON horario.idHorario=solicitud.idHorario WHERE "
                    + "solicitud.idPaciente =? AND solicitud.idServicio = '2' ORDER BY solicitud.idSolicitud DESC";
            
            cn = Conexion.getConexion();
            ps = cn.prepareStatement(sql1);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Solicitud obj;

            if (rs != null) {
                while (rs.next()) {
                    obj = new Solicitud();
                    String nom;
                    String app;
                    String apm;
                    obj.setIdSolicitud(rs.getInt("idSolicitud"));
                    obj.setIdEspecialidad(rs.getInt("idEspecialidad"));
                    obj.setEspecialidad(rs.getString("nombreAnalisis"));
                    obj.setCosto(rs.getDouble("precio"));
                    obj.setModalidad(rs.getString("modalidad"));
                    obj.setEstadoServicio(rs.getString("estadoServicio"));
                    nom = rs.getString("nombrePersonal");
                    app = rs.getString("apellido_pa");
                    apm = rs.getString("apellido_ma");
                    obj.setPersonal(nom + " " + app + " " + apm);
                    obj.setHorarioInicio(rs.getString("horarioInicio"));
                    obj.setHorarioFin(rs.getString("horarioFin"));
                    obj.setFecha(rs.getString("fechaHorario"));
                    listaCitas.add(obj);

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
        return listaCitas;
    }
    
    public boolean cancelarCita(int id) {

        boolean rp = false;
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        System.out.println("iddd:::::: " + id);
        try {
            String sql = "update solicitud SET estadoServicio='Cancelado' where idSolicitud =?";

            cn = Conexion.getConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            rp = true;
        } catch (Exception e) {
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
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return rp;
    }

    public boolean modificarCita(Solicitud obj) {

        boolean rp = false;
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;

        try {
            String sql1 = "UPDATE `solicitud` SET `idPaciente`=?,`idPersonal`=?,`idServicio`=?,`costo`=?,`modalidad`=?,`Fecha`=?,`estadoServicio`=? WHERE idSolicitud=?";

            cn = Conexion.getConexion();
            ps = cn.prepareStatement(sql1);
            ps.setInt(1, obj.getIdPaciente());
            ps.setInt(2, obj.getIdPersonal());
            ps.setInt(3, obj.getIdServicio());
            ps.setDouble(4, obj.getCosto());
            ps.setString(5, obj.getModalidad());
            ps.setString(6, obj.getFecha());
            ps.setString(7, obj.getEstadoServicio());
            ps.setInt(8, obj.getIdSolicitud());
            ps.execute();

            rp = true;
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
        return rp;
    }
}
