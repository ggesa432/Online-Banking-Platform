package com.synergisticit.repository;

import com.synergisticit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author GesangZeren
 * @project OnlineBanking
 * @date 12/11/2024
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findByUsername(@Param("username")String username);

    User findTopByOrderByUserIdDesc();
    List<User> findAllByOrderByUsernameAsc();
}
