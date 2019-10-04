package app.services;

import com.google.common.base.Strings;
import app.exceptions.*;
import app.models.*;
import app.repositories.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceFirstImplements implements UserService {
    private UserRepository repository;

    @Autowired
    public UserServiceFirstImplements(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getByID(String id) {
        if (Strings.isNullOrEmpty(id))
            throw new IllegalIDException("Null can't be applied!");
        UUID uuid = UUID.fromString(id);
        User foundUser = repository.findByID(uuid);
        if (Objects.isNull(foundUser))
            throw new UserNotFoundException("User is not found!");
        return foundUser;
    }

    @Override
    public User save(User user) {
        if (Strings.isNullOrEmpty(user.getUsername()))
            throw new IllegalUsernameException("Wrong user name!");
        if (Strings.isNullOrEmpty(user.getMd5Password()))
            throw new IllegalPasswordException("Wrong password!");
        return repository.save(user);
    }

    @Override
    public Set<User> getAll(){
        return repository.findAll();
    }
}