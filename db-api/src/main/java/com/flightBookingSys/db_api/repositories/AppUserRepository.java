package com.flightBookingSys.db_api.repositories;

import com.flightBookingSys.db_api.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, UUID> {

    @Query(nativeQuery = true, value = "select * from app_user where user_type = :user_type")
    public List<AppUser> findByUserType(String user_type);

    @Query(nativeQuery = true, value = "SELECT * FROM app_user WHERE email = :email")
    public  AppUser findUserByEmail(String email);
}
