/* June 7, 2020
 * Julia Smith
 * Project#4: Threads
 * 
 * This project is a simulation of the race between Rabbit and Turtle. 
 * Length of the race is 100 yards. The initial position of both animals is 0. 
 * Speed of the rabbit is 5, and the time it rests is 150.
 * Speed of the turtle is 3, and it rests 100.
 * Program allows us to see position of each animal throughout the race and determine when animal finished the race.
 * At the same time, program  is simulating each animal eating (thread will sleep for some length of time).
 * There is one instance of the Food class that is shared by both of the animals, only one animal can be eating at a time.
 * The rabbit eats the food for a longer time than the turtle.But, the turtle must wait until the rabbit is done eating
 *  until it can eat.Every time animal eats, amount of food decreases by 1 bite, and program will print "-1"
 */

public class Animal implements Runnable {
//variables
	String name;
	int position = 0; // the position in the race for this Animal object
	int speed; // speed of the animal

	static int restMax; // how long the Animal rests between each time it runs
	static boolean winner = false;

// constructor 
	public Animal(String name, int position, int speed, int restMax, Object food) {
		this.name = name;
		this.position = position;
		this.speed = speed;
		this.restMax = restMax;

	}

// setters and getters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getRestMax() {
		return restMax;
	}

	public void setRestMax(int restMax) {
		this.restMax = restMax;
	}

	public static void main(String[] args) {
// creating object that will be shared between both threads while animals are eating
		Food food = new Food(0);

//creating instances of the Animal class 
		Animal rabbit = new Animal("Rabbit", 0, 5, 150, food);
		Animal turtle = new Animal("Turtle", 0, 3, 100, food);
//creating threads
		Thread rabbit1 = new Thread(rabbit);
		Thread turtle1 = new Thread(turtle);

		// making threads "user" threads
		rabbit1.setDaemon(false);
		turtle1.setDaemon(false);

		// giving threads a name
		rabbit1.setName("Rabbit");
		turtle1.setName("Turtle");
//staring threads
		rabbit1.start();
		turtle1.start();

	}

	@Override

	public void run() {

		do {

			for (int i = 0; position < 100; i++) {

				// Adjusting threads, so that the rabbit wins sometimes, and the turtle wins
				// sometimes.

				Thread.yield();
				try {
					Thread.sleep((long) (Math.random() * restMax));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				this.position = this.position + this.getSpeed();
				/*
				 * Printing who is running, what is their position. When someone wins, setting
				 * the static variable winner to true, and both threads will finish their run
				 * method, and thus stop.
				 */
				System.out.println(this.name + " at position " + this.position);
				if (this.position >= 100) {
					Animal.winner = true;
					System.out.println(this.name + " finished the race !!!");

				}

				else {
					Animal.winner = false;

				}
				System.out.println(Food.eat());
			}
		} while (!winner);

	}
}
