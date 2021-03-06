package player;

import java.util.ArrayList;

import main.Language;
import main.Randomize;

/**
 * @author Lisa Joanno & J�r�my Melchor
 * 
 *         All the informations about the player : his courses, labs, if he has
 *         the cheat sheet. There's also some methods to modify his energy or
 *         add some courses or labs
 */
public class Player {
	/**
	 * The player starts with 100 points of energy. Every actions will change
	 * this level (drinking coffee, attending lectures...). theCheatSheet is a
	 * variable which, when 'true', makes you know the answers to every
	 * questions of the exam.
	 */
	public int lvlEnergy = 100;
	private ArrayList<String> completeList = new ArrayList<String>();
	private ArrayList<String> listLecture = new ArrayList<String>();
	private ArrayList<String> listLab = new ArrayList<String>();
	private boolean theCheatSheet = false;

	public Player() {
		initialize();
	}

	/**
	 * A method used to know the player's current level of energy.
	 *
	 * @return the level of energy.
	 */
	public int getLvlEnergy() {
		return lvlEnergy;
	}

	/**
	 * A method called when the player enters the print room and finds the cheat
	 * sheet on a printer (probably forgotten by a teacher.)
	 */
	public void setTheCheatSheet(boolean cheat) {
		this.theCheatSheet = cheat;
	}

	/**
	 * A method returning theCheatSheet.
	 * 
	 * @return the value of theCheatSheet
	 */
	public boolean getTheCheatSheet() {
		return this.theCheatSheet;
	}

	/**
	 * A method checking every time if the player has a low energy, or worse, if
	 * he can't continue the game.
	 */
	public void verficationEnergy() {
		if (this.lvlEnergy == 0) {
			System.out.println(Language.DEAD);
			System.exit(0);
		} else if (this.lvlEnergy <= 30) {
			System.out.println(Language.ALMOST_DEAD);
		}
	}

	/**
	 * A method lowering the level of energy. The lowest possible level is 0.
	 *
	 * @param difference
	 *            , in fact how many "points of energy" is lost.
	 */
	public void lowEnergy(int difference) {
		if (lvlEnergy - difference >= 0) {
			lvlEnergy -= difference;
		}
	}

	/**
	 * A method raising the level of energy. The highest possible level is 100.
	 *
	 * @param difference
	 *            , in fact how many "points of energy" is gained.
	 */
	public void raiseEnergy(int difference) {
		if (lvlEnergy + difference <= 100) {
			lvlEnergy += difference;
		}
	}

	/**
	 * A method initializing the list of theme the player can learn. Must be
	 * filled by the user of the application.
	 */
	void initialize() {

		/**
		 * String[] tab = { "POO 1", "POO 2", "POO 3", "POO 4", "POO 5",
		 * "POO 6", "POO 7", "POO 8", "POO 9", "POO 10" };
		 */

		String[] tab = { Language.DESIGNING_CLASSES.toString(),
				Language.INHERITANCE.toString(),
				Language.POLYMORPHISM.toString(),
				Language.ABSTRACTION.toString(), Language.INTERFACE.toString(),
				Language.ERRORS.toString(), Language.DEBUG.toString() };

		for (String el : tab) {
			completeList.add(el);
		}
	}

	/**
	 * A method adding a lecture. Used when the player follows a lecture.
	 */
	public void addLecture() {
		if (whatLectureToAttend() != null) {
			listLecture.add(whatLectureToAttend());
		} else {
		}
	}

	/**
	 * A method adding a lab. Used when the player follows a lab.
	 */
	public void addLab() {
		if (whatLabToAttend() != null) {
			listLab.add(whatLabToAttend());
		} else {
		}
	}

	/**
	 * A method finding the lecture the player should attend.
	 *
	 * @return the name of the lecture.
	 */
	public String whatLectureToAttend() {
		// finds the first element of complete list that is not in listLecture.
		for (String missingElt : completeList) {
			if (!listLecture.contains(missingElt)) {
				return missingElt;
			}
		}
		return null; // returns null if there is no lecture left to attend
	}

	/**
	 * A method finding the lab (if there is one) the player should attend.
	 *
	 * @return the name of the Lab.
	 */
	public String whatLabToAttend() {
		for (String eltLecture : listLecture) {
			if (!listLab.contains(eltLecture)) {
				return eltLecture;
			}
		}
		return null; // in case there is no lab to attend
	}

	/**
	 * A method printing every possible lecture.
	 */
	public void printAllPossibleLecture() {
		for (String el : completeList) {
			System.out.println(el);
		}
	}

	/**
	 * A method printing which lecture the player learned so far.
	 */
	public void printCurrentLecture() {
		System.out.println(Language.LECTURE);
		for (String el : listLecture) {
			System.out.println("    " + el);
		}
	}

	/**
	 * A method printing which lab the player learned so far.
	 */
	public void printCurrentLab() {
		System.out.println(Language.LAB);
		for (String el : listLab) {
			System.out.println("    " + el);
		}
	}

	/**
	 * A method printing the energy level.
	 */
	public void printEnergy() {
		System.out.println(Language.ENERGY_LEVEL + " " + lvlEnergy);
	}

	/**
	 * A method printing all your stats. (energy, lectures and labs)
	 */
	public void printAllStats() {
		System.out.println(Language.STATS);
		printEnergy();
		printCurrentLecture();
		printCurrentLab();
	}

	/**
	 * A method making the player forget a lecture. Is used when the player
	 * plays video games.
	 */
	public void forgetOneRandomLecture() {
		Randomize random = new Randomize();
		if (listLecture.size() > 0) {
			int chosenRank = random.getRandomBetween0and(listLecture.size());
			String chosenElt = listLecture.get(chosenRank);
			if (listLab.contains(chosenElt)) {
				listLab.remove(chosenElt);
			}
			listLecture.remove(chosenElt);
		}
	}

	/**
	 * A method called in the room Exam, to be sure the player can take the
	 * exam.
	 * 
	 * @return true if the player can take the exam, false otherwise.
	 */
	public boolean canTakeTheExam() {
		if (this.listLab.size() == this.completeList.size()
				&& this.listLecture.size() == this.completeList.size()
				&& this.lvlEnergy >= 70) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * A method adding to the player's list of lectures every lectures he
	 * doesn't have yet. This method will be called only if the player finds the
	 * POO book in the library.
	 */
	public void addAllLecture() {
		for (String elt : completeList) {
			if (!listLecture.contains(elt)) {
				listLecture.add(elt);
			}
		}
	}

}