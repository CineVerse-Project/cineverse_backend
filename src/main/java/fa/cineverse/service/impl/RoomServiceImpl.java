package fa.cineverse.service.impl;

import fa.cineverse.model.Room;
import fa.cineverse.model.Seat;
import fa.cineverse.model.SeatType;
import fa.cineverse.model.Theater;
import fa.cineverse.repository.RoomRepository;
import fa.cineverse.service.RoomService;
import fa.cineverse.service.SeatService;
import fa.cineverse.service.SeatTypeService;

import java.time.LocalDateTime;
import java.util.List;

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
    
    @Autowired
	private SeatService seatService;
    
    @Autowired
    private SeatTypeService seatTypeService;

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 7:59:45 AM
	 * @Note: Service save a room
	 */
	@Override
	public void save(Room room) {
		this.roomRepository.save(room);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 8:00:11 AM
	 * @Note: Service list all room
	 */
	@Override
	public List<Room> listAll() {
		return (List<Room>) roomRepository.findAllActiveList();
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 8:00:34 AM
	 * @Note: Service get a room by id
	 */
	@Override
	public Room get(String roomId) {
		return roomRepository.findById(roomId).orElse(null);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 8:00:47 AM
	 * @Note: Service delete a room by id
	 */
	@Override
	public void delete(String roomId) {
		roomRepository.deleteRoom(roomId);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 3:06:53 PM
	 * @Note: update room
	 */
	@Override
	public Room updateRoom(Room room) {
		LocalDateTime now = LocalDateTime.now();
		room.setUpdatedAt(now);
		return this.roomRepository.save(room);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 2:22:27 AM
	 * @Note: create room
	 */
	@Override
	public void createRoom(Room room) {
		SeatType seatVipSeatType = seatTypeService.get("ST-0001");
		SeatType seatNormalSeatType = seatTypeService.get("ST-0002");
		LocalDateTime now = LocalDateTime.now();
		room.setCreatedAt(now);
		room.setUpdatedAt(null);
		roomRepository.save(room);
		for (int i = 1; i <= room.getSeatRowNumber(); i++) {
            for (int j = 1; j <= room.getSeatColumnNumber(); j++) {
            	Seat seat = new Seat();
            	seat.setRoom(room);
                char rowChar = (char) ('A' + i - 1);
                String seatName = (String) (rowChar +""+ j);
                if (i <= 3) {
					seat.setSeatType(seatVipSeatType);
				}else {
					seat.setSeatType(seatNormalSeatType);
				}
                seat.setSeatName(seatName);
                seatService.createSeat(seat);
            }
        }
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 2:22:35 AM
	 * @Note: find room by name
	 */
	@Override
	public Room findByRoomName(String roomName) {
		return roomRepository.findByRoomName(roomName);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 9:54:31 AM
	 * @Note: find by theater
	 */
	@Override
	public List<Room> findByTheater(Theater theater) {
		// TODO Auto-generated method stub
		return (List<Room>) roomRepository.findByTheater(theater);
	}
	
	/**
	 * @Author: DatNH20 
	 * @Day: May 23, 2023 | @Time: 9:05:00 AM
	 * @Return: void
	 * @Note: generate Seat auto
	 */
	public static void generateSeatLayout(int rows, int columns) {
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                char rowChar = (char) ('A' + i - 1);
                System.out.print(rowChar + "" + j + " ");
            }
           
        }
    }
}
