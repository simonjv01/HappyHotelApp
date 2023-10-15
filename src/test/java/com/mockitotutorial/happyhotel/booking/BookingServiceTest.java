package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {

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
    void getAvailablePlaceCount() {
        // given
        int expected = 0;
        // when
        int actual = bookingService.getAvailablePlaceCount();
        // then
        assertEquals(expected, actual);
    }

    @Test
    void getAvailablePlaceCountWithValuesOneRoom() {
        // given
        when(this.roomServiceMock.getAvailableRooms()).thenReturn(Collections.singletonList(new Room("Room 1", 2)));
        int expected = 2;
        // when
        int actual = bookingService.getAvailablePlaceCount();
        // then
        assertEquals(expected, actual);
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

    @Test
    void should_CalculateCorrectPrice_When_CorrectInput() {
        // given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 1, 1),
                LocalDate.of(2020, 1, 5), 2, false);
        double expected = 4 * 2 * 50.0;
        // when
        double actual = bookingService.calculatePrice(bookingRequest);

        // then
        assertEquals(expected, actual);
    }
    @Test
    void calculatePriceEuro() {
    }

    @Test
    void makeBooking() {
    }

    @Test
    void cancelBooking() {
    }
}