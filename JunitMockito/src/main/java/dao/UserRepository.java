package dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import model.User;

public interface UserRepository extends MongoRepository<User, Integer> {

	List<User> findByAddress(String address);
}
