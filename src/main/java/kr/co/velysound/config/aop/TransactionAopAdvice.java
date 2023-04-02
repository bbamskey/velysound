package kr.co.velysound.config.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import java.util.Collections;
import java.util.HashMap;

/**
 * @author sjoh@mind-one.co.kr
 * @version 1.0
 * @category aop
 * @apiNote 트랜잭션 advice
 * @since 22.03.31
 */
@Aspect
@Configuration
@RequiredArgsConstructor
public class TransactionAopAdvice {
	/**
	 * 트랜잭션 적용 메소드 명
	 */
	private static final String TRANSACTION_METHOD_NAME = "*";

	/**
	 * 트랜잭션 적용 표현 방법
	 */
	private static final String TRANSACTION_EXPRESSION_NAME = "execution(* kr.co.velysound..service.*Service.*(..))";

	private final PlatformTransactionManager transactionManager;

	/**
	 * ----------------------------------------------------------------------
	 * PlatformTransactionmanager : 명령형 트랜잭션 중심 인터페이스
	 * NameMatchTransactionAttributeSource : 등록 된 이름으로 매칭시키는 구현체
	 * RuleBasedTransactionAttribute : 롤백 규칙 규정 구현체
	 * RollbackRuleAttribute : 세팅한 클래스(예하 클래스 포함)가 롤백 발생 여부 규정 구현체
	 * Collections.singletoneList(T) : 싱글톤 객체를 리스트로 반환
	 * ----------------------------------------------------------------------
	 */
	@Bean
	public TransactionInterceptor transactionAdvice() {
		TransactionInterceptor trsAdvice = new TransactionInterceptor();

		NameMatchTransactionAttributeSource trsAttributeSource = new NameMatchTransactionAttributeSource();
		RuleBasedTransactionAttribute trsAttribute = new RuleBasedTransactionAttribute();

		trsAttribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
		trsAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		HashMap<String, TransactionAttribute> trsMethods = new HashMap<String, TransactionAttribute>();
		trsMethods.put(TRANSACTION_METHOD_NAME, trsAttribute);
		trsAttributeSource.setNameMap(trsMethods);
		trsAdvice.setTransactionAttributeSource(trsAttributeSource);
		trsAdvice.setTransactionManager(transactionManager);

		return trsAdvice;
	}

	/**
	 * ---------------------------------------------------------------------------------
	 * AspectJExpressionPointcut : pointcut 표현식을 평가하기 위한 AspectJ 위버를 사용하는 구현체
	 * ---------------------------------------------------------------------------------
	 */
	@Bean
	public Advisor transactionAdviceAdvisor() {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(TRANSACTION_EXPRESSION_NAME);
		return new DefaultPointcutAdvisor(pointcut, transactionAdvice());
	}
}
