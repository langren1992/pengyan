package com.anserx.pengyan.common.exception.assertion;

import com.anserx.pengyan.common.exception.BaseException;
import com.anserx.pengyan.common.exception.CommonException;
import com.anserx.pengyan.common.exception.enums.IResponseEnum;

import java.text.MessageFormat;


public interface CommonExceptionAssert extends IResponseEnum, Assert {
    @Override
    default BaseException newException(Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new CommonException(this, args, msg);
    }
    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new CommonException(this, args, msg, t);
    }
}
