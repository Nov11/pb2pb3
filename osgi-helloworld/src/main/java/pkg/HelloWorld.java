package pkg;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

//https://www.baeldung.com/osgi
public class HelloWorld /*implements BundleActivator*/ {
    public void start(BundleContext ctx) {
        System.out.println("Hello world.");
    }

    public void stop(BundleContext bundleContext) {
        System.out.println("Goodbye world.");
    }
}
