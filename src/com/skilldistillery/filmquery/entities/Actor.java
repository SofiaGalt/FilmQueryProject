package com.skilldistillery.filmquery.entities;

import java.util.HashMap;
import java.util.Objects;

public class Actor {

	public static HashMap<Integer, Actor> actors = new HashMap<>();
	
	private int id;
	private String firstName;
	private String lastName;
	
	public Actor(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		
		actors.put(id, this);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Actor [actors=");
		builder.append(actors);
		builder.append(", id=");
		builder.append(id);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(actors, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		return Objects.equals(actors, other.actors) && id == other.id;
	}
	
	
}
