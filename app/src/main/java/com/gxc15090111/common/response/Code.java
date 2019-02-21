package com.gxc15090111.common.response;

/**
 * 微服务请求响应码
 * @author
 *
 */
public class Code {
	/**
     * 成功
     */
    public static final String SUCCESS = "200";

    /**
     * 请求格式出错。(包括表达参数，处理参数错误)
     */
    public static final String FAIL = "400";

    /**
     * 服务器错误
     */
    public static final String ERROR = "500";
    
    /**
     * 资源不存在
     */
    public static final String NOT_FOUND = "404";
    /**
     * 余额不足
     */
    public static final String NOT_ACCOUNT = "602";

    /**
     * 认证授权失败
     */
    public static final String TOKEN_INVALID = "401";

    /**
     * 存在未处理提现记录
     */
    public static final String ACCOUNT_EXISTS = "614";
    /**
     * http请求方法错误
     */
    public static final String METHOD_ERROR="405";
    /**
     * 数量已经达到上限，无法注册了
     */
    public static final String MUCH_ERROR="630";

}
