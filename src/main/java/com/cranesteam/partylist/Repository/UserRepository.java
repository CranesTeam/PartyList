package com.cranesteam.partylist.Repository;

import com.cranesteam.partylist.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * User repository
 *
 * @author ilyaivankin
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see org.springframework.data.repository.CrudRepository
 * @see User
 * */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * method find user
     *
     * @param username user's login
     * @return User
     *
     * @see User
     */
    User findByUsername(String username);

    /**
     * Find user by email address
     *
     * @param email user's email
     * @return User
     *
     * @see User
     */
    User findByEmail(String email);

    /**
     * Find user by phone number
     *
     * @param number phone number
     * @return User
     *
     * @see User
     */
    User findByNumber(String number);

    /**
     * Auth method
     * Finds a person by email ot username or phone number
     *
     * @param userString login, email or number
     * @return if find user -> User
     *
     * @see User
     */
    @Query(value = "select usr from User usr where username = :userString " +
            "or email = :userString or phone_number = :userString")
    User authFindUser(@Param("userString") String userString);

}
