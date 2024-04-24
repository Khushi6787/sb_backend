package DemoExample.SpringCrud.Service.ServiceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DemoExample.SpringCrud.Entity.User;
import DemoExample.SpringCrud.Repository.UserRepository;
import DemoExample.SpringCrud.Service.UserInterface;

@Service
public class ServiceImpl implements UserInterface{
	@Autowired
	UserRepository repository;
	

	@Override
	public List<User> getAllUser() {
		return repository.findAll();
	}
	

}
