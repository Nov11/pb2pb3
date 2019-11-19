package pkg;

import org.osgi.framework.*;
import pkg.ifce.SimpleServiceIfce;

//https://www.baeldung.com/osgi
//nov11/osgi-client/1.0-SNAPSHOT
public class Client implements BundleActivator, ServiceListener {
    private BundleContext ctx;
    private ServiceReference serviceReference;

    public void start(BundleContext ctx) {
        this.ctx = ctx;
        try {
            ctx.addServiceListener(
                    this, "(objectclass=" + SimpleServiceIfce.class.getName() + ")");
        } catch (InvalidSyntaxException ise) {
            ise.printStackTrace();
        }
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        if (serviceReference != null) {
            ctx.ungetService(serviceReference);
        }
    }

    @Override
    public void serviceChanged(ServiceEvent serviceEvent) {
        int type = serviceEvent.getType();
        switch (type) {
            case (ServiceEvent.REGISTERED):
                System.out.println("Notification of service registered.");
                serviceReference = serviceEvent
                        .getServiceReference();
                SimpleServiceIfce service = (SimpleServiceIfce) (ctx.getService(serviceReference));
                System.out.println(service.sayHiTo("John"));
                break;
            case (ServiceEvent.UNREGISTERING):
                System.out.println("Notification of service unregistered.");
                ctx.ungetService(serviceEvent.getServiceReference());
                break;
            default:
                break;
        }
    }
}
