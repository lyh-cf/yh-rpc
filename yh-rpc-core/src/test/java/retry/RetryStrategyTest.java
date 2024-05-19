package retry;

/*
 *@title RetryStrategyTest
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/19 19:23
 */

import com.lyh.rpc.fault.retry.RetryStrategy;
import com.lyh.rpc.fault.retry.impl.FixedIntervalRetryStrategy;
import com.lyh.rpc.fault.retry.impl.NoRetryStrategy;
import com.lyh.rpc.model.RpcResponse;
import org.junit.Test;

/**
 * 重试策略测试
 */
public class RetryStrategyTest {
    //可以替换 retryStrategy 对象为不同的重试策略实现类，然后观察效果
    RetryStrategy retryStrategy = new FixedIntervalRetryStrategy();

    @Test
    public void doRetry() {
        try {
            RpcResponse rpcResponse = retryStrategy.doRetry(() -> {
                System.out.println("测试重试");
                throw new RuntimeException("模拟重试失败");
            });
            System.out.println(rpcResponse);
        } catch (Exception e) {
            System.out.println("重试多次失败");
            e.printStackTrace();
        }
    }
}