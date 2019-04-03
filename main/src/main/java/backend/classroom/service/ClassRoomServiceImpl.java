package backend.classroom.service;

import backend.classroom.models.ClassRoom;
import backend.classroom.repository.ClassRoomRepository;
import backend.shared.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

@Service
public class ClassRoomServiceImpl implements ClassRoomService {

    private final ClassRoomRepository classRoomRepo;

    @Autowired
    public ClassRoomServiceImpl(ClassRoomRepository classRoomRepo) {
        this.classRoomRepo = classRoomRepo;
    }

    private void throwIfNotPositive(long id) throws IllegalArgumentException {
        if(id <= 0) throw new IllegalArgumentException("ClassRoom id was zero or negative");
    }

    /**
     * Retrieve a single ClassRoom entity from the storage
     *
     * @param id of the requested entity
     * @return retrieved entity
     * @throws IllegalArgumentException if the specified id was zero or negative.
     * @throws ResourceNotFoundException if there is no entity for the requested id.
     */
    @Override
    public Resource<ClassRoom> getOne(long id) {
        throwIfNotPositive(id);
        ClassRoom retrieved = classRoomRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ClassRoom.class));
        return new Resource<>(retrieved);
    }

    /**
     * Delete a single ClassRoom entity.
     *
     * @param id of the specified entity.
     * @throws IllegalArgumentException if the specified id was zero or negative.
     * @throws ResourceNotFoundException if there is no entity for the requested id.
     */
    @Override
    public void delete(long id) {
        throwIfNotPositive(id);
        try {
            classRoomRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(ClassRoom.class);
        }
    }
}
