package service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import dao.UserDao;
import entities.User;


@Service
public class UserService {

	@Autowired
	UserDao userDao;
	
	public ResponseEntity<String> signup(User user) {
		
		User email=userDao.findByEmail(user.getEmail());
		User username = userDao.findByUserName(user.getUserName());
		
		if(email==null && username==null){
			userDao.save(user);
			return ResponseEntity.ok("User is Saved");
		}
		return new ResponseEntity<>("Profile must be unique",HttpStatus.BAD_REQUEST);
	}
	
	
	public ResponseEntity<String> loginUser(User user) {

		User usr=userDao.findByEmailAndPassword(user.getEmail(),user.getPassword());
		
		if(usr!=null){
			return ResponseEntity.ok("User is LoggedIn");
		}
		return new ResponseEntity<>("Please enter Valid Details",HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<String> recover(User user) {
		
		User usr=userDao.findByEmail(user.getEmail());
		
		if(usr!=null){
			usr.setPassword(user.getPassword());
			userDao.save(usr);
			return ResponseEntity.ok("User is LoggedIn");
		}
		return new ResponseEntity<>("Please enter Valid Details",HttpStatus.BAD_REQUEST);
	}
}
