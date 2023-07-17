import java.sql.*;
import java.util.*;

public class DB_Driver 
{
	String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	String DB_URL = "jdbc:mysql://localhost/football?useSSL=false";

	String USER = "root";
	String PASS = "gandalf";

	Connection conn = null;
	Statement stmt = null;
	String sql;

	Scanner sc;

	public DB_Driver()
	{
		sc = new Scanner(System.in);
	}


	public void select()
	{
		System.out.print("\033[H\033[2J");  
    	System.out.flush(); 

		System.out.println("+-------------------------------------+");
		System.out.println("| 1 -> View all players               |");
		System.out.println("| 2 -> View all clubs                 |");
		System.out.println("| 3 -> View all leagues               |");
		System.out.println("| 4 -> View stats of a player         |");
		System.out.println("| 5 -> View all player stats          |");
		System.out.println("+-------------------------------------+");

		System.out.print("\nEnter your choice: ");
		int choice = sc.nextInt();

		switch(choice)
		{
			case 1: 
				select_player();
				break;
			case 2:
				select_club();
				break;
			case 3:
				select_league();
				break;
			case 4:
				select_player_stats();
				break;
			case 5:
				select_all_stats();
				break;
			default:
				System.out.println("Invalid Choice\n");
		}
	}

	public void select_player()
	{
		try
		{
			System.out.print("\033[H\033[2J");  
    		System.out.flush(); 
			
			stmt = conn.createStatement();
			sql = "SELECT * from player";
			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("+-----------------------------------------------------------------------------------------+");
			System.out.printf("| %3s | %-20s | %-20s | %3s | %-10s | %-3s | %-10s |\n", "pid", "First Name", "Last Name", "cid", "Mrkt Value", "Pos", "NT");
			System.out.println("+-----------------------------------------------------------------------------------------+");
			while(rs.next())
			{
				System.out.printf("| %3d |", rs.getInt("p_id"));
				System.out.printf(" %-20s | %-20s |", rs.getString("fname"), rs.getString("lname"));
				System.out.printf(" %3d |", rs.getInt("club_id"));
				System.out.printf(" %-10.2f |", rs.getDouble("market_value"));
				System.out.printf(" %3s |", rs.getString("position"));
				System.out.printf(" %-10s |\n", rs.getString("national_team"));
			}
			System.out.println("+-----------------------------------------------------------------------------------------+");
		}

		catch (SQLException se) 
		{ // Handle errors for JDBC
			se.printStackTrace();
		} 
		
		catch (Exception e) 
		{ // Handle errors for Class.forName
			e.printStackTrace();
		} 
	}

	public void select_club()
	{
		try
		{
			System.out.print("\033[H\033[2J");  
    		System.out.flush(); 
			
			stmt = conn.createStatement();
			sql = "SELECT * from club";
			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("+-------------------------------------------------+");
			System.out.printf("| %7s | %-25s | %9s |\n", "Club ID", "Club Name", "League ID");
			System.out.println("+-------------------------------------------------+");
			while(rs.next())
			{
				System.out.printf("| %7d |", rs.getInt("club_id"));
				System.out.printf(" %-25s |", rs.getString("name"));
				System.out.printf(" %9d |\n", rs.getInt("league_id"));
			}
			System.out.println("+-------------------------------------------------+");
		}

		catch (SQLException se) 
		{ // Handle errors for JDBC
			se.printStackTrace();
		} 
		
		catch (Exception e) 
		{ // Handle errors for Class.forName
			e.printStackTrace();
		} 
	}

	public void select_league()
	{
		try
		{
			System.out.print("\033[H\033[2J");  
    		System.out.flush(); 
			
			stmt = conn.createStatement();
			sql = "SELECT * from league";
			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("+----------------------------------------------------------+");
			System.out.printf("| %10s | %-25s | %-15s |\n", "League ID", "League Name", "Country");
			System.out.println("+----------------------------------------------------------+");
			while(rs.next())
			{
				System.out.printf("| %10d |", rs.getInt("league_id"));
				System.out.printf(" %-25s |", rs.getString("name"));
				System.out.printf(" %-15s |\n", rs.getString("country"));
			}
			System.out.println("+----------------------------------------------------------+");
		} 

		catch (SQLException se) 
		{ // Handle errors for JDBC 
			se.printStackTrace(); 
		} 
		
		catch (Exception e) 
		{ // Handle errors for Class.forName
			e.printStackTrace();
		} 
	}

