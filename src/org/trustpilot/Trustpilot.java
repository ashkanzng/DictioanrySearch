package org.trustpilot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.net.URL;

public class Trustpilot {

	private String file;
	private List<String> wordlists;
	private final String keyword = "poultryoutwitsants";

	/**
	 * Constructor.
	 * 
	 * @param file (required) file name wordlist
	 * 
	 * 
	 */
	public Trustpilot(String file) {
		ClassLoader classLoader = getClass().getClassLoader();
		URL resource = classLoader.getResource(file);
		this.file = resource.getFile();
	}

	/** 
	 * 
	 * @param variable this.wordlists filled by wordlist" 
	 */
	public void readWordList() {
		List<String> wordlist = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.file));
			String line;
			while ((line = br.readLine()) != null) {
				if (check_letter(line)) {
					wordlist.add(line);
				}
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		this.wordlists = wordlist;
	}

	
	/** 
	 * 
	 * @param check letter of word  
	 */
	public boolean check_letter(String str) {
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (this.keyword.indexOf(c) == -1) {
				return false;
			}
		}
		return true;
	}

	
	/** 
	 * 
	 * @param Convert string to MD5  
	 */
	public String toHash(String word) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] hashInBytes = md.digest(word.getBytes(StandardCharsets.UTF_8));
		StringBuilder sb = new StringBuilder();
		for (byte b : hashInBytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}

	
	

	/** 
	 * This method dictionary search
	 * @param int start
	 * @param int stop
	 */
	public void bruteForce(int start, int stop) throws InterruptedException {

		String key_easiest = "e4820b45d2277f3844eac66c903e84be";
		String key_difficult = "23170acc097c24edb98fc5488ab033fe";
		String key_hard = "665e5bcb0c20062fe8abaaf4628bb154";

		int x_loop = 0;

		for (String i : wordlists) {
			x_loop++;
			if (x_loop > start && x_loop < stop) {
				Thread t1 = new Thread(new Runnable() {
					@Override
					public void run() {
						long threadId = Thread.currentThread().getId();
						try {
							int c = 0;
							for (String j : wordlists) {
								System.out.println(i + " " + j + " Count:" + c + "  Thread " + threadId);
								c++;
								for (String x : wordlists) {
									String rs = i.trim() + " " + j.trim() + " " + x.trim();
									String rs_hash = toHash(rs);
									if (rs_hash.equals(key_easiest)) {
										System.out.println(rs + " " + rs_hash);
									}
									if (rs_hash.equals(key_difficult)) {
										System.out.println(rs + " " + rs_hash);
									}
									if (rs_hash.equals(key_hard)) {
										System.out.println(rs + " " + rs_hash);
									}
								}
							}

						} catch (Exception e) {
							System.out.println(e);
						}
					}
				});
				t1.start();
			}
		}
	}

}
