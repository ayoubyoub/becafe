package com.becafe.model;

import lombok.*;

import javax.persistence.*;

// made by Ayoub Youb with ❤️
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="name")
	private String name;

	@Column(name="username", unique = true)
	private String username;

	@Column(name="password")
	private String password;

	@Column(name="email", unique = true)
	private String email;

	@Column(name="userRole")
	@Enumerated(EnumType.STRING)
	private UserRole userRole;

}
