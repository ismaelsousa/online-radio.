/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import conection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.beans.Produto;

/**
 *
 * @author ismae
 */
public class ProdutoDAO {
    public void create(Produto p){
        Connection con = null;
        try {
            con = ConnectionFactory.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO cursos (nome,descricao,carga,totaulas,ano VALUES (?,?,?)");
            stmt.setString(1,"c++");
            stmt.setString(2,"curso louco" );
            stmt.setInt(3, 40 );
            stmt.setInt(4, 10);
            stmt.setInt(5, 2018);
            stmt.executeUpdate();
            
            System.out.println("salvo com sucesso");
                    } catch (SQLException ex) {
                        System.out.println("erro n salvou");
                       
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
