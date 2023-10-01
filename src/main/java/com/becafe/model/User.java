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
	@Column(name="userID")
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
}
