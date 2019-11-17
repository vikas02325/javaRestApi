package com.vikas.loginApi;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
@Path("user")
public class userResource {
	
 userRepository repo = new userRepository();
 @GET
 @Path("alluser")
 @Produces(MediaType.APPLICATION_JSON)
 public List<userRegister> getuserRegister(){
	 
  return repo.getallUsers();
 }
  
 @Path("/register")
 @POST
 @Consumes({MediaType.APPLICATION_JSON})
 @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
 public Response doGetperson(userRegister userRegisters) {
     try {
    	 
    	 	if(userRegisters != null){
    	 		repo.register(userRegisters);
    	 	}
         return Response.ok("{\"message\":" + "\"new user created\"" + "}").build();
     } catch (Exception ex) {  
         return Response.ok("{ \"error\":\"\"}").build();
     } 
 }
}