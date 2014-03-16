package com.buptsse.tj;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CalculateAllFriendlyValue {
	
	private static final int MAX = 1426;

	@SuppressWarnings("resource")
	public void calculateFriendlyValue() throws IOException{
		User_info[] user = new User_info[MAX];
		
		File file = new File("/Users/apple/Documents/workspace/User_Mining/src/data");
		Scanner scan = new Scanner(file);
		
		int i = 0;
		while(scan.hasNextLine()){
			user[i] = new User_info();
			int index = 0;
			String str = scan.nextLine();
			String[] attri = str.split(" ");
			user[i].setFlag(true);
			user[i].setUser_id(attri[index]);
			user[i].setFans_num(Double.parseDouble(attri[++index]));
			user[i].setUnit_comment(Double.parseDouble(attri[++index]));
			user[i].setFreq_news(Double.parseDouble(attri[++index]));
			user[i].calUser_friendly_value();
			++i;
		}
		System.out.print("" + i);
		System.out.println();
		
		FileWriter filewriter = new FileWriter("/Users/apple/Documents/workspace/User_Mining/src/allUsers", true);
		BufferedWriter output = new BufferedWriter(filewriter);
		
		//java.util.Arrays.sort(user, Collections.reverseOrder());
		
		for(int j = 0; j < MAX; ++j){
			output.write(user[j].user_id, 0, 10);
			System.out.format("%-20s", user[j].user_id + "  ");
			output.write("  " + String.valueOf(user[j].getFreq_news()));
			System.out.format("%-20s", user[j].getFreq_news() + "  ");
			output.write("  " + String.valueOf(user[j].getFans_num()));
			System.out.format("%-20s", user[j].getFans_num() + "  ");
			output.write("  " + String.valueOf(user[j].getUnit_comment()));
			System.out.format("%-20s", user[j].getUnit_comment() + "  ");
			output.write("  " + String.valueOf(user[j].getUser_friendly_value()));
			System.out.format("%-20s", user[j].getUser_friendly_value() + "  ");
			System.out.println();
			output.newLine();
		}
		output.close();
	}
}
