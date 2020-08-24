package yibao.yiwei.utils;

import java.util.Random;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;

public class TestJedis {
	 private String id;
	    private int num;
	    public TestJedis(String id,int num){
	        this.id = id;
	        this.num = num;
	    }
	    //控制单元
	    public void service(){
	        //Jedis jedis = new Jedis("127.0.0.1",6379);
	        Jedis jedis = JedisUtil.getJedis();
	        String value = JedisUtil.getStr("compid:"+id);
	        //判断该值是否存在
	        try{
	            if(value == null){
	                //不存在创建
	                jedis.setex("compid:"+id,15,Long.MAX_VALUE-num+"");
	            }else{
	                //存在 递增
	                Long val = jedis.incr("compid:"+id);
	                business(id,num-(Long.MAX_VALUE-val));
	            }
	        }catch (JedisDataException e){
	            System.out.println("使用已经到达上限，请升级会员级别");
	            return;
	        }finally {
	            jedis.close();
	        }
	    }
	    //业务操作
	    public void business(String id,Long val){
	        System.out.println("用户:"+id+" 业务操作执行第"+val+"次");
	    }
	    
	    
	    
	    //测试执行
	    public static void main(String[] args) {
	        MyThread mt = new MyThread("初级用户",10);
	        MyThread mt1 = new MyThread("高级用户",15);
	        mt.start();
	        mt1.start();
	    }
	}

	class MyThread extends Thread{
		TestJedis sc;
	    public MyThread(String id,int num){
	        sc =  new TestJedis(id,num);
	    }
	    @Override
	    public void run() {
	        while(true){
	            sc.service();
	            try{
	                Random r = new Random();
	                int a = r.nextInt(100);
	                Thread.sleep(500L+a);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }

	        }

	    }
	}
