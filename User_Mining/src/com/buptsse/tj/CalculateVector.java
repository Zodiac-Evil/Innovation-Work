package com.buptsse.tj;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
public class CalculateVector {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		double[] ratio = new double[3];
		for(int i = 0; i < 20; i++){
			System.out.println("A1表示用户发状态频率。A2表示用户被关注数。A3表示状态被评论数");
			System.out.println("输入A1.A2.A3三种评判指标中每两两指标对于友善值的重要性之比");
			
			System.out.print("A1:A2 = ");
			@SuppressWarnings("resource")
			Scanner input_1 = new Scanner(System.in);
			System.out.println();
			ratio[0] = input_1.nextDouble();
			
			System.out.print("A1:A3 = ");
			@SuppressWarnings("resource")
			Scanner input_2 = new Scanner(System.in);
			System.out.println();
			ratio[1] = input_2.nextDouble();
			
			System.out.print("A2:A3 = ");
			@SuppressWarnings("resource")
			Scanner input_3 = new Scanner(System.in);
			System.out.println();
			ratio[2] = input_3.nextDouble();
			
			double[][] a = new double[][] { { 1, ratio[0], ratio[1] },
		            { 1 / ratio[0], 1, ratio[2]},  
		            { 1 / ratio[1], 1 / ratio[2], 1 }};
			int N = a[0].length;
			double[] weight = new double[N];
			CalculateWeight cal = new CalculateWeight();
			cal.instance.weight(a, weight, N, 3, a, weight);
			System.out.println(Arrays.toString(weight));
			
			
			FileWriter writer = new FileWriter("/Users/apple/Documents/workspace/CalculateFrienlyValue/src/WeightTest", true);
			BufferedWriter output = new BufferedWriter(writer);
			
			output.write("" + cal.instance.getCR());
			output.write(Arrays.toString(weight));
			output.newLine();
			output.close();
		}
		
	}

}
