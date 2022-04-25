
import org.junit.Test;
import java.server.sql.Server;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ServerTest {
    private Client client;
    private WebTarget webTarget;
    

    @Before
    public void setUp() {
    	client = ClientBuilder.newClient();
    	webTarget = client.target(String.format("http://%s:%s/rest", '127.0.0.1', '8080'));
    }

    @Test
    public void testGetResp(){
    	 WebTarget donationsWebTarget = webTarget.path("users/response");
         Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
         Response response = invocationBuilder.get();
         assertNotNull("The connection failed.", response);
    }
    
    @Test
    public void testAddBook(){
    	 WebTarget donationsWebTarget = webTarget.path("users/addBook");
         Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
         Response response = invocationBuilder.post();
         assertNotNull("The connection failed.", response);
    }
    
    @Test
    public void testGetBooks(){
    	 WebTarget donationsWebTarget = webTarget.path("users/books");
         Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
         Response response = invocationBuilder.get();
    }
    
    @Test
    public void testCreateUser(){
    	 WebTarget donationsWebTarget = webTarget.path("users/createUser");
         Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
         Response response = invocationBuilder.post();
         assertNotNull("The connection failed.", response);
    }
    
//    @Test
//    public void testGetUserById(){
//    	 WebTarget donationsWebTarget = webTarget.path("users/addBook");
//         Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
//         Response response = invocationBuilder.get();
//   	   assertNotNull("The connection failed.", response);
//    }
}