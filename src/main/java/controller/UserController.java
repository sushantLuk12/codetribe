package controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import entities.User;
import service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public ResponseEntity<String> signup(@RequestBody User user) {
		
		if(user.getGender()!=null) {
			return userService.signup(user);
		}
		return new ResponseEntity<>("Please Select Gender",HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody User user) {
		
		if(user.getEmail()!=null && user.getPassword()!=null) {
			return userService.loginUser(user);
		}
		return new ResponseEntity<>("Please enter correct Details",HttpStatus.BAD_REQUEST);
	}
	@RequestMapping(value="/recover",method=RequestMethod.POST)
	public ResponseEntity<String> recover(@RequestBody User user) {
		
		if(user.getEmail()!=null) {
			return userService.loginUser(user);
		}
		return new ResponseEntity<>("Please enter correct email",HttpStatus.BAD_REQUEST);
	}

}
