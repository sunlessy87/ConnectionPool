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
    public Pool() {
        for (int i = 0; i <10; i++) {
            listFreeConnect.add(new Conn().getConnection());
        }
    }
// Метод определяет кол-во свободных соединений
    public int numberFreeConnection() {
        poolSize =listFreeConnect.size();
        return poolSize;
    }
// Метод получения соединения из пула доступных соединений
    public Connection getConnectionFromPoolConnection() {
        Connection newConn;
        if (listFreeConnect != null) {
            newConn = listFreeConnect.get(listFreeConnect.size()-1);
            listFreeConnect.remove(newConn);
        } else {
            System.out.println("No free connection");
            newConn = new Conn().getConnection();
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
