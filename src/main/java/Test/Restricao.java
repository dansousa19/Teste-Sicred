package Test;

import static io.restassured.RestAssured.given;
import org.junit.Test;

public class Restricao {
	
		@Test
		public void consultaRestricao() {
		given()
		
		.when()
			.get("http://localhost:8080/api/v1/restricoes/58063164083")
		.then()
			.log().all()
			.statusCode(200)
			;
	}
}
