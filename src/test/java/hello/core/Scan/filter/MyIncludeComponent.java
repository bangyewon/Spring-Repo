package hello.core.Scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) // class 레벨에 붙은 것
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}
