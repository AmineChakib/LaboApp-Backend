package org.sid.web;

import org.sid.entities.Fonction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterForm {
	private String username;
	private String password;
	private String repassword;
	private String nom;
	private String prenom;
	private String email;
	private Fonction fonction;
	public boolean isAdmin=false;
	public boolean isSuperAdmin= false;

}
