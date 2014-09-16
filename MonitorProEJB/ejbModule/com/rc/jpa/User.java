package com.rc.jpa;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity

@NamedQueries({
	@NamedQuery(name="User.findByUserName",  query="select usr from User usr where usr.username = :username")
})
@Table(name="User")
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userid")
	private int userid;

	private String username;

	private String password;

	private String isactive;

	private String isadmin;
	private String emailid;

	private String phone;
	private String address;

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	//@OneToMany(mappedBy="userid",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@OneToMany(mappedBy="userid")
	private Set<Login> loginCollection;

	//@OneToMany(mappedBy="userid", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@OneToMany(mappedBy="userid")
	private Set<Role> roleCollection;

	@OneToMany(mappedBy="userid")
	private Set<Messages> messagesCollection;

	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}

	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIsactive() {
		return this.isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public String getIsadmin() {
		return this.isadmin;
	}

	public void setIsadmin(String isadmin) {
		this.isadmin = isadmin;
	}

	public Set<Login> getLoginCollection() {
		return this.loginCollection;
	}

	public void setLoginCollection(Set<Login> loginCollection) {
		this.loginCollection = loginCollection;
	}

	public Set<Role> getRoleCollection() {
		return this.roleCollection;
	}

	public void setRoleCollection(Set<Role> roleCollection) {
		this.roleCollection = roleCollection;
	}

	public Set<Messages> getMessagesCollection() {
		return this.messagesCollection;
	}

	public void setMessagesCollection(Set<Messages> messagesCollection) {
		this.messagesCollection = messagesCollection;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
