package StudyCafe;

public class Payment {
	public int total_Income;
	final int FEE_PER_MINUTE = 100;
	final int FEE_PER_HOUR = 5000;
	
	int calculateFee(long endTime, long startTime) {
	
		if (endTime - startTime <= 60000)
			return total_Income = 100;
		else {
				return total_Income = 
					((int)(((endTime - startTime) / (1000 * 60 * 60)) * FEE_PER_HOUR) + 
					((int)(((endTime - startTime) / (1000 * 60)) * FEE_PER_MINUTE))) + 100;
			}		
	};
	
	//long setCheckIn();
	
	//long setCheckOut();
	
}
