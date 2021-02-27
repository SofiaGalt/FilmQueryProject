package com.skilldistillery.filmquery.app;

import java.util.List;

import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
		app.launch();
	}

	private void test() {
		Film film = DatabaseAccessorObject.access.findFilmById(1);
		System.out.println(film);
	}

	private void launch() {

		boolean keepRunning = true;

		while (keepRunning) {
			showMenu();
			keepRunning = executeMenuOption(UI.userInputPrompt());
		}
	}

	private void showMenu() {
		System.out.println("_________________________________________________\n\n" + "  What would you like to do? \n"
				+ "_________________________________________________\n\n" + "1 : Look up a film by its id\n"
				+ "2 : Look up a film by a search keyword\n" + "3 : Exit the application\n"
				+ "_________________________________________________\n");
	}

	/*
	 * @return boolean - returns false if the the String "3" is passed as a
	 * parameter. otherwise returns true. (executeMenuOption returns a boolean
	 * because it was designed to be used in tandem with the keepRunning boolean in
	 * launch().)
	 */
	private boolean executeMenuOption(String choice) {

		switch (choice) {

		case "1":
			lookUpFilmById();
			return true;
		case "2":
			lookUpFilmByKeyword();
			return true;
		case "3":

			return false;
		default:
			System.out.println("\nThat is not a recognized option.\n");
			UI.pressAnyKeyToContinue();
			return true;
		}
	}

	private void lookUpFilmById() {

		int intInput = 0;

		boolean keepAsking = true;

		while (keepAsking) {
			String input = UI.userInputPromptWithMessage("What is the ID of the film you are looking for?"
					+ "\n\t\t(Enter \"Exit\" to return to the Main Menu.)\n\n");

			if (input.toUpperCase().equals("EXIT"))
				return;

			try {
				intInput = Integer.parseInt(input);
				keepAsking = false;
			} catch (Exception e) {
				System.out.println("That's not a recognized input.  Please enter your response numerically.");
				UI.pressAnyKeyToContinue();
			}
		}

		Film toDisplay = DatabaseAccessorObject.access.findFilmById(intInput);

		if (toDisplay == null) {
			System.out.println(
					"No result were found for your query. It's possible that the id was out of bounds for our collection of films.");
			UI.pressAnyKeyToContinue();
			return;
		}

		System.out.println("Search Result:");
		displayFilm(toDisplay);
		UI.pressAnyKeyToContinue();
	}

	private void displayFilm(Film toDisplay) {
		System.out.println("\n title: " + toDisplay.getTitle() + "\n release date: " + toDisplay.getReleaseYear()
				+ "\n rating: " + toDisplay.getRating() + "\n description: " + toDisplay.getDescription() + "\n\n");
	}

	private void lookUpFilmByKeyword() {

		String input = UI.userInputPromptWithMessage("Enter your search terms: "
				+ "\n\t\t(Enter \"Exit\" to return to the Main Menu.)\n\n");

		if (input.toUpperCase().equals("EXIT"))
			return;

		List<Film> toDisplay = DatabaseAccessorObject.access.findFilmByKeyword(input);
		
		if (toDisplay == null || toDisplay.size() == 0 ) {
			System.out.println(
					"No result were found for your query. It's possible that the id was out of bounds for our collection of films.");
			UI.pressAnyKeyToContinue();
			return;
		}

		System.out.println("Search Results: \n");
		for(Film f : toDisplay) {
			displayFilm(f);
		}
		UI.pressAnyKeyToContinue();
	}
}
