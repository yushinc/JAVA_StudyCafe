package StudyCafe;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Management {
	Scanner scanner = new Scanner(System.in);
	Customer cst[] = new Customer[10]; //����� ������ �����ϴ� Customer ��ü �迭
	private int currentNo = 0; //�����ϴ� �մ��� �� ��°���� �����ϴ� ���� ����� --, ����� ++ �Ͽ� ���� �մ� ���� ��
	
	int index = 0; // currentNo�ʹ� ������ �ε��� !!!! �߰� ���忩�ο� ������� ��� �����Ͽ� �迭�� �ݺ�
	
	SeatManager seatMan = new SeatManager();
	Payment pay = new Payment();
	//String foodList[][]; ��� ���??
	
	Date date = new Date(); // ���� �ð� ���� ��ü ����
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy�� MM�� dd�� HH�� mm�� ss��");
	
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
				if (cst[i].in == 0) { // �߰��� ����ִ� ���� ã�� ����

					System.out.print("�̸��� �Է��ϼ���: ");
					cst[i].name = scanner.next();
					seatMan.print();
					
					System.out.println("���� ���� �¼��� " + (10 - currentNo) + "�� �Դϴ�.(V:���¼� / C:���¼�)");
					
					System.out.print("���ϴ� �¼��� ������: ");
					cst[i].seatID = scanner.nextInt();
					
					if (cst[i].seatID < 26 && cst[i].seatID > 10) { //������ ����� ������ �ڸ� ���� ����
						
						x = cst[i].seatID / 10;
						y = cst[i].seatID % 10;
						seatMan.setSeat(x, y);
					
						
						cst[i].in = 1; // ���� �ֹ� �� �ʿ��� if�� ������ ���߱� ����
						seatMan.print(); 
						
						cst[i].startTime = System.currentTimeMillis(); // 1970�� ���� �� ms�� �������� ���
						String formatDate = sdf.format(cst[i].startTime); // �ð��� ���ϴ� ���·� ����

						System.out.println();
						System.out.println("����ð�: " + formatDate);
						
						if (index == 9) { // currentNo++ �ݺ��Ͽ� �迭�� ������ �Ѿ�� 
							index = 0;
							currentNo++;
						} 
						else {
							currentNo++; // 10�� ī��Ʈ
							index++;
						}
					
						i = cst.length - 1;
						
					}	
					else if (cst[i].seatID < 3 && cst[i].seatID > 0)
						System.out.println("�¼���ȣ�� �߸� �Է��ϼ̽��ϴ�.");
					else 
						System.out.println("���� �¼��Դϴ�.");
						i = cst.length - 1;
				}
				else 
					continue;
			}
		}
	}
	
	
	void setOut() {
		System.out.print("�̸��� �Է��ϼ���: ");
		String name2 = scanner.next();
		int n = 0;
		for(int i = 0; i < cst.length; i++) {

			if(name2.equals(cst[i].name)) { // ==�� �ּҰ� ��, equals�� �� ��
				x = cst[i].seatID / 10;
				y = cst[i].seatID % 10;
				System.out.println("����� �¼���ȣ�� " + cst[i].seatID + "�Դϴ�.");
				seatMan.releaseSeat(x, y);
				System.out.println("�����Ǿ����ϴ�.");
				System.out.println();
					
				cst[i].in = 0;
				
				n = 1;
				
				cst[i].endTime = System.currentTimeMillis(); // 1970�� ���� �� ms�� �������� ���
					
				String formatDate = sdf.format(cst[i].endTime); // �ð��� ���ϴ� ���·� ����
	
				System.out.println("����ð�: " + formatDate);
					
				System.out.println("��� �ð�: " + ((int)((cst[i].endTime - cst[i].startTime) / 60000)) + "�� " 
									+ ((int)((Math.ceil((cst[i].endTime - cst[i].startTime) % 60000 / 1000))) + "��"));
					
				pay.calculateFee(cst[i].endTime, cst[i].startTime);
					
				System.out.println("�ð� ��� ���: " + pay.total_Income); // �ð� ��� ���
					
				cst[0].allTime += pay.total_Income; //�� �մԸ��� �ð� ��� �����ϱ� ���� ���Ƿ� 0�� �迭�� ����
					
				System.out.println("���� ���: " + cst[i].foodPay);
				System.out.println("�� ���: " + ((cst[i].foodPay) + pay.total_Income));
	
				
				if (index == 9) {
					index = 0;
					if (currentNo == 0)
						currentNo = 0; // ���� �� 0�� ���¿��� --�ǹǷ� 
					else
						currentNo--;
				} else {
					currentNo--;
					continue;
				}
			}
			else if (i == cst.length - 1 && n == 0)
				System.out.println("ã�� �̸��� �����ϴ�.");
			else 
				continue;
			
		}
	}
	
	void admin() {
		System.out.println("1. ���� �¼� ���� ����");
		System.out.println("2. ��ü �¼� ���� �ϱ�");
		System.out.println("3. �մ� ��Ȳ ����");
		System.out.println("4. �� ���� Ȯ���ϱ�");
		System.out.print("--> ");
		int number = scanner.nextInt();
		
		switch (number) { 
			case 1:
				System.out.print("[���� �¼� ����]");
				seatMan.print();
				System.out.println();
				System.out.println("�� �¼�: " + currentNo);
				System.out.println("���� �¼�: " + (10 - currentNo));
				break;
			case 2:
				
				for(int i = 0; i < cst.length; i++) {
					if (cst[i].in == 1) {
						cst[i].endTime = System.currentTimeMillis(); 
						String formatDate = sdf.format(cst[i].endTime); 
		
						System.out.println(cst[i].name + "��, �¼���ȣ: " + cst[i].seatID);
						System.out.println("����ð�: " + formatDate);
							
						System.out.println("��� �ð�: " + ((int)((cst[i].endTime - cst[i].startTime) / 60000)) + "�� " 
								+ ((int)((Math.ceil((cst[i].endTime - cst[i].startTime) % 60000 / 1000))) + "��"));
				
						pay.calculateFee(cst[i].endTime, cst[i].startTime);
				
						System.out.println("�ð� ��� ���: " + pay.total_Income); // �ð� ��� ���
							
						cst[0].allTime += pay.total_Income; //�� �մԸ��� �ð� ��� �����ϱ� ���� ���Ƿ� 0�� �迭�� ����
							
						System.out.println("���� ���: " + cst[i].foodPay);
						System.out.println("�� ���: " + ((cst[i].foodPay) + pay.total_Income));
						
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
					System.out.println("�¼���ȣ     �̸�");
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
					System.out.println("�մ��� �����ϴ�.");
					break;
				}
				
			case 4:
				int allFood = 0;
				
				for (int i = 0; i < cst.length; i++) {
					if (cst[i].in == 0) {
						
						allFood += cst[i].foodPay;
					} // ���� ������ ���� ��� ���� ���� �����ؾ� ���� ���� !!!!!!!
				}
				System.out.println("���� ����: " + allFood);
				System.out.println("�ð� ����: " + cst[0].allTime);
				System.out.println("�� ����: " + (allFood + cst[0].allTime));
				break;
		}
			
	}
	
	void orderFood() {
	
		
		System.out.print("�¼���ȣ�� �Է��ϼ���: ");
		int seatID2 = scanner.nextInt(); //�¼���ȣ �Է¹ޱ�
		
		for (int i = 0; i < cst.length; i++) {
			if (cst[i].in == 1) {
				if (seatID2 == cst[i].seatID) {
					System.out.println("1: ������ġ          5000");
					System.out.println("2: ��ġ������         6500");
					System.out.println("3: �ֵ���            3000");
					System.out.println("4: ���̽��Ƹ޸�ī��     2500");
					System.out.println("5: �ݶ�, ���̴�       1500");
					System.out.println("6: ���̽�ũ��         1200");
					System.out.println("7: ����             800");
					System.out.println();

					boolean a = true;
					
					while(a) {
						System.out.print("���ϴ� �޴��� ��ȣ�� �� ���� �ϳ��� �Է��ϼ���.(�׸� �ֹ��Ͻ÷��� 99 �Է�): ");
						int foodNum = scanner.nextInt();
						
						if (foodNum > 0 && foodNum < 8 || foodNum == 99) {
							switch (foodNum) {
								case 1: System.out.println("������ġ ����: 5000��"); cst[i].foodPay += 5000; continue;
								case 2: System.out.println("��ġ������ ����: 6500��"); cst[i].foodPay += 6500; continue;
								case 3: System.out.println("�ֵ��� ����: 3000��"); cst[i].foodPay += 3000; continue; 
								case 4: System.out.println("���̽��Ƹ޸�ī�� ����: 2500��"); cst[i].foodPay += 2500; continue;
								case 5: System.out.println("�ݶ�, ���̴� ����: 1500��"); cst[i].foodPay += 1500; continue;
								case 6: System.out.println("���̽�ũ�� ����: 1200��"); cst[i].foodPay += 1200; continue;
								case 7: System.out.println("���� ����: 800��"); cst[i].foodPay += 800; continue;
								case 99: System.out.println("�� �ݾ�: " + cst[i].foodPay); a = false; i = cst.length - 1; // for�� �ݺ��Ͽ� ��µǱ� ����
							}
						}
						else
							System.out.println("���� �޴��Դϴ�.");					
					}
				}
			}
			else {
				i = cst.length - 1; // for�� �ݺ��Ͽ� ��µǱ� ����
				System.out.println("���� �� �ֹ��� �����մϴ�.");
			}	
		}
	}
}
//���� �� admin �����¼�Ȯ�� �� �¼� ���� ���µ� ����
//�����¼�Ȯ�� �� ����� �¼� ���� ���µ� ����


	

