package com.buptsse.tj;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class ChooseFinal {    //选出最终用于被推送的用户，以用户ID存储
	
	public ChooseFinal() {
	}
	

	@SuppressWarnings("resource")
	public void pickFinal() throws IOException, InterruptedException {
		int user_num = 160;
		
		
		User_info[] user = new User_info[user_num];
		User_info[] user_final = new User_info[user_num];     //存储最终被挑选用来推送商品广告的用户
		User_info[] user_rest = new User_info[user_num];     //存储未被挑选的用户，用来验证是否通过挑选的用户使得这些用户也能收到商品广告,可用于以后的二次推送
		
		
		//从高瑾的文件中读取所有的用户到数组str_01
		File file_01 = new File("/Users/apple/Documents/workspace/User_Mining/src/userTemp");
		//Reader reader = new InputStreamReader(new FileInputStream(file_01));
		Scanner scan_01 = new Scanner(file_01);
		String[] str_01 = new String[user_num];
		
		//将目标用户存到数组
		int i = 0;
		while(scan_01.hasNextLine()){
			str_01[i] = scan_01.nextLine();
			i++;
		}//end while
		
		
		//将目标用户中的字段都填充完成,数据来自于已经分割好的文件
		
		for(i = 0; i < user_num; i++){
			user[i] = new User_info();
			File file = new File("/Users/apple/Documents/workspace/User_Mining/src/" + str_01[i]);
			if(file.exists()){
				Scanner scan = new Scanner(file);
				String[] friends = new String[200];
				while(scan.hasNextLine()){
					int index = 0;
					String str = scan.nextLine();
					String[] attri_row = str.split(" ");
					if(attri_row[index].equals(str_01[i])){
						user[i].setFlag(true);
						user[i].setUser_id(attri_row[index]);
						user[i].setFans_num(Double.parseDouble(attri_row[++index]));
						user[i].setUnit_comment(Double.parseDouble(attri_row[++index]));
						user[i].setFreq_news(Double.parseDouble(attri_row[++index]));
						user[i].getUser_friendly_value();
					}// end if
					else{
						friends[i] = attri_row[index];
					}// end else
				}// end while
				user[i].setUser_friends(friends);
			}//end if
		}// end for
				
		
		System.out.println("排序前：");
		for(i = 0; i < user_num; i++){
			System.out.println(user[i].getUser_id() + "  " + user[i].getUser_friendly_value());
		}
		//按照友善值的高低从大到小对对象数组进行排序
		Arrays.sort(user, Collections.reverseOrder());
		System.out.println("排序后：");
		for(i = 0; i < user_num; i++){
			System.out.println(user[i].getUser_id() + "  " + user[i].getUser_friendly_value());
		}
		
		
		//从头开始遍历用户结点，从后往前与之检查是否为其好友，如果是还有则将flag标记为false,反之亦然
		int j = 0;
		for(i = 0; i < user_num; i++){
			if(user[i].isFlag()){
				for(j = i + 1; j < user_num; j++){
					System.out.println(user[j].isFlag());
					if(user[j].isFlag()){
						for(int k = 0; k < 200; k++){
							if(user[j].getUser_id().equals(user[i].user_id)){
								user[j].setFlag(false);
								System.out.println("用户" + user[i].getUser_id() + "被移出去了！");
							}//end if
						}//end for
					}//end if
				}//end for
			}// end if
		}//end for
		
		
		//打印最终用户到文件
		FileWriter writer = new FileWriter("/Users/apple/Documents/workspace/User_Mining/src/final_users", true);
		BufferedWriter output = new BufferedWriter(writer);
		
		
		//所有flag标记为true的用户就是我们需要最终推送的用户,将他们存到user_final数组,友善值由高到低
		int k = 0;
		for(i = 0, j = 0; i < user_num; i++){
			user_final[j] = new User_info();
			if(user[i].isFlag()){
				user_final[j] = (User_info) user[i].clone();
				System.out.println(user_final[j].getUser_id());
				output.write("用户ID:" + user_final[i].getUser_id());
				output.write("  粉丝数：" + user_final[i].getFans_num());
				output.write("  单位状态被评论数：" + user_final[i].getUnit_comment());
				output.write("  发状态频率：" + user_final[i].getFreq_news());
				output.write("  友善值:" + user_final[i].getUser_friendly_value());
				output.newLine();
				j++;
			}//end if
			else{
				user_rest[k] = (User_info) user[i].clone();
				k++;
			}//end else
		}//end for
		
		output.write("以上就是针对该产品需要推送的客户群！");
		
		output.close();
	}
}