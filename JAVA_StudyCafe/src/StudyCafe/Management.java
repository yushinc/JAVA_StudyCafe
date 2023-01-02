package StudyCafe;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Management {
	Scanner scanner = new Scanner(System.in);
	Customer cst[] = new Customer[10]; //사용자 정보를 저장하는 Customer 객체 배열
	private int currentNo = 0; //입장하는 손님이 몇 번째인지 저장하는 변수 퇴장시 --, 입장시 ++ 하여 실제 손님 수를 셈
	
	int index = 0; // currentNo와는 별개의 인덱스 !!!! 추가 퇴장여부와 관계없이 계속 증가하여 배열을 반복
	
	SeatManager seatMan = new SeatManager();
	Payment pay = new Payment();
	//String foodList[][]; 어디에 사용??
	
	Date date = new Date(); // 현재 시간 저장 객체 생성
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
	
	int seatID, x, y;
	String name;
	
	
	
	Management() {
		for(int i = 0; i < 10; i++) {
			cst[i] =  new Customer(name);
			cst[i].in = 0;
		}
		
	}

	
	
	void setIn() { 
		if (currentNo < 10) {
			
			for (int i = 0; i < cst.length; i++) {
				if (cst[i].in == 0) { // 중간에 비어있는 곳을 찾기 위해

					System.out.print("이름을 입력하세요: ");
					cst[i].name = scanner.next();
					seatMan.print();
					
					System.out.println("현재 남은 좌석은 " + (10 - currentNo) + "석 입니다.(V:빈좌석 / C:찬좌석)");
					
					System.out.print("원하는 좌석을 고르세요: ");
					cst[i].seatID = scanner.nextInt();
					
					if (cst[i].seatID < 26 && cst[i].seatID > 10) { //범위를 벗어나지 않으면 자리 배정 실행
						
						x = cst[i].seatID / 10;
						y = cst[i].seatID % 10;
						seatMan.setSeat(x, y);
					
						
						cst[i].in = 1; // 음식 주문 시 필요한 if문 조건을 맞추기 위해
						seatMan.print(); 
						
						cst[i].startTime = System.currentTimeMillis(); // 1970년 부터 몇 ms가 지났는지 계산
						String formatDate = sdf.format(cst[i].startTime); // 시간을 원하는 형태로 변경

						System.out.println();
						System.out.println("입장시간: " + formatDate);
						
						if (index == 9) { // currentNo++ 반복하여 배열의 개수를 넘어가면 
							index = 0;
							currentNo++;
						} 
						else {
							currentNo++; // 10명 카운트
							index++;
						}
					
						i = cst.length - 1;
						
					}	
					else if (cst[i].seatID < 3 && cst[i].seatID > 0)
						System.out.println("좌석번호를 잘못 입력하셨습니다.");
					else 
						System.out.println("없는 좌석입니다.");
						i = cst.length - 1;
				}
				else 
					continue;
			}
		}
	}
	
	
	void setOut() {
		System.out.print("이름을 입력하세요: ");
		String name2 = scanner.next();
		int n = 0;
		for(int i = 0; i < cst.length; i++) {

			if(name2.equals(cst[i].name)) { // ==는 주소값 비교, equals는 값 비교
				x = cst[i].seatID / 10;
				y = cst[i].seatID % 10;
				System.out.println("당신의 좌석번호는 " + cst[i].seatID + "입니다.");
				seatMan.releaseSeat(x, y);
				System.out.println("해제되었습니다.");
				System.out.println();
					
				cst[i].in = 0;
				
				n = 1;
				
				cst[i].endTime = System.currentTimeMillis(); // 1970년 부터 몇 ms가 지났는지 계산
					
				String formatDate = sdf.format(cst[i].endTime); // 시간을 원하는 형태로 변경
	
				System.out.println("퇴장시간: " + formatDate);
					
				System.out.println("사용 시간: " + ((int)((cst[i].endTime - cst[i].startTime) / 60000)) + "분 " 
									+ ((int)((Math.ceil((cst[i].endTime - cst[i].startTime) % 60000 / 1000))) + "초"));
					
				pay.calculateFee(cst[i].endTime, cst[i].startTime);
					
				System.out.println("시간 사용 요금: " + pay.total_Income); // 시간 요금 계산
					
				cst[0].allTime += pay.total_Income; //각 손님마다 시간 요금 저장하기 위해 임의로 0번 배열에 저장
					
				System.out.println("음식 요금: " + cst[i].foodPay);
				System.out.println("총 요금: " + ((cst[i].foodPay) + pay.total_Income));
	
				
				if (index == 9) {
					index = 0;
					if (currentNo == 0)
						currentNo = 0; // 퇴장 후 0인 상태에서 --되므로 
					else
						currentNo--;
				} else {
					currentNo--;
					continue;
				}
			}
			else if (i == cst.length - 1 && n == 0)
				System.out.println("찾는 이름이 없습니다.");
			else 
				continue;
			
		}
	}
	
	void admin() {
		System.out.println("1. 현재 좌석 상태 보기");
		System.out.println("2. 전체 좌석 리셋 하기");
		System.out.println("3. 손님 현황 보기");
		System.out.println("4. 총 수입 확인하기");
		System.out.print("--> ");
		int number = scanner.nextInt();
		
		switch (number) { 
			case 1:
				System.out.print("[현재 좌석 상태]");
				seatMan.print();
				System.out.println();
				System.out.println("찬 좌석: " + currentNo);
				System.out.println("남은 좌석: " + (10 - currentNo));
				break;
			case 2:
				
				for(int i = 0; i < cst.length; i++) {
					if (cst[i].in == 1) {
						cst[i].endTime = System.currentTimeMillis(); 
						String formatDate = sdf.format(cst[i].endTime); 
		
						System.out.println(cst[i].name + "님, 좌석번호: " + cst[i].seatID);
						System.out.println("퇴장시간: " + formatDate);
							
						System.out.println("사용 시간: " + ((int)((cst[i].endTime - cst[i].startTime) / 60000)) + "분 " 
								+ ((int)((Math.ceil((cst[i].endTime - cst[i].startTime) % 60000 / 1000))) + "초"));
				
						pay.calculateFee(cst[i].endTime, cst[i].startTime);
				
						System.out.println("시간 사용 요금: " + pay.total_Income); // 시간 요금 계산
							
						cst[0].allTime += pay.total_Income; //각 손님마다 시간 요금 저장하기 위해 임의로 0번 배열에 저장
							
						System.out.println("음식 요금: " + cst[i].foodPay);
						System.out.println("총 요금: " + ((cst[i].foodPay) + pay.total_Income));
						
						System.out.println();
					
					}	
					else 
						continue;
				}
				
					
				index = 0;
				currentNo = 0;
				
				for (int i = 0; i < cst.length; i++) {
					cst[i].name = null;
					cst[i].seatID = 0;
					cst[i].in = 0;
				}
				seatMan.clear();
				
				break;
				
				
			case 3:
				if (cst[0].in == 1 || cst[1].in == 1
						|| cst[2].in == 1 || cst[3].in == 1
						|| cst[4].in == 1 || cst[5].in == 1
						|| cst[6].in == 1 || cst[7].in == 1 
						|| cst[8].in == 1 || cst[9].in == 1) {
					System.out.println("좌석번호     이름");
					System.out.println("-----------------");
					for (int i = 0; i < cst.length; i++) {
						if (cst[i].in == 1) {
							System.out.print(cst[i].seatID + "        ");
							System.out.print(cst[i].name);
							System.out.println();
						}
						 else 
							continue;
					} break;
				}
				else {
					System.out.println("손님이 없습니다.");
					break;
				}
				
			case 4:
				int allFood = 0;
				
				for (int i = 0; i < cst.length; i++) {
					if (cst[i].in == 0) {
						
						allFood += cst[i].foodPay;
					} // 퇴장 전에는 음식 요금 누적 안함 퇴장해야 누적 수정 !!!!!!!
				}
				System.out.println("음식 수익: " + allFood);
				System.out.println("시간 수익: " + cst[0].allTime);
				System.out.println("총 수익: " + (allFood + cst[0].allTime));
				break;
		}
			
	}
	
	void orderFood() {
	
		
		System.out.print("좌석번호를 입력하세요: ");
		int seatID2 = scanner.nextInt(); //좌석번호 입력받기
		
		for (int i = 0; i < cst.length; i++) {
			if (cst[i].in == 1) {
				if (seatID2 == cst[i].seatID) {
					System.out.println("1: 샌드위치          5000");
					System.out.println("2: 김치볶음밥         6500");
					System.out.println("3: 핫도그            3000");
					System.out.println("4: 아이스아메리카노     2500");
					System.out.println("5: 콜라, 사이다       1500");
					System.out.println("6: 아이스크림         1200");
					System.out.println("7: 생수             800");
					System.out.println();

					boolean a = true;
					
					while(a) {
						System.out.print("원하는 메뉴의 번호를 한 번에 하나씩 입력하세요.(그만 주문하시려면 99 입력): ");
						int foodNum = scanner.nextInt();
						
						if (foodNum > 0 && foodNum < 8 || foodNum == 99) {
							switch (foodNum) {
								case 1: System.out.println("샌드위치 선택: 5000원"); cst[i].foodPay += 5000; continue;
								case 2: System.out.println("김치볶음밥 선택: 6500원"); cst[i].foodPay += 6500; continue;
								case 3: System.out.println("핫도그 선택: 3000원"); cst[i].foodPay += 3000; continue; 
								case 4: System.out.println("아이스아메리카노 선택: 2500원"); cst[i].foodPay += 2500; continue;
								case 5: System.out.println("콜라, 사이다 선택: 1500원"); cst[i].foodPay += 1500; continue;
								case 6: System.out.println("아이스크림 선택: 1200원"); cst[i].foodPay += 1200; continue;
								case 7: System.out.println("생수 선택: 800원"); cst[i].foodPay += 800; continue;
								case 99: System.out.println("총 금액: " + cst[i].foodPay); a = false; i = cst.length - 1; // for문 반복하여 출력되기 때문
							}
						}
						else
							System.out.println("없는 메뉴입니다.");					
					}
				}
			}
			else {
				i = cst.length - 1; // for문 반복하여 출력되기 때문
				System.out.println("입장 후 주문이 가능합니다.");
			}	
		}
	}
}
//퇴장 후 admin 현재좌석확인 시 좌석 상태 리셋됨 오류
//현재좌석확인 후 입장시 좌석 상태 리셋됨 오류


	

