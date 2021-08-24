package com.ixfosa.typeconverter;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by ixfosa on 2021/4/12 15:27
 */
public class StringToDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String s) {

        if ((s == null)) {
            throw new RuntimeException("请传入数据");
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(s);
        } catch (ParseException e) {
            throw new RuntimeException("数据类型转换错误");
        }
    }
}
