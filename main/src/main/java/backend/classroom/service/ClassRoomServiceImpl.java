package backend.classroom.service;

import backend.classroom.models.ClassRoom;
import backend.classroom.repository.ClassRoomRepository;
import backend.shared.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ClassRoomServiceImpl implements ClassRoomService {

    @Autowired
    private ClassRoomRepository classRoomRepo;

    /**
     * Retrieve a single ClassRoom entity from the storage
     *
     * @param id of the requested entity
     * @return retrieved entity
     * @throws ResourceNotFoundException if there is no entity for the requested id.
     */
    @Override
    public Resource<ClassRoom> getOne(String id) {
        if(Objects.isNull(id) || id.equals(""))
            throw new IllegalArgumentException("ClassRoom id was null or empty");
        ClassRoom retrieved = classRoomRepo.findByClassName(id)
                .orElseThrow(() -> new ResourceNotFoundException(ClassRoom.class));
        return new Resource<>(retrieved);
    }
}
