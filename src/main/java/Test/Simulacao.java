package Test;

import static io.restassured.RestAssured.given;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import io.restassured.http.ContentType;
import users.User;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class Simulacao {
	private static int id;
	private static String cpf;
	
		@Test
		public void t01_criarSimulacao() {
			cpf = given()
				.body("{\r\n"
						+ "  \"nome\": \"Fulano de Tal\",\r\n"
						+ "  \"cpf\": 84809766081,\r\n"
						+ "  \"email\": \"email@email.com\",\r\n"
						+ "  \"valor\": 1200,\r\n"
						+ "  \"parcelas\": 3,\r\n"
						+ "  \"seguro\": true\r\n"
						+ "}")
				.contentType(ContentType.JSON)
			.when()
				.post("http://localhost:8080/api/v1/simulacoes")
			.then()
				.log().all()
				.statusCode(201)
				.extract().body().path("cpf")
				;
			
		
	}
		@Test
		public void t02_retornoSimulacao() {
				id = given()
					.contentType(ContentType.JSON)
				.when()
					.get("http://localhost:8080/api/v1/simulacoes/" + cpf)
				.then()
				.log().all()
					.statusCode(200)
					.extract().body().path("id")
				;
		}
		
		@Test
		public void t03_alterarSimulacao() {
				given()
					.body("{\r\n"
							+ "  \"nome\": \"Fulano de Tal\",\r\n"
							+ "  \"cpf\": 84809766082,\r\n"
							+ "  \"email\": \"email@email.com\",\r\n"
							+ "  \"valor\": 1200,\r\n"
							+ "  \"parcelas\": 3,\r\n"
							+ "  \"seguro\": true\r\n"
							+ "}")
					.contentType(ContentType.JSON)
				.when()
					.put("http://localhost:8080/api/v1/simulacoes/" + cpf)
				.then()
					.log().all()
					.statusCode(200)
					;
		}
		
		@Test
		public void t04_DeleteSimulacao() {
				given()
					.contentType(ContentType.JSON)
				.when()
					.delete("http://localhost:8080/api/v1/simulacoes/" + id)
				.then()
				.log().all()
					.statusCode(200)
				;

}
		
}
