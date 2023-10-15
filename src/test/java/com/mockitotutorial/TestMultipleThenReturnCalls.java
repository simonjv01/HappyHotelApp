package com.mockitotutorial;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
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
    void should_CountAvailablePlaces_When_CalledMultipleTimes() {

        // given
        when(this.roomServiceMock.getAvailableRooms())
        .thenReturn(Collections.singletonList(new Room("Room 1",5)))
        .thenReturn(Collections.emptyList());
        int expectedFirstCall = 5;
        int expectedSecondCall = 0;
        // when
        int actualFirst = bookingService.getAvailablePlaceCount();
        int actualSecond = bookingService.getAvailablePlaceCount();
        // then
        assertAll(
            () -> assertEquals(expectedFirstCall, actualFirst),
            () -> assertEquals(expectedSecondCall, actualSecond));

      
    }
}
