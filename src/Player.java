import java.util.*;

public class Player 
{
	int cid;
	double value;
	String fname, lname, pos, national_team;

	Player()
	{
		Scanner sc = new Scanner(System.in);

		System.out.print("\nFirst Name: ");
		fname = sc.next();

		System.out.print("Last Name: ");
		lname = sc.next();

		System.out.print("Club ID: ");
		cid = sc.nextInt();

		System.out.print("Market Value: ");
		value = sc.nextDouble();

		System.out.print("Position: ");
		pos = sc.next();

		System.out.print("National Team: ");
		national_team = sc.next();
	}
}