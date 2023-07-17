import java.util.*;

public class League
{
	int id;
	String name, country;

	League()        
	{
		Scanner sc = new Scanner(System.in);

		System.out.print("\nLeague Name: ");
		name = sc.nextLine();

		System.out.print("Country: ");
		country = sc.next();
	}
}
