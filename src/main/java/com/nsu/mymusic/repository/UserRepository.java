package com.nsu.mymusic.repository;

import com.nsu.mymusic.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
   Optional<Users> findByUsername(String username);

   @Query("SELECT u FROM Users u JOIN u.roles r WHERE r.roleName = :roleName")
   Optional<Users> findByRoleName(@Param("roleName") String roleName);

}
