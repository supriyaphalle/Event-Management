package com.eventmanegement.event.service.impl;

import com.eventmanegement.event.constants.AppConstants;
import com.eventmanegement.event.dto.*;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

        Event event =new Event();
        LocalDate date = LocalDate.ofEpochDay(0);
        try {
//            date = LocalDate.parse(eventDto.getDate(), DateTimeFormatter.ISO_DATE);

            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(eventDto.getDate());

            event.setDate(date1);
            System.out.println(date1);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception : " + e);
        } catch (DateTimeParseException | ParseException e) {
            System.out.println("Exception: " + e);
        }

        event = mapper.map(eventDto, Event.class);
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

    @Override
    public TicketDto getTicketByTicketId(String ticketId) {
        Ticket ticket = this.ticketRepository.findById(ticketId).orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));
        return mapper.map(ticket, TicketDto.class);
    }

    @Override
    public ViewBookingRequest viewBookingWithUser(String eventId) {
        Event event = this.eventRepository.findById(eventId).orElseThrow(() -> new ResourceNotFoundException("Event not found"));
        System.out.println(event.getName());
        List<Ticket> ticketList = this.ticketRepository.findByEvent(event);
        System.out.println(ticketList);
        long count = ticketList.size();
        List<User> list = ticketList.stream().map(Ticket::getUser).toList();
        List<UserDto> userDtos = list.stream().map(l -> mapper.map(l, UserDto.class)).collect(Collectors.toList());
        return new ViewBookingRequest(count, userDtos);

    }

    @Override
    public String CancelEvent(String eventId) {
        Event event = this.eventRepository.findById(eventId).orElseThrow(() -> new ResourceNotFoundException("Event not found with id:"));
        System.out.println(event);
        LocalDate currentDate = LocalDate.now();

        long remainingDays = java.time.temporal.ChronoUnit.DAYS.between((Temporal) currentDate, (Temporal) event.getDate());

        if (remainingDays >= 2) {
            this.eventRepository.deleteById(eventId);
//            this.eventRepository.delete(event);
            List<Ticket> tickets = this.ticketRepository.findByEvent(event);
            for (Ticket t : tickets) {
//                this.ticketRepository.delete(t);
                this.ticketRepository.deleteById(t.getTicketId());
            }
            return "Event canceled Successfully";
        } else {
            return "Its too late to Cancel event";
        }

    }


}
