package edu.upc.eetac.dsa.services;

import edu.upc.eetac.dsa.GameManager;
import edu.upc.eetac.dsa.GameManagerImpl;
import edu.upc.eetac.dsa.Objeto;
import edu.upc.eetac.dsa.Usuario;
import edu.upc.eetac.dsa.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;

@Api(value="/orders", description = "Endpoint to Order Service")
@Path("/orders")
public class UsersService {

    final static Logger log = Logger.getLogger(UsersService.class.getName());

    private GameManager gm;

    public UsersService() {
        this.gm = GameManagerImpl.getInstance();
        if(gm.getNumUsuarios() == 0){
            Objeto producto1 = new Objeto("Manzana",1.5);
            Objeto producto2 = new Objeto("Pastel",10);
            Objeto producto3 = new Objeto("Cerveza",2.5);
            Objeto producto4 = new Objeto("Calamares",5);
            Usuario user = new Usuario("Pepe", "Bichuela");
            String id = user.getId();
            //user.addObjeto(producto1);
            gm.addUser(new Usuario("Pepe", "Bichuela"));

            gm.addObject(gm.getUsuarios().get(id), producto1);
            gm.addObject(gm.getUsuarios().get(id), producto2);
            gm.addObject(gm.getUsuarios().get(id), producto3);
            gm.addObject(gm.getUsuarios().get(id), producto4);
        }
    }

    @GET
    @ApiOperation(value = "get all users in the list", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer = "List of Users")
    })
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        HashMap<String, Usuario> users  = this.gm.getUsuarios();

        GenericEntity<HashMap<String, Usuario>> entity = new GenericEntity<HashMap<String, Usuario>>(users){};
        return Response.status(201).entity(entity).build();
    }





    @POST
    @ApiOperation(value = "Add a User", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
    })
    @Path("/addUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response AddUser(Usuario u) {

        this.gm.addUser(u);
        return Response.status(201).build();
    }


    @PUT
    @ApiOperation(value = "update a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
    })
    @Path("/")
    public Response updateTrack(Usuario u) {

        this.gm.updateUsuario(u);
        return Response.status(201).build();
    }

    @GET
    @ApiOperation(value = "get all info od a user", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer = "List of Users")
    })
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserInfo(Usuario u) {
        String[] info = this.gm.getUsuarioInfo(u);

        GenericEntity<String[]> entity = new GenericEntity<String[]>(info){};
        return Response.status(201).entity(entity).build();
    }

    @PUT
    @ApiOperation(value = "add object to user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
    })
    @Path("/")
    public Response addObject(Objeto o, Usuario u) {

        this.gm.addObject(u, o);
        return Response.status(201).build();
    }

}