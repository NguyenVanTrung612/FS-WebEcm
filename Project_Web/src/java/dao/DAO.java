/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import entity.Account;
import entity.Category;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pisano
 */
public class DAO {
    
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<Product> getAllProduct(){
        List<Product> list = new ArrayList<>(); 
        String query ="select * from product";
        try{
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs=ps.executeQuery();
            while (rs.next()){
                list.add(new Product(rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getDouble(4),
                rs.getString(5),
                rs.getString(6)));
                
            }
        }catch (Exception e){
            
        }
        
        return list;
    }
    public List<Category> getAllCategory(){
        List<Category> list = new ArrayList<>(); 
        String query ="select * from Category";
        try{
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs=ps.executeQuery();
            while (rs.next()){
                list.add(new Category(rs.getInt(1),
                rs.getString(2)));
                
            }
        }catch (Exception e){
            
        }
        
        return list;
    }
    public Product getLast(){
        String query ="select top 1* from product\n"
                +"order by id desc";
        try{
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs=ps.executeQuery();
            while (rs.next()){
                return new Product(rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getDouble(4),
                rs.getString(5),
                rs.getString(6));
                
            }
        }catch (Exception e){
            
        }
        
        return null;
    }
    public List<Product> getProductByCID(String cid){
        List<Product> list = new ArrayList<>(); 
        String query ="select * from product\n"
                +"where cateID =?";
        try{
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1,cid);
            rs=ps.executeQuery();
            while (rs.next()){
                list.add(new Product(rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getDouble(4),
                rs.getString(5),
                rs.getString(6)));
                
            }
        }catch (Exception e){
            
        }
        
        return list;
    }
        public Product getProductByID(String id){
        String query ="select * from product\n"
                +"where id =?";
        try{
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1,id);
            rs=ps.executeQuery();
            while (rs.next()){
                return new Product(rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getDouble(4),
                rs.getString(5),
                rs.getString(6));
                
            }
        }catch (Exception e){
            
        }
        return null;
     }
     public List<Product> searchByName(String txtSearch){
        List<Product> list = new ArrayList<>(); 
        String query ="select * from product\n"
                +"where [name] like ?";
        try{
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1,"%"+txtSearch+"%");
            rs=ps.executeQuery();
            while (rs.next()){
                list.add(new Product(rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getDouble(4),
                rs.getString(5),
                rs.getString(6)));
                
            }
        }catch (Exception e){
            
        }
        
        return list;
    }
    public Account login(String user, String pass){
        String query ="select * from account\n"
                +"where [user] = ?\n"
                +"and pass = ?";
        try{
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1,user);
            ps.setString(2,pass);
            rs=ps.executeQuery();
            while(rs.next()){
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
                
            }
        }catch (Exception e){
            
        }
        return null;
    }
    public Account checkAccountExist(String user){
        String query ="select * from account\n"
                +"where [user] = ?\n"
                +"and pass = ?";
        try{
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1,user);
            rs=ps.executeQuery();
            while(rs.next()){
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
                
            }
        }catch (Exception e){
            
        }
        return null;
    }
    public void signup(String user, String pass){
        String query = "insert into account\n"
                +"values(?,?,0,0)";
        try{
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1,user);
            ps.setString(2,pass);
            //Do insert ko tra ve gia tri nen:
            ps.executeUpdate();
            
        }catch(Exception e){
            
        }
    }

    public List<Product> getProductSellCID(int id){
        List<Product> list = new ArrayList<>(); 
        String query ="SELECT * from product\n"
                    +"where sell_ID =?";
        try{
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while (rs.next()){
                list.add(new Product(rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getDouble(4),
                rs.getString(5),
                rs.getString(6)));
                
            }
        }catch (Exception e){
            
        }
        
        return list;
    }
    
    public void deleteProduct(String pid){
        String query = "delete from product\n"
                +"where id =?";
        try{
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1,pid);
            //Do insert ko tra ve gia tri nen:
            ps.executeUpdate();
            
        }catch(Exception e){
            
        }
    }
    public void insertProduct(String name, String image, String price,
            String title,String description, String category, int sid){
        String query ="INSERT [dbo].[product] \n"
                +"( [name], [image], [price], [title], [description], [cateID], [sell_ID]) \n"
                +"VALUES(?,?,?,?,?,?,?)";
        try{
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1,name);
            ps.setString(2,image);
            ps.setString(3,price);
            ps.setString(4,title);
            ps.setString(5,description);
            ps.setString(6,category);
            ps.setInt(7,sid);
            
            //Do insert ko tra ve gia tri nen:
            ps.executeUpdate();
            
        }catch(Exception e){
            
        }
    }
    
        public void editProduct(String name, String image, String price,
            String title,String description, String category, String pid){
       // String query ="update product \n"
        //        +"set [name]=?,\n)"
          //      +"[image]=?,\n)"
            //    +"price=?,\n)"
              //  +"title=?,\n)"
                //+"[description]=?,\n)"
            //    +"cateID=?\n)"
              //  +"where id = ?";
        String query =  "update product \n" 
                +"set [name] = ?,\n"
                +"[image]=?,\n"
                +"price=?,\n"
                +"title=?,\n"
                +"[description]=?,\n"
                +"cateID=?\n"
                +"where id = ?";
        try{
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1,name);
            ps.setString(2,image);
            ps.setString(3,price);
            ps.setString(4,title);
            ps.setString(5,description);
            ps.setString(6,category);
            ps.setString(7,pid);
            
            //Do insert ko tra ve gia tri nen:
            ps.executeUpdate();
            
        }catch(Exception e){
            
        }
    }
        
        /////////ACCOUNT DAO
                    public List<Account> getAllAccounts() {
        List<Account> list = new ArrayList<>();
        String query = "select * from Account";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void deleteAccount(String id) {
        String query = "delete from Account where uID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return;
    }

    public void editAccount(String id, String user, String pass, String isSell, String isAdmin) {
        String query = "UPDATE Account\n"
                + "SET [user] = ?,\n"
                + "pass = ?,\n"
                + "isSell = ?,\n"
                + "isAdmin = ?\n"
                + "WHERE uID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setString(3, isSell);
            ps.setString(4, isAdmin);
            ps.setString(5, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public Account getAccountByID(String id) {
        String query = "select * from Account where uID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public int countAllAccount() {
        String query = "select count(*) from Account";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
    
    public static void main(String[] args){
         DAO dao =new DAO();
         List<Product> list =dao.getAllProduct();
         List<Category> listCC =dao.getAllCategory();
         
         
         for (Category o :listCC)
             System.out.println(o);
         
         //for (Product o :list)
             //System.out.println(o);
             
         
     }
}
