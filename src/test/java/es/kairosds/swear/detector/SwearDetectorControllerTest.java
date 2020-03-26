package es.kairosds.swear.detector;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(SwearDetectorController.class)
public class SwearDetectorControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void hasSwearWords_without_swear_word_in_comment() throws Exception {
		
		String comment = "El tigre comía trigo en el trigal";
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/swear")
				.content(comment)
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)
				.andExpect(status().isAccepted())
				.andReturn();

	}
	
	@Test
	public void hasSwearWords_with_swear_word_in_comment() throws Exception {
		
		String comment = "El tigre comía lechuguino en el trigal";
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/swear")
				.content(comment)
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)
				.andExpect(status().isBadRequest())
				.andReturn();

	}
}
