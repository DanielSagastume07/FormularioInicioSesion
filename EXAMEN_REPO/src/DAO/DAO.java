/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel Sagastume
 */
public class DAO {
   Connection conn;
   
   public DAO(String dbName)
    {
        try
        {
            String Driver = "com.mysql.cj.jdbc.Driver";
            JOptionPane.showMessageDialog(null,"=> Cargando Driver");
            
            Class.forName(Driver);
            JOptionPane.showMessageDialog(null,"=> Driver OK");
            
            String url ="jdbc:mysql://localhost/"+dbName;
            String user="root";
            String Password="";
            
            JOptionPane.showMessageDialog(null,"=> Conectando a la DB.");
            
            conn = DriverManager.getConnection(url, user, Password);
            
            JOptionPane.showMessageDialog(null,"=> Conectado a la DB.");
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Error al conectar con la DB: " +ex.toString());
        }
    }
    
    public Connection getConn()
    {
        return conn;
    }
}
