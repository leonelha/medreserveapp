/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import ConfigBD.Conexion;
import Entidades.Especialidad;
import Entidades.Horario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Entidades.Paciente;
import Entidades.Personal;
import Entidades.Rol;

/**
 *
 * @author Deary
 */
public class ValidarSesion {
    
    public Rol validar(String correo, String pass) {
        
        Rol r= new Rol();
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        int iduser=0, rol=0, idPersonal=0, idPaciente=0;
        try
        {
            cn = Conexion.getConexion();
            String sql = "SELECT * FROM usuarios WHERE correo=? AND password=?";
            //String sql = "SELECT * FROM usuarios INNER JOIN paciente on paciente.idPaciente=usuarios.fk_paciente WHERE correo=? AND password=?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            if(rs!=null)
            {
                if(rs.next())
                {
                    iduser= rs.getInt("id_usuario");
                    rol= rs.getInt("fk_rol");
                    idPersonal= rs.getInt("fk_personal");
                    idPaciente= rs.getInt("fk_paciente");
                }
                if(idPaciente!=0)
                {
                    r.setIdUser(idPaciente);
                    r.setIdrol(rol);
                    return r;
                }
                else if(idPersonal!=0)
                {
                   r.setIdUser(idPersonal);
                   r.setIdrol(rol);
                   return r;
                }
            }
            else
            {
                System.out.println("Usuario Invalido");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(rs != null) rs.close();
                if(ps != null) ps.close();
                if(cn != null) cn.close();
            }
            catch(Exception e2)
            {
                e2.printStackTrace();
            }
        }
        return null;
    }
    public Paciente sesionPaciente(int idpa) {
        Paciente obj = new Paciente();
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try
        {
            String sql = "SELECT * FROM usuarios INNER JOIN paciente on paciente.idPaciente=usuarios.fk_paciente WHERE fk_paciente=?";
            cn = Conexion.getConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, idpa);
            rs = ps.executeQuery();
            if(rs!=null)
            {
                if(rs.next())
                {
                    obj.setIdPaciente(rs.getInt("idPaciente"));
                    obj.setNombre(rs.getString("nombrePaciente"));
                    obj.setCorreo(rs.getString("correo"));
                    obj.setRol(rs.getInt("fk_rol"));
                }
            }
            else
            {
                obj=null;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(rs != null) rs.close();
                if(ps != null) ps.close();
                if(cn != null) cn.close();
            }
            catch(Exception e2)
            {
                e2.printStackTrace();
            }
        }
        return obj;
    }
    public Personal sesionPersonal(int idpe) {
        //falta
        Personal obj = new Personal();
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try
        {
            String sql = "SELECT * FROM usuarios INNER JOIN personal on personal.idPersonal=usuarios.fk_personal WHERE fk_personal=?";
            cn = Conexion.getConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, idpe);
            rs = ps.executeQuery();
            if(rs!=null)
            {
                if(rs.next())
                {
                    obj.setIdPersonal(rs.getInt("idPersonal"));
                    obj.setNombre(rs.getString("nombrePersonal"));
                    obj.setCorreo(rs.getString("correo"));
                    obj.setRol(rs.getInt("fk_rol"));
                }
            }
            else
            {
                obj=null;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(rs != null) rs.close();
                if(ps != null) ps.close();
                if(cn != null) cn.close();
            }
            catch(Exception e2)
            {
                e2.printStackTrace();
            }
        }
        return obj;
    }
    public boolean registro(Paciente obj) {
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        try
        {
            cn = Conexion.getConexion();
            int id=0;
            
            String sql1 = "insert into paciente (idTipoDocumento,documento,nombrePaciente,apellido_pa,apellido_ma,idSexo,direccionPaciente,telefonoPaciente) values (?,?,?,?,?,?,?,?)";
            ps = cn.prepareStatement(sql1);
            ps.setInt(1, obj.getTipodoc());
            ps.setString(2, obj.getDocumento());
            ps.setString(3, obj.getNombre());
            ps.setString(4, obj.getApellido_paterno());
            ps.setString(5, obj.getApellido_materno());
            ps.setInt(6, obj.getSexo());
            ps.setString(7, obj.getDireccion());
            ps.setInt(8, obj.getTelefono());
            
            ps.execute();
            
            String sql = "select * from paciente ORDER BY idPaciente DESC LIMIT 1";
            ps3 = cn.prepareStatement(sql);
            rs = ps3.executeQuery();
            if(rs.next())
            {
                id=rs.getInt("idPaciente");
            }
            String sql2 = "insert into usuarios (correo,password,fk_rol,fk_paciente) values (?,?,?,?)";
            ps2 = cn.prepareStatement(sql2);
            ps2.setString(1, obj.getCorreo());
            ps2.setString(2, obj.getPassword());
            ps2.setInt(3, obj.getRol());
            ps2.setInt(4, id);
            ps2.execute();
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(rs != null) rs.close();
                if(ps != null) ps.close();
                if(cn != null) cn.close();
            }
            catch(SQLException e2)
            {
                e2.printStackTrace();
            }
        }
        return true;
    }

    public boolean registro(Personal objPersonal) {
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        
        try
        {
            cn = Conexion.getConexion();
            int id=0;
            
            String sql1 = "insert into personal (idTipoDocumento,nroDocumento,nombrePersonal,apellido_pa,apellido_ma,idSexo,direccionPersonal,telefonoPersonal,idRol, idEspecialidad) values (?,?,?,?,?,?,?,?,?,?)";
            ps = cn.prepareStatement(sql1);
            ps.setInt(1, objPersonal.getTipodoc());
            ps.setString(2, objPersonal.getDocumento());
            ps.setString(3, objPersonal.getNombre());
            ps.setString(4, objPersonal.getApellido_paterno());
            ps.setString(5, objPersonal.getApellido_materno());
            ps.setInt(6, objPersonal.getSexo());
            ps.setString(7, objPersonal.getDireccion());
            ps.setInt(8, objPersonal.getTelefono());
            ps.setInt(9, objPersonal.getRol());
            ps.setInt(10, objPersonal.getIdEspecialidad());
            
            
            ps.execute();
            
            String sql = "select * from personal ORDER BY idPersonal DESC LIMIT 1";
            ps3 = cn.prepareStatement(sql);
            rs = ps3.executeQuery();
            if(rs.next())
            {
                id=rs.getInt("idPersonal");
            }
            String sql2 = "insert into usuarios (correo,password,fk_rol,fk_personal) values (?,?,?,?)";
            ps2 = cn.prepareStatement(sql2);
            ps2.setString(1, objPersonal.getCorreo());
            ps2.setString(2, objPersonal.getPassword());
            ps2.setInt(3, objPersonal.getRol());
            ps2.setInt(4, id);
            ps2.execute();
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(rs != null) rs.close();
                if(ps != null) ps.close();
                if(cn != null) cn.close();
            }
            catch(SQLException e2)
            {
                e2.printStackTrace();
            }
        }
        return true;
    }
    public boolean registroEspecialidad(Especialidad objEspecialidad) {
       Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        
        try
        {
            cn = Conexion.getConexion();
            int id=0;
            
            String sql1 = "insert into especialidad (tipoEspecialidad,costo) values (?,?)";
            ps = cn.prepareStatement(sql1);
            ps.setString(1, objEspecialidad.getNomEspecialidad());
            ps.setDouble(2, objEspecialidad.getCosto());
            
            
            
            ps.execute();
            
            
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(rs != null) rs.close();
                if(ps != null) ps.close();
                if(cn != null) cn.close();
            }
            catch(SQLException e2)
            {
                e2.printStackTrace();
            }
        }
        return true;
    }
    public boolean actualizarEspecialidad(Especialidad objEspecialidad) {
       Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        
        try
        {
            cn = Conexion.getConexion();
            
            
            String sql1 = "update especialidad set tipoEspecialidad=?, costo=? where idEspecialidad=?";
            ps = cn.prepareStatement(sql1);
           
            ps.setString(1, objEspecialidad.getNomEspecialidad());
            ps.setDouble(2, objEspecialidad.getCosto());
            ps.setInt(3,objEspecialidad.getIdEspecialidad());
            
            
            ps.execute();
            
            
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(rs != null) rs.close();
                if(ps != null) ps.close();
                if(cn != null) cn.close();
            }
            catch(SQLException e2)
            {
                e2.printStackTrace();
            }
        }
        return true;
    }
    public boolean registroHorario(Horario objHorario) {
       Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        
        try
        {
            cn = Conexion.getConexion();
            int id=0;
            
            String sql1 = "insert into horario (horarioInicio,horarioFin,fechaHorario,estadoHorario,idpersonal) values (?,?,?,?,?)";
            ps = cn.prepareStatement(sql1);
            ps.setString(1, objHorario.getHoraInicio());
            ps.setString(2, objHorario.getHoraFin());
            ps.setString(3, objHorario.getFecha());
            ps.setString(4, objHorario.getEstadoHorario());
            ps.setInt(5, objHorario.getIdMedico());
            
            
            
            ps.execute();
            
            
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(rs != null) rs.close();
                if(ps != null) ps.close();
                if(cn != null) cn.close();
            }
            catch(SQLException e2)
            {
                e2.printStackTrace();
            }
        }
        return true;
    }
    
    public boolean actualizarHorario(Horario objHorario) {
       Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        
        try
        {
            cn = Conexion.getConexion();
            int id=0;
            
           String sql1 = "update horario set horarioInicio=?, horarioFin=?, fechaHorario=?, idpersonal=? where idHorario=?";
            ps = cn.prepareStatement(sql1);
            ps.setString(1, objHorario.getHoraInicio());
            ps.setString(2, objHorario.getHoraFin());
            ps.setString(3, objHorario.getFecha());
            ps.setInt(4,objHorario.getIdMedico());
            ps.setInt(5, objHorario.getIdHorario());
        
            
            
            
            ps.execute();
            
            
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(rs != null) rs.close();
                if(ps != null) ps.close();
                if(cn != null) cn.close();
            }
            catch(SQLException e2)
            {
                e2.printStackTrace();
            }
        }
        return true;
    }
    
    public boolean actualizarPaciente(Paciente objPaciente) {
       Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        
        try
        {
            cn = Conexion.getConexion();
          
            
           String sql1 = "update paciente set nombrePaciente=?, apellido_pa=?, apellido_ma=?, direccionPaciente=?, telefonoPaciente=? where idPaciente=?";
            ps = cn.prepareStatement(sql1);
            ps.setString(1, objPaciente.getNombre());
            ps.setString(2, objPaciente.getApellido_paterno());
            ps.setString(3, objPaciente.getApellido_materno());
            ps.setString(4, objPaciente.getDireccion());
            ps.setInt(5,objPaciente.getTelefono());
            
            ps.setInt(6, objPaciente.getIdPaciente());
  
            ps.execute();
            
            String sql2 = "update usuarios set correo=?, password=? where fk_paciente=?";
            ps2 = cn.prepareStatement(sql2);
            ps2.setString(1, objPaciente.getCorreo());
            ps2.setString(2, objPaciente.getPassword());
            ps2.setInt(3, objPaciente.getIdPaciente());
            
            
           
  
            ps2.execute();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(rs != null) rs.close();
                if(ps != null) ps.close();
                if(cn != null) cn.close();
            }
            catch(SQLException e2)
            {
                e2.printStackTrace();
            }
        }
        return true;
    }
    
     public boolean actualizarPersonal(Personal objPersonal) {
       Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        
        try
        {
            cn = Conexion.getConexion();
          
            
           String sql1 = "update personal set nombrePersonal=?, apellido_pa=?, apellido_ma=?, direccionPersonal=?, telefonoPersonal=?, idRol=?, idEspecialidad=? where idPersonal=?";
            ps = cn.prepareStatement(sql1);
            ps.setString(1, objPersonal.getNombre());
            ps.setString(2, objPersonal.getApellido_paterno());
            ps.setString(3, objPersonal.getApellido_materno());
            ps.setString(4, objPersonal.getDireccion());
            ps.setInt(5,objPersonal.getTelefono());
            ps.setInt(6,objPersonal.getRol());
            ps.setInt(7,objPersonal.getIdEspecialidad());
            ps.setInt(8, objPersonal.getIdPersonal());
  
            ps.execute();
            
            String sql2 = "update usuarios set correo=?, password=? where fk_personal=?";
            ps2 = cn.prepareStatement(sql2);
            ps2.setString(1, objPersonal.getCorreo());
            ps2.setString(2, objPersonal.getPassword());
            ps2.setInt(3, objPersonal.getIdPersonal());
            
            
           
  
            ps2.execute();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(rs != null) rs.close();
                if(ps != null) ps.close();
                if(cn != null) cn.close();
            }
            catch(SQLException e2)
            {
                e2.printStackTrace();
            }
        }
        return true;
    }
}
