import java.util.*;

public class Club
{
	int l_id;
	String name;

	Club()        
	{
		Scanner sc = new Scanner(System.in);

		System.out.print("\nClub Name: ");
		name = sc.nextLine();

		System.out.print("League ID: ");
		l_id = sc.nextInt();
	}
}