package controller;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import dao.UserDao;
import entities.User;
import service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserDao userDao;
	
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public ResponseEntity<String> signup(@RequestBody User user ) {
        
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
	
	
	@PostMapping("/profileUpload")
    public ResponseEntity<String> profileUpload( @RequestParam("image") MultipartFile file) throws IOException {
     

             byte[] imageByte = file.getBytes();
               
                if(imageByte != null) {
                User user=new User();
                user.setImage(imageByte);
                userDao.save(user);
    			return ResponseEntity.ok("Image is Uploaded");

                }
 
		return new ResponseEntity<>("Please upload image again",HttpStatus.BAD_REQUEST);
}
}