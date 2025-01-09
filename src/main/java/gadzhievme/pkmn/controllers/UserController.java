package gadzhievme.pkmn.controllers;

import gadzhievme.pkmn.models.Card;
import gadzhievme.pkmn.models.Student;
import gadzhievme.pkmn.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final StudentService studentService;

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.getAll();
    }

    @GetMapping("/{group}")
    public List<Student> getStudentsFromGroup(@PathVariable String group) {
        return studentService.getStudentsByGroup(group);
    }

    @PostMapping("")
    public Student saveStudent(@RequestBody Student student) {
        return studentService.save(student);
    }
}
