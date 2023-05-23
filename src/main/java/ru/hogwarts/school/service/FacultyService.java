package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private long counterId = 0;
    private final Map<Long, Faculty> faculties = new HashMap<>();

    public Faculty add(Faculty faculty) {
        faculty.setId(++counterId);
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Optional<Faculty> update(Long id, Faculty faculty) {
        if (faculties.containsKey(id)) {
            faculties.replace(id, faculty);
            return Optional.of(faculty);
        }
        return Optional.empty();
    }

    public Optional<Faculty> getById(Long id) {
        return Optional.ofNullable(faculties.get(id));
    }

    public Collection<Faculty> getAll() {
        return Collections.unmodifiableCollection(faculties.values());
    }

    public Optional<Faculty> deleteById(Long id) {
        return Optional.ofNullable(faculties.remove(id));
    }

    public Collection<Faculty> getAllByColor(String color) {
        return faculties.values().stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
