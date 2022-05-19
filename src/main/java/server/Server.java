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
import server.data.domain.*;
import server.sql.DB;
import server.sql.DBException;


@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class Server {

	private List<User> usersList;
	private List<Book> booksList;
	private List<Room> roomList;
	private List<Fine> finesList;
	private List<Supply> supplysList;
	private Connection con;
	private DB db = new DB();
	private static final Logger logger = LogManager.getLogger(Server.class);

	public Server() throws SQLException {

		try {
			con = DB.initBD();
			usersList = DB.getUsersList(con);
			booksList = DB.getBooksList(con);
			finesList = DB.getFinesList(con);
			roomList = DB.getRoomsList(con);
			supplysList = DB.getSuppliesList(con);
		} catch (DBException e) {
			e.printStackTrace();
		}
		//logger.info("Servidor Iniciado");
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
		//logger.info("Devolviendo listado de libros");
		User admin = new User("","","",null,this.booksList, new ArrayList<Fine>());
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
		logger.info(res.getReservs().get(0));
		return Response.ok(res).build();
	}

	@POST
	@Path("/addRoom")
	public Response addRoom(Room reserv) throws DBException {
		logger.info(reserv);
		DB.addRoom(con, reserv.getName(), reserv.getDay(),  reserv.getMonth(), reserv.getHourBeg(), reserv.getHourEnd(), reserv.getBooked(), reserv.getUser().getEmail());
		this.roomList.add(reserv);
		logger.info("Habitacion añadida correctamente");
		return Response.ok(reserv).build();
	}

	@POST
	@Path("/fine")
	public Response fineUser(Fine fine) throws DBException {
		logger.info(fine);
		DB.addFine(con, fine.getQuantity(), fine.getUser().getEmail());
		logger.info("Usuario multado correctamente");
		return Response.ok(fine).build();
	}

	@POST
	@Path("/fines")
	public Response getFines(User user) {
		ArrayList<Fine> f = new ArrayList<Fine>();
		for (Fine value : finesList) {
			if (value.getUser().getEmail().equalsIgnoreCase(user.getEmail())) {
				f.add(value);
			}
		}
		user.setFines(f);
		logger.info("Devolviendo Multas de usuario");
		return Response.ok(user).build();
	}

	@GET
	@Path("/supplies")
	public Response getSupplies() {
		ArrayList<Supply> starter = new ArrayList<Supply>();
		ArrayList<Supply> main = new ArrayList<Supply>();
		ArrayList<Supply> pastry = new ArrayList<Supply>();
		ArrayList<Supply> drink = new ArrayList<Supply>();
		for (Supply s: supplysList){
			switch (s.getType()) {
				case "Starter":
					starter.add(s);
					break;
				case "Main Course":
					main.add(s);
					break;
				case "Pastry":
					pastry.add(s);
					break;
				default:
					drink.add(s);
					break;
			}
		}
		Menu res = new Menu(starter, main, pastry, drink);
		logger.info("Devolviendo contenidos de cafeteria");
		return Response.ok(res).build();
	}

	@POST
	@Path("/addSupply")
	public Response addSupply(Supply supply) throws DBException {
		logger.info(supply);
		DB.addSupply(con, supply.getName(), supply.getPrice(), supply.getType());
		this.supplysList.add(supply);
		logger.info("Supply añadido correctamente");
		return Response.ok(supply).build();
	}

	@GET
	@Path("/response")
	public Response getResp() {
		String s = "Response";
		return Response.ok(s).build();
	}

}