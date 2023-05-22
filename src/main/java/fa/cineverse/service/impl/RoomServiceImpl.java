package fa.cineverse.service.impl;

import fa.cineverse.model.Room;
import fa.cineverse.repository.RoomRepository;
import fa.cineverse.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room findById(String roomId) {
        return roomRepository.findById(roomId).orElse(null);
    }
}
