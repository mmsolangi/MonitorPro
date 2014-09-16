package com.rc.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the lob database table.
 * 
 */
@Entity
@NamedQuery(name="Lob.findAll", query="SELECT l FROM Lob l")
public class Lob implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int lobid;

	private String isactive;

	private String name;

	public Lob() {
	}

	public int getLobid() {
		return this.lobid;
	}

	public void setLobid(int lobid) {
		this.lobid = lobid;
	}

	public String getIsactive() {
		return this.isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}