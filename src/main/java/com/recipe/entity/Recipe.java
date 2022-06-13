package com.recipe.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/*@Entity: This annnotation defines that a class can be mapped to a table.*/
@Entity

/*@Table: This annotation allows you to specify the details of the table that will be used to 
persist the entity in the database*/
@Table(name = "Recipe")

public class Recipe {

/*@Id: This annotation is to specify the primary key of an entity*/	
	@Id
	
/*@GeneratedValue: This annotation provides the specification of generation strategies for 
the values of primary keys.*/	
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private int rid;
	

/*@Column: This annotation is used for Adding the column the name in the table of a particular
 database.*/
	@Column
	private String rname;
	
    @Column
    
/*@JsonFormat: This annotation is used to tell Jackson that the format in which the value for a 
field is serialized. It specifies the format using the JsonFormat. */
    @JsonFormat
	private LocalDate createdOn;  

	@Column
	private boolean veg;

	@Column
	private int DS;
	//servings;

	@Column
	private String Instructions;
	
/*@OneToMany: This annotation is used to define a one-to-many relationship between two entities 
in Spring Data JPA.*/	
	@OneToMany(targetEntity = Ingredients.class, cascade=CascadeType.ALL)
	
/*@JoinColumn: This annotation helps us specify the column we'll use for joining an entity 
association or element collection*/
	@JoinColumn(name="rid_ingid",referencedColumnName = "rid")
	
	private List<Ingredients> ingredientsList;

	
	
	public Recipe() {
		super();
	}



	public Recipe(int rid, String rname, LocalDate createdOn, boolean veg, int dS, String instructions,
			List<Ingredients> ingredientsList) {
		super();
		this.rid = rid;
		this.rname = rname;
		this.createdOn = createdOn;
		this.veg = veg;
		DS = dS;
		Instructions = instructions;
		this.ingredientsList = ingredientsList;
		
		
	}



	public int getRid() {
		return rid;
	}



	public void setRid(int rid) {
		this.rid = rid;
	}



	public String getRname() {
		return rname;
	}



	public void setRname(String rname) {
		this.rname = rname;
	}



	public LocalDate getCreatedOn() {
		return createdOn;
	}



	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}



	public boolean isVeg() {
		return veg;
	}



	public void setVeg(boolean veg) {
		this.veg = veg;
	}



	public int getDS() {
		return DS;
	}



	public void setDS(int dS) {
		DS = dS;
	}



	public String getInstructions() {
		return Instructions;
	}



	public void setInstructions(String instructions) {
		Instructions = instructions;
	}



	public List<Ingredients> getIngredientsList() {
		return ingredientsList;
	}



	public void setIngredientsList(List<Ingredients> ingredientsList) {
		this.ingredientsList = ingredientsList;
	}



	@Override
	public String toString() {
		return "Recipe [rid=" + rid + ", rname=" + rname + ", createdOn=" + createdOn + ", veg=" + veg + ", DS=" + DS
				+ ", Instructions=" + Instructions + ", ingredientsList=" + ingredientsList + "]";
	}



	



	
	
	
	

	}