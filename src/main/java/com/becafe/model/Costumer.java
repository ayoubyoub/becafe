package com.becafe.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "costumers")
@PrimaryKeyJoinColumn(name = "userID") // Indicates that Customer class shares the primary key with the User class
public class Costumer {

	@Id
	@Column(name = "customerID")
	private String customerID;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "address")
	private String address;

	@Column(name = "phone")
	private String phone;

}
