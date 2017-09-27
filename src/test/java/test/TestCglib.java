package test;

import net.sf.cglib.proxy.Enhancer;
import org.hibernate.bytecode.enhance.spi.EnhancerConstants;
import org.junit.Test;

/**
 * Created by Administrator on 2017/2/17.
 */
public class TestCglib {
    @Test
    public void t1(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Person.class);
        enhancer.setCallback(new PersonInv());
        Person p=(Person) enhancer.create();
        p.slepp();

    }
}
