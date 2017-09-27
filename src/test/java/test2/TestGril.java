package test2;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/2/17.
 */
public class TestGril {
    @Test
    public void t1(){
        Girl z = new Girl();
        Person loveProxy = (Person) Proxy.newProxyInstance(z.getClass().getClassLoader(),z.getClass().getInterfaces(),new GrilProxy(z));
        loveProxy.say();
    }
    @Test
    public void t2(){
        Girl z = new Girl();
        Girl loveProxy = (Girl) Proxy.newProxyInstance(z.getClass().getClassLoader(),z.getClass().getInterfaces(),new GrilProxy(z));
        loveProxy.say();
    }
}
