package com.ongsat.blog.web.config;

import cn.hutool.core.date.DateUtil;
import com.ongsat.blog.api.exception.ParameterErrorException;
import com.ongsat.blog.api.util.GsonUtil;
import com.ongsat.blog.web.common.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.awt.image.BufferedImage;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * 异常捕获 加 请求日志记录
 * @author orange
 */
@Slf4j
@Aspect
@Component
public class ExceptionHandler {

	@Value("${app.interface-log:true}")
	private Boolean interfaceLog;

    @Pointcut("execution(* com.ongsat.blog.web..controller.*.*.*(..))")
    private void allMethod() {
    }

	@Around("allMethod()")
	public Object doAround(ProceedingJoinPoint call) throws Throwable {

		try {
			Object result = call.proceed();

			if (interfaceLog) {
				StringBuilder basicBuilder = getBasicBuilder(call);
				basicBuilder.append("请求返回结果: ");
				if (result instanceof BufferedImage) {
					basicBuilder.append("图片流 BufferedImage");
				} else {
					basicBuilder.append(GsonUtil.buildGson().toJson(result));
				}
				log.info(basicBuilder.toString());
			}

			return result;

		} catch (ConstraintViolationException c) {
			Set<ConstraintViolation<?>> constraintViolations = c.getConstraintViolations();
			for (ConstraintViolation<?> con : constraintViolations) {
				if (interfaceLog) {
					PathImpl pathImpl = (PathImpl) con.getPropertyPath();
					StringBuilder basicBuilder = getBasicBuilder(call);
					basicBuilder.append("参数效验不通过: ");
					basicBuilder.append("\n");
					basicBuilder.append(pathImpl.getLeafNode().getName());
					basicBuilder.append("\n");
					basicBuilder.append(con.getMessage());
					log.info(basicBuilder.toString());
				}
				break;
			}
			if (interfaceLog && 0 == constraintViolations.size()) {
				StringBuilder basicBuilder = getBasicBuilder(call);
				basicBuilder.append("参数效验不通过: ");
				log.info(basicBuilder.toString());
			}
			throw new ParameterErrorException();
		} catch (ParameterErrorException p) {
			if (interfaceLog) {
				StringBuilder basicBuilder = getBasicBuilder(call);
				basicBuilder.append("参数效验不通过: ");
				basicBuilder.append(p.getMessage());
				log.info(basicBuilder.toString());
			}
			throw p;
		} catch (Throwable e) {
			throw e;
		}
	}

	private StringBuilder getBasicBuilder(ProceedingJoinPoint call) {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

		MethodSignature signature = (MethodSignature) call.getSignature();
		Method method = signature.getMethod();
		String methodName = method.getName();
		String[] classNameArray = method.getDeclaringClass().getName().split("\\.");
		String className = classNameArray[classNameArray.length - 1];

		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		builder.append(">>>>>>>>接口日志<<<<<<<<<");
		builder.append("\n");
		builder.append("请求开始时间: ");
		builder.append(DateUtil.now());
		builder.append("\n");
		builder.append("请求url: ");
		builder.append(request.getRequestURI());
		builder.append("\n");
		builder.append("请求函数: ");
		builder.append(className + "." + methodName);
		builder.append("\n");
		builder.append("请求IP: ");
		builder.append(IpUtil.getIpAddress(request));
		builder.append("\n");

		return builder;
    }
}
