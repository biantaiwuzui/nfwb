package test2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/2/17.
 */
public class GrilProxy implements InvocationHandler{
    Object z = null;
    public GrilProxy(Object o){
        this.z = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object o = method.invoke(z, args);
        System.out.println("after");
        return o;
    }
}
