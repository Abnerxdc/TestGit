import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2017/6/1.
 */
public class testMain {
    private static Logger logger = Logger.getLogger(testMain.class);

    public static void main(String[] args) {
        // 记录debug级别的信息
        logger.debug("This is debug message.");
        // 记录info级别的信息
        logger.info("This is info message.");
        // 记录error级别的信息
        logger.error("This is error message.");

        logger.info("I am in master");
        logger.debug("-----");
        logger.debug("--I am hotfix---");

    }
}
