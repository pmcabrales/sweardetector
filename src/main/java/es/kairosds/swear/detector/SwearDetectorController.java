package es.kairosds.swear.detector;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SwearDetectorController {

	List<String> words = Arrays.asList("cantamañanas", "lechuguino", "abrazafarolas");
	
	@PostMapping("/swear")
	ResponseEntity<Boolean> hasSwearWords(@RequestBody String comment) {
		
		boolean res = (comment==null) || 
						words.parallelStream()
							.map(String::toUpperCase)
							.anyMatch(word -> comment.toUpperCase().contains(word));
		
		return res ? new ResponseEntity<>(true,HttpStatus.BAD_REQUEST) : new ResponseEntity<>(false,HttpStatus.ACCEPTED);
	
	}
}
