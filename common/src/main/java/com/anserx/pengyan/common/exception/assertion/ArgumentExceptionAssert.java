package com.anserx.pengyan.common.exception.assertion;

import com.anserx.pengyan.common.exception.ArgumentException;
import com.anserx.pengyan.common.exception.BaseException;
import com.anserx.pengyan.common.exception.enums.IResponseEnum;

import java.text.MessageFormat;

/**
 * <p>参数异常</p>
 * <p>在处理业务过程中校验参数出现错误, 可以抛出该异常</p>
 * <p>编写公共代码（如工具类）时，对传入参数检查不通过时，可以抛出该异常</p>
 *
 */
public interface ArgumentExceptionAssert extends IResponseEnum, Assert {
    @Override
    default BaseException newException(Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new ArgumentException(this, args, msg);
    }
    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new ArgumentException(this, args, msg, t);
    }
}
