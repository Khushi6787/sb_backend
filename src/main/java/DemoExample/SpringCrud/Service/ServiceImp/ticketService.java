package DemoExample.SpringCrud.Service.ServiceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import DemoExample.SpringCrud.Entity.ticket;
import DemoExample.SpringCrud.Entity.user_mst;
import DemoExample.SpringCrud.Repository.ticketRepository;
@Service
public class ticketService {
	@Autowired
	ticketRepository ticketRepository;
	
	@Autowired
	userService us;
	public void deleteTicketById(int tid) {
        ticketRepository.deleteById(tid);
    }
	
	public void updateTicket(int tid, ticket updatedTicket, int userId) {
        ticket ticket = ticketRepository.findById(tid).get();
        if (ticket != null) {
            user_mst user = us.getUserById(userId);
            if (user != null) {
                ticket.setDepartment(updatedTicket.getDepartment());
                ticket.setMessage(updatedTicket.getMessage());
                ticket.setPriority(updatedTicket.getPriority());
                ticket.setSubject(updatedTicket.getSubject());
                ticket.setStatus(updatedTicket.getStatus());
                ticket.setUser(user);
                ticketRepository.save(ticket);
            } else {
                throw new RuntimeException("User with ID " + userId + " not found");
            }
        } else {
            throw new RuntimeException("Ticket with ID " + tid + " not found");
        }
    }
	
	public void save(ticket ticket) {
        ticketRepository.save(ticket);
    }

	public Page<ticket> getAllTicket(Integer id, Pageable pageable) {
	    return ticketRepository.findByUid(id, pageable);
	}
	
	public ticket getbyid(Integer id) {
		return ticketRepository.findById(id).get();
	}
	
	public Page<ticket> getAllT(Pageable pageable) {
	    return ticketRepository.findAll(pageable);
	}
}
