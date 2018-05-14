package com.huhusky.common.utils.util;
import java.lang.reflect.Field;

public class SqlBuilder {

    static String[] Jobs = {"insert", "update", "create"};
    static String job = null;
    static String table = null;
    static Class<?> clazz = null;

    public static void build(Class<?> clazz, int type, String table) {
        SqlBuilder.clazz = clazz;
        SqlBuilder.table = table;
        SqlBuilder.job = SqlBuilder.Jobs[type];

        StringBuffer sb = new StringBuffer(job);
        if (job.equals("update")) {
            sb.append(" ").append(table).append(" set ");
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                sb.append(field.getName()).append("=#{").append(field.getName()).append("}, ");
            }
        } else if (job.equals("insert")) {
            sb.append(" into ").append(table).append(" (");
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                if (i != 0) {
                    sb.append(",");
                }
                sb.append(field.getName());
            }
            sb.append(") values (");
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                if (i != 0) {
                    sb.append(",");
                }
                sb.append(" #{").append(field.getName()).append("}");
            }
            sb.append(");");
        } else if (job.equals("create")) {
            sb.append(" table ").append(table).append(" (");
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                if (i != 0) {
                    sb.append(", ");
                }
                sb.append(field.getName()).append(" ").append(fieldTypeMapper(field.getType()));
            }
            sb.append(");");
        }

        System.out.println(sb.toString());
    }

    private static String fieldTypeMapper(Class<?> clazz) {
        if (clazz.equals(String.class)) {
            return "varchar(255)";
        } else if (clazz.equals(long.class) || clazz.equals(Long.class)) {
            return "bigint";
        } else if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
            return "int";
        } else if (clazz.equals(Boolean.class) || clazz.equals(boolean.class)) {
            return "tinyint(1)";
        } else if (clazz.equals(Float.class) || clazz.equals(float.class)) {
            return "float";
        } else {
            return clazz.getName();
        }
    }

}
