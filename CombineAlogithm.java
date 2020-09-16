package main;

public class CombineAlogithm {  
     
	public void myMethod(Character c) {  
	  System.out.println("My Character");  
	}  

	public void myMethod(Object o) {  
	  System.out.println("My Object");  
	}  
	
	
   //获得不重复的随机数数组，取值范围[min,max)，个数size  
   public static int[] getRandomIntWithoutReduplicate( int min, int max, int size )  
   {  
     int[] result = new int[size];//用于存储结果的数组  
     int arraySize = max - min;//用于放"牌"的数组大小  
     int[] intArray = new int[arraySize];//用于放"牌"的数组  
  // 初始化"牌盒"，比如取值范围是[3,10)则"牌盒"里放的"牌"就是3，4，5，6，7，8，9  
   for( int i = 0 ; i < intArray.length ; i++ )  
     {  
       intArray[i] = i + min;  
     }  
    // 获取不重复的随机数数组  
    for( int i = 0 ; i < size ; i++ )  
    {  
       int c = getRandomInt( min, max - i );//获取到一个随机数  
       int index = c - min;//这个随机数在"牌盒"里的位置  
       swap( intArray, index, arraySize - 1 - i );//将这张"牌"放到"牌盒"的最后面  
      result[i] = intArray[ arraySize - 1 - i ];//把这张"牌"的值扔到存储结果的数组里  
     }  
    return result;  
   }  
   //获取随机数，随机数取值范围为[min, max)  
   public static int getRandomInt( int min, int max )  
   {  
     // include min, exclude max  
     int result = min + new Double( Math.random() * ( max - min ) ).intValue();  
   
   return result;  
  }  
     
   private static void swap( int[] array, int x, int y )  
  {//交换数组arry, 序号x与序号y值的顺序  
     int temp = array[x];  
     array[x] = array[y];  
    array[y] = temp;  
   }  

   
    public static void main(String args[]){  
	    //判断null是Object还是Character
        Test t = new Test();  
        t.myMethod(null);  
        System.out.println("============Random Test Begin===========");
		int[] aaa = getRandomIntWithoutReduplicate(1,33,6);
		for(int i=0;i<aaa.length;i++){
			System.out.println(aaa[i]);
		}
        System.out.println("============Random Test End=============");
    }  
}
