/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Factory;
import DAO.DAO;
import Entity.Entity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
/**
 *
 * @author Daniel Sagastume
 */
public class Factory {
    
    DAO conn = new DAO("examenrepo");
    Statement st=null;
    ResultSet rs=null;
    Connection c;
    
    public Entity search(String Id){
        Entity entity= new Entity();
        
        try{
            c = conn.getConn();
            st = c.createStatement();
            rs = st.executeQuery("SELECT * FROM testudiantes WHERE id='"+Id+"'");
            
            while(rs.next()){
                entity.setId(rs.getInt("id"));
                entity.setNombre(rs.getString("nombre"));
                entity.setApellido(rs.getString("apellido"));
                entity.setEdad(rs.getInt("edad"));
                entity.setCarrera(rs.getString("carrera"));

                entity.setItExists(true);
            }
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Ocurrio un error: "+ex);
        }
        
        return entity;
    }
    
    public boolean insert(Entity entity){
        boolean success=false;
        String query="";
        
        try{
            c = conn.getConn();
            st = c.createStatement();
            query +="INSERT INTO testudiantes(id,nombre,apellido,edad,carrera) VALUES(";
            query +="'"+entity.getId()+"', ";
            query +="'"+entity.getNombre()+"', ";
            query +="'"+entity.getApellido()+"', ";
            query +="'"+entity.getEdad()+"', ";
            query +="'"+entity.getCarrera()+"' ";
            query +=")";
            st.execute(query);         
            success = true;
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Ocurrio un error: "+ex);
        }
        return success;
    }
    
    public boolean update(Entity entity){
        boolean success=false;
        String query="";
        
        try{
            c = conn.getConn();
            st = c.createStatement();
            
            query +="UPDATE testudiantes SET ";
            
            query +="Nombre='"+entity.getNombre()+"', ";
            query +="Apellido='"+entity.getApellido()+"', ";
            query +="Edad='"+entity.getEdad()+"', ";
            query +="Carrera='"+entity.getCarrera()+"' ";
            query +="WHERE id='"+entity.getId()+"'";
            st.execute(query);         
            success = true;
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Ocurrio un error: "+ex);
        }
        return success;
    }
    
    public boolean delete(String id){
        boolean success=false;
        String query="";
        
        try{
            c = conn.getConn();
            st = c.createStatement();
            query +="DELETE FROM testudiantes WHERE id='"+id+"'";
           
            st.execute(query);         
            success = true;
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Ocurrio un error: "+ex);
        }    
        return success;
    }
}
