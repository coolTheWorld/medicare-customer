package yibao.yiwei.exception;

/**
 * 构建异常
 *
 * @author sunshy
 */
public class BuildProcessException extends RuntimeException {
    private String message;

    public BuildProcessException(String message) {
        super(message);
        this.message = message;
    }
}
