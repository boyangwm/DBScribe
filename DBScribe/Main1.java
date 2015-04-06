import java.util.ArrayList;


public class Main1 {
	public static void main(String [] args){
		
		int[] array = new int[]{1,2,3};
		
		call(array);
		//System.out.println(array.length);
		ABC t = new ABC();
		t.val = 1;
		int a = 2+3;
		int b = a + 54;
		System.out.println(t.val);
		call2(t);
		System.out.println(t.val);
		
		
		
		
	}
	public static void call(int[] A){
		A = new int[]{2,3};
	}
	
	
	public static void call2(ABC a){
		a.val = 5;
	}
	
}
