/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import ConfigBD.Conexion;
import Entidades.Solicitud;
import Entidades.TiposAnalisis;
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
 * @author Deary
 */
public class laboratorioAcciones {
    public List<Solicitud> labPendientes(int id) {
        //recupera las citas pendientes del personal
        List<Solicitud> listcitas = new ArrayList();
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            cn = Conexion.getConexion();

            String sql1 = "SELECT * FROM personal INNER JOIN horario on horario.idpersonal=personal.idPersonal INNER JOIN "
                    + "solicitud on solicitud.idPersonal=personal.idPersonal INNER JOIN paciente ON paciente.idPaciente=solicitud.idPaciente INNER JOIN "
                    + "tipoanalisis ON tipoanalisis.idAnalisis=solicitud.idAnalisis WHERE solicitud.idServicio='2' AND solicitud.idPersonal=? "
                    + "AND (solicitud.estadoServicio='Pendiente' or solicitud.estadoServicio='Muestra Tomada') and horario.estadoHorario='Ocupado'";
            ps = cn.prepareStatement(sql1);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    String nom, app, apm;
                    Solicitud obj = new Solicitud();
                    obj.setIdSolicitud(rs.getInt("idSolicitud"));
                    obj.setIdPaciente(rs.getInt("idPaciente"));
                    obj.setIdAnalisis(rs.getInt("idAnalisis"));
                    obj.setHistoriaXanalisis(rs.getInt("historiaXanalisis"));
                    nom=rs.getString("nombrePaciente");
                    app=rs.getString("paciente.apellido_pa");
                    apm=rs.getString("paciente.apellido_ma");
                    obj.setNomPaciente(nom+" "+app+" "+apm);
                    obj.setFecha(rs.getString("Fecha"));
                    obj.setDocu(rs.getString("documento"));
                    obj.setHorarioInicio(rs.getString("horarioInicio"));
                    obj.setHorarioFin(rs.getString("horarioFin"));
                    obj.setModalidad(rs.getString("modalidad"));
                    obj.setPrioridad(rs.getString("prioridad"));
                    obj.setNombreAnalisis(rs.getString("nombreAnalisis"));
                    obj.setEstadoServicio(rs.getString("estadoServicio"));
                    listcitas.add(obj);
                }
            } else {
                listcitas = null;
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
        return listcitas;
    }
    
    public List<TiposAnalisis> getTiposAnalisis() {

        List<TiposAnalisis> listaAn = new ArrayList<>();

        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            String sql1 = "SELECT * FROM `tipoanalisis`";
            cn = Conexion.getConexion();
            ps = cn.prepareStatement(sql1);
            rs = ps.executeQuery();
            
            TiposAnalisis obj;

            if (rs != null) {
                while (rs.next()) {
                    obj = new TiposAnalisis();

                    obj.setIdAnalisis(rs.getInt(1));
                    obj.setNombreAnalisis(rs.getString(2));
                    obj.setPrecioAnalisis(rs.getDouble(3));
                    listaAn.add(obj);
                }
            } else {
                listaAn = null;
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
        return listaAn;
    }
    
    public boolean RegistrarResultadosLab(String resultado, String fecha, int idtipoa ,int idSol, int idHisto)  {
        
        boolean rp = false;
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        PreparedStatement ps4 = null;
        int idRes = 0;
        try {
            String updateSoli = "update solicitud SET estadoServicio='Atendido' where idSolicitud =?";
            String insertResult = "INSERT INTO `resultados_laboratorio`(`idTipoAnalisis`, `resultado`, `fecha`, `idSolicitud`) VALUES (?,?,?,?)";
            String updateHistoria = "update historiaclinica SET idResultado=? where idHistoriaClinica =?";
            String updateHorario = "update horario SET estadoHorario='Atendido' where idHorario =?";
            cn = Conexion.getConexion();
            cn.setAutoCommit(false);    //iniciamos transaccion
            ps = cn.prepareStatement(insertResult,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idtipoa);
            ps.setString(2, resultado);
            ps.setString(3, fecha);
            ps.setInt(4, idSol);
            ps.executeUpdate();
            
            //actualizar el estado del solicitud
            ps2 = cn.prepareStatement(updateSoli);
            ps2.setInt(1,idSol);
            ps2.executeUpdate();
            
            //actualizar la historia clinica 
            rs=ps.getGeneratedKeys();
            if (rs.next()){
                //obtenemos el ultimo id generado por la query
                idRes=rs.getInt(1);
            }
            ps3 = cn.prepareStatement(updateHistoria);
            ps3.setInt(1,idRes);
            ps3.setInt(2, idHisto);
            ps3.executeUpdate();
            
            //actualizar el estado del horario
            int idhorario=buscarIdHorario(idSol);
            ps4 = cn.prepareStatement(updateHorario);
            ps4.setInt(1,idhorario);
            ps4.executeUpdate();
            
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
    
    public boolean cambiarEstadoSol(String id)  {
        
        boolean rp = false;
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String updateSoli = "update solicitud SET estadoServicio='Muestra Tomada' where idSolicitud =?";
            cn = Conexion.getConexion();
            cn.setAutoCommit(false);    //iniciamos transaccion
            ps = cn.prepareStatement(updateSoli);
            ps.setString(1, id);
            ps.executeUpdate();
            
            cn.commit();    //ejecutamos todos si todo salio bien
            rp = true;

        } catch (SQLException e) {
            rp=false;
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
    
    public int buscarIdHorario(int idsol) {
        //busca los datos de una persona por su documento
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        int idhorario=0;
        try {
            cn = Conexion.getConexion();

            String sql1 = "SELECT * FROM `solicitud` WHERE idSolicitud=?";
            ps = cn.prepareStatement(sql1);
            ps.setInt(1, idsol);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    idhorario=rs.getInt("idHorario");
                }
            } else {
                idhorario = 0;
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
        return idhorario;
    }
}
