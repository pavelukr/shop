package shop;

public class ConnectionData {
    public static final String URL = "jdbc:mysql://localhost:3306/shop"+
            "?verifyServerCertificate=false"+
            "&useSSL=false"+
            "&requireSSL=false"+
            "&useLegacyDatetimeCode=false"+
            "&amp"+
            "&serverTimezone=UTC";
    public static final String USER = "pavelmaster";
    public static final String PASSWORD = "SenseiNail7!";

    //for HA
  /*  public static final String DRIVER = "org.h2.Driver";
    public static final String DB = "test";
    public static final String URL = "jdbc:h2:tcp://localhost/~/" + DB;
    public static final String USER = "sa";
    public static final String PASSWORD = "";*/
}
