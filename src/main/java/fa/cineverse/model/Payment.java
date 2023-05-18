/**
 * Created At {11 May 2023
 * By HuuNQ
 */
package fa.cineverse.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"booking"})
public class Payment {
	@Id
	@Column(columnDefinition = "VARCHAR(20)")
    @GeneratedValue(generator = "prod-generator")
    @GenericGenerator(name = "prod-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "PM"),
            strategy = "fa.cineverse.common.IdentityCodeGenerator")
	private String paymentId;
	
	private Double totalMoney;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	private boolean isDelete;
	
	@OneToOne
	@JoinColumn(name = "booking_id") 
	private Booking booking;
	
	public Payment() {
		super();
	}

	public Payment(String paymentId, Double totalMoney, LocalDateTime createdAt, LocalDateTime updatedAt,
			boolean isDelete) {
		super();
		this.paymentId = paymentId;
		this.totalMoney = totalMoney;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.isDelete = isDelete;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
}
