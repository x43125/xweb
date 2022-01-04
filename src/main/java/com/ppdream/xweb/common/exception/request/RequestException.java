package com.ppdream.xweb.common.exception.request;

import com.ppdream.xweb.common.exception.base.BaseException;

/**
 * @Author: x43125
 * @Date: 22/01/04
 */
public class RequestException extends BaseException {
    private static final long serialVersionUID = 1L;

    public RequestException(String code, Object[] args) {
        super("request", code, args, null);
    }
}
