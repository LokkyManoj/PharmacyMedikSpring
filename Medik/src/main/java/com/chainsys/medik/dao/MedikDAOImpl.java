package com.chainsys.medik.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chainsys.medik.model.CartItem;
import com.chainsys.medik.model.Orders;
import com.chainsys.medik.model.Payment;
import com.chainsys.medik.model.Products;
import com.chainsys.medik.model.User;

import jakarta.servlet.http.HttpServletRequest;

import com.chainsys.medik.mapper.CartItemRowMapper;
import com.chainsys.medik.mapper.MedikDetailsMapper;
import com.chainsys.medik.mapper.ProductsMapper;


@Repository
public class MedikDAOImpl implements MedikDAO{
	@Autowired
	JdbcTemplate jdbcTemplate;
	public void insertUser(User user)  {
		String save="insert into user(name,mobile_no,email,password) values(?,?,?,?)";
		Object[] params= {user.getUserName(),user.getMobileNo(),user.getEmail(),user.getPassword()};
		int rows=jdbcTemplate.update(save, params);
		System.out.println("Rows Affected:"+rows);
	}
	@Override
	public User findUserByEmailAndPassword(String email, String password) {
        String query = "select * from user where email = ? and password = ?";
        Object[] params = {email, password};
        try {
            return jdbcTemplate.queryForObject(query, new MedikDetailsMapper(), params);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
	@Override
	public boolean isUserExists(String email) {
        String query = "select count(*) from user where email = ?";
        Integer count = jdbcTemplate.queryForObject(query,Integer.class, new Object[]{email});
        return count != null && count > 0;
    }
	@Override
	public boolean addProduct(Products product){
        String sql = "INSERT INTO pharmacy_admin (product_id, product_name, product_image, product_quantity, product_price, description, uses, contains, product_category, mfd_date, exp_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] params = { 
            product.getProductId(), product.getProductName(), product.getProductImage(),
            product.getProductQuantity(), product.getProductPrice(), product.getDescription(),
            product.getUses(), product.getContains(), product.getCategory(), product.getMfdDate(),
            product.getExpDate()
        };
        int row = jdbcTemplate.update(sql, params);
        return row > 0;
    }
	@Override
	 public List<Products> getAllProducts() {
	        String sql = "SELECT * FROM pharmacy_admin WHERE is_deleted = 0";
	        
	        return jdbcTemplate.query(sql, new ProductsMapper());
	    }
	@Override  
	 public void updateProducts(Products product) {
		 String update="update pharmacy_admin set product_name=?,product_quantity=?,product_price=?,description=?,uses=?,contains=?,product_category=?,mfd_date=?,exp_date=?  WHERE product_id=?";
		 Object[] params = { 
				  product.getProductName(),
		            product.getProductQuantity(), product.getProductPrice(), product.getDescription(),
		            product.getUses(), product.getContains(), product.getCategory(), product.getMfdDate(),
		            product.getExpDate(),product.getProductId()
		        };
		 jdbcTemplate.update(update, params);
	 }
	@Override
	 public Products findProductById(int productId) {
	        String query = "select product_id,product_name,product_image,product_quantity,product_price,description,uses,contains,product_category,mfd_date,exp_date,is_deleted from pharmacy_admin where product_id = ?";
	        return jdbcTemplate.queryForObject(query, new ProductsMapper(), productId);
	    }
	@Override
	 public void deleteProduct(int productId) {
	        String delete = "update pharmacy_admin set is_deleted=1 where product_id=?";
	        jdbcTemplate.update(delete, productId);
	    }
	@Override
	 public List<Products> searchProductsByName(String name) {
		    String query = "SELECT * FROM pharmacy_admin WHERE is_deleted = 0 AND product_name LIKE ?";
		    String searchPattern = "%" + name + "%";
		    return jdbcTemplate.query(query, new ProductsMapper(), searchPattern);
		}
	@Override
	 public List<Products>getProductsByCategory(String category, int isDeleted){
		 
		 String sql="SELECT * FROM pharmacy_admin WHERE product_category = ? AND is_deleted = ?";
		 Object[] params = { category, isDeleted };
		 return jdbcTemplate.query(sql, new ProductsMapper(),params);
	 }
	@Override
	 public boolean addToCart(int userId, int productId, int quantity) {
		    String sql = "INSERT INTO add_cart (quantity, id, product_id) VALUES (?, ?, ?)";
		    Object[] params = { quantity, userId, productId };
		    int row = jdbcTemplate.update(sql, params);
		    return row > 0;
		}
	@Override
	 public List<CartItem> getCartItemsByUserId(int userId, HttpServletRequest request) {
	        String sql = "SELECT c.cart_id, a.product_id, a.product_name, a.product_price, a.product_image, c.quantity " +
	                     "FROM pharmacy_admin a " +
	                     "JOIN add_cart c ON a.product_id = c.product_id " +
	                     "WHERE c.id = ?";

	        return jdbcTemplate.query(sql,  new CartItemRowMapper(request),new Object[]{userId});
	    }
	@Override
	 public int getCartItemCount(int userId) {
	        String sql = "SELECT COUNT(*) AS cart_count FROM add_cart WHERE id = ?";
	        return jdbcTemplate.queryForObject(sql, Integer.class,new Object[]{userId});
	    }
	@Override
	 public boolean deleteCartItemsByCartId(int cartId) {
	        String sql = "DELETE FROM add_cart WHERE cart_id = ?";
	        int rowsAffected = jdbcTemplate.update(sql, cartId);
	        return rowsAffected > 0;
	    }
	@Override
	 public boolean updateCartQuantity(int cartId,int quantity) {
		 String update="UPDATE add_cart SET quantity = ? WHERE cart_id = ?";
		 int rowsUpdated = jdbcTemplate.update(update, quantity, cartId);
		 return rowsUpdated > 0;
	 }
	
	 @Override
	    public boolean placeOrder(Orders order) {
	        String sql = "INSERT INTO orders (product_id, order_date, quantity, status, expected_delivery_date, id, address) VALUES (?, ?, ?, ?, ?, ?, ?)";

	        try {
	            int rowsInserted = jdbcTemplate.update(sql,
	                    order.getProductId(),
	                    order.getOrderDate(),
	                    order.getQuantity(),
	                    order.getStatus(),
	                    order.getExpectedDeliveryDate(),
	                    order.getUserId(),
	                    order.getAddress());
	            return rowsInserted > 0;
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return false;
	        }
	    }
	@Override
	public boolean payment(Payment payment) {
		String sql = "INSERT INTO payment (payment_date, payment_method, amount, id, product_id) VALUES (?, ?, ?, ?, ?)";
	    Object[] params = { 
	        payment.getPaymentDate(), 
	        payment.getPaymentMethod(), 
	        payment.getAmount(), 
	        payment.getUserId(), 
	        payment.getProductId() 
	    };
	    int rowsAffected = jdbcTemplate.update(sql, params);
	    return rowsAffected > 0;
	}
	@Override
	public boolean updateProductQuantity(int productId, int newQuantity) {
		 String query = "UPDATE pharmacy_admin SET product_quantity = ? WHERE product_id = ?";
	        int rowsUpdated = jdbcTemplate.update(query, newQuantity, productId);
	        return rowsUpdated > 0;	
	        }
	@Override
	public boolean deleteCartItemsByUserId(int userId) {
		String sql = "DELETE FROM add_cart WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, userId);
        return rowsAffected > 0;	
        }

}


