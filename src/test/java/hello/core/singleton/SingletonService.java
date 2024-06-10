package hello.core.singleton;

public class SingletonService {
    //싱글톤 테스트
    //관례상 new SingletonService 자기 자신을 내부의 private로 하나 갖고있음 (static)으로
    //이렇게하면 class레벨에 하나만 올라가게됨 static영역에
    private static final SingletonService instance = new SingletonService();
    //내부적으로 생성해 instance에 넣어놓게됨 -> 자기 자신을 인스턴스 객체 하나 생성해 이 안에만 들어가있음

    public static SingletonService getInstance() {
        return instance;
    }
    //생성자를 private로 선언해 외부에서 선언 못하게 만들기 (단 한개만 있어야 하기에)
    private  SingletonService() {
    }
    public  void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
