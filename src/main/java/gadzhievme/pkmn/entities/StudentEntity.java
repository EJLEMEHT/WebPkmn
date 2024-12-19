package gadzhievme.pkmn.entities;

import gadzhievme.pkmn.models.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "students")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity {
    @Id
    private UUID id;
    private String firstName;
    private String surName;
    private String familyName;
    private String group;
    public static StudentEntity fromDTO(Student student) {
        if (student != null)
            return StudentEntity.builder()
                    .id(UUID.randomUUID())
                    .firstName(student.getFirstName())
                    .surName(student.getSurName())
                    .familyName(student.getFamilyName())
                    .group(student.getGroup())
                    .build();
        return null;
    }
}
