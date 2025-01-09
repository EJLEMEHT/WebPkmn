package gadzhievme.pkmn.dao;

import gadzhievme.pkmn.entities.CardEntity;
import gadzhievme.pkmn.entities.StudentEntity;
import gadzhievme.pkmn.models.Card;
import gadzhievme.pkmn.models.Student;
import gadzhievme.pkmn.repository.StudentEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StudentDao {
    private final StudentEntityRepository studentEntityRepository;

    @SneakyThrows
    public Student getStudentByName(String patronicName, String firstName, String familyName) {
        return Student.fromEntity(studentEntityRepository.findBySurNameAndFirstNameAndFamilyName(patronicName, firstName, familyName).orElseThrow(
                () -> new UserPrincipalNotFoundException("Student" +
                        "with name " + familyName + " " + firstName
                        + " " + patronicName + " not found")
        ));
    }

    @SneakyThrows
    public UUID getStudentIdByName(String patronicName, String firstName, String familyName) {
        return studentEntityRepository.findBySurNameAndFirstNameAndFamilyName(patronicName, firstName, familyName).orElseThrow(
                () -> new UserPrincipalNotFoundException("Student" +
                        "with name " + familyName + " " + firstName
                        + " " + patronicName + " not found")
        ).getId();
    }

    @SneakyThrows
    public Student getStudent(String patronicName, String firstName, String familyName, String group) {
        return Student.fromEntity(studentEntityRepository.findBySurNameAndFirstNameAndFamilyNameAndGroup(patronicName, firstName, familyName, group).orElseThrow(
                () -> new UserPrincipalNotFoundException("Student" +
                        "with name " + familyName + " " + firstName
                        + " " + patronicName + " not found")
        ));
    }

    @SneakyThrows
    public UUID getStudentId(String patronicName, String firstName, String familyName, String group) {
        return studentEntityRepository.findBySurNameAndFirstNameAndFamilyNameAndGroup(patronicName, firstName, familyName, group).orElseThrow(
                () -> new UserPrincipalNotFoundException("Student" +
                        "with name " + familyName + " " + firstName
                        + " " + patronicName + " not found")
        ).getId();
    }

    @SneakyThrows
    public List<Student> getStudentsByGroup(String group) {
        return studentEntityRepository.findByGroup(group)
        .stream().map(Student::fromEntity).toList();
    }

    @SneakyThrows
    public Student save(Student student) {
        return Student.fromEntity(studentEntityRepository.save(StudentEntity.fromDTO(student)));
    }

    @SneakyThrows
    public List<Student> getAll() {
        return studentEntityRepository.findAll().stream().map(Student::fromEntity).toList();
    }

    public boolean studentExists(Student student) {
        return studentEntityRepository.existsByFirstNameAndSurNameAndFamilyName(student.getFirstName(),
                student.getSurName(),
                student.getFamilyName());
    }
}
