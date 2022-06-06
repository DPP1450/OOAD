public class Uml {
    public static void main(String[] args) {
        UmlFrame.getInstance();
        UmlFrame.getInstance().newItem(); // 新增frame 上的物件
        UmlFrame.getInstance().addItem(); // 將上面新增的物件加到frame 中
        UmlFrame.getInstance().initLayout(); // 初始化佈局
        UmlFrame.getInstance().groupAddActionListener(); // 新增menubar item的ActionListener
    }
}