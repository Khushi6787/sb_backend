package DemoExample.SpringCrud.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="roles")
public class roles {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int rid;
		
		@Column(name="r_name")
		private String r_name;

		public int getRid() {
			return rid;
		}

		public void setRid(int rid) {
			this.rid = rid;
		}

		public String getR_name() {
			return r_name;
		}

		public void setR_name(String r_name) {
			this.r_name = r_name;
		}
		
		
}
