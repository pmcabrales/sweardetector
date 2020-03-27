package es.kairosds.swear.detector;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SwearDetectorControllerIT {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void shouldReturnBadRequest() throws Exception {
		
		final ResponseEntity<Boolean> response = this.restTemplate.postForEntity("/swear", "El tigre comía lechuguino en el trigal", Boolean.class);
		final Boolean res = response.getBody();
		
		assertThat(res, is(true));
	    assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
	}
	
	@Test
	public void shouldReturnAccepted() throws Exception {
		
		final ResponseEntity<Boolean> response = this.restTemplate.postForEntity("/swear", "El tigre comía trigo en el trigal", Boolean.class);
		final Boolean res = response.getBody();
		
		assertThat(res, is(false));
	    assertThat(response.getStatusCode(), is(HttpStatus.ACCEPTED));
	}
	
}
