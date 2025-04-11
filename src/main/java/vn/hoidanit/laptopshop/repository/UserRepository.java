package vn.hoidanit.laptopshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.User;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByEmail(String email);

    User findFirstByEmail(String email);

    List<User> findByEmailAndAddress(String email, String address);

    User findById(long id);

    boolean existsByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);

    Page<User> findAll(Pageable pageable);

}
