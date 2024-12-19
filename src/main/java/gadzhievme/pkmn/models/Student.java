package gadzhievme.pkmn.models;

import gadzhievme.pkmn.entities.StudentEntity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@Data
@Builder
public class Student implements Serializable {
    private String firstName;
    private String surName;
    private String familyName;
    private String group;
    public static final long serialVersionUID = 1L;

    public Student(){}
    public Student(String firstName, String surName, String familyName, String group) {
        this.firstName = firstName;
        this.surName = surName;
        this.familyName = familyName;
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", group='" + group + '\'' +
                '}';
    }

    public static Student fromEntity(StudentEntity entity) {
        if (entity != null) {
            return Student.builder()
                    .firstName(entity.getFirstName())
                    .surName(entity.getSurName())
                    .familyName(entity.getFamilyName())
                    .group(entity.getGroup())
                    .build();
        }
        return null;
    }
}
