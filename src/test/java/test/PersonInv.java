package test;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import javax.faces.view.facelets.MetaTagHandler;
import java.lang.invoke.MethodHandleInfo;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/2/17.
 */
public class PersonInv implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("before:"+method);
        Object o = proxy.invokeSuper(obj, args);
        System.out.println("after:"+method);
        return o;
    }
}
