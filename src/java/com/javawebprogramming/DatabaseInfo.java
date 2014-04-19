/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.javawebprogramming;

import com.model.Product;
import com.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author hanifnaufal
 */
public class DatabaseInfo {
    public static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DATABASE_URL = "jdbc:mysql://localhost/tokoonline";
    public static final String DATABASE_USERNAME = "root";
    public static final String DATABASE_PASSWORD = "";
    private Connection con = null;
    private Statement stmt = null;
    
   /**
    * Membuka koneksi database
    */ 
    private void openConnection(){
        try {        
            Class.forName(DATABASE_DRIVER);
            con = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            stmt = con.createStatement();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseInfo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Menutup koneksi database
     */
    private void closeConnection(){
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * melakukan kueri update 
     * @param query kueri
     * @return banyak row db yang terkait
     */
    public int doUpdate(String query){
        int ret = 0;
        try {            
            openConnection();
            ret = stmt.executeUpdate(query);            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInfo.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeConnection();
        }
        return ret;
    }    
    
    /**
     * melakukan login
     * @param userName username
     * @param password password
     * @return status keberhasilan
     */
    public boolean login(String userName, String password){
        boolean authenticated = false;
        String query = "select count(*) from user where username='"+userName+"' and password='"+ password + "'";        
        try{
            openConnection();
            ResultSet res; 
            res = stmt.executeQuery(query);
            while(res.next()){
                authenticated = res.getInt(1) == 1; 
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInfo.class.getName()).log(Level.SEVERE, null, ex);
        }finally { 
            closeConnection();
        }
            
        
        return authenticated;
    }
    
    /**
     * mengecek duplikasi username
     * @param username username yang akan dicek
     * @return status duplikasi username 
     */
    public boolean isUserExist(String username){
        boolean exist = false;
        String query = "SELECT COUNT(*) FROM user WHERE username='"+username+"'";
        try{
            openConnection();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                exist = res.getInt(1) == 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInfo.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeConnection();
        }
        return exist;        
    }
    
    /**
     * Mengambil featured product
     * @param limit jumlah maksimal yang diambil, jika -1 tidak ada limit
     * @return featured product
     */
    public ArrayList<Product> getFeaturedProduct(int limit){
        ArrayList<Product> result = new ArrayList<Product>();
        String query;
        if(limit != -1){
             query = "SELECT * FROM product WHERE isFeatured=1 LIMIT 0,"+limit;        
        }else{
            query = "SELECT * FROM product WHERE isFeatured=1";
        }
        
        try{
            openConnection();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                result.add(new Product(res.getInt(1), res.getString(2), res.getDouble(3), res.getInt(4), res.getString(5), res.getString(6), res.getInt(7) == 1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInfo.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeConnection();
        }
        return result;        
    }
    
    /**
     * mengambil produk 
     * @param query 
     * @return produk
     */
    public ArrayList<Product> getProductCart(String query){
        ArrayList<Product> result = new ArrayList<Product>();                        
        try{
            openConnection();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                Product p = new Product();
                p.setId(res.getInt("id"));
                p.setName(res.getString("name"));
                p.setPrice(res.getDouble("price"));
                result.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInfo.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeConnection();
        }
        return result;
        
    }                
    
    public User getUser(String username){
        User user = new User();
        try{
            openConnection();
            ResultSet res = stmt.executeQuery("SELECT * FROM user WHERE username='"+username+"'");
            while(res.next()){
                user.setId(res.getInt("id"));
                user.setAlamat(res.getString("alamat"));
                user.setEmail(res.getString("email"));
                user.setNama(res.getString("nama"));
                user.setTelepon(res.getString("telepon"));
                user.setUsername(res.getString("username"));
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInfo.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeConnection();
        }
        return user;        
    }
}
