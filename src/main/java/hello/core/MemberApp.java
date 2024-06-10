package hello.core;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.lang.model.element.AnnotationValue;

public class MemberApp {
    //main 메소드로 확인하는 것은 좋은것이 아님
    public static void main(String[] args) { // 팁 : psvm 엔터 치면 해당 코드 낟옴
//        AppConfig appConfig = new AppConfig(); //appConfig에서 모두 결정하도록 했음
//        MemberService memberService = appConfig.memberService(); // MemberServiceImpl이 들어가있음
//        MemberService memberService = new MemberServiceImpl();

        //해당 문구 쓰게되면 app-config에 있는 환경 설정 정보 갖고 스프링이 인자의 @Bean이 붙은 것을 spring 컨테이너에 객체 생성한걸 집어넣어 관리해줌
        ApplicationContext applicationContext = new
                AnnotationConfigApplicationContext(AppConfig.class);
        //기존에는  new AppConfig()로 직접 찾아왔지만 이제는 스프링 통해 찾아옴
        //getBean()의 인자 안에는 이름을 써줘야함(어떤것을 찾을지)
        //두번째 인자는 반환타입을 써줘야함
        //이제 꺼낼때는 이름 써주면 됨
        MemberService memberService =
                applicationContext.getBean("memberService", MemberService.class);
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}