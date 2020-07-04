package com.trendcore.agent;

import org.junit.Test;
import sun.jvmstat.monitor.*;

import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

public class ListJVMs {

    @Test
    public void name() throws MonitorException, URISyntaxException {
        MonitoredHost local = MonitoredHost.getMonitoredHost("localhost");
        Set vmlist = new HashSet(local.activeVms());

        for (Object id : vmlist) {
            MonitoredVm vm = local.getMonitoredVm(new VmIdentifier("//" + id));
            String processname = MonitoredVmUtil.mainClass(vm, true);
            System.out.println(id + " ------> " + processname);
        }

    }
}
