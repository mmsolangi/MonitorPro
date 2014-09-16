package com.rc.jpa;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Lob")
public class Lob implements Serializable {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int lobid;

	private String name;

	private String isactive;

	@OneToMany(mappedBy="lobid",fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Set<Role> roleCollection;

	private static final long serialVersionUID = 1L;

	public Lob() {
		super();
	}

	public int getLobid() {
		return this.lobid;
	}

	public void setLobid(int lobid) {
		this.lobid = lobid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsactive() {
		return this.isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public Set<Role> getRoleCollection() {
		return this.roleCollection;
	}

	public void setRoleCollection(Set<Role> roleCollection) {
		this.roleCollection = roleCollection;
	}

}
