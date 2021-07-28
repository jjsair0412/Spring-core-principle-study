package hello.core.scan.Filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentFilterAppConfigTest {
    @Test
    void filterScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);

        // 존재해야하니까 not null
        assertThat(beanA).isNotNull();

        // BeanB는 bean객체로 존재하면 안됌.
        assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class)
        );
    }

    @Configuration
    @ComponentScan(
            /**
            includeFilters로  컴포넌트 스캔 대상을 추가로 지정할 수 있고
            excludeFilters로 컴포넌트 스캔 대상을 제외할 수 있다.
            BeanA는 @MyIncludeComponent 어노테이션을 지정해주었기 때문에 bean객체로 존재하는것이고
            BeanB는 @MyExcludeComponent 어노테이션을 지정해주었기 때문에 컴포넌트 스캔 대상에서 제외되어
            bean객체로 존재하지 않는 것이다.
            **/

            includeFilters = @Filter(type = FilterType.ANNOTATION,classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION,classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig{

    }
}
