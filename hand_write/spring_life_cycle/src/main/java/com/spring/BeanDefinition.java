package com.spring;

/**
 * @author hjy
 **/
public class BeanDefinition {

    private Class clazz;
    private String scope;

    public BeanDefinition(Class clazz, String scope) {
        this.clazz = clazz;
        this.scope = scope;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return scope;
    }

}
