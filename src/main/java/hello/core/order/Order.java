package hello.core.order;

public class Order {
    private  Long memberId;
    private  String itemName;
    private  int itemPrice;
    private  int discountPrice;
    //생성자 만들기
    public Order(Long memberId,String itemName,int itemPrice,int discountPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }
    //할인계산
    public int calculatePrice() {
        return  itemPrice - discountPrice;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    @Override // 객체 출력하면 string으로 나오도록 toString출력되도록 -> 데이터 쉽게 볼 수 있도록
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ",itemName=" +itemName+"\'"+
                ",itemPrice=" + itemPrice +
                ",discountPrice=" +discountPrice +
                '}';
    }
}