	public void select_player_stats()
	{
		System.out.print("\nEnter player ID: ");
		int p_id = sc.nextInt();

		try
		{
			System.out.print("\033[H\033[2J");  
    		System.out.flush(); 
			
			stmt = conn.createStatement();
			sql = "SELECT * from player_stats where p_id = " + p_id + ";";
			sql = "select ps.p_id, concat(p.fname, ' ', p.lname) 'Name', ps.goal, ps.assist, ps.yellow_card, ps.red_card from player p inner join player_stats ps on p.p_id = ps.p_id where ps.p_id = " + p_id + ";";

			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("+--------------------------------------------------------------------------------+");
			System.out.printf("| %4s | %-25s | %5s | %7s | %13s | %9s |\n", "p_id", "Goals", "Name", "Assists", "Yellow Cards", "Red Cards");
			System.out.println("+--------------------------------------------------------------------------------+");
			while(rs.next())
			{
				System.out.printf("| %4d |", rs.getInt("p_id"));
				System.out.printf(" %-25s |", rs.getString("Name"));
				System.out.printf(" %5s |", rs.getInt("goal"));
				System.out.printf(" %7s |", rs.getInt("assist"));
				System.out.printf(" %13s |", rs.getInt("yellow_card"));
				System.out.printf(" %9s |\n", rs.getInt("red_card"));
			}
			System.out.println("+--------------------------------------------------------------------------------+");
		} 

		catch (SQLException se) 
		{ // Handle errors for JDBC 
			se.printStackTrace(); 
		} 
		
		catch (Exception e) 
		{ // Handle errors for Class.forName
			e.printStackTrace();
		} 
	}

	public void select_all_stats()
	{
		try
		{
			System.out.print("\033[H\033[2J");  
    		System.out.flush(); 
			
			stmt = conn.createStatement();
			sql = "select ps.p_id, concat(p.fname, ' ', p.lname) 'Name', ps.goal, ps.assist, ps.yellow_card, ps.red_card from player p inner join player_stats ps on p.p_id = ps.p_id;";
			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("+--------------------------------------------------------------------------------+");
			System.out.printf("| %4s | %-25s | %5s | %7s | %13s | %9s |\n", "p_id", "Goals", "Name", "Assists", "Yellow Cards", "Red Cards");
			System.out.println("+--------------------------------------------------------------------------------+");
			while(rs.next())
			{
				System.out.printf("| %4d |", rs.getInt("p_id"));
				System.out.printf(" %-25s |", rs.getString("Name"));
				System.out.printf(" %5s |", rs.getInt("goal"));
				System.out.printf(" %7s |", rs.getInt("assist"));
				System.out.printf(" %13s |", rs.getInt("yellow_card"));
				System.out.printf(" %9s |\n", rs.getInt("red_card"));
			}
			System.out.println("+--------------------------------------------------------------------------------+");
		} 

		catch (SQLException se) 
		{ // Handle errors for JDBC 
			se.printStackTrace(); 
		} 
		
		catch (Exception e) 
		{ // Handle errors for Class.forName
			e.printStackTrace();
		} 
	}

	public void add()
	{
		System.out.print("\033[H\033[2J");  
    	System.out.flush(); 

		System.out.println("+-------------------------------------+");
		System.out.println("| 1 -> Add a new player               |");
		System.out.println("| 2 -> Add a new club                 |");
		System.out.println("| 3 -> Add a new league               |");
		System.out.println("+-------------------------------------+");

		System.out.print("\nEnter your choice: ");
		int choice = sc.nextInt();

		switch(choice)
		{
			case 1: 
				add_player();
				break;
			case 2:
				add_club();
				break;
			case 3:
				add_league();
				break;
			default:
				System.out.println("Invalid Choice\n");
		}
	}

