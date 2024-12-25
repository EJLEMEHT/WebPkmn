package gadzhievme.pkmn.service;

import gadzhievme.pkmn.dao.CardDao;
import gadzhievme.pkmn.dao.StudentDao;
import gadzhievme.pkmn.entities.CardEntity;
import gadzhievme.pkmn.entities.StudentEntity;
import gadzhievme.pkmn.models.Card;
import gadzhievme.pkmn.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentDao studentDao;

    @Override
    public Student getStudentByName(String familyName, String firstName, String patronicName) {
        return studentDao.getStudentByName(familyName, firstName, patronicName);
    }

    @Override
    public List<Student> getStudentsByGroup(String group) {
        return studentDao.getStudentsByGroup(group);
    }

    @Override
    public Student save(Student student) {
        if (studentDao.studentExists(student)) {
            throw new IllegalArgumentException("Student is already exist error");
        }
        return studentDao.save(student);
    }

    @Override
    public List<Student> getAll() {
        return studentDao.getAll();
    }
}
