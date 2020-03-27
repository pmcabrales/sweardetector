package es.kairosds.swear.detector;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(SwearDetectorController.class)
public class SwearDetectorControllerTest {

	private SwearDetectorController swearDetectorControllerSUT;
	private String swearComment;
	private String politeComment;
	
	@Before
    public void init() {
		swearDetectorControllerSUT = new SwearDetectorController();
		politeComment = "El tigre comía trigo en el trigal";
		swearComment = "El tigre comía lechuguino en el trigal";
    }

	
	@Test
    public void noSwearWords() {

        ResponseEntity<Boolean> response = swearDetectorControllerSUT.hasSwearWords(politeComment);

        assertThat(response.getBody(), is(false));
    }
	
	@Test
    public void swearWords() {

        ResponseEntity<Boolean> response = swearDetectorControllerSUT.hasSwearWords(swearComment);

        assertThat(response.getBody(), is(true));
    }
	
	@Test
    public void nullComment() {

        ResponseEntity<Boolean> response = swearDetectorControllerSUT.hasSwearWords(null);

        assertThat(response.getBody(), is(true));
    }


}