	public void add_player()
	{
		Player p = new Player();
		try
		{
			stmt = conn.createStatement();
			System.out.println("\nExecuting query.....");
			sql = "insert into player(fname, lname, club_id, market_value, position, national_team) values "
				   + "('" + p.fname + "','" + p.lname + "'," + p.cid + "," + p.value + ",'" + p.pos + "','" + p.national_team + "');";

			stmt.executeUpdate(sql);

			stmt = conn.createStatement();
			sql = "select max(p_id) from player";
			ResultSet rs = stmt.executeQuery(sql);

			int p_id = 0;

			if(rs.next())
				p_id = rs.getInt("max(p_id)");

			stmt = conn.createStatement();
			sql = "insert into player_stats(p_id, goal, assist, yellow_card, red_card) values (" + p_id + ",0, 0, 0, 0);";
			stmt.executeUpdate(sql);
		}

		catch (SQLException se) 
		{ // Handle errors for JDBC
			se.printStackTrace();
		} 
		
		catch (Exception e) 
		{ // Handle errors for Class.forName
			e.printStackTrace();
		} 
	}

	public void add_club()
	{
		Club c = new Club();
		try
		{
			stmt = conn.createStatement();
			System.out.println("\nExecuting query.....");
			sql = "insert into club(name, league_id) values "
				   + "('" + c.name + "'," + c.l_id + ");";

			stmt.executeUpdate(sql);
		}

		catch (SQLException se) 
		{ // Handle errors for JDBC
			se.printStackTrace();
		} 
		
		catch (Exception e) 
		{ // Handle errors for Class.forName
			e.printStackTrace();
		} 
	}

	public void add_league()
	{
		League l = new League();
		try
		{
			stmt = conn.createStatement();
			System.out.println("\nExecuting query.....");
			sql = "insert into league(name, country) values "
				   + "('" + l.name + "','" + l.country + "');";

			stmt.executeUpdate(sql);
		}

		catch (SQLException se) 
		{ // Handle errors for JDBC
			se.printStackTrace();
		} 
		
		catch (Exception e) 
		{ // Handle errors for Class.forName
			e.printStackTrace();
		} 
	}


	public void delete()
	{
		System.out.print("\033[H\033[2J");  
    	System.out.flush(); 

		System.out.println("+-------------------------------------+");
		System.out.println("| 1 -> Delete a player                |");
		System.out.println("| 2 -> Delete a club                  |");
		System.out.println("| 3 -> Delete a league                |");
		System.out.println("+-------------------------------------+");

		System.out.print("\nEnter your choice: ");
		int choice = sc.nextInt();

		switch(choice)
		{
			case 1: 
				delete_player();
				break;
			case 2:
				delete_club();
				break;
			case 3:
				delete_league();
				break;
			default:
				System.out.println("Invalid Choice\n");
		}
	}

	public void delete_player()
	{
		System.out.print("\nEnter player ID: ");
		int id = sc.nextInt();

		try
		{
			stmt = conn.createStatement();
			System.out.println("\nExecuting query.....");
			sql = "delete from player where p_id = " + id + ";";

			stmt.executeUpdate(sql);
		}

		catch (SQLException se) 
		{ // Handle errors for JDBC
			se.printStackTrace();
		} 
		
		catch (Exception e) 
		{ // Handle errors for Class.forName
			e.printStackTrace();
		} 
	}

	public void delete_club()
	{
		System.out.println("WARNING : Deleting a club with existing players will set the club_id of those players to 0 (NULL).");
		System.out.print("\nEnter club ID: ");
		int id = sc.nextInt();

		try
		{
			stmt = conn.createStatement();
			System.out.println("\nExecuting query.....");
			sql = "delete from club where club_id = " + id + ";";

			stmt.executeUpdate(sql);
		}

		catch (SQLException se) 
		{ // Handle errors for JDBC
			se.printStackTrace();
		} 
		
		catch (Exception e) 
		{ // Handle errors for Class.forName
			e.printStackTrace();
		} 
	}

	public void delete_league()
	{
		System.out.println("WARNINGP : Make sure that no club is associated with the league before deleting.");
		System.out.print("\nEnter league ID: ");
		int id = sc.nextInt();

		try
		{
			stmt = conn.createStatement();
			System.out.println("\nExecuting query.....");
			sql = "delete from league where league_id = " + id + ";";

			stmt.executeUpdate(sql);
		}

		catch (SQLException se) 
		{ // Handle errors for JDBC
			se.printStackTrace();
		} 
		
		catch (Exception e) 
		{ // Handle errors for Class.forName
			e.printStackTrace();
		} 
	}

