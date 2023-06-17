package school;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;


public class StudentManager {

	private static String DB_URL = "jdbc:mysql://localhost/school";
	private static String DB_USER = "admin";
	private static String DB_PASSWORD = "1234";

	void addStudent(Student student) throws SQLException {

		Connection conn = null;
		Statement stmt = null;
		conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

		stmt = conn.createStatement();
		String sqlCommand = "INSERT INTO student (name, email) VALUES ('" + student.getName() + "',  '"
				+ student.getEmail() + "')";
		stmt.executeUpdate(sqlCommand, Statement.RETURN_GENERATED_KEYS);

		conn.close();

	}

	List<Student> getStudents() throws SQLException {
		
		List<Student> studentList = new ArrayList<Student>();
		Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		Statement stmt = conn.createStatement();

		String sqlCommand = "SELECT * FROM student";
		stmt.execute(sqlCommand);

		ResultSet rs = stmt.getResultSet();

		while (rs.next()) {
			Student student = new Student();
			int id = rs.getInt("id");
			student.setId(id);
			String name = rs.getString("name");
			student.setName(name);
			String email = rs.getString("email");
			student.setEmail(email);
			
			System.out.println("Id: " + id + " - Name: " + name + " - Email: " + email);
		}
		conn.close();
	    return studentList;
	}

	void updateStudent(Student student) throws SQLException {

		Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		Statement stmt = conn.createStatement();

		String sqlCommand = "UPDATE student SET name = '" + student.getName() + "', email = '" + student.getEmail()
				+ "' WHERE id = " + student.getId();
		stmt.execute(sqlCommand);

		int rowsUpdated = stmt.getUpdateCount();

		System.out.println("Rows updated: " + rowsUpdated);

		conn.close();

	}

	void deleteStudent(Student student) throws SQLException {

		Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		Statement stmt = conn.createStatement();

		String sqlCommand = "DELETE FROM student WHERE id = " + student.getId();

		stmt.execute(sqlCommand);

	}
}

/*
 * GRANT ALL ON school.* TO admin@”%” identified by ”1234”; FLUSH PRIVILEGES;
 */
