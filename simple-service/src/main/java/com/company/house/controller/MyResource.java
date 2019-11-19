package com.company.house.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
//import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myResource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     * @throws IOException 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public String getIt(@PathParam("id") String id ) throws IOException {
    	System.out.println("requested ID is: "+id);
		/*
		 * URL oracle = new URL(
		 * "https://gist.githubusercontent.com/divya051988/191e42740b1bbc545e2e441337aa1228/raw/268cc8fc18dd51b6fe97d1c200efcdba10a44d6e/games.json"
		 * ); URLConnection yc = oracle.openConnection(); BufferedReader in = new
		 * BufferedReader(new InputStreamReader( yc.getInputStream())); String resp =
		 * null; String inputLine; while ((inputLine = in.readLine()) != null) resp =
		 * resp + inputLine;
		 * 
		 * //System.out.println("hii--"); in.close();
		 * 
		 * System.out.println(resp);
		 */
        
        Client client = Client.create();

		WebResource webResource = client
		   .resource("http://www.mocky.io/v2/5d02988a3100003400ab30b0");

		ClientResponse response = webResource.accept("application/json")
                   .get(ClientResponse.class);

		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}

		String output = response.getEntity(String.class);

		System.out.println("Output from Server .... \n");
		System.out.println(output);
		
		
        return output;
    }
}
