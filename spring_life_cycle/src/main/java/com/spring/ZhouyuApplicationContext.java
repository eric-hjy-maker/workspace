package com.spring;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hjy
 **/
public class ZhouyuApplicationContext {

    private Class configClass;

    private ConcurrentHashMap<String, Object> singletonMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList();

    public ZhouyuApplicationContext(Class configClass) throws Exception{
        this.configClass = configClass;

        // 解析配置类
        // ComponentScan注解--》扫描路径--》扫描路径下的类--》解析类上的注解--》封装BeanDefinition
        scan(configClass);

        for (String beanName : beanDefinitionMap.keySet()) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (beanDefinition.getScope().equals("singleton")) {
                Object bean = createBean(beanName, beanDefinition);
                singletonMap.put(beanName, bean);
            }
        }
    }

    public Object createBean(String beanName, BeanDefinition beanDefinition) {
        Class beanClass = beanDefinition.getClazz();
        Object bean = null;
        try {
            bean = beanClass.newInstance();

            // 依赖注入
            for (Field field : beanClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    Object dependencyBean = getBean(field.getName());
                    field.setAccessible(true);
                    field.set(bean, dependencyBean);
                }
            }

            // Aware
            if (bean instanceof BeanNameAware) {
                ((BeanNameAware) bean).setBeanName(beanName);
            }

            // 初始化前
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                bean = beanPostProcessor.postProcessBeforeInitialization(bean, beanName);
            }

            // 初始化
            if (bean instanceof InitailizingBean) {
                ((InitailizingBean) bean).afterPropertiesSet();
            }

            // 初始化后
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                bean = beanPostProcessor.postProcessAfterInitialization(bean, beanName);
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return bean;
    }

    private void scan(Class configClass) throws Exception {
        ComponentSacn componentSacn = (ComponentSacn) configClass.getAnnotation(ComponentSacn.class);
        String path = componentSacn.value();

        // 扫描
        // bootstrap-->jre/lib/
        // ext-->jre/ext
        // app-->classpath
        ClassLoader classLoader = ZhouyuApplicationContext.class.getClassLoader();
        URL resource = classLoader.getResource(path.replaceAll("\\.", "/"));
        File file = new File(resource.getFile());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                Class<?> aClass = classLoader.loadClass(path + "." + f.getName().replaceAll(".class", ""));
                if (aClass.isAnnotationPresent(Component.class)) {

                    if (BeanPostProcessor.class.isAssignableFrom(aClass)) {
                        BeanPostProcessor beanPostProcessor = (BeanPostProcessor) aClass.newInstance();
                        beanPostProcessorList.add(beanPostProcessor);
                    }

                    Component component = aClass.getAnnotation(Component.class);
                    String beanName = component.value();

                    BeanDefinition beanDefinition = new BeanDefinition(aClass, "");
                    if (aClass.isAnnotationPresent(Scope.class)){
                        Scope scopeAnnotation = aClass.getAnnotation(Scope.class);
                        beanDefinition.setScope(scopeAnnotation.value());
                    }else {
                        beanDefinition.setScope("singleton");
                    }
                    beanDefinitionMap.put(beanName, beanDefinition);
                }
            }
        }
    }

    public Object getBean(String beanName) {
        if (beanDefinitionMap.containsKey(beanName)) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            String scope = beanDefinition.getScope();
            if ("singleton".equals(scope)) {
                Object bean = singletonMap.get(beanName);
                if (bean == null) {
                    bean = createBean(beanName, beanDefinition);
                    singletonMap.put(beanName, bean);
                }
                return bean;
            } else {
                return createBean(beanName, beanDefinition);
            }
        }else {
            throw new RuntimeException("beanName不存在");
        }
    }
}
