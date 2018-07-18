package gui.weng.miaosha.util.validator.mobile;


import gui.weng.miaosha.util.validator.ValidatorUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsMobileValidation implements ConstraintValidator<IsMobile,String>{

    private boolean required = false;


    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(required){
            return ValidatorUtil.isMobile(value);
        }else {
            if(value.isEmpty()){
                return true;
            }else{
                return ValidatorUtil.isMobile(value);
            }
        }
    }
}
