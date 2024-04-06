import javax.swing.*;
import java.awt.*;

public class RuleGUI extends JPanel {
    private String statement = "玩法規則：\n" +
            "1. 目標是找出所有地雷的位置，不觸發地雷。\n" +
            "2. 每個非地雷的格子會顯示周圍的地雷數量。\n" +
            "3. 右鍵標記地雷，左鍵揭開格子。\n" +
            "4. 當所有非地雷的格子都被揭開，遊戲勝利。";
    private ImageIcon backgroundIcon;
    private Controller controller;

    public RuleGUI(Controller controller) {
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        // 設置布局
        this.setLayout(new BorderLayout());

        // 加載背景圖片
        backgroundIcon = new ImageIcon(getClass().getResource("./img/background.png"));
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        this.add(backgroundLabel, BorderLayout.CENTER);

        // 添加描述文字的背景區域
        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false); // 設置為透明以顯示背景圖片
        textPanel.setPreferredSize(new Dimension(400, 300)); // 設置大小
        textPanel.setLayout(new BorderLayout());

        // 添加描述文字
        JTextArea ruleText = new JTextArea(statement);
        ruleText.setEditable(false);
        ruleText.setOpaque(true); // 設置為不透明以顯示背景色
        ruleText.setFont(new Font("Microsoft JhengHei", Font.BOLD, 16)); // 使用支持中文的字體
        ruleText.setForeground(Color.BLACK);
        ruleText.setBackground(new Color(240, 240, 240)); // 設置背景色
        ruleText.setMargin(new Insets(20, 20, 20, 20)); // 設置邊緣空間

        textPanel.add(ruleText, BorderLayout.CENTER);

        // 添加返回主選單的按鈕
        JButton backButton = new JButton("返回主選單");
        backButton.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14)); // 設定字體和大小
        backButton.setForeground(new Color(200, 200, 200)); // 設定文字顏色
        backButton.setBackground(Color.darkGray); // 設定按鈕背景顏色
        backButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // 設定邊框
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setForeground(new Color(30, 30, 30));
                backButton.setBackground(Color.lightGray);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setForeground(new Color(200, 200, 200));
                backButton.setBackground(Color.darkGray);
            }
        });
        backButton.addActionListener(e -> {
            controller.switchPanel("MAIN");
        });
        textPanel.add(backButton, BorderLayout.SOUTH); // 將按鈕添加到 textPanel 的南側（底部）

        backgroundLabel.setLayout(new GridBagLayout());
        backgroundLabel.add(textPanel, new GridBagConstraints());
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}
