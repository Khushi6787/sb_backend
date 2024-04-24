package DemoExample.SpringCrud.Entity;

import java.util.Date;
import jakarta.persistence.*;
import jakarta.persistence.Table;
@Entity
@Table(name = "ticket")
public class ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tid;

    private String department;
    private String message;
    private String priority;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;
    
    private String subject;
    private String status;
    
    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
    
    @ManyToOne 
    @JoinColumn(name = "cid") 
    private user_mst user;

       public user_mst getUser() {
        return user;
    }

    public void setUser(user_mst user) {
        this.user = user;
    }

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getStatus() {
		return status;
	}
//
	public void setStatus(String status) {
	    if (status == null) {
	        // Set default value here
	        this.status = "Open";
	    } else {
	        this.status = status;
	    }
	}

    
}
