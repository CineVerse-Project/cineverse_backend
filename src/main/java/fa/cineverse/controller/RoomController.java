package fa.cineverse.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;

import fa.cineverse.dto.RoomDTO;

import fa.cineverse.model.Room;

import fa.cineverse.service.RoomService;
import net.bytebuddy.asm.Advice.Return;

@RestController
@RequestMapping("/room")
public class RoomController {
	@Autowired
	private RoomService roomService;

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 2:16:01 PM
	 * @Return: ResponseEntity<?>
	 * @Note: list all room and check if that list empty
	 */
	@GetMapping("")
	public ResponseEntity<?> findAll() {
		List<Room> roomList = roomService.listAll();
		if (roomList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(roomList, HttpStatus.OK);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 2:14:54 PM
	 * @Return: ResponseEntity<?>
	 * @Note: create a room and check validate input
	 */
	@PostMapping("")
	public ResponseEntity<?> createRoom(@Validated @RequestBody RoomDTO roomDTO, BindingResult bindingResult) {
		List<Room> roomOfTheateRooms = roomService.findByTheater(roomDTO.getTheater());
		if (bindingResult.hasFieldErrors()) {
			return new ResponseEntity<>(bindingResult.getFieldErrors(), HttpStatus.BAD_REQUEST);
		}

		for (Room room : roomOfTheateRooms) {
			if (roomDTO.getRoomName().equals(room.getRoomName())) {
				System.out.println("2");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
		System.out.println(roomDTO.getTheater().getTheaterId());
		Room room = new Room();
		BeanUtils.copyProperties(roomDTO, room);
		roomService.createRoom(room);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 2:13:48 PM
	 * @Return: ResponseEntity<Province>
	 * @Note: delete by id check id exited or not if it does then delete it(transfer
	 *        to 1)
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<Room> deleteRoom(@PathVariable("id") String id) {
		Room room = this.roomService.get(id);
		if (room == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			if ("".equals(id) || "null".equals(id)) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			this.roomService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 4:17:55 PM
	 * @Return: ResponseEntity<?>
	 * @Note: update room moi
	 */
	@PatchMapping(value = "/{roomId}")
	public ResponseEntity<?> updateRoom(@PathVariable String roomId, @Validated @RequestBody RoomDTO roomDTO,
			BindingResult bindingResult) {
		List<Room> roomOfTheateRooms = roomService.findByTheater(roomDTO.getTheater());
		if (bindingResult.hasFieldErrors()) {
			return new ResponseEntity<>(bindingResult.getFieldErrors(), HttpStatus.BAD_REQUEST);
		}
		Room roomOptional = roomService.get(roomId);
		if (roomOptional == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Room roomUpdate = null;
		System.out.println(roomOptional.getTheater().getTheaterId().equals(roomDTO.getTheater().getTheaterId()));
		if (roomOptional.getRoomName().equals(roomDTO.getRoomName())
				&& roomOptional.getTheater().getTheaterId().equals(roomDTO.getTheater().getTheaterId())) {
			roomOptional.setRoomId(roomId);
			BeanUtils.copyProperties(roomDTO, roomOptional);
			roomUpdate = this.roomService.updateRoom(roomOptional);
			System.out.println("2");
			return new ResponseEntity<>(roomUpdate, HttpStatus.OK);
		} else {
			for (Room room : roomOfTheateRooms) {
				if (roomDTO.getRoomName().equals(room.getRoomName())) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			}
		}
		roomOptional.setRoomId(roomId);
		BeanUtils.copyProperties(roomDTO, roomOptional);
		roomUpdate = this.roomService.updateRoom(roomOptional);
		return new ResponseEntity<>(roomUpdate, HttpStatus.OK);
	}

}
