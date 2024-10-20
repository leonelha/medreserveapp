/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import ConfigBD.Conexion;
import Entidades.DetalleVenta;
import Entidades.Farmaco;
import Entidades.Receta;
import Entidades.Venta;
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
public class ventaFarmacia {

    public boolean generarVenta(Venta venta, String idReceta[], String idProducto[], String cantidad[], String precioVenta[]) {

        boolean rp = false;
        Connection cn = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet rs=null;
        int idVenta = 0;
        try {
            cn = Conexion.getConexion();
            cn.setAutoCommit(false);    //iniciamos transaccion
            //primero se agrega la venta
            String sql1 = "INSERT INTO `venta`(`idPaciente`, `idComprobante`, `monto`, `totalVenta`, `tipodePago`, `tipoEntrega`, `estadoVenta`) VALUES (?,?,?,?,?,?,?)";
            ps1 = cn.prepareStatement(sql1,Statement.RETURN_GENERATED_KEYS);
            ps1.setInt(1, venta.getIdPaciente());
            ps1.setInt(2, venta.getIdComprobante());
            ps1.setDouble(3, venta.getMonto());
            ps1.setDouble(4, venta.getTotalVenta());
            ps1.setString(5, venta.getTipoPago());
            ps1.setString(6, venta.getTipoEntrega());
            ps1.setString(7, venta.getEstadoVenta());
            ps1.executeUpdate();
            rs=ps1.getGeneratedKeys();
            if (rs.next()){
                //obtenemos el ultimo idgenerado por la venta
                idVenta=rs.getInt(1);
            }
            else{
                System.out.println("no hay iddd");
            }
            //luego  se agrega al detalle venta con el id de la venta generado por el anterior query
            String sql = "INSERT INTO `detalleventa`(`idVenta`, `idProducto`, `cantidad`, `precioVenta`, `idReceta`) VALUES (?,?,?,?,?)";
            String sql2 = "UPDATE `receta` SET `estadoReceta` = 'Adquirido' WHERE idReceta = ?";
            for (int i = 0; i < idProducto.length; i++) {
                ps = cn.prepareStatement(sql);
                ps2 = cn.prepareStatement(sql2);
                ps2.setInt(1, Integer.parseInt(idReceta[i]));
                ps.setInt(1, idVenta);
                ps.setDouble(2, Double.parseDouble(idProducto[i]));
                ps.setDouble(3, Double.parseDouble(cantidad[i]));
                ps.setDouble(4, Double.parseDouble(precioVenta[i]));
                ps.setInt(5, Integer.parseInt(idReceta[i]));
                ps.execute();
                ps2.execute();
            }
            
            cn.commit();    //ejecutamos todos si todo salio bien
            rp = true;
        } catch (SQLException e) {
            try {
                cn.rollback();  //si algo sale mal hacemos rollback
            } catch (SQLException ex) {
                Logger.getLogger(MantenimientoCita.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    public List<Venta> getVenta(int id) {

        List<Venta> venta = new ArrayList();

        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            cn = Conexion.getConexion();
            String sql = "SELECT * FROM venta WHERE idPaciente = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Venta obj;
            if (rs != null) {
                while (rs.next()) {
                    obj = new Venta();
                    obj.setIdVenta(rs.getInt("idVenta"));
                    obj.setIdPaciente(rs.getInt("idPaciente"));
                    obj.setIdComprobante(rs.getInt("idComprobante"));
                    obj.setMonto(rs.getDouble("monto"));
                    obj.setTotalVenta(rs.getInt("totalVenta"));
                    obj.setTipoPago(rs.getString("tipodePago"));
                    obj.setTipoEntrega(rs.getString("tipoEntrega"));
                    obj.setEstadoVenta(rs.getString("estadoVenta"));
                    venta.add(obj);
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
        return venta;
    }

    public List<DetalleVenta> getDetalleVenta(int id) {

        List<DetalleVenta> detalle = new ArrayList();

        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            cn = Conexion.getConexion();
            String sql = "SELECT * FROM detalleventa WHERE idVenta = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            DetalleVenta obj;
            if (rs != null) {
                while (rs.next()) {
                    obj = new DetalleVenta();
                    obj.setIdDetalleVenta(rs.getInt("idDetalleVenta"));
                    obj.setIdVenta(rs.getInt("idVenta"));
                    obj.setIdProducto(rs.getInt("idProducto"));
                    obj.setCantidad(rs.getInt("cantidad"));
                    obj.setPrecioVenta(rs.getDouble("precioVenta"));
                    detalle.add(obj);
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
        return detalle;
    }

    public int unaVenta(int id) {
        int ultimoID = 0;
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String sql = "select * from venta where idPaciente=? and idVenta = (select max(idVenta) FROM venta)";
            cn = Conexion.getConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                ultimoID = rs.getInt("idVenta");
            }
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
        return ultimoID;
    }

    public List<Receta> getReceta(int idHistoria) {
        //recupera las lista de recetas associadas al historial
        List<Receta> listReceta = new ArrayList();

        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            cn = Conexion.getConexion();

            String sql1 = "SELECT * FROM receta INNER JOIN producto on receta.idProducto=producto.idProducto INNER JOIN historiaclinica on "
                    + "historiaclinica.idHistoriaClinica=receta.idHistoria WHERE receta.idHistoria=?";
            ps = cn.prepareStatement(sql1);
            ps.setInt(1, idHistoria);
            rs = ps.executeQuery();
            Receta obj;
            if (rs != null) {
                while (rs.next()) {
                    obj = new Receta();
                    obj.setIdReceta(rs.getInt("idReceta"));
                    obj.setProducto(rs.getString("nombreProducto"));
                    obj.setCantidad(rs.getInt("cantidad"));
                    obj.setIdProducto(rs.getInt("idProducto"));
                    obj.setObservacion(rs.getString("observacion"));
                    obj.setEstadoReceta(rs.getString("estadoReceta"));
                    obj.setIdAnalisis(rs.getInt("idAnalisis"));
                    obj.setIdhistoria(rs.getInt("idHistoria"));
                    obj.setPrecioMedicamento(rs.getDouble("precioProducto"));
                    listReceta.add(obj);
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
        return listReceta;
    }
    public List<Farmaco> getFarmacos() {
        //recupera las lista de recetas associadas al historial
        List<Farmaco> listFarmaco = new ArrayList();

        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            cn = Conexion.getConexion();

            String sql1 = "SELECT * FROM `producto`";
            ps = cn.prepareStatement(sql1);
            rs = ps.executeQuery();
            Farmaco obj;
            if (rs != null) {
                while (rs.next()) {
                    obj = new Farmaco();
                    obj.setIdFarmaco(rs.getInt("idProducto"));
                    obj.setNombreFarmaco(rs.getString("nombreProducto"));
                    obj.setPrecio(rs.getDouble("precioProducto"));
                    obj.setStock(rs.getInt("Stock"));
                    obj.setEstadoProducto(rs.getString("estadoProducto"));
                    listFarmaco.add(obj);
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
        return listFarmaco;
    }
}
