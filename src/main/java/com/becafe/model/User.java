package com.becafe.model;

import lombok.*;

import javax.persistence.*;

// made by Ayoub Youb with ❤️
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "users")
public class User {

	@Id
	@Column(name="userID", unique = true)
	private String userID;

	@Column(name="username", unique = true)
	private String username;

	@Column(name="password")
	private String password;

	@Column(name="email", unique = true)
	private String email;

	@Column(name="role")
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(name="active", columnDefinition = "boolean default false")
	private boolean active;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "address")
	private String address;

	@Column(name = "phone")
	private String phone;
}
