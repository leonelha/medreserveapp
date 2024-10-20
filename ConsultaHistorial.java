/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import ConfigBD.Conexion;
import Entidades.Historial;
import Entidades.Receta;
import Entidades.ResultadoLaboratorio;
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
public class ConsultaHistorial {
    
    public List<Historial> getHistorial(String id) {
        
        List<Historial> listHistoria= new ArrayList();
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        String sql1=null;
        
        try
        {
            cn = Conexion.getConexion();
            if(id!=null){
                sql1 = "SELECT * FROM historiaclinica INNER JOIN paciente on historiaclinica.idPaciente=paciente.idPaciente INNER JOIN "
                    + "personal on personal.idPersonal=historiaclinica.idPersonal WHERE paciente.documento=? or paciente.idPaciente=?";
                ps = cn.prepareStatement(sql1);
                ps.setString(1, id);
                ps.setString(2, id);
                rs = ps.executeQuery();
            }else{
                sql1 = "SELECT * FROM historiaclinica INNER JOIN paciente on historiaclinica.idPaciente=paciente.idPaciente INNER JOIN "
                        + "personal on personal.idPersonal=historiaclinica.idPersonal";
                ps = cn.prepareStatement(sql1);
                rs = ps.executeQuery();
            }
            if(rs!=null)
            {
                while(rs.next())
                {
                    Historial obj= new Historial();
                    String nom;
                    String app;
                    String apm;
                    obj.setIdHistoria(rs.getInt("idHistoriaClinica"));
                    obj.setFecha(rs.getString("fecha"));
                    obj.setTriaje(rs.getString("triaje"));
                    obj.setDiagnostico(rs.getString("diagnostico"));
                    obj.setIdResultado(rs.getInt("idResultado"));
                    nom=rs.getString("nombrePersonal");
                    app=rs.getString("personal.apellido_pa");
                    apm=rs.getString("personal.apellido_ma");
                    obj.setPersonal(nom+" "+app+" "+apm);
                    listHistoria.add(obj);
                }
            }
            else
            {
                listHistoria=null;
            }
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
        return listHistoria;
    }
    
    public List<Receta> getReceta(int id) {
        
        List<Receta> listReceta= new ArrayList();
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        String sql1=null;
        try
        {
            cn = Conexion.getConexion();
            if(id!=0){
                sql1 = "SELECT * FROM receta INNER JOIN producto on receta.idProducto=producto.idProducto INNER JOIN historiaclinica on "
                        + "historiaclinica.idHistoriaClinica=receta.idHistoria LEFT JOIN tipoanalisis on tipoanalisis.idAnalisis=receta.idAnalisis WHERE historiaclinica.idPaciente=?";
                ps = cn.prepareStatement(sql1);
                ps.setInt(1, id);
                rs = ps.executeQuery();
            }else{
                sql1 = "SELECT * FROM receta INNER JOIN producto on receta.idProducto=producto.idProducto INNER JOIN historiaclinica on "
                        + "historiaclinica.idHistoriaClinica=receta.idHistoria LEFT JOIN tipoanalisis on tipoanalisis.idAnalisis=receta.idAnalisis";
                ps = cn.prepareStatement(sql1);
                rs = ps.executeQuery();
            }

            Receta obj;
            if(rs!=null)
            {
                while(rs.next())
                {
                    obj= new Receta();
                    obj.setIdReceta(rs.getInt("idReceta"));
                    obj.setProducto(rs.getString("nombreProducto"));
                    obj.setCantidad(rs.getInt("cantidad"));
                    obj.setObservacion(rs.getString("observacion"));
                    obj.setNombreAnalisis(rs.getString("nombreAnalisis"));
                    obj.setIdAnalisis(rs.getInt("receta.idAnalisis"));
                    obj.setIdhistoria(rs.getInt("idHistoria"));
                    listReceta.add(obj);
                }
            }
            else
            {
                obj=null;
            }
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
        return listReceta;
    }
    
    public List<ResultadoLaboratorio> getResultLaboratorio(int id) {
        
        List<ResultadoLaboratorio> listResult= new ArrayList();
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try
        {
            cn = Conexion.getConexion();
            String sql1 = "SELECT * FROM resultados_laboratorio INNER JOIN tipoanalisis ON tipoanalisis.idAnalisis=resultados_laboratorio.idTipoAnalisis WHERE idResultado=?";
            ps = cn.prepareStatement(sql1);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            ResultadoLaboratorio obj;
            if(rs!=null)
            {
                while(rs.next())
                {
                    obj= new ResultadoLaboratorio();
                    obj.setIdResultado(rs.getInt("idResultado"));
                    obj.setResultadoLaboratorio(rs.getString("resultado"));
                    obj.setNombreAnalisis(rs.getString("nombreAnalisis"));
                    obj.setFecha(rs.getString("fecha"));
                    obj.setIdSolicitud(rs.getInt("idSolicitud"));
                    listResult.add(obj);
                }
            }
            else
            {
                obj=null;
            }
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
        return listResult;
    }
    
    public String getTriajeEmergencia(int id) {
        
        String triaje=null;
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try
        {
            cn = Conexion.getConexion();
            String sql1 = "SELECT * FROM historiaclinica WHERE idHistoriaClinica=(SELECT MAX(idHistoriaClinica) FROM historiaclinica WHERE idPaciente=?) AND idSolicitud IS null AND idPersonal IS null";
            ps = cn.prepareStatement(sql1);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs!=null)
            {
                while(rs.next())
                {
                    triaje=rs.getString("triaje");
                }
            }
            else
            {
                triaje=null;
            }
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
        return triaje;
    }
}
