package pkg.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import pkg.ifce.SimpleServiceIfce;

import java.util.Hashtable;

public class SimpleServiceImpl implements SimpleServiceIfce, BundleActivator {
    private ServiceReference<SimpleServiceIfce> reference;
    private ServiceRegistration<SimpleServiceIfce> registration;

    @Override
    public String sayHiTo(String name) {
        return "Hello " + name;
    }

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Registering service.");
        registration = context.registerService(
                SimpleServiceIfce.class,
                new SimpleServiceImpl(),
                new Hashtable<String, String>());
        reference = registration.getReference();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Unregistering service.");
        registration.unregister();
    }
}
