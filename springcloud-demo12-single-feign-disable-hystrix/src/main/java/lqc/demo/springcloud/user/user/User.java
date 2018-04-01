package lqc.demo.springcloud.user.user;

import java.io.Serializable;
import java.math.BigDecimal;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 129708296684536218L;
	private Long id;
	private String username;
	private String name;
	private Short age;
	private BigDecimal balance;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Short getAge() {
		return age;
	}
	public void setAge(Short age) {
		this.age = age;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
}