package StudyCafe;
import java.util.Scanner;

public class Menu {
	public static void main(String[] args) {
		
		Management manage = new Management(); 
		/*management객체를 while문 안에서 생성했었는데 이렇게 하면 메뉴에서 번호를 입력받고 continue할 떄마다
		 새로 객체가 새로 생성되므로 모든 변수와 객체의 내용이 초기화됨 while문 밖에서 선언하여 내용이 이어지도록 해야함*/
		
		while(true) {
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("------------------------------------");
			System.out.println("[LaLaLa StudyRoom]");
			System.out.println("1. 입장");
			System.out.println("2. 퇴장");
			System.out.println("3. 음식주문");
			System.out.println("4. 관리자");
			System.out.println("5. 종료");
			System.out.print("--> ");
			String number = scanner.next();
			if (number.equals("1") || number.equals("2") || number.equals("3") || number.equals("4") || number.equals("5")) {
	
				switch (number) {
					case "1": manage.setIn(); continue;
					case "2": manage.setOut(); continue;
					case "3": manage.orderFood(); continue;
					case "4": manage.admin(); continue;
					case "5": System.out.print("프로그램이 종료됩니다."); 
							System.exit(0);
				}
			}
			else 
				System.out.println("문자를 입력하셨습니다.");
		}
	}
}
