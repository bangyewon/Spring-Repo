package hello.core.singleton;

public class StatefulService {
//    private int price; // 상태 유지하는 필드
    //A가 들어와서 10000원일때 B가 들어와서 20000원 넣게되면
    //상태가 변경되버림

    public int order(String name,int price) { // void가 아닌 int으로 넘겨버리기 !
        System.out.println("name =" + name + "price = " + price);
//        this.price = price; // 여기가 문제가 됨
        return price; // 이렇게 하게되면 지역변수로 변경됨

    }

//    public int  getPrice() {
//        return price;
//    }
}
