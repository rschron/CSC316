
public class Ride {
	private int[] cars;
	private boolean[] openCars;
	
	public Ride(int numCars){
		this.cars = new int[numCars];
		for(int i = 1; i <= numCars; i++){
			cars[i] = i;
		}
		
		this.openCars = new boolean[numCars];
		for(int i = 0; i < numCars; i++){
			openCars[i] = true;
		}
	}
	
	private int checkAvailability(){
		for(int i = 0; i < openCars.length; i++){
			if(openCars[i]){
				return i;
			}
		}
		return 0;
	}
	
	public synchronized int getCar(){
		int car;
		while((car = checkAvailability()) == 0){
			try{
				wait();
			} catch(InterruptedException e){
				System.out.println("Error: Thread Interrupted");
			}
		}
		openCars[car] = false;
		notifyAll();
		return car + 1;
	}
	
	public synchronized void returnCar(int car){
		while(openCars[car - 1] == true){
			try{
				wait();
			} catch(InterruptedException e){}
		}
		openCars[car - 1] = true;
		notifyAll();
	}

}