	public void update()
	{
		System.out.print("\033[H\033[2J");  
    	System.out.flush(); 

		System.out.println("+-------------------------------------+");
		System.out.println("| 1 -> Update player info             |");
		System.out.println("| 2 -> Update player stats            |");
		System.out.println("+-------------------------------------+");

		System.out.print("\nEnter your choice: ");
		int choice = sc.nextInt();

		switch(choice)
		{
			case 1: 
				update_player();
				break;
			case 2:
				update_player_stats();
				break;
			default:
				System.out.println("Invalid Choice\n");
		}
	}

	public void update_player()
	{
		System.out.print("\033[H\033[2J");  
    	System.out.flush();
		System.out.println("+----------------------------------+");
		System.out.println("| What do you want to update?      |");
		System.out.println("+----------------------------------+");
		// System.out.println("|                                  |");
		System.out.println("| 1 -> Club ID                     |");
		System.out.println("| 2 -> Market Value                |");
		System.out.println("| 3 -> Position                    |");
		System.out.println("+----------------------------------+");

		System.out.print("\nEnter your choice: ");
		int choice = sc.nextInt();
		int pid;

		switch(choice)
		{
			case 1:
				System.out.print("\nEnter player ID: ");
				pid = sc.nextInt();
				System.out.print("Enter new Club_ID: ");
				int cid = sc.nextInt();
				
				sql = "update player set club_id = " + cid + " where p_id = " + pid + ";";
				break;
			
			case 2:
				System.out.print("\nEnter player ID: ");
				pid = sc.nextInt();
				System.out.print("Enter new Market Value: ");
				double value = sc.nextDouble();

				sql = "update player set market_value = " + value + " where p_id = " + pid + ";";
				break;
			
			case 3:
				System.out.print("\nEnter player ID: ");
				pid = sc.nextInt();
				System.out.print("Enter new Position: ");
				String pos = sc.next();

				sql = "update player set position = '" + pos + "' where p_id = " + pid + ";";
				break;
			
			default:
				System.out.println("Invalid Choice");
				return;
		}

		try
		{
			stmt = conn.createStatement();
			System.out.println("\nExecuting query.....");
			stmt.executeUpdate(sql);
		}

		catch (SQLException se) 
		{ // Handle errors for JDBC
			se.printStackTrace();
		} 
		
		catch (Exception e) 
		{ // Handle errors for Class.forName
			e.printStackTrace();
		}
	}

	public void update_player_stats()
	{
		System.out.print("\033[H\033[2J");  
    	System.out.flush();
		System.out.println("+----------------------------------+");
		System.out.println("| What do you want to update?      |");
		System.out.println("+----------------------------------+");
		// System.out.println("|                                  |");
		System.out.println("| 1 -> Goals                       |");
		System.out.println("| 2 -> Assists                     |");
		System.out.println("| 3 -> Yellow Cards                |");
		System.out.println("| 4 -> Red Cards                   |");
		System.out.println("+----------------------------------+");

		System.out.print("\nEnter your choice: ");
		int choice = sc.nextInt();
		int pid;

		switch(choice)
		{
			case 1:
				System.out.print("\nEnter player ID: ");
				pid = sc.nextInt();
				System.out.print("Enter updated Goals: ");
				int goals = sc.nextInt();
				
				sql = "update player_stats set goal = " + goals + " where p_id = " + pid + ";";
				break;
			
			case 2:
				System.out.print("\nEnter player ID: ");
				pid = sc.nextInt();
				System.out.print("Enter updated Assists: ");
				int assists = sc.nextInt();

				sql = "update player_stats set assist = " + assists + " where p_id = " + pid + ";";
				break;
			
			case 3:
				System.out.print("\nEnter player ID: ");
				pid = sc.nextInt();
				System.out.print("Enter updated Yellow Cards: ");
				int yc = sc.nextInt();

				sql = "update player_stats set yellow_card = " + yc + " where p_id = " + pid + ";";
				break;

			case 4:
				System.out.print("\nEnter player ID: ");
				pid = sc.nextInt();
				System.out.print("Enter updated Red Cards: ");
				int rc = sc.nextInt();

				sql = "update player_stats set red_card = " + rc + " where p_id = " + pid + ";";
				break;
			
			default:
				System.out.println("Invalid Choice");
				return;
		}

		try
		{
			stmt = conn.createStatement();
			System.out.println("\nExecuting query.....");
			stmt.executeUpdate(sql);
		}

		catch (SQLException se) 
		{ // Handle errors for JDBC
			se.printStackTrace();
		} 
		
		catch (Exception e) 
		{ // Handle errors for Class.forName
			e.printStackTrace();
		}
	}

