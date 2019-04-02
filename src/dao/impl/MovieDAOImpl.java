package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.MovieDAO;
import db.DBCon;

public class MovieDAOImpl implements MovieDAO {

	private static final String SELECT_MOVIE_LIST = "select mi_num, mi_name, mi_year, mi_national, mi_vendor, mi_director from movie_info";
	private static final String SELECT_MOVIE_BY_MI_NUM = "select * from movie_info where mi_num=?";
	private static final String INSERT_MOVIE = "insert into movie_info(mi_num, mi_name, mi_year, mi_national, mi_vendor, mi_director) " 
												+ " values(seq_mi_num.nextval,?,?,?,?,?) ";
	
	private static final String DELETE_MOVIE_BY_MI_NUM = "delete from movie_info where mi_num=?";
	
	
	@Override
	public List<Map<String, String>> selectMovieList() {
		try {
			List<Map<String,String>> movieList = new ArrayList<>();
			PreparedStatement ps = DBCon.getCon().prepareStatement(SELECT_MOVIE_LIST);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Map<String,String> movie = new HashMap<>();
				movie.put("mi_num", rs.getString("mi_num"));
				movie.put("mi_name", rs.getString("mi_name"));
				movie.put("mi_year", rs.getString("mi_year"));
				movie.put("mi_national", rs.getString("mi_national"));
				movie.put("mi_vendor", rs.getString("mi_vendor"));
				movie.put("mi_director", rs.getString("mi_director"));
				movieList.add(movie);
			}
			return movieList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, String> selectMovieByMiNum(int miNum) {
			try {
				PreparedStatement ps = DBCon.getCon().prepareStatement(SELECT_MOVIE_BY_MI_NUM);
				ps.setInt(1, miNum);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					Map<String,String> movie = new HashMap<>();
					movie.put("mi_num", rs.getString("mi_num"));
					movie.put("mi_name", rs.getString("mi_name"));
					movie.put("mi_year", rs.getString("mi_year"));
					movie.put("mi_national", rs.getString("mi_national"));
					movie.put("mi_vendor", rs.getString("mi_vendor"));
					movie.put("mi_director", rs.getString("mi_director"));
					return movie;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}
	
	@Override
	public int insertMovie(Map<String, String> movie) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(INSERT_MOVIE);
			ps.setString(1, movie.get("mi_name"));
			ps.setString(2, movie.get("mi_year"));
			ps.setString(3, movie.get("mi_national"));
			ps.setString(4, movie.get("mi_vendor"));
			ps.setString(5, movie.get("mi_director"));
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCon.close();
		} return 0;
	}
	
	
	@Override
	public int deleteMovie(int miNum) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(DELETE_MOVIE_BY_MI_NUM);
			ps.setInt(1, miNum);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return 0;
	}
	
	public static void main(String[] args) {
		MovieDAO m = new MovieDAOImpl();
		System.out.println(m.selectMovieList());
	}

	

	
}
