package rpi.edu.rpimobile.model;

//Class for storing tracking details for each shuttle
public class Shuttle {

	//Shuttle identification
	public int id = 0;
	public String name = "";
	
	//Shuttle's latest position
	public int heading = 0;			//used for rotating icon
	public String latitude = "";
	public String longitude = "";
	public int speed = 0;
	public String timestamp = "";
	public String cardinal_point = "";
	public String public_status_msg = "";
	
}
