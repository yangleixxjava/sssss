package com.yl.entity;

import java.io.Serializable;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * 
 * @author yanglei
 *
 */

@Table(name="custom")
@Entity
public class UserEntity extends  BaseEntity  implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
    private String name;
    private String account;
    private String password;
    private String sex; 
    private String phone;
	
    
    //nullable=false ,
    @Id
   // @GeneratedValue(strategy= GenerationType.IDENTITY)
    
    @GeneratedValue
    @ Column(name="UID",length=11)
	    public String getId() {
		return id;
	}
	    
	   
	public void setId(String id) {
		this.id = id;
	}
	    
	    @Column(name="UNAME",length=20)
	
	public String getName() {
		return name;
	}
	    
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "UACCOUNT",  length = 20)
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	
	 @Column(name = "UPASSWORD",  length = 20)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
    @Column(name = "USEX", length = 10)
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
	   @Column(name = "UPHONE",  length = 15)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}


	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", name=" + name + ", account="
				+ account + ", password=" + password + ", sex=" + sex
				+ ", phone=" + phone + "]";
	}
	
	
}
