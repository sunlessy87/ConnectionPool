import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Pool {
    //Лист свободных подключений
    List<Connection> listFreeConnect = new ArrayList<>();
    //Лист занятых подключений
    List<Connection> listUseConnect = new ArrayList<>();
    //кол-во подключений
    int poolSize;

    public Pool(int numberCon) {
        for (int i = 0; i < numberCon; i++) {
            listFreeConnect.add(new Conn().getConnection());
        }

    }
// Метод определяет кол-во свободных соединений
    public int numberFreeConnection() {
        poolSize =listFreeConnect.size();
        return poolSize;
    }
// Метод получения соединений из пула доступных соединений
    public Connection getPoolConnection() {
        Connection newConn;
        if (poolSize == 0) {
            System.out.println("No free connection");
            newConn = new Conn().getConnection();
        } else {
            newConn = listFreeConnect.get(listFreeConnect.size());
            listFreeConnect.remove(newConn);
        }
        listUseConnect.add(newConn);
        return newConn;

    }
// Метод возврата соединений в пул доступных соединений
    public void putConnection(Connection conn) {
        if (conn != null) {
            listFreeConnect.add(conn);
            listUseConnect.remove(conn);
        } else {
            throw new NullPointerException("No Connection");
        }
    }

}
