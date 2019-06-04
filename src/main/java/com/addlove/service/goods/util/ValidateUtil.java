package com.addlove.service.goods.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import com.addlove.service.goods.exception.ArgumentInvalidResult;
import com.addlove.service.goods.message.CommonResponseCode;
import com.addlove.service.goods.message.ResponseMessage;

/**
 * 用于controller参数校验工具类
 * @author lw
 */
public class ValidateUtil {

    private static final ValidatorFactory FACTORY = Validation.buildDefaultValidatorFactory();

    /**
     * 校验工具类
     *
     * @param validator 校验类型
     * @param <T> t 校验类型
     * @param t 参数对象
     * @return 校验结果
     */
    public static <T> List<String> validate(org.springframework.validation.Validator validator, T t) {
        if (null == validator) {
            Validator defaultValidator = FACTORY.getValidator();
            Set<ConstraintViolation<T>> constraintViolations = defaultValidator.validate(t);

            List<String> messageList = new ArrayList<>();
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                messageList.add(constraintViolation.getMessage());
            }
            return messageList;
        }

        BindException br = new BindException(t, t.getClass().getName());
        validator.validate(t, br);

        ResponseMessage result = new ResponseMessage(CommonResponseCode.SERVICE_INTERFACE_PARAM_ERROR.getMsg(),
                CommonResponseCode.SERVICE_INTERFACE_PARAM_ERROR.getCode(), null);
        // 按需重新封装需要返回的错误信息
        List<ArgumentInvalidResult> invalidArguments = new ArrayList<ArgumentInvalidResult>();
        // 解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        for (FieldError error : br.getFieldErrors()) {
            ArgumentInvalidResult air = new ArgumentInvalidResult();
            air.setDefaultMessage(error.getDefaultMessage());
            air.setField(error.getField());
            air.setRejectedValue(error.getRejectedValue());
            invalidArguments.add(air);
        }
        result.setData(invalidArguments);
        return null;
    }

}
