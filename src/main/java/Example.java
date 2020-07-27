import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@RestController
@EnableAutoConfiguration
public class Example {

    @RequestMapping("/")
    String home() {
		String user = "user";
        Connection conn = null;
        String url = "jdbc:mysql://127.0.0.1:3306/";
        String dbName = "Employee_Schema";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "root";
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);

            Statement st = conn.createStatement();
            String query = "SELECT * FROM  User where userId='" + user + "'" + " AND password='" + password + "'";
            System.out.println("Query : " + query);
            System.out.printf(query);
            ResultSet res = st.executeQuery(query);

            System.out.println("Results");
            while (res.next()) {
                String s = res.getString("username");
                System.out.println("\t\t" + s);
            }
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
     finally {
        System.out.close();
    }
		
        return "Hello World - v3!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class, args);
    }

}
