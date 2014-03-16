package com.buptsse.tj;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileProcess {
	private final static int user_num = 161;
	private static String[] str_01 = new String[user_num];
	
	public FileProcess() throws FileNotFoundException{
		
	}
	public String[] getStr_01() {
		return str_01;
	}
	@SuppressWarnings("static-access")
	public void setStr_01(String[] str_01) {
		this.str_01 = str_01;
	}
	@SuppressWarnings({ "resource" })
	public static void main(String[] args) throws IOException {
		int i = 0;
		
		//从高瑾的文件中读取所有的用户到数组str_01
		File file_01 = new File("/Users/apple/Documents/workspace/User_Mining/src/userTemp");
		//Reader reader = new InputStreamReader(new FileInputStream(file_01));
		Scanner scan_01 = new Scanner(file_01);
		
		
		//将目标用户存到数组
		while(scan_01.hasNextLine()){
			str_01[i] = scan_01.nextLine();
			System.out.println(str_01[i]);
			i++;
		}//end while
		
		
		
		int j = 0;
		File file_02 = new File("/Users/apple/Documents/workspace/User_Mining/src/data");
		Scanner scan_02 = new Scanner(file_02);
		String str_02;
		while(scan_02.hasNextLine()){
			int index = 0;
			str_02 = scan_02.nextLine();
			String[] attri_row = str_02.split(" ");     //将data文件中每一行数据按空格分为4个词语
			
			if(attri_row[index].equals(str_01[j])){
				FileWriter writer = new FileWriter("/Users/apple/Documents/workspace/User_Mining/src/" + str_01[j], true);
				BufferedWriter output = new BufferedWriter(writer);
				output.write(str_01[j]);
				output.write(" ");
				output.write(attri_row[++index]);
				output.write(" ");
				output.write(attri_row[++index]);
				output.write(" ");
				output.write(attri_row[++index]);
				output.newLine();
				output.close();
				j++;
			}// end if
			else{//如果没遇到j所指的用户就写入前一个用户所在文件中，即j-1对应的用户文件
				FileWriter writer = new FileWriter("/Users/apple/Documents/workspace/User_Mining/src/" + str_01[j - 1], true);
				BufferedWriter output = new BufferedWriter(writer);
				output.write(attri_row[index]);
				output.write(" ");
				output.write(attri_row[++index]);
				output.write(" ");
				output.write(attri_row[++index]);
				output.write(" ");
				output.write(attri_row[++index]);
				output.newLine();
				output.close();
			}// end else
			
		}// end while
		
	}
}