	public void top_performers()
	{
		try
		{
			System.out.print("\033[H\033[2J");  
    		System.out.flush(); 

			System.out.println("TOP PERFORMERS ACROSS ALL LEAGUES\n");
			
			stmt = conn.createStatement();
			sql = "select concat(p1.fname, ' ', p1.lname) 'Name', goal from player p1 inner join player_stats ps on p1.p_id = ps.p_id where ps.goal in (select max(goal) from player_stats);";
			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("+----------------------------------------------------+");
			System.out.printf("| %-35s | %12s |\n", "Name", "Goals");
			System.out.println("+----------------------------------------------------+");
			while(rs.next())
			{
				System.out.printf("| %-35s |", rs.getString("Name"));
				System.out.printf(" %12d |\n", rs.getInt("goal"));

			}
			System.out.println("+----------------------------------------------------+");

			stmt = conn.createStatement();
			sql = "select concat(p1.fname, ' ', p1.lname) 'Name', assist from player p1 inner join player_stats ps on p1.p_id = ps.p_id where ps.assist in (select max(assist) from player_stats);";
			rs = stmt.executeQuery(sql);

			System.out.println("\n+----------------------------------------------------+");
			System.out.printf("| %-35s | %12s |\n", "Name", "Assists");
			System.out.println("+----------------------------------------------------+");
			while(rs.next())
			{
				System.out.printf("| %-35s |", rs.getString("Name"));
				System.out.printf(" %12d |\n", rs.getInt("assist"));

			}
			System.out.println("+----------------------------------------------------+");

			stmt = conn.createStatement();
			sql = "select concat(p1.fname, ' ', p1.lname) 'Name', yellow_card from player p1 inner join player_stats ps on p1.p_id = ps.p_id where ps.yellow_card in (select max(yellow_card) from player_stats);";
			rs = stmt.executeQuery(sql);

			System.out.println("\n+----------------------------------------------------+");
			System.out.printf("| %-35s | %12s |\n", "Name", "Yellow Cards");
			System.out.println("+----------------------------------------------------+");
			while(rs.next())
			{
				System.out.printf("| %-35s |", rs.getString("Name"));
				System.out.printf(" %12d |\n", rs.getInt("yellow_card"));

			}
			System.out.println("+----------------------------------------------------+");

			stmt = conn.createStatement();
			sql = "select concat(p1.fname, ' ', p1.lname) 'Name', red_card from player p1 inner join player_stats ps on p1.p_id = ps.p_id where ps.red_card in (select max(red_card) from player_stats);";
			rs = stmt.executeQuery(sql);

			System.out.println("\n+----------------------------------------------------+");
			System.out.printf("| %-35s | %12s |\n", "Name", "Red Cards");
			System.out.println("+----------------------------------------------------+");
			while(rs.next())
			{
				System.out.printf("| %-35s |", rs.getString("Name"));
				System.out.printf(" %12d |\n", rs.getInt("red_card"));

			}
			System.out.println("+----------------------------------------------------+");
		} 

		catch (SQLException se) 
		{ // Handle errors for JDBC 
			se.printStackTrace(); 
		} 
		
		catch (Exception e) 
		{ // Handle errors for Class.forName
			e.printStackTrace();
		} 
	}

	public void exit()
	{
		try 
			{
				if (stmt != null)
					stmt.close();
			} 

			catch (SQLException se2) 
			{

			}

			try 
			{
				if (conn != null)
					conn.close();
			} 

			catch (SQLException se) 
			{
				se.printStackTrace();
			} // end finally try
	}

	public void start()
	{
		try 
		{
			// STEP 2a: Register JDBC driver
			Class.forName(JDBC_DRIVER);
			// STEP 2b: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} 
		
		catch (SQLException se) 
		{ // Handle errors for JDBC
			se.printStackTrace();
		} 
		
		catch (Exception e) 
		{ // Handle errors for Class.forName
			e.printStackTrace();
		} 
	}
}
