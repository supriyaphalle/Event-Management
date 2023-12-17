package com.eventmanegement.event.service.impl;

import com.eventmanegement.event.constants.AppConstants;
import com.eventmanegement.event.dto.EventDto;
import com.eventmanegement.event.dto.PageableResponse;
import com.eventmanegement.event.dto.TicketDto;
import com.eventmanegement.event.entities.Event;
import com.eventmanegement.event.entities.Ticket;
import com.eventmanegement.event.entities.User;
import com.eventmanegement.event.exception.ResourceNotFoundException;
import com.eventmanegement.event.healper.Healper;
import com.eventmanegement.event.repository.EventRepository;
import com.eventmanegement.event.repository.TicketRepository;
import com.eventmanegement.event.repository.UserRepository;
import com.eventmanegement.event.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper mapper;


    @Override
    public EventDto addEvent(EventDto eventDto) {
        String id = UUID.randomUUID().toString();

        Event event = mapper.map(eventDto, Event.class);
        event.setDate(new Date());

        event.setEventId(id);
        Event save = eventRepository.save(event);
        return mapper.map(save, EventDto.class);


    }

    @Override
    public PageableResponse<EventDto> getAllEventBooking(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Event> allPage = eventRepository.findAll(pageable);
        PageableResponse<EventDto> response = Healper.getPageableResponse(allPage, EventDto.class);
        System.out.println(response.getContent());
        return response;

    }

    @Override
    public TicketDto RegisterUserForEvent(String eventId, String userId) {
        Event event1 = this.eventRepository.findById(eventId).orElseThrow(() -> new ResourceNotFoundException(AppConstants.NOT_FOUND));
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(AppConstants.USER_NOT_FOUND));
//        user.setEvent(event1);

        String ticketId = UUID.randomUUID().toString();

        Ticket ticket = Ticket.builder().ticketId(ticketId).ticketPrice(event1.getTicketPrice())
                .bookingDate(new Date())
                .user(user)
                .ticketStatus("Conform")
                .event(event1).build();

        Ticket save = this.ticketRepository.save(ticket);
//            event1.setTickets();
//        event1.getTickets().add(ticket);
//        eventRepository.save(event1);
//        user.getTickets().add(ticket);
//        userRepository.save(user);
        return mapper.map(save, TicketDto.class);

    }

//    @Override
//    public void deleteEventWithUserId(String userId) {
//        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(AppConstants.USER_NOT_FOUND));
////        user.setEvent(null);
//        User save = this.userRepository.save(user);
//
////        return mapper.map(save,UserDto.class);
//
//    }
//
//    @Override
//    public void deleteEventByEventId(String eventId) {
//        Event event = this.eventRepository.findById(eventId).orElseThrow(() -> new ResourceNotFoundException(AppConstants.NOT_FOUND));
//        eventRepository.delete(event);
//    }
}
