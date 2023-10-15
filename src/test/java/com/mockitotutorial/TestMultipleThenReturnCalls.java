package com.mockitotutorial;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mockitotutorial.happyhotel.booking.BookingDAO;
import com.mockitotutorial.happyhotel.booking.BookingService;
import com.mockitotutorial.happyhotel.booking.MailSender;
import com.mockitotutorial.happyhotel.booking.PaymentService;
import com.mockitotutorial.happyhotel.booking.Room;
import com.mockitotutorial.happyhotel.booking.RoomService;

public class TestMultipleThenReturnCalls {

        private BookingService bookingService;
    private PaymentService paymentServiceMock;
    private RoomService roomServiceMock;
    private BookingDAO bookingDAOMock;
    private MailSender mailSenderMock;

    @BeforeEach
    void setup() {
        this.paymentServiceMock = mock(PaymentService.class);
        this.roomServiceMock = mock(RoomService.class);
        this.bookingDAOMock = mock(BookingDAO.class);
        this.mailSenderMock = mock(MailSender.class);
        this.bookingService = new BookingService(
                paymentServiceMock,
                roomServiceMock,
                bookingDAOMock,
                mailSenderMock
        );

    }
    

        @Test
    void getAvailablePlaceCountWhenMultipleRoomsAvailable() {
        // List<Room> roomList = new ArrayList<>();
        // roomList.add(new Room("Room 1", 2));
        // roomList.add(new Room("Room 2", 4));
        // roomList.add(new Room("Room 3", 6));
        // given
        List<Room> rooms = Arrays.asList(new Room("Roome 1",2), new Room("Room 2",4 ));
        when(this.roomServiceMock.getAvailableRooms()).thenReturn(rooms);
        int expected = 6;
        // when
        int actual = bookingService.getAvailablePlaceCount();
        // then
        assertEquals(expected, actual);
    }
}
