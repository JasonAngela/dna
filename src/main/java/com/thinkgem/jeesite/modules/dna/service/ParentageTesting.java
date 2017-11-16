package com.thinkgem.jeesite.modules.dna.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.thinkgem.jeesite.modules.dna.entity.DnaGeneFrequency;
import com.thinkgem.jeesite.modules.dna.entity.ParentageTestingEntity;

/**
 * 亲权指数计算
 * @author doonly
 */
public class ParentageTesting {
	public static  double u = 0.002;
	/**
	 * 孩子
	 * @param c 孩子 p 父亲|母亲
	 * @return
	 */
	public static Object[] duos(ParentageTestingEntity c,ParentageTestingEntity p){
		
			return duosNormal(c,p);
		
	}
	//符合遗传规律的
	private static Object[] duosNormal(ParentageTestingEntity c, ParentageTestingEntity p) {
		Object[] result = new Object[6];
		
		 
		//ab是父亲  cd是孩子
		
 		double n1 =  Math.abs(sub( Double.valueOf(p.getFirst().getValue()),Double.valueOf(c.getFirst().getValue())));//a-c  pc                    
		double n2 =  Math.abs(sub(Double.valueOf(p.getFirst().getValue()),Double.valueOf(c.getSecond().getValue())));//a-d  pd
		double n3 =  Math.abs(sub(Double.valueOf(p.getSecond().getValue()) ,Double.valueOf(c.getFirst().getValue())));//b-c   pc
		double n4 =  Math.abs(sub(Double.valueOf(p.getSecond().getValue()) , Double.valueOf(c.getSecond().getValue())));//b-d  pd
		
		
		double B[]={n1,n2,n3,n4};  // 声明整数数组A,并赋初值  
		
		List<Double>li=new ArrayList<Double>();
		
		for (Double double1 : B) {
			if(double1==0.0||double1==0){
				li.add(double1);
			}  
		}
		
		result[3]=c.getFirstProbability();
		result[4]=c.getSecondProbability();
		
		
if(li.size()==1){		
		 
		
		//有且仅有一个为0  1/4pc||4pd   pc为p  pd为q
  		 if(n1==0.0&&n2!=0.0&&n3!=0.0&&n4!=0.0){
			 result[0] = "1/(4p)";
			 result[1] = 1/(4*c.getFirstProbability());
			 result[2] = "1/(4*"+c.getFirstProbability()+")";
			 result[5]=0.0;
		  }
		 
		 if(n2==0.0&&n1!=0.0&&n3!=0.0&&n4!=0.0){
			 result[0] = "1/(4q)";
			 result[1] = 1/(4*c.getSecondProbability());
			 result[2] = "1/(4*"+c.getSecondProbability()+")";
			 result[5]=0.0;
		  }
		 
		 if(n3==0.0&&n2!=0.0&&n1!=0.0&&n4!=0.0){
			 result[0] = "1/(4p)";
			 result[1] = 1/(4*c.getFirstProbability());
			 result[2] = "1/(4*"+c.getFirstProbability()+")";
			 result[5]=0.0;
		  }
		 
		 if(n4==0.0&&n2!=0.0&&n3!=0.0&&n1!=0.0){
			 result[0] = "1/(4q)";
			 result[1] = 1/(4*c.getSecondProbability());
			 result[2] = "1/(4*"+c.getSecondProbability()+")";
			 result[5]=0.0;
		  }
		 return result;
}	
		 /*有且仅有两个0 
			 * n1=n4=0  1/4pc+1/4pd;
			 * n2=n4=0  1/4pd  
			 * n2=n1=0  1/4pc+1/4pd;
			 * n1=n3=0  1/4pc 
			 * n3=n4=0  1/4pc+1/4pd;
			 * 
			 */
		
else if(li.size()==2){
	double pcd2=0;
	String pc2="";
	String pc3="";
		if(n1==0.0){
			pcd2+=1/(4*c.getFirstProbability());
			 pc2+="+1/4p";
			 pc3+="+1/"+"4("+c.getFirstProbability() +")";
		}
		if(n2==0.0){
			pcd2+=1/(4*c.getSecondProbability());
			 pc2+="+1/4q"; 
			 pc3+="+1/"+"4("+c.getSecondProbability() +")";
			 
		}
		
		if(n3==0.0){
				pcd2+=1/(4*c.getFirstProbability());
				pc2+="+1/4p";
				 pc3+="+1/"+"4("+c.getFirstProbability() +")";
		}
		
		if(n4==0.0){
				pcd2+=1/(4*c.getSecondProbability());
				 pc2+="+1/4q";
				 pc3+="+1/"+"4*("+c.getSecondProbability() +")";
		}
	    
		
		 pc2=pc2.substring(1, pc2.length());
		 pc3=pc3.substring(1, pc3.length());
		result[0] = pc2;
		result[1] =pcd2;
		result[2] =pc3;
		 result[5]=0.0;
				return result;
}else if(li.size()==4){
		 result[0] = "1/p";
			result[1] = 1/c.getFirstProbability();
			result[2] = "1/"+c.getFirstProbability();
			 result[5]=0.0;
			return result;
}
		 /* 大于0的整数
			 *   abcd
			 * 取它的pc||pd 
			 * 
			 * 如果有一个最小整数     1/4pc||4pd * μ/2*10 ^n-1
			 * 
			 * 如果有两个最小整数     (1/4pc||4pd+1/4pc||4pd) * μ/2*10 ^n-1
			 */
else {	 
		 if(p.getAppellation().contains("母亲")){
				DecimalFormat    df   = new DecimalFormat("######0.0000");
					u= Double.valueOf( df.format(0.0005));
				}else{
					u=0.002;
				}

			double A[]={n1,n2,n3,n4};  // 声明整数数组A,并赋初值  
			
			List<Double>list=new ArrayList<Double>();
			for (double e : A) {
				if(e!=0.0&&isIntegerForDouble(e)==true){
					list.add(e);
				}
			}
			double min=0.0;
				if(list.size()>0){
				 min=list.get(0);
				for(int i=0;i<list.size();i++){  
					if(isIntegerForDouble(list.get(i))==true){
						if(list.get(i)<min){   
							min=list.get(i); 
						} 
					}
				}
				}else{
				        double min1=A[0]; // 把数据中的第1个元素存min
				        for(int w=1;w<A.length;w++){ // 从第二个元素开始遍历数组
				            if(A[w]<min1){  // 假如元素小于min 就把当前值赋值给min
				                min1=A[w];
				            }
				        }
				        min=min1;
			}
			double pcd=0;
			String pc="";
			
		 if(min>=1.0&&isIntegerForDouble(min)==true){
				 if(n1==min){
					 pcd+=1/(4*c.getFirstProbability());
					 pc+="+1/4p";
				 }
				 if(n2==min){
					 pcd+=1/(4*c.getSecondProbability());
					 pc+="+1/4q";
				 }
				 if(n3==min){
					 pcd+=1/(4*c.getFirstProbability());
					 pc+="+1/4p";
				 }
				 if(n4==min){
					 pcd+=1/(4*c.getSecondProbability());
					 pc+="+1/4q";
				 }                                         
				 pc="("+pc.substring(1, pc.length())+")*";
				 	result[0] = pc+"μ/("+"2*10^(n-1)"+")";
				 	
					try {
						//5.296610169491526
						result[1]=mul(pcd,divide(u,mul(2,Math.pow(10, min-1)),0));
					} catch (IllegalAccessException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				 	
					 
					result[2] =pcd+ "*u"
							+"/("+2*Math.pow(10, min-1)+")"+"n="+min ;
					 result[5]=min;
				 return result;
		   }else{
			   result[0]="u";
			   result[1]=u;
			   result[2]="u"+"n="+min;
			   result[5]=min;
			   return result; 
		   }
		 
		}	 

	}
		 
		 
		//n1  n2  n3  n4  取其中最小值
		
		
		//都是0  1/pc
		 
		//有且仅有一个为0  1/4pc||4pd
		
		/*有且仅有两个0 
		 * n1=n4=0  1/4pc+1/4pd;
		 * n2=n4=0  1/4pd  
		 * 
		 * n1=n3=0  1/4pc 
		 */
		
		
		/* 大于0的整数
		 * 
		 * 取它的pc||pd 
		 * 
		 * 如果有一个最小整数     1/4pc||4pd * μ/2*10 ^n-1
		 * 
		 * 如果有两个最小整数     (1/4pc||4pd+1/4pc||4pd) * μ/2*10 ^n-1
		 */
		
		
	
	public static String[] trios(int[]x){
		return null;
	}

	public static void main(String[] args) {

		
		try {
			System.out.println( mul(5.296610169491526,divide(0.0005,mul(2,Math.pow(10, 11-1)),0)));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	System.out.println();
	}

	//判断是否为整数
	public  static  boolean isIntegerForDouble(double obj) {  
	    double eps = 1e-10;  // 精度范围  
	    return obj-Math.floor(obj) < eps;  
	}  
	
	

	public static Double add(Double v1,Double v2){
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		
		
		return b1.add(b2).doubleValue();
	
		}
		//两个Double数相减
	public static Double sub(Double v1,Double v2){
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return b1.subtract(b2).doubleValue();
	}
	                                                                                                                                                                                                                                                                                                                                                           
	
	
	
	//相减
	public static double mul(double value1,double value2){
	BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
	 BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
	return b1.multiply(b2).doubleValue();
	}
	
//相除
	public static double divide(double value1,double value2,int scale) throws IllegalAccessException{
	if(scale<0){
	throw new IllegalAccessException("精确度不能小于0");
	}
	 BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
	BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
	return b1.divide(b2, scale).doubleValue();    
		}
}
