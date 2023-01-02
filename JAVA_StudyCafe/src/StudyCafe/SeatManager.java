package StudyCafe;

public class SeatManager {
	
	boolean setTable[][] = new boolean[2][5];
	
	public SeatManager() { //�迭�� ��� ��Ҹ� false�� �ʱ�ȭ
		
	}
	
	void clear() { //�迭�� ��� ��Ҹ� false�� �ʱ�ȭ
		for(int i = 0; i < setTable.length; i++) {
			for(int j = 0; j < setTable[0].length; j++) {
				setTable[i][j] = false;
			}
		}
	}
	
	void print() {
		System.out.println();
		
		for(int i = 1; i < setTable.length + 1; i++) {
			for(int j = 1; j < setTable[0].length + 1; j++) {
				if(setTable[i-1][j-1] == false) 
					System.out.print("V[" + i + j +"]" + "  ");
				else
					System.out.print("C[" + i + j +"]" + "  ");
			}
			System.out.println();
		}
	}
	
	void setSeat(int x, int y) {
		for(int i = 1; i < setTable.length + 1; i++) {
			for(int j = 1; j < setTable[0].length + 1; j++) {
				if(i == x && j == y) {
					if(setTable[i-1][j-1] == false) {
						setTable[i-1][j-1] = true; //V�� C�� ����
						System.out.println("���õǾ����ϴ�.");
					}
					else {
						System.out.print("�̹� ��� �� �Դϴ�.");
						// currentNo--; 
					}
						
				} else continue;
			}
		}
	}
	
	
	void releaseSeat(int x, int y) {
		if(setTable[x-1][y-1] == true)
			setTable[x-1][y-1] = false;
	}
}
