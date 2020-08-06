package com.trendcore;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AgentLoader {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {

        VirtualMachine.list().forEach(virtualMachineDescriptor -> {
            System.out.println(virtualMachineDescriptor.id() + " " + virtualMachineDescriptor.displayName());
        });

        Scanner scanner = new Scanner(System.in);

        String processId = scanner.next();

        String resource = JarReader.class.getResource(".").getFile();
        String dependenciesPath = resource + "../../../../agentLib";

        String agentFilePath = dependenciesPath + "/agent-1.0-SNAPSHOT.jar";

        System.out.println(agentFilePath);
        File agentFile = new File(agentFilePath);
        VirtualMachine vm = VirtualMachine.attach(processId);
        System.out.println(agentFile.getAbsolutePath());
        System.out.println(dependenciesPath);

        vm.loadAgent(agentFile.getAbsolutePath(),"dependenciesPath="+dependenciesPath + "/;storage=DATABASE");

        scanner.next();

        vm.detach();

        /*VirtualMachine jvm = VirtualMachine.attach(jvmPid);
        jvm.
        jvm.detach();*/
    }

}
