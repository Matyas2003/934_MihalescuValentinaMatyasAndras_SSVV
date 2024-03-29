package org.example.validation;

import org.example.domain.Student;

import java.util.Objects;
import java.util.regex.Pattern;

public class StudentValidator implements Validator<Student> {

    /**
     * Valideaza un student
     *
     * @param entity - studentul pe care il valideaza
     * @throws ValidationException - daca studentul nu e valid
     */
    @Override
    public void validate(Student entity) throws ValidationException {
        try {
            if (entity.getID().equals("")) {
                throw new ValidationException("Id incorect!");
            }
            if (entity.getID() == null) {
                throw new ValidationException("Id incorect!");
            }
            if (Integer.parseInt(entity.getID()) <= 0) {
                throw new ValidationException("Id incorect!");
            }
            if (Integer.parseInt(entity.getID()) > Integer.MAX_VALUE) {
                throw new ValidationException("Id incorect!");
            }
            if (Objects.equals(entity.getNume(), "")) {
                throw new ValidationException("Nume incorect!");
            }
            if (entity.getNume() == null) {
                throw new ValidationException("Nume incorect!");
            }
            if (!Pattern.compile("^[A-Z][a-z]+ [A-Z][a-z]+(?: [A-Z][a-z]+)?$").matcher(entity.getNume()).matches()) {
                throw new ValidationException("Nume incorect!");
            }
            if (entity.getGrupa() < 100) {
                throw new ValidationException("Grupa incorecta!");
            }
            if (entity.getGrupa() > 999) {
                throw new ValidationException("Grupa incorecta!");
            }
            if (entity.getEmail().equals("")) {
                throw new ValidationException("Email incorect!");
            }
            if (entity.getEmail() == null) {
                throw new ValidationException("Email incorect!");
            }
            if (!Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$").matcher(entity.getEmail()).matches()) {
                throw new ValidationException("Email incorect!");
            }
        } catch (Exception e) {
            throw new ValidationException(e.toString());
        }
    }
}
