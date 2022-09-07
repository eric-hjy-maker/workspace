package com.mybatis;

import com.zhouyu.User;

import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hjy
 **/
public class MapperProxyFactory {

    private static Map<Class, TypeHandler> typeHandlerMap = new HashMap<>();

    static {
        typeHandlerMap.put(Integer.class, new IntegerTypeHandler());
        typeHandlerMap.put(String.class, new StringTypeHandler());
    }

    public static <T> T getMapper(Class<T> mapper) {
        Object proxyInstance = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{mapper}, (proxy, method, args) -> {
            // 解析sql-->执行sql-->返回结果

            // 1.创建数据库链接
            Connection connection = getConnection();

            Select annotation = method.getAnnotation(Select.class);
            // select * from user where name = #{name} and first_name = #{name}
            String sql = annotation.value();

            Map<String, Object> paramValueMapping = new HashMap<>();
            Parameter[] paramters = method.getParameters();
            for (int i = 0; i < paramters.length; i++) {
                Param annotation1 = paramters[i].getAnnotation(Param.class);
                if (annotation1 != null) {
                    paramValueMapping.put(annotation1.value(), args[i]);
                }
                paramValueMapping.put(paramters[i].getName(), args[i]);
            }
            System.out.println(paramValueMapping);

            ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
            GenericTokenParser parser = new GenericTokenParser("#{", "}", tokenHandler);
            String parseSql = parser.parse(sql);
            List<String> parameterMappings = tokenHandler.getParameterMappings();


            // 2.构建PrepareStatement
            PreparedStatement statement = connection.prepareStatement(parseSql);
            for (int i = 0; i < parameterMappings.size(); i++) {
                String paramName = parameterMappings.get(i);
                Object paramValue = paramValueMapping.get(paramName);
                Class<?> paramType = paramters[i].getType();
                typeHandlerMap.get(paramType).setParameter(statement, i + 1, paramValue);
            }


            // 3.执行PreparedStatement
            statement.execute();

            // 4.封装结果
            List<User> list = new ArrayList<>();

            Class resultType = null;
            Type genericReturnType = method.getGenericReturnType();
            if (genericReturnType instanceof Class){
                // 不是泛型
                resultType = (Class) genericReturnType;
            }else if (genericReturnType instanceof ParameterizedType){
                // 是泛型
                Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
                resultType = (Class) actualTypeArguments[0];
            }

            ResultSetMetaData metaData = statement.getResultSet().getMetaData();
            List<String> columnList = new ArrayList<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                columnList.add(metaData.getColumnName(i));
            }

            while (statement.getResultSet().next()) {
                Object instance = resultType.newInstance();

                for (String column : columnList) {
                    Object value = statement.getResultSet().getObject(column);
                    String fieldName = columnToFieldName(column);
                    Field field = resultType.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    field.set(instance, value);
                }

                /*user.setId(statement.getResultSet().getInt("id"));
                user.setName(statement.getResultSet().getString("name"));
                user.setAge(statement.getResultSet().getInt("age"));
                list.add(user);*/
            }

            // 5.关闭资源
            connection.close();

            return list;
        });
        return (T)proxyInstance;
    }

    public static String columnToFieldName(String column){
        String[] split = column.split("_");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            if (i == 0){
                sb.append(split[i]);
            }else {
                sb.append(split[i].substring(0, 1).toUpperCase());
                sb.append(split[i].substring(1));
            }
        }
        return sb.toString();
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/hand_mybatis", "root", "123456");
    }
}
