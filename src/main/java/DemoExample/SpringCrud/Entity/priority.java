package DemoExample.SpringCrud.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="priority")
public class priority {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int pid;
		private String p_level;
		public int getPid() {
			return pid;
		}
		public void setPid(int pid) {
			this.pid = pid;
		}
		public String getP_level() {
			return p_level;
		}
		public void setP_level(String p_level) {
			this.p_level = p_level;
		}
		

}
