package com.skilldistillery.filmquery.app;

import java.util.Scanner;

public class UI {

	private static Scanner in = new Scanner(System.in);
	
	public static String userInputPrompt() {
		System.out.print("\n\tEnter your choice: ");
		String toReturn = in.nextLine();
		System.out.println();
		return toReturn;
	}
	
	public static String userInputPromptWithMessage(String message) {
		System.out.print("\n" + message + " : ");
		String toReturn = in.nextLine();
		System.out.println();
		return toReturn;
	}
	
	public static void pressAnyKeyToContinue() {
		System.out.print("\n\tEnter any key to continue : ");
		in.nextLine();
		System.out.println();
	}
	
}
