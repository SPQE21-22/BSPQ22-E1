package server;

import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import server.data.domain.Book;
import server.data.domain.User;
import server.sql.DB;
import server.sql.DBException;


@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class Server {

	private List<User> usersList;
	private List<Book> booksList;
	private List<User> listaUsuarios;
	private List<Book> listaLibros;

	private Connection con;
	private DB db = new DB();

	public Server() throws SQLException {

		try {
			con = DB.initBD();
			DB.createTables(con);
			DB.addUser(con, "Alex", "a@mail", "1234",new Date(2022, 1, 10));
			DB.addUser(con, "Aida", "a@mail", "1234", new Date(2001, 2, 10));
			DB.addBook(con, "El nombre del Viento", "Path",  new Date(2006, 3, 15), true);
			usersList = DB.getUsersList(con);
			booksList = DB.getBooksList(con);
		} catch (DBException e) {
			e.printStackTrace();
		}
		for (User u:
				usersList) {
			System.out.println(u);
		}

		for (Book b:
				booksList) {
			System.out.println(b);
		}

		System.out.println("AHHHHHHHHHHHHHH");
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
		return Response.ok(this.listaLibros).build();
	}

	@POST
	@Path("/addBook")
	public Response addBook(Book book) {

		this.listaLibros.add(book);
		// return Response.status(Status.CREATED).build();
		return Response.ok(listaLibros).build();

	}

	@GET
	@Path("/response")
	public Response getResp() {
		String s = "Response";
		return Response.ok(s).build();
	}

}