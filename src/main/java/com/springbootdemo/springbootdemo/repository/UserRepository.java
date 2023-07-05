package com.springbootdemo.springbootdemo.repository;


import com.springbootdemo.springbootdemo.model.Invoice;
import com.springbootdemo.springbootdemo.model.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, String> {
    @Query("SELECT id, name FROM \"user\" where user_id = :userId")
    Iterable<User> findByUserId(@Param("userId") String userId);
}
