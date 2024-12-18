package com.example.demo.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Payments")
public class Payments {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	 
	 @ManyToOne
	 @JoinColumn(name = "ticketId", referencedColumnName = "id")
	 private Ticket ticket;
	 
	 @Column(name="amount")
	 private int amount;
	 
	 @CreationTimestamp
	 @Column(name = "createTime", nullable = false, updatable = false)
	 private LocalDateTime createTime;
	 
	 public Payments() {}

	 public Payments(Ticket ticket, int amount) {
		super();
		this.ticket = ticket;
		this.amount = amount;
		this.createTime = LocalDateTime.now();
	 }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Payments [id=" + id + ", ticket=" + ticket + ", amount=" + amount + ", createTime=" + createTime + "]";
	};
}
