package com.baidu.ee.core;

/**
 * Created by xuchen04 on 2015/3/5.
 */
public class RestResponse<T> {

    private String code;
    private T content;

    public RestResponse(RestResponseCode code) {
        this.code = code.getValue();
    }

    public RestResponse(RestResponseCode code, T content) {
        this.code = code.getValue();
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public enum RestResponseCode{
        SUCC("success"),ERR("error");

        private String value;

        RestResponseCode(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
