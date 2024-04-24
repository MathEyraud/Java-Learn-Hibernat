package com.mycompany.tennis.core.entity;

import com.mycompany.tennis.core.dto.MatchDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="match_tennis")
public class Match {
	
	/** 
	 * ATTRIBUTS
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_EPREUVE")
	private Epreuve epreuve;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_VAINQUEUR")
	private Joueur vainqueur;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_FINALISTE")
	private Joueur finaliste;
	
	@Transient
	private Score score;
	
	/*
	 * CONSTRUCTEURS
	 */
	public Match() {

	}
	public Match(Epreuve epreuve, Joueur vainqueur, Joueur finaliste) {
		this.epreuve 	= epreuve;
		this.vainqueur 	= vainqueur;
		this.finaliste 	= finaliste;
	}
	// Convertir depuis un DTO
	public Match(MatchDTO newMatch) {
		this.id 		= newMatch.getId();
		this.epreuve 	= new Epreuve(newMatch.getEpreuve());
		this.vainqueur 	= new Joueur(newMatch.getVainqueur());
		this.finaliste 	= new Joueur(newMatch.getFinaliste());
		//this.score		= newMatch.getScore();
	}
	
	/** 
	 * METHODES
	 */
	@Override
    public String toString() {
        return "Match{" +
                "id=" 		+ this.id 		+ " / " +
                "Epreuve="	+ this.epreuve 	+ " / " +
                "Vainqueur="+ this.vainqueur+ " / " +
                "Finaliste="+ this.finaliste+ " / " +
                "Score=" 	+ this.score 	+
                '}';
    }
	
	/** 
	 * GETTERS/SETTERS
	 * @return
	 */
	public Long 	getId() 						{return id;}
	public void 	setId(Long id) 					{this.id = id;}
	public Epreuve 	getEpreuve() 					{return epreuve;}
	public void 	setEpreuve(Epreuve epreuve) 	{this.epreuve = epreuve;}
	public Joueur 	getVainqueur() 					{return vainqueur;}
	public void 	setVainqueur(Joueur vainqueur) 	{this.vainqueur = vainqueur;}
	public Joueur 	getFinaliste() 					{return finaliste;}
	public void 	setFinaliste(Joueur finaliste) 	{this.finaliste = finaliste;}
	public Score 	getScore() 						{return score;}
	public void 	setScore(Score score) 			{this.score = score;}
	
}
