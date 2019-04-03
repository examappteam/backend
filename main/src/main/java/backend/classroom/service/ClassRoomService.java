package backend.classroom.service;

import backend.classroom.models.ClassRoom;
import backend.shared.exceptions.ResourceNotFoundException;
import org.springframework.hateoas.Resource;

public interface ClassRoomService {
    /**
     * Retrieve a single ClassRoom entity from the storage
     *
     * @param id of the requested entity
     * @return retrieved entity
     * @throws IllegalArgumentException if the specified id was zero or negative.
     * @throws ResourceNotFoundException if there is no entity for the requested id.
     */
    Resource<ClassRoom> getOne(long id);

    /**
     * Delete a single ClassRoom entity.
     * @param id of the specified entity.
     * @throws IllegalArgumentException if the specified id was zero or negative.
     * @throws ResourceNotFoundException if there is no entity for the requested id.
     */
    void delete(long id);
}
