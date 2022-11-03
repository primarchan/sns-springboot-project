package com.primarchan.sns.util;

import java.util.Optional;

public class ClassUtils {

    public static <T> Optional<T> getSafCastInstance(Object o, Class<T> clazz) {
        return clazz != null && clazz.isInstance(o) ? Optional.of(clazz.cast(o)) : Optional.empty();
    }
}
