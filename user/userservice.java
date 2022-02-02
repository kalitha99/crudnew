package com.example.democrud.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class userservice {
    @Autowired
    private userrepository repo;
    public List<user> listAll() {

        return  (List<user>) repo.findAll();
    }

    public void save(user user) {
        repo.save(user);

    }

    public user get (Integer id) throws UserNotFoundException {
        Optional<user> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("no users for the id"+id);
    }

    public void delete(Integer id) {
        repo.deleteById(id);

    }
}

