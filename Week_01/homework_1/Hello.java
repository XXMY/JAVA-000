public class Hello {
	public int add(int arg_1, int arg_2) {
		return arg_1 + arg_2;
	}

	public double subtract(double arg_1, double arg_2) {
		return arg_1 - arg_2;
	}

	public int multiply(int arg_1, int arg_2) {
		return arg_1 * arg_2;
	}	

	public double divide(double arg_1, double arg_2) {
		String str = "hello";
		if (arg_2 == 0) 
			return 0;
		return arg_1 / arg_2;
	}

	public static void main(String[] args) {
		Hello hello = new Hello();
		System.out.println(hello.add(100 , 101));
		System.out.println(hello.subtract(20.01d , 10.54d));
		System.out.println(hello.multiply(1, 128));
		System.out.println(hello.divide(4300.22d, 2.00d));
		
		// 对于有符号整型数据，-1~5 会在指令上，-32768~32767
		// 的其他数据会在操作数上，超过这个范围的数据会在常量池上
		int im100000 = -100000;
		int im32769 = -32769;
		int im32768 = -32768;
		int im32767 = -32767;
		int im129 = -129;
		int im128 = -128;
		int im127 = -127;
		int im2 = -2;
		int im1 = -1;
		int i0 = 0;
		int i1 = 1;
		int i2 = 2;
		int i3 = 3;
		int i4 = 4;
		int i5 = 5;
		int i6 = 6;
		int i100 = 100;	
		int i127 = 127;
		int i128 = 128;
		int i129 = 129;
		int i32767 = 32767;
		int i32768 = 32768;
		int i32769 = 32769;
		String str = "hello";
		for (int i=100; i< 10; i++) {
			System.out.println(hello.add(i, i+1));
		}
	}
}
