package lqc.demo.springcloud.movie.bean;

import java.util.Date;

public class Movie {

	private long id;
	private String moviename;
	private Date srceenDate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMoviename() {
		return moviename;
	}
	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}
	public Date getSrceenDate() {
		return srceenDate;
	}
	public void setSrceenDate(Date srceenDate) {
		this.srceenDate = srceenDate;
	}
	
}
