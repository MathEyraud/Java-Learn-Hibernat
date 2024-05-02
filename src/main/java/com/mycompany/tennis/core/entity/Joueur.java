package com.mycompany.tennis.core.entity;

import org.hibernate.annotations.NamedQuery;

import com.mycompany.tennis.core.dto.JoueurDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@NamedQuery(query = "SELECT j FROM Joueur j WHERE j.sexe=?1",name="getJoueursBySexe")
@Entity
@Table(name="joueur")
public class Joueur {
	
	/** 
	 * ATTRIBUTS
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NOM")
	private String nom;
	
	@Column(name = "PRENOM")
	private String prenom;
	
	@Column(name = "SEXE")
	private Character sexe;
	
	/** 
	 * CONSTRUCTEUR
	 */
	public Joueur() {}
	
	public Joueur(String nom, String prenom, String sexe) {
		this.nom 	= nom;
		this.prenom = prenom;
		this.sexe 	= sexe.charAt(0);
	}
	
	public Joueur(Long id, String nom, String prenom, Character sexe) {
		this.id 	= id;
		this.nom 	= nom;
		this.prenom = prenom;
		this.sexe 	= sexe;
	}
	
	public Joueur(Joueur joueur) {
		this.id		= joueur.getId();
		this.nom 	= joueur.getNom();
		this.prenom = joueur.getPrenom();
		this.sexe 	= joueur.getSexe();
	}
	
	public Joueur(JoueurDTO dto) {
		this.id		= dto.getId();
		this.nom 	= dto.getNom();
		this.prenom = dto.getPrenom();
		this.sexe 	= dto.getSexe();
	}
	
	/** 
	 * METHODES
	 */
	@Override
    public String toString() {
        return "Joueur{" +
                "id=" 		+ this.id 		+ " / " +
                "nom=" 		+ this.nom 		+ " / " +
                "prenom=" 	+ this.prenom 	+ " / " +
                "sexe=" 	+ this.sexe 	+
                '}';
    }
	
	/** 
	 * GETTERS/SETTERS
	 * @return
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Character getSexe() {
		return sexe;
	}
	public void setSexe(Character sexe) {
		this.sexe = sexe;
	}
}
