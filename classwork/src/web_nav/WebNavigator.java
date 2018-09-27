/*
* Name: WebNavigator
* Authors: Patrick Utz / Jeremy Goldberg / Matt Stein
* Date: 9/27/2018
* Description: Emulates web browser navigation suite that can be used to (1) visit sites,
*  (2) return users to previously visited sites, and (3) move forward to previously visited 
*  sites that were returned from (just like how you (1) visit sites on a browser and can (2)
*   hit the back button or (3) hit the forward button).
*/

package web_nav;

// TODO: Choose imports based on DS choice
import java.util.Scanner;
import java.util.LinkedList;

public class WebNavigator {

    // Fields
    private String current; // Tracks currently visited site
    private LinkedList<String> pageCurrent; 
    private LinkedList<String> forwardHistory;
    
    // Constructor
    WebNavigator () {
        current = null;
        pageCurrent = new LinkedList<String> ();
        forwardHistory = new LinkedList<String> ();
    }
    
    // Methods
    // [!] YOU DO NOT HAVE TO MODIFY THIS METHOD FOR YOUR SOLUTION
    public boolean getNextUserCommand (Scanner input) {
        String command = input.nextLine();
        String[] parsedCommand = command.split(" ");
        
        // Switch on the command (issued first in input line)
        switch(parsedCommand[0]) {
        case "exit":
            System.out.println("Goodbye!");
            return false;
        case "visit":
            visit(parsedCommand[1]);
            break;
        case "back":
            back();
            break;
        case "forward":
            forw();
            break;
        default:
            System.out.println("[X] Invalid command, try again");
        }
        
        System.out.println("Currently Visiting: " + current);
        
        return true;
    }
    
    /*
     *  Visits the current site, clears the forward history,
     *  and records the visited site in the back history
     */
    public void visit (String site) {
    	pageCurrent.push(site);
    	forwardHistory = new LinkedList<String> ();
        current = pageCurrent.getFirst();
    }
    
    /*
     *  Changes the current site to the one that was last
     *  visited in the order on which visit was called on it
     */
    public void back () {
    	// check if there is no previous website to go back to
        if (pageCurrent.size() < 2) {
        	return;
        }
        forwardHistory.push(pageCurrent.getFirst());
    	pageCurrent.poll();
        current = pageCurrent.getFirst();
    }
    
    public void forw () {
    	// check if there is no forward website to go to
    	if (forwardHistory.size() < 1) {
    		return;
    	}
    	pageCurrent.push(forwardHistory.getFirst());
    	forwardHistory.poll();
        current = pageCurrent.getFirst();
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        WebNavigator navi = new WebNavigator();
        
        System.out.println("Welcome to ForneyFox, enter a command "
        		+ "from your ForneyFox user manual!");
        while (navi.getNextUserCommand(input)) {}
        System.out.println("Goodbye!");
    }

}