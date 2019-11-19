package com.company.house.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.company.house.database.CompanyHouseDao;
import com.company.house.dto.CompanyHousReport;
import com.company.house.dto.Game;

@Path("games")
public class CompanyHouseServicesController {

	public CompanyHouseDao dao = new CompanyHouseDao();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getGame(@PathParam("id") String gameid) {
		//int gameId = Integer.parseInt(gameid);
		System.out.println("getting game info by ID:" + gameid);
		Game response=dao.getGameById(gameid);
		System.out.println("response has been received.."+response);
		return Response.ok(response).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/report")
	public Response getFullReport() {
		System.out.println("Retreiving games report till date");
		CompanyHousReport report = dao.getAllGamesReport();
		System.out.println("Response has been received: ");
		return Response.ok(report).build();
	}

}
