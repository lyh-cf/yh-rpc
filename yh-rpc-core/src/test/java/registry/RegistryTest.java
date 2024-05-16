package registry;

/*
 *@title RegistryTest
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/14 23:57
 */

import com.lyh.rpc.config.RegistryConfig;
import com.lyh.rpc.model.ServiceMetaInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.lyh.rpc.registry.Registry;
import com.lyh.rpc.registry.impl.EtcdRegistry;
import java.util.List;
/**
 * 注册中心测试
 */
public class RegistryTest {

    final Registry registry = new EtcdRegistry();

    @Before
    public void init() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("http://localhost:2379");
        registry.init(registryConfig);//开启心跳检测
    }

    @Test
    public void register() throws Exception {
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName("myService");
        serviceMetaInfo.setServiceVersion("1.0");
        serviceMetaInfo.setServiceHost("localhost");
        serviceMetaInfo.setServicePort(1234);
        registry.register(serviceMetaInfo);
        serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName("myService");
        serviceMetaInfo.setServiceVersion("1.0");
        serviceMetaInfo.setServiceHost("localhost");
        serviceMetaInfo.setServicePort(1235);
        registry.register(serviceMetaInfo);
        serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName("myService");
        serviceMetaInfo.setServiceVersion("2.0");
        serviceMetaInfo.setServiceHost("localhost");
        serviceMetaInfo.setServicePort(1234);
        registry.register(serviceMetaInfo);
    }

    @Test
    public void unRegister() {
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName("myService");
        serviceMetaInfo.setServiceVersion("1.0");
        serviceMetaInfo.setServiceHost("localhost");
        serviceMetaInfo.setServicePort(1234);
        registry.unRegister(serviceMetaInfo);
    }

    @Test
    public void serviceDiscovery() {
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName("myService");
        serviceMetaInfo.setServiceVersion("1.0");
        String serviceKey = serviceMetaInfo.getServiceKey();
        List<ServiceMetaInfo> serviceMetaInfoList = registry.serviceDiscovery(serviceKey);
        /*
        [ServiceMetaInfo(serviceName=myService, serviceVersion=1.0, serviceHost=localhost, servicePort=1234, serviceGroup=default),
        ServiceMetaInfo(serviceName=myService, serviceVersion=1.0, serviceHost=localhost, servicePort=1235, serviceGroup=default)]
         */
        System.out.println("serviceMetaInfoList："+serviceMetaInfoList);
        Assert.assertNotNull(serviceMetaInfoList);
    }
    @Test
    public void heartBeat() throws Exception {
        // init 方法中已经执行心跳检测了
        register();
        // 阻塞 1 分钟
        Thread.sleep(60 * 1000L);
    }
}