package nafos.core.mode;

import nafos.core.entry.ClassAndMethod;
import nafos.core.entry.HttpRouteClassAndMethod;
import nafos.core.entry.SocketRouteClassAndMethod;
import nafos.core.mode.filter.HttpMessageFilterInit;
import nafos.core.mode.filter.RemoteCallFilterInit;
import nafos.core.mode.filter.SecurityFilterInit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 作者 huangxinyu
 * @version 创建时间：2018年1月4日 下午2:40:05 
 * 开始初始化加载被注解的路由
 */
public class InitMothods  {
	private static Logger logger = LoggerFactory.getLogger(InitMothods.class);

	private static ApplicationContext context = null;

	//http注册路由
	private static Map<String, HttpRouteClassAndMethod> httpRouteMap ;

	//socket注册路由
	private static Map<Integer, SocketRouteClassAndMethod> socketRouteMap ;

	//其他filter等处理事件
	private static final HashMap<String, ClassAndMethod> filterMap = new HashMap<>();

	private static final HashMap<String, List<ClassAndMethod>> filterListMap = new HashMap<>();



	public static void init( ApplicationContext ac) {
		context = ac;

		//1.遍获取实现Route的类
		RouteFactory rf = new RouteFactory(context);
		httpRouteMap = rf.getHttpRouteMap();
		socketRouteMap = rf.getSocketRouteMap();

		//2.获取通信前置filter
		filterMap.put("httpMessageFilter",new HttpMessageFilterInit(context).getFilter());

		//3.获取远程前置filter
		filterMap.put("remoteCallFilter",new RemoteCallFilterInit(context).getFilter());

		//4.获取http安全校验filter
		filterMap.put("httpSecurityFilter",new SecurityFilterInit(context).getHttpSecurityFilter());

		//5.获取socket安全校验filter
		filterMap.put("socketSecurityFilter",new SecurityFilterInit(context).getSocketSecurityFilter());

        //6.获取socket连接filter
		filterListMap.put("connectClassAndMethods",new SocketConnectFactory(context).getConnectClassAndMethod());

        //7.获取socket断开连接filter
		filterListMap.put("disConnectClassAndMethods",new SocketConnectFactory(context).getDisConnectClassAndMethod());

//
//		//3.获取MQ消息处理handle
//		mqQueueMessageListener = new QueueMessageListenerInit(context).getListener();
//
//		//4.MQ消息监听handle路由
//		mqQueueHandleMap = new QueueMessageHandleInit(context).getHandleRoute();
//


	}
	

	public static void setFilter(String key,ClassAndMethod classAndMethod){
		logger.debug("设置了filter:{}",key);
		filterMap.put(key,classAndMethod);
	}

	public static ClassAndMethod getFilter(String key){
		return filterMap.get(key);
	}

	
	
	 /**
	  * 获取路由方法
	  * @param uri
	  * @return
	  */
	public static HttpRouteClassAndMethod getHttpHandler(String uri) {
		return   httpRouteMap.get(uri);
	}

	public static SocketRouteClassAndMethod getSocketHandler(int code) {
		return   socketRouteMap.get(code);
	}

	public static ClassAndMethod getMessageFilter(){
		return filterMap.get("httpMessageFilter");
	}

	public static ClassAndMethod getRemoteCallFilter(){
		return filterMap.get("remoteCallFilter");
	}

	public static ClassAndMethod getHttpSecurityFilter(){
		return filterMap.get("httpSecurityFilter");
	}

    public static ClassAndMethod getSocketSecurityFilter(){
        return filterMap.get("socketSecurityFilter");
    }

    public static List<ClassAndMethod> getSocketConnectFilter(){
		return filterListMap.get("connectClassAndMethods");
    }

    public static List<ClassAndMethod> getSocketDisConnectFilter(){
		return filterListMap.get("disConnectClassAndMethods");
    }

	
}