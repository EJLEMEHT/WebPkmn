package gadzhievme.pkmn.repository;

import gadzhievme.pkmn.entities.CardEntity;
import gadzhievme.pkmn.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentEntityRepository extends JpaRepository<StudentEntity, UUID> {
    Optional<StudentEntity> findBySurNameAndFirstNameAndFamilyNameAndGroup(String surName, String firstName, String familyName, String group);
    Optional<StudentEntity> findBySurNameAndFirstNameAndFamilyName(String surName, String firstName, String familyName);
    List<StudentEntity> findByGroup(String group);
    boolean existsByFirstNameAndSurNameAndFamilyName(String firstName, String surName, String familyName);
}
