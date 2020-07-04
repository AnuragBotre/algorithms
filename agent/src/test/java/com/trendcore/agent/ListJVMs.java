package com.trendcore.agent;

import org.junit.Test;
import sun.jvmstat.monitor.*;

import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

public class ListJVMs {

    @Test
    public void getListOfJVMs() throws MonitorException, URISyntaxException {
        MonitoredHost local = MonitoredHost.getMonitoredHost("localhost");
        Set<Integer> vmLists = local.activeVms();

        for (Integer id : vmLists) {
            MonitoredVm vm = local.getMonitoredVm(new VmIdentifier("//" + id));
            String processname = MonitoredVmUtil.mainClass(vm, true);
            System.out.println(id + " ------> " + processname);
        }

    }
}
