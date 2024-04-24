package com.mycompany.tennis.core.entity;

import com.mycompany.tennis.core.dto.TournoiDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tournoi")
public class Tournoi {
	
	/** 
	 * ATTRIBUTS
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NOM")
	private String nom;
	
	@Column(name = "CODE")
	private String code;
	
	/** 
	 * CONSTRUCTEUR
	 */
	public Tournoi() {}
	
	public Tournoi(String nom, String code) {
		this.nom 	= nom;
		this.code 	= code;
	}
	
	public Tournoi(Long id, String nom, String code) {
		this.id 	= id;
		this.nom 	= nom;
		this.code 	= code;
	}
	
	public Tournoi(TournoiDTO tournoi) {
		this.id 	= tournoi.getId();
		this.nom 	= tournoi.getNom();
		this.code 	= tournoi.getCode();
	}
	
	/** 
	 * METHODES
	 */
	@Override
    public String toString() {
        return "Tournoi{" 	+
                "id=" 		+ this.id 	+ " / " +
                "Nom="		+ this.nom 	+ " / " +
                "Code=" 	+ this.code	+
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
