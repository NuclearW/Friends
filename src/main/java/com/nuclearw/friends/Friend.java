package com.nuclearw.friends;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.validation.NotEmpty;

@Entity
@Table(name="nw_friends")
public class Friend {
	@Id
	@Column(name="id")
	private int id;

	@NotEmpty
	@Column(name="owner")
	private String owner;

	@NotEmpty
	@Column(name="friend")
	private String friend;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getFriend() {
		return friend;
	}

	public void setFriend(String friend) {
		this.friend = friend;
	}
}
