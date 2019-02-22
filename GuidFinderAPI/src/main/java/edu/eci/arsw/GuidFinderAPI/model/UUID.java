package edu.eci.arsw.GuidFinderAPI.model;

public class UUID {
	
	private String fecha;

	private String Guid;
	private int count;
	
	public UUID(String fecha, String Guid, int count) {
		this.fecha  = fecha;
		this.Guid = Guid;
		this.count = count;
	}
	
	public UUID() {
	
	}
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getGuid() {
		return Guid;
	}
	public void setGuid(String guid) {
		Guid = guid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	

}
