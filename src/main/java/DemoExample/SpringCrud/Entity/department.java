package DemoExample.SpringCrud.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="department")
public class department {
			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			private int did;
			private String dep_name;
			public int getDid() {
				return did;
			}
			public void setDid(int did) {
				this.did = did;
			}
			public String getDep_name() {
				return dep_name;
			}
			public void setDep_name(String dep_name) {
				this.dep_name = dep_name;
			}		
}
