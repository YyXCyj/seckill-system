package org.example.seckill.validator;

import org.example.seckill.utils.ValidatorUtil;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

/**
 * 验证手机号
 *
 * @author: LC
 * @date 2022/3/2 3:05 下午
 * @ClassName: isMobile
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {
            org.example.seckill.validator.IsMobileValidator.class
        }
)
public @interface IsMobile {

    boolean required() default true;

    String message() default "手机号码格式错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
