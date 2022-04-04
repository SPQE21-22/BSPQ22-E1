package server;

import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import server.data.domain.Book;
import server.data.domain.User;
import server.sql.DBTest;
import server.sql.DBException;


@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class Server {

	private List<User> usersList;
	private List<Book> booksList;
	private List<User> listaUsuarios;
	private List<Book> listaLibros;

	private Connection con;

	public Server() throws SQLException {

		try {
			con = DBTest.initBD();
			DBTest.createTables(con);
			usersList = DBTest.getUsersList(con);
			booksList = DBTest.getBooksList(con);
			System.out.println("List of users stored in the database: " + usersList);
			System.out.println("List of books stored in the database: " + booksList);
		} catch (DBException e) {
			e.printStackTrace();
		}

//        listaUsuarios= new ArrayList<User>();
//        listaUsuarios.add(new User("Alex", "a@mail", "1234", new Date(), new ArrayList<Book>()));
//        listaUsuarios.add(new User("Ruben", "r@mail", "1234", new Date(), new ArrayList<Book>()));
//
//        listaLibros= new ArrayList<Book>();
//        listaLibros.add(new Book("El camino", "BD", new Date(), true));
//        listaLibros.add(new Book("El imperio", "BD", new Date(), true));
	};

	@GET
	@Path("/{email}")
	public Response getUserById(@PathParam("email") String email) {
		User found = null;
		for (int i = 0; i < listaUsuarios.size(); i++) {
			if (listaUsuarios.get(i).getEmail().equalsIgnoreCase(email)) {
				found = listaUsuarios.get(i);
			}
		}
		if (found == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("User not found").build();
		} else {
			return Response.ok(found).build();
		}
	}

	@POST
	@Path("/createUser")
	public Response createUser(User userRequest) {

		this.listaUsuarios.add(userRequest);
		// return Response.status(Status.CREATED).build();
		return Response.ok(listaUsuarios).build();

	}

	@GET
	@Path("/books")
	public Response getBooks() {
		String s = "AHHHHH";
		return Response.ok(s).build();
		//return Response.ok(this.listaLibros).build();
	}

	@POST
	@Path("/addBook")
	public Response createUser(Book book) {

		this.listaLibros.add(book);
		// return Response.status(Status.CREATED).build();
		return Response.ok(listaLibros).build();

	}

}