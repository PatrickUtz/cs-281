/*
* Name: ForneymonCard
* Authors: Patrick Utz / Jeremy Goldberg / Matt Stein
* Date: 9/13/2018
* Description: Acts as the superclass for FlippingForneymonCard.java subclass. Includes   
* constructor methods, getter methods (for name and type), and a toString method.
*/

package classwork_2;

public class ForneymonCard {
	
	private String name; 
	private String type; 
	
	// Superclass constructor
	ForneymonCard (String n, String t){
		
		if ( t == "Burnymon"  ) {
			type = "Burnymon"; 
		}
		else if (t == "Dampymon" ){
			type = "Dampymon"; 
		}
		else if (t == "Leafymon" ) {
			type = "Leafymon";  
		}
		else {
			throw new IllegalArgumentException(); 
		}
		name = n; 

	}
	
	// Accounts for improper use of constructor
	ForneymonCard (String n){
		throw new IllegalArgumentException(); 
	}
	
	// Accounts for empty constructor
	ForneymonCard (){
		name = "MissingNu"; 
		type = "Burnymon";
	}
	
	// Getter methods
	public String checkName() {
		return name;
	}
	
	public String checkType() {
		return type;
	}
	
	// toString method
	public String toString() {
		return type + ": " + name; 
	}
}
