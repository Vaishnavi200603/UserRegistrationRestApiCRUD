package com.MakersharksAssesment.UserRegistration.Repository;

import com.MakersharksAssesment.UserRegistration.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {
}
