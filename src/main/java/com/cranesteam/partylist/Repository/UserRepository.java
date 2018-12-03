package com.cranesteam.partylist.Repository;

import com.cranesteam.partylist.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User repository
 *
 * @author ilyaivankin
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see org.springframework.data.repository.CrudRepository
 * */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * method find user (simple crud)
     *
     * @param username login
     * @return user
     */
    User findByUsername(String username);
}
