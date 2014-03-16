package com.buptsse.tj;
import java.math.BigDecimal;

public class CalculateWeight {
	//a为N*N矩阵
	  
      
    
    
    private static final CalculateWeight acw = new CalculateWeight();//单例
	
	public CalculateWeight instance = CalculateWeight.getInstance();
	private double lamta = 0.0;//最大特征值
	private double CR = 0.0;//随机一致性比率
	private double[] RI = {0.00, 0.00, 0.58, 0.90, 1.12, 1.21, 1.32, 1.41};//平均随机一致性指针
	
	public CalculateWeight(){//构造函数
	}
	
	public void weight(double[][] a2, double[] weight2, int n2, int N, double[][] a, double[] weight) {
		double[] w0 = new double[N];//初始向量Wk
		for(int i = 0; i < N; i++)
			w0[i] = 1.0 / N;
		
		double[] w1 = new double[N];//一般向量W(k+1)
		double[] w2 = new double[N];//w(k+1)的归一化向量
		double sum = 1.0;
		double d = 1.0;
		
		double delt = 0.00001;//误差
		
		while(d > delt){
			d = 0.0;
			sum = 0;
			
			for(int j = 0; j < N; j++){//获取向量
				double t = 0.0;
				for(int l = 0; l < N; l++)
					t += a[j][l]*w0[l];
				w1[j] = t;
				sum += w1[j];
			}
			
			for(int k = 0; k < N; k++){//向量归一化
				w2[k] = w1[k]/sum;
				
				d = Math.max(Math.abs(w2[k] - w0[k]), d);//最大差值
				w0[k] = w2[k];//用于下次迭代使用
			}
		}
		//计算矩阵最大特征值lamta, CI, RI
		lamta  = 0.0;
		for(int k = 0; k < N; k++)
			lamta += w1[k]/(N*w0[k]);
		
		double CI = (lamta - N)/(N - 1);
		
		if(RI[N - 1] != 0)
			CR = CI/RI[N - 1];
		//四舍五入处理
		lamta = round(lamta, 3);
		CI = round(CI, 3);
		CR = round(CR, 3);
		
		for(int i = 0; i < N; i++){
			w0[i] = round(w0[i], 4);
			w1[i] = round(w1[i], 4);  
            w2[i] = round(w2[i], 4);
		}
		//控制打印台输出
		System.out.println("最大特征值lamta=" + lamta);  
        System.out.println("一致性指标CI=" + CI);  
        System.out.println("一致性比率CR=" + CR);
        //控制台打印权重
        System.out.print("w0[]=");  
        for (int i = 0; i < N; i++) {  
            System.out.print(w0[i] + " ");  
        }  
        System.out.println("");  
  
        System.out.print("w1[]=");  
        for (int i = 0; i < N; i++) {  
            System.out.print(w1[i] + " ");  
        }  
        System.out.println("");  
  
        System.out.print("w2[]=");  
        for (int i = 0; i < N; i++) {  
            weight[i] = w2[i];  
            System.out.print(w2[i] + " ");  
        }  
        System.out.println("");
        
	}

	private double round(double v, int scale) {//四舍五入
		if(scale < 0){
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public static CalculateWeight getInstance() {
		return acw;
	}
	
	
	public double getCR(){//返回随机一致性比率
		return CR;
	}
}
