package org.example;

import org.junit.Test;
import org.junit.BeforeClass;
import org.example.domain.Student;
import org.example.repository.NotaXMLRepo;
import org.example.repository.StudentXMLRepo;
import org.example.repository.TemaXMLRepo;
import org.example.service.Service;
import org.example.validation.NotaValidator;
import org.example.validation.StudentValidator;
import org.example.validation.TemaValidator;
import org.example.validation.ValidationException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AppTest {

    public static Service service;

    @BeforeClass
    public static void setup() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        AppTest.service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }
    // ID tests
    @Test
    public void addStudent_ID_Zero() {
        assertThrows(ValidationException.class, () -> {
            service.addStudent(new Student("0", "John Smith", 934, "john@example.com"));
        });
    }

    @Test
    public void addStudent_ID_MaxInt() {
        assertDoesNotThrow(() -> {
            service.addStudent(new Student(Integer.toString(Integer.MAX_VALUE), "John Smith", 934, "john@example.com"));
            service.deleteStudent(Integer.toString(Integer.MAX_VALUE));
        });
    }

    @org.junit.jupiter.api.Test
    public void addStudent_ID_MinusOne() {
        assertThrows(ValidationException.class, () -> {
            service.addStudent(new Student("-1", "John Smith", 934, "john@example.com"));
        });
    }

    @org.junit.jupiter.api.Test
    public void addStudent_ID_One() {
        assertDoesNotThrow(() -> {
            service.addStudent(new Student("1", "John Smith", 934, "john@example.com"));
            service.deleteStudent("1");
        });
    }

    @org.junit.jupiter.api.Test
    public void addStudent_ID_MaxIntMinusOne() {
        assertDoesNotThrow(() -> {
            service.addStudent(new Student(Integer.toString(Integer.MAX_VALUE - 1), "John Smith", 934, "john@example.com"));
            service.deleteStudent(Integer.toString(Integer.MAX_VALUE - 1));
        });
    }

    @org.junit.jupiter.api.Test
    public void addStudent_ID_MaxIntPlusOne() {
        assertThrows(ValidationException.class, () -> {
            service.addStudent(new Student(Integer.toString(Integer.MAX_VALUE + 1), "John Smith", 934, "john@example.com"));
        });
    }

    // Name tests
    @org.junit.jupiter.api.Test
    public void addStudent_Name_AaA() {
        assertDoesNotThrow(() -> {
            service.addStudent(new Student("1", "Aa Aa", 934, "john@example.com"));
            service.deleteStudent("1");
        });
    }

    @org.junit.jupiter.api.Test
    public void addStudent_Name_AaA_A() {
        assertThrows(ValidationException.class, () -> {
            service.addStudent(new Student("1", "Aa Aa-A", 934, "john@example.com"));
        });
    }

    @org.junit.jupiter.api.Test
    public void addStudent_Name_A() {
        assertThrows(ValidationException.class, () -> {
            service.addStudent(new Student("1", "Aa", 934, "john@example.com"));
        });
    }

    @org.junit.jupiter.api.Test
    public void addStudent_Name_Empty() {
        assertThrows(ValidationException.class, () -> {
            service.addStudent(new Student("1", "", 934, "john@example.com"));
        });
    }

    @org.junit.jupiter.api.Test
    public void addStudent_Name_1a() {
        assertThrows(ValidationException.class, () -> {
            service.addStudent(new Student("1", "1a", 934, "john@example.com"));
        });
    }

    @org.junit.jupiter.api.Test
    public void addStudent_Name_1() {
        assertThrows(ValidationException.class, () -> {
            service.addStudent(new Student("1", "1", 934, "john@example.com"));
        });
    }

    // Group tests
    @org.junit.jupiter.api.Test
    public void addStudent_Group_100() {
        assertDoesNotThrow(() -> {
            service.addStudent(new Student("1", "John Smith", 100, "john@example.com"));
            service.deleteStudent("1");
        });
    }

    @org.junit.jupiter.api.Test
    public void addStudent_Group_101() {
        assertDoesNotThrow(() -> {
            service.addStudent(new Student("1", "John Smith", 101, "john@example.com"));
            service.deleteStudent("1");
        });
    }

    @org.junit.jupiter.api.Test
    public void addStudent_Group_999() {
        assertDoesNotThrow(() -> {
            service.addStudent(new Student("1", "John Smith", 999, "john@example.com"));
            service.deleteStudent("1");
        });
    }

    @org.junit.jupiter.api.Test
    public void addStudent_Group_998() {
        assertDoesNotThrow(() -> {
            service.addStudent(new Student("1", "John Smith", 998, "john@example.com"));
            service.deleteStudent("1");
        });
    }

    @org.junit.jupiter.api.Test
    public void addStudent_Group_99() {
        assertThrows(ValidationException.class, () -> {
            service.addStudent(new Student("1", "John Smith", 99, "john@example.com"));
        });
    }

    @org.junit.jupiter.api.Test
    public void addStudent_Group_1000() {
        assertThrows(ValidationException.class, () -> {
            service.addStudent(new Student("1", "John Smith", 1000, "john@example.com"));
        });
    }

    @org.junit.jupiter.api.Test
    public void addStudent_Group_Zero() {
        assertThrows(ValidationException.class, () -> {
            service.addStudent(new Student("1", "John Smith", 0, "john@example.com"));
        });
    }

    // Email tests
    @org.junit.jupiter.api.Test
    public void addStudent_Email_A_Valid() {
        assertDoesNotThrow(() -> {
            service.addStudent(new Student("1", "John Smith", 934, "a@a.com"));
            service.deleteStudent("1");
        });
    }

    @org.junit.jupiter.api.Test
    public void addStudent_Email_AtA_Invalid() {
        assertThrows(ValidationException.class, () -> {
            service.addStudent(new Student("1", "John Smith", 934, "@a.com"));
        });
    }

    @org.junit.jupiter.api.Test
    public void addStudent_Email_AAt_Invalid() {
        assertThrows(ValidationException.class, () -> {
            service.addStudent(new Student("1", "John Smith", 934, "a@a."));
        });
    }

    @org.junit.jupiter.api.Test
    public void addStudent_Email_A_Invalid() {
        assertThrows(ValidationException.class, () -> {
            service.addStudent(new Student("1", "John Smith", 934, "a"));
        });
    }

    @Test
    public void addStudent_Email_Empty() {
        assertThrows(ValidationException.class, () -> {
            service.addStudent(new Student("1", "John Smith", 934, ""));
        });
    }
}