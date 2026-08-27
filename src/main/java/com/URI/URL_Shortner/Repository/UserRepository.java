package com.URI.URL_Shortner.Repository;

import com.URI.URL_Shortner.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
//import java.lang.ScopedValue;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
//    Optional<User> findByProviderIdAndProviderType(String providerId, String providerType);

}
