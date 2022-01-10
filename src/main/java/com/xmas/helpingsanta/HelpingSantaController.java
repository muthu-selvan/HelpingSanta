/**
 * 
 */
package com.xmas.helpingsanta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author muthu
 *
 * The controller class for Helping Santa.
 */
@RestController
public class HelpingSantaController {

	@Autowired HelpingSantaControllerHelper helper;
	
	@PostMapping(path = "/hoodfiller", consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Integer> minimumHoods(@RequestParam final String hood_capacity, @RequestParam final String[] present_weights) {
		return helper.getMinimumHoods(hood_capacity, present_weights);
	}
}
