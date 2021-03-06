package rest;

import DTOs.PersonDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Person;
import entities.RenameMe;
import utils.EMF_Creator;
import facades.PersonFacade;
import facades.IPersonFacadeInterface;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("person")
public class IPersonRessource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final PersonFacade FACADE =  PersonFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        long count = FACADE.getRenameMeCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    
    
    @Path("fill")
    @GET
     @Produces({MediaType.APPLICATION_JSON})
    public String addToDatabase() {
    
        FACADE.addPerson("Claus", "Mikkelsen", "007"); 
       
        return GSON.toJson("Person added");
        
    }
   
    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPersonById(@PathParam("id") int id) {
        
        PersonDTO person = FACADE.getPerson(id);
         return GSON.toJson((person));
        
    }
    
     @Path("fill")
    @GET
     @Produces({MediaType.APPLICATION_JSON})
    public String deletePersonById(int id) {
    
        FACADE.deletePerson(id);
       
        return GSON.toJson("Person deleted");
        
    }
    
}