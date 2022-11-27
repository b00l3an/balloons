package com.acme.balloons.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.acme.balloons.ConnectionPool;
import com.acme.balloons.DBUtil;
import com.acme.balloons.Beans.Product;
import com.acme.balloons.ConnectionPool;
import com.acme.balloons.DBUtil;
        
public class ProductDao {
    
    public static int insert(Product product){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        int generatedkey = 0;
        
        String query = "INSERT INTO `product` " + 
                "(`name`, price, description, category_id) " + 
                "VALUES (?, ?, ?, ?)";
        
        try{
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getProductname());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getProductDescription());
            ps.setInt(4, product.getCategoryId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                generatedkey=rs.getInt(1);
            }
            if(generatedkey > 0){
                
            } else{
                connection.rollback();
                return 0;
            }
            connection.commit();
            return generatedkey;
        } catch (SQLException e){
            System.out.println(e);
            return 0;
        } finally{
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);           
        }
    }
    
    public static int update(Product product){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE product SET name = ?, price = ? , " 
                + " description = ? WHERE id = ?";
        
        try {
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(query);
            ps.setString(1, product.getProductname());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getProductDescription());
            ps.setInt(4, product.getProductid());
            ps.executeUpdate();
            connection.commit();
            return 1;
        } catch (SQLException e){
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
    public static int delete(int productId){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        // TESTED: This deletes row 
        String query = "DELETE FROM balloonsrus.product WHERE id = ?";
        
        try{
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(query);
            ps.setInt(1, productId);
            ps.executeUpdate();
            connection.commit();
            return 1;
        } catch (SQLException e){
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
    public static boolean exists(int productid){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        // TESTED: This query returns id from balloonsrus db
        String query = "SELECT id FROM balloonsrus.product where id = ?";
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1, productid);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static List<Product> getList(){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        
        String query = "SELECT * FROM balloonsrus.product";
        try{
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            Product product = null;
            while (rs.next()){
                product = new Product();
                product.setProductid(rs.getInt("id"));
                product.setProductname(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setProductDescription(rs.getString("description"));
                product.setCategoryId(rs.getInt("category_id"));
                list.add(product);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static Product getProduct(int id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM product where id = ?";
        
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            Product product = null;
            
            while (rs.next()){
                product = new Product();
                product.setProductid(rs.getInt("id"));
                product.setProductname(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setProductDescription(rs.getString("description"));
                product.setCategoryId(rs.getInt("category_id"));
                break;
            }
            return product;
            
        } catch (SQLException e){
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }        
    }
}
