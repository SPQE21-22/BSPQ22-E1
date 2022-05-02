package server;

import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import server.data.domain.Book;
import server.data.domain.Reserv;
import server.data.domain.Room;
import server.data.domain.User;
import server.sql.DB;
import server.sql.DBException;


@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class Server {

	private List<User> usersList;
	private List<Book> booksList;
	private List<Room> roomList;
	private Connection con;
	private DB db = new DB();
	private static final Logger logger = LogManager.getLogger(Server.class);

	public Server() throws SQLException {

		try {
			con = DB.initBD();
			usersList = DB.getUsersList(con);
			booksList = DB.getBooksList(con);
			roomList = DB.getRoomsList(con);
		} catch (DBException e) {
			e.printStackTrace();
		}
		logger.info("Servidor Iniciado");
	};

	@POST
	@Path("/login")
	public Response login(String email) {
		User found = null;
		for (int i = 0; i < usersList.size(); i++) {
			if (usersList.get(i).getEmail().equalsIgnoreCase(email) ) {
				found = usersList.get(i);
			}
		}
		if (found == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("User not found").build();
		} else {
			logger.info("Login correcto");
			return Response.ok(found).build();
		}
	}

	@POST
	@Path("/createUser")
	public Response createUser(User userRequest) throws DBException {
		DB.addUser(con, userRequest.getName(), userRequest.getEmail(), userRequest.getPassword(), new Date(userRequest.getBirthDate().getYear(), userRequest.getBirthDate().getYear(), userRequest.getBirthDate().getDay()));
		this.usersList.add(userRequest);
		logger.info("Usuario creado correctamente");
		return Response.ok(userRequest).build();

	}

	@GET
	@Path("/books")
	public Response getBooks() {
		logger.info("Devolviendo listado de libros");
		User admin = new User("","","",null,this.booksList);
		return Response.ok(admin).build();
	}

	@POST
	@Path("/addBook")
	public Response addBook(Book book) throws DBException {
		DB.addBook(con, book.getName(), book.getAuthor(),  new Date(book.getPublishDate().getYear(), book.getPublishDate().getMonth(), book.getPublishDate().getDay()), true);
		this.booksList.add(book);
		logger.info("Libro añadido correctamente");
		return Response.ok(book).build();

	}

	@PUT
	@Path("/updateBook")
	public Response updateBook(Book book) throws DBException {
		for (int i = 0; i < booksList.size(); i++) {
			if (booksList.get(i).getName().equalsIgnoreCase(book.getName()) ) {
				this.booksList.remove(booksList.get(i));
			}
		}
		DB.updateBookAvailability(con, book.getName(), book.getAvailable());
		logger.info("Libro actualizado correctamente");
		return Response.ok(book).build();

	}

	@GET
	@Path("/rooms")
	public Response getRooms() {
		logger.info("Devolviendo Reservas de habitaciones");
		Reserv res = new Reserv(roomList);
		System.out.println(res.getReservs().get(0));
		return Response.ok(res).build();
	}

	@POST
	@Path("/addRoom")
	public Response addRoom(Room reserv) throws DBException {
		System.out.println("///////////////////////////");
		System.out.println(reserv);
		System.out.println("///////////////////////////");
		DB.addRoom(con, reserv.getName(), reserv.getDay(),  reserv.getMonth(), reserv.getHourBeg(), reserv.getHourEnd(), reserv.getBooked());
		this.roomList.add(reserv);
		logger.info("Libro añadido correctamente");
		return Response.ok(reserv).build();
	}

	@GET
	@Path("/response")
	public Response getResp() {
		String s = "Response";
		return Response.ok(s).build();
	}

}