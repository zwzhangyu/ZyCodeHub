package com.zy.utils.number;

import cn.hutool.core.util.ReflectUtil;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/***
 *
 *   数值类型工具类
 * @author ZhangYu
 * @date 2021/10/10
 */
public class MyNumberUtil {
    /***
     *   消除BigDecimal中多余的0
     * @param list   输入集合
     * @author ZhangYu
     * @date 2021/9/30
     */
    public  static void transformBigDecimalMap(List<Map<String, Object>> list ){
        list.stream().parallel().forEach(
                p->{
                    for (Map.Entry<String, Object> entry: p.entrySet()) {
                        Class<?> aClass = entry.getValue().getClass();
                        if (aClass.isAssignableFrom(BigDecimal.class) ){
                            try {
                                BigDecimal value = (BigDecimal)entry.getValue();
                                if (value!=null){
                                    String newValue = value.stripTrailingZeros().toPlainString();
                                    entry.setValue(newValue);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else if (aClass.isAssignableFrom(Double.class)) {
                            try {
                                Double value = (Double)entry.getValue();
                                if (value!=null){
                                    DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
                                    entry.setValue(decimalFormat.format(value));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else if (aClass.isAssignableFrom(Long.class)) {
                            try {
                                Long value = (Long)entry.getValue();
                                if (value!=null){
                                    DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
                                    entry.setValue(decimalFormat.format(value));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }


                }
        );
    }



    /***
     *   自动去除BigDecimal尾小数点儿后多余的0
     * @param list   输入集合
     * @author ZhangYu
     * @date 2021/9/30
     */
    public  static void transformBigDecimal(List<?> list ){
        //并行处理提高效率
        list.stream().parallel().forEach(
                p->{
                    Class<?> aClass = p.getClass();
                    Field[] fields = ReflectUtil.getFields(aClass);
                    for (Field field : fields) {
                        Class<?> type = field.getType();
                        if (type.isAssignableFrom(BigDecimal.class)){
                            try {
                                field.setAccessible(true);
                                BigDecimal value = (BigDecimal) field.get(p);
                                if (value!=null){
                                    //返回一个 BigDecimal ，它在数字上等于此值， BigDecimal表示中删除任何尾随的零
                                    field.set(p,value.stripTrailingZeros());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } }
        );
    }

    /**
     * Object转BigDecimal类型
     *
     * @param value 传入Object值
     * @return 转成的BigDecimal类型数据
     */
    public static BigDecimal ToBigDecimal(Object value) {
        BigDecimal bigDec = null;
        if (value != null) {
            if (value instanceof BigDecimal) {
                bigDec = (BigDecimal) value;
            } else if (value instanceof String) {
                bigDec = new BigDecimal((String) value);
            } else if (value instanceof BigInteger) {
                bigDec = new BigDecimal((BigInteger) value);
            } else if (value instanceof Number) {
                bigDec = new BigDecimal(((Number) value).doubleValue());
            } else {
                throw new ClassCastException("Can Not make [" + value + "] into a BigDecimal.");
            }
        }
        return bigDec;
    }



}
