import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Pool {
    List<Connection> listFreeConnect = new ArrayList<>();
    List<Connection> listUseConnect = new ArrayList<>();
    int poolSize;

    public Pool(int numberCon) {
        for (int i = 0; i < numberCon; i++) {
            listFreeConnect.add(new Conn().getConnection());
        }

    }

    public int numberFreeConnection() {
        poolSize =listFreeConnect.size();
        return poolSize;
    }

    public Connection getPoolConnection() {
        Connection newConn;
        if (poolSize == 0) {
            System.out.println("No free connection");
            newConn = new Conn().getConnection();
        } else {
            newConn = listFreeConnect.get(listFreeConnect.size());

        }
        return newConn;

    }

    public void putConnection(Connection conn) {
        if (conn != null) {
            listFreeConnect.add(conn);
            listUseConnect.remove(conn);
        } else {
            throw new NullPointerException("No Connection");
        }
    }

}
