/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import ConfigBD.Conexion;
import Entidades.Paciente;
import Entidades.Historial;
import Entidades.Receta;
import Entidades.Solicitud;
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
public class medicoAcciones {

    public Paciente buscarPersona(String doc) {
        //busca los datos de una persona por su documento
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Paciente obj = null;
        try {
            cn = Conexion.getConexion();

            String sql1 = "SELECT * FROM `paciente` WHERE documento=?  or idPaciente=?";
            ps = cn.prepareStatement(sql1);
            ps.setString(1, doc);
            ps.setString(2, doc);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    obj = new Paciente();
                    obj.setIdPaciente(rs.getInt("idPaciente"));
                    obj.setDocumento(rs.getString("documento"));
                    obj.setNombre(rs.getString("nombrePaciente"));
                    obj.setApellido_paterno(rs.getString("apellido_pa"));
                    obj.setApellido_materno(rs.getString("apellido_ma"));
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

    public List<Solicitud> citasPendientes(int id) {
        //recupera las citas pendientes del personal
        List<Solicitud> listcitas = new ArrayList();
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        String a="";
        if( a.equals("s")){
        
    }
        try {
            cn = Conexion.getConexion();

            String sql1 = "SELECT * FROM personal INNER JOIN horario on horario.idpersonal=personal.idPersonal INNER JOIN"
                    + " solicitud on solicitud.idPersonal=personal.idPersonal INNER JOIN paciente ON paciente.idPaciente=solicitud.idPaciente "
                    + "WHERE solicitud.idServicio='1' AND solicitud.idPersonal=? AND solicitud.estadoServicio='Pendiente' AND horario.estadoHorario='Ocupado' AND horario.idHorario= solicitud.idHorario";
            ps = cn.prepareStatement(sql1);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    String nom, app, apm;
                    Solicitud obj = new Solicitud();
                    obj.setIdSolicitud(rs.getInt("idSolicitud"));
                    obj.setIdPaciente(rs.getInt("idPaciente"));
                    nom=rs.getString("nombrePaciente");
                    app=rs.getString("paciente.apellido_pa");
                    apm=rs.getString("paciente.apellido_ma");
                    obj.setNomPaciente(nom+" "+app+" "+apm);
                    obj.setFecha(rs.getString("Fecha"));
                    obj.setDocu(rs.getString("documento"));
                    obj.setHorarioInicio(rs.getString("horarioInicio"));
                    obj.setHorarioFin(rs.getString("horarioFin"));
                    obj.setModalidad(rs.getString("modalidad"));
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

    public int buscarUltimoHistorial(int doc) {
        //busca los datos de una persona por su documento
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        int id = 0;

        try {
            cn = Conexion.getConexion();
           
            String sql1 = "SELECT * FROM `historiaclinica` WHERE idPaciente = ? ORDER BY `idHistoriaClinica` DESC LIMIT 1";
            ps = cn.prepareStatement(sql1);
            ps.setInt(1, doc);
            rs = ps.executeQuery();
            if(rs!=null)
            {
                if(rs.next())
                {
                    id = rs.getInt("idHistoriaClinica");                
                }
            } else {
                id = 0;
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
        return id;
    }

    public boolean RegistrarHistorial(Historial obj)  {
        
        boolean rp = false;
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        String IdSolicitud=null;
        if(obj.getIdSolicitud() != 0 )
        {
            IdSolicitud=String.valueOf(obj.getIdSolicitud());
        }
        try {
            String sql = "update solicitud SET estadoServicio='Atendido' where idSolicitud =?";
            String sql1 = "INSERT INTO `historiaclinica`(`idSolicitud`, `triaje`, `diagnostico`, `idPaciente`, `idPersonal`,  `fecha`) VALUES (?,?,?,?,?,?)";
            cn = Conexion.getConexion();
            cn.setAutoCommit(false);    //iniciamos transaccion
            ps = cn.prepareStatement(sql1);
            ps.setString(1, IdSolicitud );
            ps.setString(2, obj.getTriaje());
            ps.setString(3, obj.getDiagnostico());
            ps.setInt(4, obj.getIdPaciente());
            ps.setInt(5, obj.getIdPersonal());
            ps.setString(6, obj.getFecha());
            ps.execute();
            
            //actualizar el estado del solicitud
            if (obj.getIdSolicitud() != 0 )
            {
                ps2 = cn.prepareStatement(sql);
                ps2.setInt(1, obj.getIdSolicitud());
                ps2.execute();
            }
            
            cn.commit();    //ejecutamos todos si todo salio bien
            rp = true;

        } catch (SQLException e) {
            try {
                cn.rollback();  //si algo sale mal hacemos rollback
            } catch (SQLException ex) {
                Logger.getLogger(medicoAcciones.class.getName()).log(Level.SEVERE, null, ex);
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
       
    public boolean actualizarHistorialEmergencia (Historial obj)  {
        
        boolean rp = false;
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
            
        try {
            String sql1 = "update `historiaclinica` SET diagnostico=?, idPersonal=?, fecha=? WHERE idHistoriaClinica = (SELECT idHistoriaClinica FROM `historiaclinica` WHERE idPaciente = ? ORDER BY `idHistoriaClinica` DESC LIMIT 1)";
            cn = Conexion.getConexion();
            cn.setAutoCommit(false);    //iniciamos transaccion
            ps = cn.prepareStatement(sql1);           
            ps.setString(1, obj.getDiagnostico());
            ps.setInt(2, obj.getIdPersonal());
            ps.setString(3, obj.getFecha());
            ps.setInt(4, obj.getIdPaciente());
            ps.execute();
                
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
    
    public boolean RegistrarTriaje (String triaje, int idPaciente)  {
        
        boolean rp = false;
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        try {
            String sql1 = "INSERT INTO `historiaclinica`( `triaje`, `idPaciente`) VALUES (?,?)";
            cn = Conexion.getConexion();
            ps = cn.prepareStatement(sql1);
            
            ps.setString(1, triaje);
            ps.setInt(2, idPaciente);
            
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
    
    public boolean RegistroReceta(String id[],String cantidad[],String observacion[],String idAn,int idHist) {

        boolean rp = false;
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            cn = Conexion.getConexion();
            String sql1 = "INSERT INTO `receta`(`idProducto`, `cantidad`, `observacion`, `idAnalisis`, `idHistoria`) VALUES (?,?,?,?,?)";
            for (int i = 0; i < id.length; i++) {
                ps = cn.prepareStatement(sql1);
                ps.setInt(1, Integer.parseInt(id[i]));
                ps.setInt(2, Integer.parseInt(cantidad[i]));
                ps.setString(3, observacion[i]);
                if(idAn==null){
                    ps.setString(4, null);
                }else{
                    ps.setString(4, idAn);
                    idAn=null;
                }
                ps.setInt(5, idHist);
                ps.execute();
            }
            rp = true;
        } catch (SQLException e) {
            e.printStackTrace();
            rp = false;
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
