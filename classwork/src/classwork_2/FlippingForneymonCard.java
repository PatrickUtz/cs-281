/*
* Name: FlippingForneymonCard
* Authors: Patrick Utz / Jeremy Goldberg / Matt Stein
* Date: 9/13/2018
* Description: A subclass of the ForneymonCard.java superclass. User can create a Forneymon
* card, flip the card, check the status of the card, and match the card with other 
* cards (there is a method for each of these actions). A toString method is also included.   
*/

package classwork_2;

public class FlippingForneymonCard extends ForneymonCard {
	
	private boolean faceStatus; 
	
	// Default constructor. Utilizes superclass constructor
	public FlippingForneymonCard(String n, String t, Boolean f) {
		super(n,t); 
		faceStatus = f; 
	}
	
	// Accounts for an empty constructor
	public FlippingForneymonCard() {
		super("MissingNu", "Burnymon"); 
		faceStatus = true;
	}
	
	// Flips the card and changes the face status
	public Boolean flip() {
		if (faceStatus == true) {
			faceStatus = false; 
			return false;
		}
		else {
			faceStatus = true;
			return true;
		}
	}
	
	// Returns the face status as true for down and false for up
	public boolean checkFaceStatus() {
		return faceStatus; 
	}
	
	// Returns 2 if either card is face down. Returns 1 if both cards are face up and have the 
	// same type and name. Returns 0 if neither of the previous conditions are met
	public int match( FlippingForneymonCard other) {
		
		if ( (other.checkFaceStatus() ) || (faceStatus) ) {
			return 2; 
		}
		else if ( (other.checkName().equals(checkName()) ) && 
				  (other.checkType().equals(checkType()) ) ) {
			return 1;
		}
		else {
			return 0; 
		}
	}

	@Override 	
	public String toString() {
		if ( faceStatus ) {
			return "?: ?";
		}
		else {
			return super.toString();
		}
	}
	
	public static void main (String[] args) {
		
		// Create Forneymon Cards
        FlippingForneymonCard lmuMascot = new FlippingForneymonCard("iggy", "Dampymon", true);
        FlippingForneymonCard lmuMascotTLaw = new FlippingForneymonCard("iggy", "Dampymon", false);
        FlippingForneymonCard leafMan = new FlippingForneymonCard("leafboy", "Leafymon", false);
        FlippingForneymonCard flames = new FlippingForneymonCard("fireeee", "Burnymon", true);
        FlippingForneymonCard missingNu = new FlippingForneymonCard();
        
        
        // Check toString() and flip() methods
        System.out.println(lmuMascot.toString()); // "?: ?"
        lmuMascot.flip();
        System.out.println(lmuMascot.toString()); // "Dampymon: iggy"
        lmuMascot.flip();
        System.out.println(lmuMascot.toString());
        
        System.out.println(leafMan); //"Leafymon: leafboy"
        leafMan.flip(); 
        System.out.println(leafMan.toString());
        
        System.out.println(flames.toString()); //"?: ?"
        flames.flip(); 
        System.out.println(flames.toString()); //"Burnymon: fireeee"
		
        System.out.println(missingNu); // "?: ?"
        missingNu.flip();
        System.out.println(missingNu); // "Burnymon: MissingNu"
        
        // Check match() method
        System.out.println(lmuMascot.match(missingNu)); // 2
        lmuMascot.flip();
        
        System.out.println(lmuMascot.match(missingNu)); // 0
        
        System.out.println(lmuMascot.match(lmuMascotTLaw)); // 1
    }	
}