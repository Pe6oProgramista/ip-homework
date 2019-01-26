package org.elsys.netprog.rest;

import java.util.HashMap;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/")
public class Controller {
	private static HashMap<String, CarReg> cars = new HashMap<String, CarReg>(); 
	
	@PUT
	@Path("/green/{car_reg}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response greenZone(@PathParam("car_reg") String car_reg) throws Exception{
		System.out.println("yes green");
		if(!car_reg.matches("[ETYOPAHKXCBM]{2}\\d{4}[ETYOPAHKXCBM]{2}")) {
			System.out.println("Not match with regex");
			return Response.status(400).build();
		}

		CarReg car;
		if((car = cars.get(car_reg)) == null) {
			car = new CarReg(car_reg);
		}
		car.subscribe("green");
		cars.put(car_reg, car);
		
		return Response.status(200).build();
	}
	
	@PUT
	@Path("/blue/{car_reg}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response blueZone(@PathParam("car_reg") String car_reg) throws Exception{
		System.out.println("yes blue");
		if(!car_reg.matches("[ETYOPAHKXCBM]{2}\\d{4}[ETYOPAHKXCBM]{2}")) {
			System.out.println("Not match with regex");
			return Response.status(400).build();
		}
		CarReg car;
		if((car = cars.get(car_reg)) == null) {
			car = new CarReg(car_reg);
		}
		car.subscribe("blue");
		cars.put(car_reg, car);
		
		return Response.status(200).build();
	}
	
	@GET
	@Path("/{car_reg}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response getGames(@PathParam("car_reg") String car_reg) {
		CarReg car;
		if((car = cars.get(car_reg)) != null) {
			return Response.status(200).entity(car.toJSON().toString()).build();
		}
		return Response.status(400).build();
	}
}
