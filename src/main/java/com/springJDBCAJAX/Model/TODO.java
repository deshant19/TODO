package com.springJDBCAJAX.Model;

public class TODO {
	private int id;  
	private String name;  
	private float amount;  
	  
	public int getId() {  
	    return id;  
	}  
	public void setId(int id) {  
	    this.id = id;  
	}  
	public String getName() {  
	    return name;  
	}  
	public void setName(String name) {  
	    this.name = name;  
	}  
	public float getAmount() {  
	    return amount;  
	}  
	public void setAmount(float amount) {  
	    this.amount = amount;  
	}
	@Override
	public String toString() {
		return "Emp [id=" + id + ", name=" + name + ", amount=" + amount + "]";
	}  

}
