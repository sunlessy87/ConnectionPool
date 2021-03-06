import java.sql.Connection;
import java.sql.DriverManager;

// Класс соединение с БД
public class Conn {
    private Connection createConnection() throws Exception {
// Подгрузка драйвера БД Derby
        Class.forName("org.postgresql.Driver").newInstance();
        // Получение соединения с БД
        return DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/rentAuto", "postgres", "postgres");
    }

    //Метод для создания соединения с БД
    public Connection getConnection() {
        Connection con = null;
        try {
            con = createConnection();
        } catch (Exception e) {
            System.err.print("Connection was not created");
        }
        return con;
    }

}
