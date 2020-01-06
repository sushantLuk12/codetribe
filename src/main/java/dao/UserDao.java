package dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entities.User;


@Repository
@Transactional
public interface UserDao extends JpaRepository<User, Integer>{

	public User findByEmail(String email);
	public User findByGender(String gender);
	public User findByUserName(String name);
	public User findByEmailAndPassword(String email,String password);
}

