package com.eventmanegement.event.service;

import com.eventmanegement.event.dto.*;

import java.awt.print.Pageable;
import java.util.List;

public interface EventService {

    public EventDto addEvent(EventDto event);

    public PageableResponse<EventDto> getAllEventBooking(int pageNumber, int pageSize, String sortBy, String sortDir);


    public TicketDto RegisterUserForEvent(String eventId, String userId);

    TicketDto getTicketByTicketId(String ticketId);


//    public void deleteEventWithUserId(String userId);
//
//    public void deleteEventByEventId(String eventId);

    public ViewBookingRequest viewBookingWithUser(String eventId);

}
