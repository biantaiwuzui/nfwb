import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/2/8.
 */
public class Demo {
    @Test
    public void test(){
        try {
            Date time = new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-01");
            System.out.println(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void t2() throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        String ip=addr.getHostAddress().toString();//获得本机IP
        String address=addr.getHostName().toString();//获得本机名称
        System.out.println(ip);
        System.out.println(address);

    }

}
