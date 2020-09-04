
//class that represents some data that will be shared by multiple threads
public class Food {

	static int food;

	public synchronized int getFood() {
		return food;
	}

	public synchronized void setFood(int food) {
		this.food = food;
	}

	public Food(int food) {
		this.food = food;
	}

	synchronized public static int eat() {
		int pieceOfFood = food;

		pieceOfFood--;
		// Indicate which animal it is that starts to eat.
		System.out.println(Thread.currentThread().getName() + " is beginning to eat... Chomp...Chomp...Chomp");

		try {

			Thread.sleep((long) (Math.random() * Animal.restMax));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(Thread.currentThread().getName() + " is full");
		// Every time animal eats, amount of food decreases by 1 bite, and program will
		// print "-1"
		return pieceOfFood;
	}
}
