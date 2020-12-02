package com.lsf.studymybatis.entity;


import java.util.Arrays;
import java.util.Optional;

public interface BaseEnum<T> {

    String getCode();

    static <T> BaseEnum valueOfEnum(Class<BaseEnum> enumClass, T value) {
        if (value == null) {
            return null;
        }
        BaseEnum[] enums = enumClass.getEnumConstants();
        Optional<BaseEnum> optional = Arrays.asList(enums).stream().filter(baseEnum -> baseEnum.getCode().equals(value)).findAny();
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new RuntimeException("未找到：" + value + "对应的" + enumClass.getName());
    }
}
