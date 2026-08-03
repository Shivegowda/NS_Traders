package com.traders.nst.persistance.repository;

import com.traders.nst.persistance.entity.UserDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends CrudRepository<UserDetails, Integer> {

 UserDetails findByUserName(String userName);
}
