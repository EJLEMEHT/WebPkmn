package gadzhievme.pkmn.service;

import gadzhievme.pkmn.entities.StudentEntity;
import gadzhievme.pkmn.models.Student;

import java.util.List;

public interface StudentService {
    Student getStudentByName(String familyName, String firstName, String patronicName);

    List<Student> getStudentsByGroup(String group);

    Student save(Student student);

    List<Student> getAll();
}
