package main;
/** salut */
import rooms.*;
import player.*;
import things.*;

public class Game {
    Room currentRoom;
    public Player player;
    private Parser parser;
	
    public Game() throws InterruptedException {
		player = new Player();
		parser = new Parser();
    }
    
    /**
     * A method which will be used when the user is following a lecture, a lab,
     * reading a book, etc... It "passes the time".
     * 
     * @throws InterruptedException
     */
    public void waiting() throws InterruptedException {
        System.out.print(".");
        Thread.sleep(1000);
        System.out.print(".");
        Thread.sleep(1000);    
        System.out.print(".");
        Thread.sleep(1000);
        System.out.print(".");
        Thread.sleep(1000);  
        System.out.println(".");
        Thread.sleep(1000);
    }
    
    /**
     * A method creating every rooms in the game. Will be launch once in a game.
     */
    private void createRooms() {
        Room ali = new Ali();
        Room exam = new Exam();
        Room foyer = new Foyer();
        Room lab1 = new Lab();
        Room lab2 = new Lab();
        Room lab3 = new Lab();
        Room lecture1 = new Lecture();
        Room lecture2 = new Lecture();
        Room lecture3 = new Lecture();
        Room library = new Library();
        Room lunchRoom = new LunchRoom();
        Room print = new Print();
        Room secretariat = new Secretariat();
        Room c1 = new Corridor();
        Room c2 = new Corridor();
        Room c3 = new Corridor();
        Room c4 = new Corridor();
        Room c5 = new Corridor();
        Room c6 = new Corridor();
        Room c7 = new Corridor();
        Room c8 = new Corridor();
        Room c9 = new Corridor();
        Room c10 = new Corridor();
        Room c11 = new Corridor();
        Room c12 = new Corridor();
        Room u1 = new UselessRoom();
        Room u2 = new UselessRoom();
        Room u3 = new UselessRoom();        
        
        foyer.setExit("north",c1);
        c1.setExit("north",c2);
        c1.setExit("south", foyer);
        c1.setExit("east", lecture1);
        c1.setExit("west", secretariat);
        
        c2.setExit("north", c3);
        c2.setExit("south", c1);
        c2.setExit("east", lecture2);
        c2.setExit("west", u1);
        
        c3.setExit("north", c6);
        c3.setExit("south", c2);
        c3.setExit("east", lecture3);
        c3.setExit("west", c4);
        
        c4.setExit("east", c3);
        c4.setExit("west", c5);
        
        c5.setExit("south", exam);
        c5.setExit("east", c4);
        
        c6.setExit("north", c7);
        c6.setExit("south", c3);
        c6.setExit("east", lab1);
        
        c7.setExit("north", c8);
        c7.setExit("south", c6);
        c7.setExit("east", lab2);
        
        c8.setExit("north", c9);
        c8.setExit("south", c7);
        c8.setExit("east", lab3);
        
        c9.setExit("north", c10);
        c9.setExit("south", c8);
        c9.setExit("east", c11);
        c9.setExit("west", lunchRoom);
        
        c10.setExit("north", library);
        c10.setExit("south", c9);
        c10.setExit("east", u2);
        
        c11.setExit("east", c12);
        c11.setExit("west", c9);
        
        c12.setExit("north", ali);
        c12.setExit("south", print);
        c12.setExit("east", u3);
        c12.setExit("west", c11);
        
        currentRoom = foyer;    
    }
    
    public Player getPlayer() {
        return player;
    }
    
    /**
     * Main play routine. Loops until end of play.
     */
    public void play() {
    	createRooms();
        printWelcome();

        // Enter the main command loop. Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
			String command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type \"help\" if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * 
     * @param command
     *            The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(String command) {
        boolean wantToQuit = false;

        switch (command) {          

        case "help":
            printHelp();
            break;

        case "east":
            goRoom(command);
            break;
            
        case "west":
            goRoom(command);
            break;
        
        case "north":
            goRoom(command);
            break;
           
        case "south":
            goRoom(command);
            break;

        case "quit":
            wantToQuit = quit(command);
            break;
 
		case "stats":
			printStats();
			break;

		case "coffee":
			currentRoom.use(new Coffee(), player);
			break; 
			
		case "babyfoot":
			currentRoom.use(new Babyfoot(), player);
			break;
			
		case "lights" :
			currentRoom.switchLight();
			break;
            
        default:
        	System.out.println("I don't know what you mean...");
            break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information. Here we print some stupid, cryptic
     * message and a list of the command words.
     */
    private void printHelp() {
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * Try to go in one direction. If there is an exit, enter the new room,
     * otherwise print an error message.
     */
    private void goRoom(String command) {
        String direction = command;

         //Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see whether we
     * really quit the game.
     * 
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(String command) {
            return true; // signal that we want to quit
    }    
    
	/**
	 * A method printing all the player's stats in case he wants to know his
	 * energy level, his current lectures and current labs.
	 */
	private void printStats() {
		player.printAllStats();
	}
}