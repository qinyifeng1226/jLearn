package com.qyf.jlearn.spring.aop;

/**
 * 动态代理
 * 1、org.springframework.aop.framework.CglibAopProxy.DynamicAdvisedInterceptor#intercept
 * <p>
 * 2、获取对应的链对象：org.springframework.aop.framework.AdvisedSupport#getInterceptorsAndDynamicInterceptionAdvice
 * 3、如果上一步链对象为空，则通过工厂创建，获得TransactionInterceptor：org.springframework.aop.framework.DefaultAdvisorChainFactory#getInterceptorsAndDynamicInterceptionAdvice
 * 4、创建并调用处理 ：new CglibAopProxy.CglibMethodInvocation#proceed
 * 5、org.springframework.aop.framework.ReflectiveMethodInvocation#proceed
 * <p>
 * 6、事务拦截开始：org.springframework.transaction.interceptor.TransactionInterceptor#invoke
 * 7、org.springframework.transaction.interceptor.TransactionAspectSupport#invokeWithinTransaction
 * 8、创建并返回 数据源事务管理器DataSourceTransactionManager：invokeWithinTransaction#determineTransactionManager
 * 9、将DataSourceTransactionManager转换为接口PlatformTransactionManager：invokeWithinTransaction#asPlatformTransactionManager
 * 10、获得调用方法定义描述：invokeWithinTransaction#methodIdentification（RuleBasedTransactionAttribute继承自DefaultTransactionAttribute继承自DefaultTransactionDefinition继承自TransactionDefinition）
 * 11、如果需要则创建事务，TransactionAspectSupport.TransactionInfo：createTransactionIfNecessary
 * 12、事务拦截结束：TransactionInterceptor处理调用： retVal = invocation.proceedWithInvocation();
 * <p>
 * 13、回到new CglibAopProxy.CglibMethodInvocation#proceed
 * 14、回到org.springframework.aop.framework.ReflectiveMethodInvocation#proceed
 * 15、回到org.springframework.aop.framework.CglibAopProxy.CglibMethodInvocation#invokeJoinpoint
 * <p>
 * 16、方法代理执行调用：org.springframework.cglib.proxy.MethodProxy#invoke
 * 17、创建FastClassInfo，动态创建代理对象类：org.springframework.cglib.proxy.MethodProxy#init，org.springframework.cglib.proxy.MethodProxy#helper
 * <p>
 * 18、回到org.springframework.transaction.interceptor.TransactionAspectSupport#invokeWithinTransaction
 * 19、回到org.springframework.aop.framework.CglibAopProxy.DynamicAdvisedInterceptor#intercept
 */
public class CglibAopProxyTest {

}
