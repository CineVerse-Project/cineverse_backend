/**
 * Created At {11 May 2023
 * By HuuNQ
 */
package fa.cineverse.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author HuuNQ
 *
 * 11 May 2023
 * 
 */
@Entity
public class Payment {
	@Id
	private String paymentId;
	
	private Double totalMoney;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	private boolean isDelete;
	
	@OneToOne(mappedBy = "payment")
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
