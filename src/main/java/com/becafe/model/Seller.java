package com.becafe.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sellers")
@PrimaryKeyJoinColumn(name = "userID") // Indicates that Seller class shares the primary key with the User class
public class Seller {

	@Id
	@Column(name = "sellerID")
	private String sellerID;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "address")
	private String address;

	@Column(name = "phone")
	private String phone;

}
