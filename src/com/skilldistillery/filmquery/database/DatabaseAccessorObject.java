package com.skilldistillery.filmquery.database;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.Rating;

public class DatabaseAccessorObject implements DatabaseAccessor {

	public static DatabaseAccessorObject access = new DatabaseAccessorObject();
	
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private static String user = "student";
	private static String pass = "student";
	private static Connection conn = null;
	
	private DatabaseAccessorObject() {}
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, user, pass);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Film findFilmById(int filmId){

		if(filmId < 1 ) return null;
		try {
			String sql = "SELECT * FROM film where film.id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, filmId);
			
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				
				return new Film(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getDouble(7), rs.getInt(8), rs.getDouble(9), Rating.valueOf(rs.getString(10)), rs.getString(11), findActorsByFilmId(filmId) );
			}
			
		} catch(SQLException e) {
			System.out.println(e);
		}
		
		return null;
	}

	@Override
	public Actor findActorById(int actorId) {
		
		
		if( actorId < 1 ) return null;
		
		try {
			String sql = "SELECT * FROM actor where actor.id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, actorId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				return new Actor(rs.getInt(1), rs.getString(2), rs.getString(3) );
			}
		} catch(SQLException e) {
			System.out.println(e);
		}
		
		return null;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {

		if(filmId < 1 ) return null;
		
		try {
			String sql = "select * from film join film_actor on film.id = film_actor.film_id join actor on film_actor.actor_id = actor.id where film.id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, filmId);
			
			ResultSet rs = ps.executeQuery();
			
			List<Actor> cast = new LinkedList<>();
			while(rs.next()) {
				
				cast.add(new Actor(rs.getInt("actor.id"), rs.getString("actor.first_name"), rs.getString("actor.last_name") ));
			}
			
			return cast;
		} catch(SQLException e) {
			System.out.println(e);
		}
		
		return null;
	}
	
	public List<Film> findFilmByKeyword(String keyword) {
		
		List<Film> filmsMatchedKeyword = new LinkedList<>();
		
		try {
			String sql = "select * from film where film.title like ? or film.description like ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			ps.setString(2, "%" + keyword + "%");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				filmsMatchedKeyword.add( new Film(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getDouble(7), rs.getInt(8), rs.getDouble(9), Rating.valueOf(rs.getString(10)), rs.getString(11), findActorsByFilmId(rs.getInt(1)) ));
			}
			
			return filmsMatchedKeyword;
			
		} catch(SQLException e) {
			System.out.println(e + "from findFilmByKeyword!!!!!!!!!!!!!!!!!!!!!");
			
		}
		
		return null;
	}
	
}
