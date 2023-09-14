package com.hackerrank.springboot.tests;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HackerrankApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackerrankApplication.class, args);
		String s = "12:05:39AM";
		String split[] = s.substring(0, s.length() - 2).split(":");
		//Si es am o pm
		char amPm = s.charAt(s.length()-2);
		//HORA en 0, MINUTOS EN 1, SEGUNDOS EN 2 
		//System.out.println(split[0] + " " + split[1] + " " + split[2]);
		if (amPm == 'A') { 
				if (split[0].equals("12"))  split[0] = "00";
		}
		else {
			if (split[0].equals("12")) split[0] = String.valueOf(Integer.parseInt(split[0]) + 12);
		}
		
		System.out.println(split[0]+":"+split[1]+":"+split[2]);
	}

}