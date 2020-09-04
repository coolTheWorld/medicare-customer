package yibao.yiwei.exception;

/**
 * @description: 责任链处理异常
 * @author: sunshy
 * @create: 2020-09-04
 **/
public class HandlerChainException extends RuntimeException{
    private String message;
    public HandlerChainException(String message){
        super(message);
        this.message = message;
    }
}
