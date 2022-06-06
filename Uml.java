public class Uml {
    public static void main(String[] args) {
        UmlFrame.getInstance();
        UmlFrame.getInstance().newItem(); // 新增frame 上的物件
        UmlFrame.getInstance().addItem(); // 把他加到frame 中
        UmlFrame.getInstance().initLayout(); // 佈局
        UmlFrame.getInstance().groupAddActionListener();
    }
}