package gadzhievme.pkmn.entities;

import gadzhievme.pkmn.models.Student;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "students")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentEntity {
    @Id
    private UUID id;
    private String firstName;
    private String surName;
    private String familyName;
    @Column(name = "\"group\"")
    private String group;
    public static StudentEntity fromDTO(Student student) {
        if (student != null) {
            return StudentEntity.builder()
                    .id(UUID.randomUUID())
                    .firstName(student.getFirstName())
                    .surName(student.getSurName())
                    .familyName(student.getFamilyName())
                    .group(student.getGroup())
                    .build();
        }
        return null;
    }
}
