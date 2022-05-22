package server;

/**
 * Server functionality.
 * @author Alex Egaña, Eneko Eguiguren, Rubén García, Aida Gomezbueno & Tyler de Mier - BSPQ22-E1
 * @version 1.0
 * @since 2022-03-20
 */

import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

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
	private Menu dailymenu;
	private Connection con;
	private DB db = new DB();
	private static final Logger logger = LogManager.getLogger(Server.class);
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());

	/**
	 * Gets DB information.
	 * @throws SQLException
	 */
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
	};

	/**
	 * Login function.
	 * @param email
	 * @return Response ok found if user found. Bad_Request if not found.
	 */
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
			logger.info(resourceBundle.getString("loginSuccess"));
			return Response.ok(found).build();
		}
	}

	/**
	 * Adds a user to the DB.
	 * @param userRequest
	 * @return Response ok new user.
	 * @throws DBException
	 */
	@POST
	@Path("/createUser")
	public Response createUser(User userRequest) throws DBException {
		DB.addUser(con, userRequest.getName(), userRequest.getEmail(), userRequest.getPassword(), new Date(userRequest.getBirthDate().getYear(), userRequest.getBirthDate().getMonth(), userRequest.getBirthDate().getDay()));
		this.usersList.add(userRequest);
		logger.info(resourceBundle.getString("userCreated"));
		return Response.ok(userRequest).build();

	}

	/**
	 * Gets all the books from the DB.
	 * @return Response ok book list.
	 */
	@GET
	@Path("/books")
	public Response getBooks() {
		//logger.info(resourceBundle.getString("bookList"));
		User admin = new User("","","",null,this.booksList, new ArrayList<Fine>());
		return Response.ok(admin).build();
	}

	/**
	 * Adds a book to the DB.
	 * @param book
	 * @return Response ok new book.
	 * @throws DBException
	 */
	@POST
	@Path("/addBook")
	public Response addBook(Book book) throws DBException {
		DB.addBook(con, book.getName(), book.getAuthor(),  new Date(book.getPublishDate().getYear(), book.getPublishDate().getMonth(), book.getPublishDate().getDay()), true);
		this.booksList.add(book);
		logger.info(resourceBundle.getString("addBook"));
		return Response.ok(book).build();

	}

	/**
	 * Changes availability of a book and removes from the available list.
	 * @param book
	 * @return Response ok updated book.
	 * @throws DBException
	 */
	@PUT
	@Path("/updateBook")
	public Response updateBook(Book book) throws DBException {
		for (int i = 0; i < booksList.size(); i++) {
			if (booksList.get(i).getName().equalsIgnoreCase(book.getName()) ) {
				this.booksList.remove(booksList.get(i));
			}
		}
		DB.updateBookAvailability(con, book.getName(), book.getAvailable());
		logger.info(resourceBundle.getString("updateBook"));
		return Response.ok(book).build();

	}

	/**
	 * Gets all the rooms from the DB.
	 * @return Response ok all rooms.
	 */
	@GET
	@Path("/rooms")
	public Response getRooms() {
		logger.info(resourceBundle.getString("getRooms"));
		Reserve res = new Reserve(roomList);
		logger.info(res.getReserves().get(0));
		return Response.ok(res).build();
	}

	/**
	 * Adds a room to the DB.
	 * @param reserve
	 * @return Response ok new reservation.
	 * @throws DBException
	 */
	@POST
	@Path("/addRoom")
	public Response addRoom(Room reserve) throws DBException {
		logger.info(reserve);
		DB.addRoom(con, reserve.getName(), reserve.getDay(),  reserve.getMonth(), reserve.getHourBeg(), reserve.getHourEnd(), reserve.getBooked(), reserve.getUser().getEmail());
		this.roomList.add(reserve);
		logger.info(resourceBundle.getString("addRoom"));
		return Response.ok(reserve).build();
	}

	/**
	 * Adds a fine in the DB.
	 * @param fine
	 * @return Response ok new fine.
	 * @throws DBException
	 */
	@POST
	@Path("/fine")
	public Response fineUser(Fine fine) throws DBException {
		logger.info(fine);
		DB.addFine(con, fine.getQuantity(), fine.getUser().getEmail());
		logger.info(resourceBundle.getString("fine"));
		return Response.ok(fine).build();
	}

	/**
	 * Displays all the fines of a user.
	 * @param user
	 * @return Response ok user fines.
	 */
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
		logger.info(resourceBundle.getString("getFines"));
		return Response.ok(user).build();
	}

	/**
	 * Displays all the supplies (Menu)
	 * @return Response ok menu.
	 */
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
		logger.info(resourceBundle.getString("getSupplies"));
		return Response.ok(res).build();
	}

	/**
	 * Adds a supply to the DB.
	 * @param supply
	 * @return Response ok new supply.
	 * @throws DBException
	 */
	@POST
	@Path("/addSupply")
	public Response addSupply(Supply supply) throws DBException {
		logger.info(supply);
		DB.addSupply(con, supply.getName(), supply.getPrice(), supply.getType());
		this.supplysList.add(supply);
		logger.info(resourceBundle.getString("addSupply"));
		return Response.ok(supply).build();
	}

	/**
	 * Creates a new menu.
	 * @param menu
	 * @return Response ok menu.
	 * @throws DBException
	 */
	@POST
	@Path("/createMenu")
	public Response createMenu(Menu menu) throws DBException {
		logger.info(menu);
		dailymenu = new Menu(menu.getStarter(), menu.getMain(), menu.getPastry(), menu.getDrink());
		logger.info(resourceBundle.getString("createMenu"));
		return Response.ok(menu).build();
	}

	/**
	 * Displays today's menu.
	 * @return Response ok daily menu.
	 */
	@GET
	@Path("/menu")
	public Response getMenu() {
		Menu res = dailymenu;
		logger.info(resourceBundle.getString("displayMenu"));
		return Response.ok(res).build();
	}

	/**
	 * Get response.
	 * @return Response ok.
	 */
	@GET
	@Path("/response")
	public Response getResp() {
		String s = "Response";
		return Response.ok(s).build();
	}

}