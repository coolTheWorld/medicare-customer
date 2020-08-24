package yibao.yiwei.utils;

import java.util.ResourceBundle;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 集成jedis 使用redis缓存
 * @author Administrator
 *
 */

public class JedisUtil {

	private static JedisPool jp = null;
	private static String host = null;
	private static int port;
	private static int maxTotal;
	private static int maxIdle;
	
	/**
	 * 将基础配置放到静态块中
	 */
	static {
		ResourceBundle rb = ResourceBundle.getBundle("redis");//读取配置文件
		host = rb.getString("redis.host");//获取配置文件中redis的ip地址
		port =  Integer.parseInt(rb.getString("redis.port"));//获取redis的端口
		maxTotal = Integer.parseInt(rb.getString("redis.maxTotal"));//获取redis的最大连接数
		maxIdle = Integer.parseInt(rb.getString("redis.maxIdle"));//最大空闲数

		JedisPoolConfig jpc = new JedisPoolConfig();
		jpc.setMaxTotal(maxTotal);
		jpc.setMaxIdle(maxIdle);

		jp = new JedisPool(jpc,host,port);
	}
	
	/**
	 * 获取Jedis
	 * @return
	 */
	public static Jedis getJedis(){

		return jp.getResource();
	}
	
	/**
	 * 存储String类型的数据
	 * @param key
	 * @param value
	 * @return
	 */
	public static void setStr(String key,String value) {
		Jedis jedis = JedisUtil.getJedis();
		jedis.set(key, value);
		jedis.close();
	}
	
	/**
	 * 获取String类型的key的value
	 * @param key
	 * @param value
	 * @return
	 */
	public static String getStr(String key) {
		Jedis jedis = JedisUtil.getJedis();
		String value = jedis.get(key) != null?jedis.get(key):null;
		jedis.close();
		return value;
	}
	
	
	
	public static void main(String[] args) {
		
		JedisUtil.getJedis();
	}



}
