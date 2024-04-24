package DemoExample.SpringCrud.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DemoExample.SpringCrud.Entity.ticket;
import DemoExample.SpringCrud.Entity.user_mst;
import DemoExample.SpringCrud.Repository.ticketRepository;
import DemoExample.SpringCrud.Service.ServiceImp.ticketService;

@RestController
@CrossOrigin(origins = "http:localhost:3000")
public class ticketController {
	@Autowired
	ticketService tc;

	@PostMapping("/tickets")
	public String createTicket(@RequestBody ticket ticket) {
		try {
			tc.save(ticket);
			return "Ticket created successfully";
		} catch (Exception e) {
			return "Failed to create ticket. Error: " + e.getMessage();
		}
	}

	@DeleteMapping("deleteTicket/{tid}")
	public ResponseEntity<String> deleteTicket(@PathVariable int tid) {
		try {
			tc.deleteTicketById(tid);
			return ResponseEntity.ok("Ticket with ID " + tid + " has been deleted successfully.");
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build(); // Ticket with the given ID does not exist
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete ticket.");
		}
	}

	@GetMapping("/ticketCounts/{id}")
	public Map<String, Integer> getTicketCounts(@PathVariable Integer id) {
		List<ticket> tickets = tc.getAllTicket(id);
		Map<String, Integer> ticketCounts = new HashMap<>();
		ticketCounts.put("High", 0);
		ticketCounts.put("Medium", 0);
		ticketCounts.put("Low", 0);
		for (ticket t : tickets) {
			switch (t.getPriority()) {
			case "High":
				ticketCounts.put("High", ticketCounts.get("High") + 1);
				break;
			case "Medium":
				ticketCounts.put("Medium", ticketCounts.get("Medium") + 1);
				break;
			case "Low":
				ticketCounts.put("Low", ticketCounts.get("Low") + 1);
				break;
			default:
				break;
			}
		}

		return ticketCounts;
	}

	@GetMapping("/ticketCounts")
	public Map<String, Integer> getTicketAdminCounts(@RequestParam(defaultValue = "0") int page,
	                                                 @RequestParam(defaultValue = "10") int size) {
	    Pageable pageable = PageRequest.of(page, size);
	    List<ticket> tickets = tc.getAllT(pageable).getContent();
	    
	    Map<String, Integer> ticketCounts = new HashMap<>();
	    ticketCounts.put("High", 0);
	    ticketCounts.put("Medium", 0);
	    ticketCounts.put("Low", 0);
	    
	    for (ticket t : tickets) {
	        String priority = t.getPriority();
	        if (priority != null) {
	            switch (priority) {
	                case "High":
	                    ticketCounts.put("High", ticketCounts.get("High") + 1);
	                    break;
	                case "Medium":
	                    ticketCounts.put("Medium", ticketCounts.get("Medium") + 1);
	                    break;
	                case "Low":
	                    ticketCounts.put("Low", ticketCounts.get("Low") + 1);
	                    break;
	                default:
	                    break;
	            }
	        }
	    }
	    return ticketCounts;
	}

	@CrossOrigin
	@GetMapping("/getT")
	public ResponseEntity<Page<ticket>> getAllTickets(
	    @RequestParam Integer pageNo,
	    @RequestParam Integer pageSize
	) {
		
	    Pageable paging = PageRequest.of(pageNo, pageSize);

	    Page<ticket> pageResult = tc.getAllT(paging);

	    return ResponseEntity.ok(pageResult); 
	}


	@GetMapping("/getAlltickets/{id}")
	public List<ticket> getAllTickets(@PathVariable Integer id) {
		return tc.getAllTicket(id);
	}

	@GetMapping("/getbytid/{id}")
	public ResponseEntity<ticket> getbytid(@PathVariable Integer id) {
		ticket t = tc.getbyid(id);
		if (t != null) {
			return ResponseEntity.ok(t);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/updateTicket/{tid}/{userId}")
	public ResponseEntity<String> updateTicket(@PathVariable int tid, @RequestBody ticket updatedTicket,
			@PathVariable int userId) {
		try {
			tc.updateTicket(tid, updatedTicket, userId);
			return ResponseEntity.ok("Ticket with ID " + tid + " updated successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to update ticket. Error: " + e.getMessage());
		}
	}
}
