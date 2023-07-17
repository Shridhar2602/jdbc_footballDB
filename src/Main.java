import java.util.*;

class Main 
{
	static DB_Driver db = new DB_Driver();

	public static void main(String[] args)
	{
		

		Scanner sc = new Scanner(System.in);

		db.start();

		int query = 1;

		MAIN_MENU : while(true)
		{
			System.out.print("\033[H\033[2J");  
			System.out.flush();

			System.out.println("+------------------------------------------------------+");
			System.out.println("|              Football Player Database                |");
			System.out.println("|                                                      |");
			System.out.println("|                       - Shridhar Sharma (IMT2020065) |");
			System.out.println("+------------------------------------------------------+");
			System.out.println("| 1 -> List players / clubs / leagues / stats          |");
			System.out.println("| 2 -> Update player info / stats                      |");
			System.out.println("| 3 -> Add a new player / club / league                |");
			System.out.println("| 4 -> Delete a player / club / league                 |");
			System.out.println("| 5 -> View top performers                             |");
			System.out.println("| 6 -> Exit                                            |");
			System.out.println("+------------------------------------------------------+");

			System.out.print("\nEnter your choice: ");
			query = sc.nextInt();

			switch(query)
			{
				case 1:
					db.select();
					break;

				case 2:
					db.update();
					break;

				case 3:
					db.add();
					break;
				
				case 4:
					db.delete();
					break;

				case 5:
					db.top_performers();
					break;

				case 6:
					db.exit();
					break MAIN_MENU;

				default:
					System.out.println("Invalid Input");
					break;
			}

			System.out.println("\nPress \"ENTER\" to return to main menu...");
   			Scanner scanner = new Scanner(System.in);
   			scanner.nextLine();
		}
	}
}