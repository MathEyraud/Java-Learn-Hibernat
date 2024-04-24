package com.mycompany.tennis.core.entity;

import java.util.Set;

import com.mycompany.tennis.core.dto.EpreuveFullDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="epreuve")
public class Epreuve {
	
	/** 
	 * ATTRIBUTS
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "ANNEE")
	private Short annee;
	
	@Column(name = "TYPE_EPREUVE")
	private Character 	typeEpreuve;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TOURNOI")
	private Tournoi tournoi;
	
	// ManyToMany : Lazy est par defaut
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		    name = "participants",
		    joinColumns = @JoinColumn(name = "ID_EPREUVE"),
		    inverseJoinColumns = @JoinColumn(name = "ID_JOUEUR")
		)
	private Set<Joueur> participants;
	
	/** 
	 * CONSTRUCTEUR
	 */
	public Epreuve(){}
	public Epreuve(Long id, Short annee, Character typeEpreuve, Tournoi tournoi){
		this.id 			= id;
		this.annee 			= annee;
		this.typeEpreuve 	= typeEpreuve;
		this.tournoi 		= tournoi;
	}
	Epreuve(EpreuveFullDTO epreuve){
		this.id 			= epreuve.getId();
		this.annee 			= epreuve.getAnnee();
		this.typeEpreuve 	= epreuve.getTypeEpreuve();
		this.tournoi 		= new Tournoi(epreuve.getTournoi());
	}
	
	/** 
	 * METHODES
	 */
	@Override
    public String toString() {
        return "Epreuve{" +
                "Id épreuve ="		+ this.id 				+ " / " +
                "Annee=" 			+ this.annee 			+ " / " +
                "Type d'épreuve=" 	+ this.typeEpreuve		+ " / " +
                "Id du tournoi=" 	+ this.tournoi.getId()	+
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

	public Short getAnnee() {
		return annee;
	}

	public void setAnnee(Short annee) {
		this.annee = annee;
	}

	public Character getTypeEpreuve() {
		return typeEpreuve;
	}

	public void setTypeEpreuve(Character typeEpreuve) {
		this.typeEpreuve = typeEpreuve;
	}

	public Tournoi getTournoi() {
		return tournoi;
	}

	public void setTournoi(Tournoi tournoi) {
		this.tournoi = tournoi;
	}
	public Set<Joueur> getParticipants() {
		return participants;
	}
	public void setParticipants(Set<Joueur> participants) {
		this.participants = participants;
	}

}
