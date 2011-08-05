package app.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbl_employee")
public class Employee implements Serializable {

	@Id
    private String id;
    private String name;
    private String gender;
    
    @Temporal(TemporalType.DATE)
    private Date dob;
    
    private String email;
    private String phone;
    
    @Temporal(TemporalType.TIME)
    private Date workTime;
    
    private Boolean status;
    
    public Employee() {}

    public Employee(String id, String name) {
    	this.id = id;
    	this.name = name;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getWorkTime() {
		return workTime;
	}
	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
	}

	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
}
