package com.example.hoda_jatte_anissa.Repository;

import com.example.hoda_jatte_anissa.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
