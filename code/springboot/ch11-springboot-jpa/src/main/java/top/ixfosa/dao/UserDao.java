package top.ixfosa.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import top.ixfosa.domain.User;

public interface UserDao extends JpaRepository<User, Integer> {
}
