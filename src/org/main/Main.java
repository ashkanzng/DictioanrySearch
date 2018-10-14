package org.main;


import org.trustpilot.Trustpilot;


public class Main {

	public static void main(String[] args) throws InterruptedException {

		Trustpilot trustpilot = new Trustpilot("wordlist");
		trustpilot.readWordList();
		int start = Integer.parseInt(args[0]);
		int stop = Integer.parseInt(args[1]);
		trustpilot.bruteForce(start,stop);

	}
}
