package org;


import java.util.Scanner;

import org.XmlParser;

public class Zork {
	public static void main(String[ ] args){
		System.out.println("Welcome to Zork");
		System.out.println("Please provide a world in xml file:");
		Scanner in= new Scanner(System.in);
		String inputfile = in.nextLine();
		Mymap newworld = XmlParser.Readxml(inputfile);
		if(newworld.Items!=null){
			Gameloop newgame = new Gameloop(newworld);
			newgame.start();
		}
		
	}
}
