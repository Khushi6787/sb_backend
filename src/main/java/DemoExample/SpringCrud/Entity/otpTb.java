package DemoExample.SpringCrud.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="otpTb")
public class otpTb {
			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			private int oid;
			private String email;
			public String getEmail() {
				return email;
			}

			public void setEmail(String email) {
				this.email = email;
			}

			private String code;
			
			public int getOid() {
				return oid;
			}

			public void setOid(int oid) {
				this.oid = oid;
			}

			public String getCode() {
				return code;
			}

			public void setCode(String code) {
				this.code = code;
			}

			
			
		    public String getUser() {
				return user;
			}

			public void setUser(String user) {
				this.user = user;
			}

			private String user;
			
}
