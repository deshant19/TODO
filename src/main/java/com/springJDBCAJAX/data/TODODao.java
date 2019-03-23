package com.springJDBCAJAX.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springJDBCAJAX.Model.TODO;
@Transactional(rollbackFor = Exception.class)
@Repository
public class TODODao {
	@Autowired
	JdbcTemplate template;  
	
	public void setTemplate(JdbcTemplate template) {  
	    this.template = template;  
	}  
	  
	public List<TODO> getEmployeesByPage(int pageid,int total){  
	    String sql="select * from employee limit "+(pageid-1)+","+total;  
	    return template.query(sql,new RowMapper<TODO>(){  
	        public TODO mapRow(ResultSet rs, int row) throws SQLException {  
	            TODO e=new TODO();  
	            e.setId(rs.getInt(1));  
	            e.setName(rs.getString(2));  
	            e.setAmount(rs.getFloat(3));  
	            return e;  
	        }  
	    });  
	} 
	
	@Transactional(rollbackFor = Exception.class)
	public int save(TODO p){  
	    String sql="insert into employee(id,name,salary) values(?,?,?)";
	
		return template.update(sql,p.getId(),p.getName(),p.getAmount());
	}  
	@Transactional(rollbackFor = Exception.class)
	public int update(TODO p){  
	    String sql="update employee set name='"+p.getName()+"', salary="+p.getAmount()+"where id="+p.getId(); 
	    
	    return template.update(sql);  
	}  
	@Transactional(rollbackFor = Exception.class)
	public int delete(int id){  
	    String sql="delete from employee where id="+id+""; 
	   
	    return template.update(sql);  
	}  
	@Transactional(rollbackFor = Exception.class)
	public TODO getEmpById(int id){  
	    String sql="select * from employee where id=?";  
	    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<TODO>(TODO.class));  
	}  
	
	
	
	
}
