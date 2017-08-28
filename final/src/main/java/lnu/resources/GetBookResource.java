package lnu.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;

import lnu.models.Book;
import lnu.dao.*;

import java.io.*;
import javax.ws.rs.NotFoundException;

@Produces(MediaType.APPLICATION_JSON)
@Path("/books")
public class GetBookResource {

}
