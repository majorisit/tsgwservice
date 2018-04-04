package com.example;

import java.io.BufferedReader;
import java.io.FileReader;

public class EmailFilter {
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new FileReader("C://AryaSuryaHome//TamilSangam//evite//evite4html.txt"));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		        if(line.contains("@"))
			    System.out.println(line);	
		    }
		    String everything = sb.toString();
		} finally {
		    br.close();
		}		
	}
}
