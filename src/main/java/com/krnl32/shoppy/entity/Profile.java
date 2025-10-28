package com.krnl32.shoppy.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "profile")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "bio")
	private String bio;

	@Column(name = "company")
	private String company;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "user_id")
	private User user;

	@Override
	public String toString() {
		return "Profile{" +
			"id=" + id +
			", firstName='" + firstName + '\'' +
			", lastName='" + lastName + '\'' +
			", dateOfBirth=" + dateOfBirth +
			", phoneNumber='" + phoneNumber + '\'' +
			", bio='" + bio + '\'' +
			", company='" + company + '\'' +
			", user=" + user.getId() +
			'}';
	}
}
