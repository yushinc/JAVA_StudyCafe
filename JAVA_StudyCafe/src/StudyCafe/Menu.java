package StudyCafe;
import java.util.Scanner;

public class Menu {
	public static void main(String[] args) {
		
		Management manage = new Management(); 
		/*management��ü�� while�� �ȿ��� �����߾��µ� �̷��� �ϸ� �޴����� ��ȣ�� �Է¹ް� continue�� ������
		 ���� ��ü�� ���� �����ǹǷ� ��� ������ ��ü�� ������ �ʱ�ȭ�� while�� �ۿ��� �����Ͽ� ������ �̾������� �ؾ���*/
		
		while(true) {
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("------------------------------------");
			System.out.println("[LaLaLa StudyRoom]");
			System.out.println("1. ����");
			System.out.println("2. ����");
			System.out.println("3. �����ֹ�");
			System.out.println("4. ������");
			System.out.println("5. ����");
			System.out.print("--> ");
			String number = scanner.next();
			if (number.equals("1") || number.equals("2") || number.equals("3") || number.equals("4") || number.equals("5")) {
	
				switch (number) {
					case "1": manage.setIn(); continue;
					case "2": manage.setOut(); continue;
					case "3": manage.orderFood(); continue;
					case "4": manage.admin(); continue;
					case "5": System.out.print("���α׷��� ����˴ϴ�."); 
							System.exit(0);
				}
			}
			else 
				System.out.println("���ڸ� �Է��ϼ̽��ϴ�.");
		}
	}
}
