package group2.grade15.njuse.data.databaseimpl;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by dell on 2016/11/20.
 */
public class DatabaseMySql {
    DatabaseInfo info=null;
    Connection c=null;

    public DatabaseMySql(DatabaseInfo info) {
        this.info = info;
    }

    public Connection init(){
       try{
           Class.forName("org.postgresql.Driver");
           c= DriverManager.getConnection(info.url,info.name,info.password);
           c.setAutoCommit(false);
           System.out.print("success");
       }catch(Exception e){
           e.printStackTrace();
       }
       return c;
    }
}
