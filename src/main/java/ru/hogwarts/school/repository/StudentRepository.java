package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByAge(int age);

    List<Student> findAllByAgeBetween(int minAge, int maxAge);

    List<Student> findAll();

    @Query(value = "Select COUNT(*) FROM student", nativeQuery = true)
    Integer getCountAllStudents();

    @Query(value = "Select AVG(age) FROM student", nativeQuery = true)
    double getAverageAge();

    @Query(value = "Select * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Student> getLastFiveStudents();

    List<Student> findAllByName(String name);
}
