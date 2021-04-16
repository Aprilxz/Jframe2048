package com.aobote.Fram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class mainFram extends JFrame implements KeyListener, ActionListener {
    //图片数组
    int[][] datas = new int[4][4];
            /*{2, 2, 4, 0},
            {4, 4, 8, 8},
            {8, 8, 2, 2},
            {16, 16, 32, 64}*/
            /*{2, 4, 8, 2},
            {16, 32, 64, 8},
            {128, 2, 256, 2},
            {512, 8, 1024, 2048}*/;

    int loseCheck = 1;
    //记录得分变量
    int score = 0;
    //换肤变量
    String theme = "A-";
    //导航栏
    JMenuItem item1 = new JMenuItem("经典");
    JMenuItem item2 = new JMenuItem("霓虹");
    JMenuItem item3 = new JMenuItem("糖果");
    JMenuItem item4 = new JMenuItem("创作:张晓风。  联系方式：15136261697");
    //初始化数据
    public void initDate() {
        genertorNum();
        genertorNum();
    }

    //空参构造
    public mainFram() {
        //初始化窗体
        initFrame();
        //初始化导航栏
        initmenu();
        //初始化数据
        genertorNum();
        //绘制界面
        paintView();
        //给窗体添加键盘监听
        this.addKeyListener(this);
        //调用方法显示窗口
        setVisible(true);

    }

    private void initmenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("换肤");
        JMenu menu2 = new JMenu("联系我们");
        menuBar.add(menu1);
        menuBar.add(menu2);
        menu1.add(item1);
        menu1.add(item2);
        menu1.add(item3);
        menu2.add(item4);
        item1.addActionListener(this);
        item2.addActionListener(this);
        item3.addActionListener(this);
        item4.addActionListener(this);
        setJMenuBar(menuBar);
    }

    /**
     * 此方法用于初始化窗体，所有窗体的相关设置都在这个方法中完成
     */
    public void initFrame() {
        //创建Random对象
        //Random rd = new Random();
        //对象名，调用方法，产生0-9的随机数
        /*int a = rd.nextInt(10);
        System.out.println(a);*/
        //调用方法设置宽高
        setSize(514, 538);
        //调用方法设置窗体集中
        setLocationRelativeTo(null);
        //调用方法设置窗体置顶
        setAlwaysOnTop(true);
        //调用方法设置关闭模式，点击关闭按钮结束程序
        setDefaultCloseOperation(3);
        //调用方法设置窗体标题
        setTitle("2048小游戏");
        //取消默认布局
        setLayout(null);
        //创建按钮对象
        /* JButton jb = new JButton("点击");
        jb.setBounds(150,150,100,100);*/
        //获取容器对象
        /*frame.getContentPane().add(jb);*/


    }

    /**
     * 此方法用于绘制游戏界面
     */
    public void paintView() {
        //移除掉，界面中所有的内容
        getContentPane().removeAll();
        if (loseCheck == 2) {
            JLabel losLabel = new JLabel(new ImageIcon("D:\\image\\"+theme+"lose.png"));
            losLabel.setBounds(90, 100, 334, 228);
            getContentPane().add(losLabel);
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JLabel jl = new JLabel(new ImageIcon("D:\\image\\"+theme+"" + datas[i][j] + ".png"));
                jl.setBounds(50 + 100 * j, 50 + 100 * i, 100, 100);
                super.getContentPane().add(jl);

            }
        }

        //设置背景
        JLabel banground = new JLabel(new ImageIcon("D:\\image\\"+theme+"Background.jpg"));
        banground.setBounds(40, 40, 420, 420);
        super.getContentPane().add(banground);
        //计算分数
        JLabel scoreLbael = new JLabel("分数" + score);
        scoreLbael.setBounds(40, 10, 120, 30);
        getContentPane().add(scoreLbael);
        //刷新界面
        getContentPane().repaint();

    }

    /**
     * keyTyped 无法鉴定键盘中的特殊按键比如上下左右shift ctrl等
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * 顺时针旋转
     */
    public void clockwise() {
        int[][] newArr = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newArr[j][3 - i] = datas[i][j];

            }
        }

        datas = newArr;
    }

    /**
     * 逆时针旋转
     */
    public void anticlockwise() {
        int[][] newArr = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newArr[3 - j][i] = datas[i][j];


            }
        }

        datas = newArr;


    }

    /**
     * 此方法用户数据右移动
     */
    private void moveToRight() {
        //反转数据
        horizontalSwap();
        //向左移动
        moveToleft();
        //反转数据
        horizontalSwap();
    }

    /**
     * 一维数组的反转
     */
    public void reverseArray(int[] arr) {
        for (int stat = 0, end = arr.length - 1; stat < end; stat++, end--) {
            int temp = arr[stat];
            arr[stat] = arr[end];
            arr[end] = temp;
        }

    }

    /**
     * 二维数组的反转
     */
    public void horizontalSwap() {
        for (int i = 0; i < datas.length; i++) {
            reverseArray(datas[i]);
        }
    }

    /**
     * 用于处理数据上移动
     */
    public void moveToTop() {
        anticlockwise();
        moveToleft();
        clockwise();
    }

    /**
     * 此方法用于下移动
     */
    public void moveToBottom() {
        clockwise();
        moveToleft();
        anticlockwise();
    }


    /**
     * 此方法用于数据的左移动
     */
    public void moveToleft() {
        //数据前移
        for (int i = 0; i < datas.length; i++) {
            int[] newArr = new int[4];
            int index = 0;
            for (int x = 0; x < datas[i].length; x++) {
                if (datas[i][x] != 0) {
                    newArr[index] = datas[i][x];
                    index++;
                }
            }
            //数据补零
            datas[i] = newArr;
            for (int x = 0; x < 3; x++) {
                if (datas[i][x] == datas[i][x + 1]) {
                    datas[i][x] *= 2;
                    //计算得分
                    score += datas[i][x];
                    for (int j = x + 1; j < 3; j++) {
                        datas[i][j] = datas[i][j + 1];
                    }
                    datas[i][3] = 0;
                }
            }
        }

    }

    /**
     * 检查上下左右是否成功
     */
    public void check() {
        if (checkLeft() == false && checkRight() == false && checkTop() == false && checkBottom() == false) {
            //失败的状态
            System.out.println("游戏失败");
            loseCheck = 2;
        }
    }

    /**
     * 此方法用于二维数组的拷贝
     *
     * @param src  原数组
     * @param dest 新数组
     */
    public void copyArray(int[][] src, int[][] dest) {
        for (int i = 0; i < src.length; i++) {
            for (int j = 0; j < src[i].length; j++) {
                dest[i][j] = src[i][j];
            }
        }
    }

    /**
     * 判断数据是否可以左移动
     */
    public boolean checkLeft() {
        //创建新数组用于备份原数组数据
        int[][] newArr = new int[4][4];
        //将原数组数据拷贝到新数组中
        copyArray(datas, newArr);
        //调用左移的方法，对数组进行左移动
        moveToleft();

        boolean flag = false;
        lo:
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                if (datas[i][j] != newArr[i][j]) {
                    //只要元素有一个不一样的，就代表数据可以变化，数据移动了
                    flag = true;
                    break lo;
                }
            }
        }
        //数据还原
        copyArray(newArr, datas);
        //返回结果
        return flag;
    }

    /**
     * 判断数据是否可以右移动
     */
    public boolean checkRight() {
        int[][] newArr = new int[4][4];
        copyArray(datas, newArr);
        boolean flag = false;
        moveToRight();

        lo:
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                if (datas[i][j] != newArr[i][j]) {
                    flag = true;
                    break lo;
                }
            }
        }
        copyArray(newArr, datas);

        return flag;
    }

    /**
     * 判断数据是否可以上移动
     */
    public boolean checkTop() {
        int[][] newArr = new int[4][4];
        copyArray(datas, newArr);
        moveToTop();
        boolean flag = false;
        lo:
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                if (datas[i][j] != newArr[i][j]) {
                    flag = true;
                    break lo;
                }
            }
        }
        copyArray(newArr, datas);
        return flag;
    }

    /**
     * 判断数据是否可以下移动
     */
    public boolean checkBottom() {
        int[][] newArr = new int[4][4];
        copyArray(datas, newArr);
        boolean flag = false;
        moveToBottom();

        lo:
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                if (datas[i][j] != newArr[i][j]) {
                    flag = true;
                    break lo;
                }
            }
        }
        copyArray(newArr, datas);
        return flag;
    }

    /**
     * KeyReleased是键盘松开时的状态
     */

    /**
     * KeyPressed键盘被按下时，所触发的方法，在这个方法中区分上下左右按键
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == 37) {
            moveToleft();
            genertorNum();
        } else if (keyCode == 38) {
            moveToTop();
            genertorNum();
        } else if (keyCode == 39) {
            moveToRight();
            genertorNum();
        } else if (keyCode == 40) {
            moveToBottom();
            genertorNum();
        }

        //每一次移动完都检查flag的值，判定游戏是否失败
        check();
        //重新绘制界面
        paintView();
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    //此方法用于从空白位置，随机产生数字块
    public void genertorNum() {
        int[] arrayi = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        int[] arrayj = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        int w = 0;
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                if (datas[i][j] == 0) {
                    arrayi[w] = i;
                    arrayj[w] = j;
                    w++;
                }
            }
        }
        if (w != 0) {
            Random r = new Random();
            int index = r.nextInt(w);
            int x = arrayi[index];
            int y = arrayj[index];
            datas[x][y] = 2;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == item1 ){
            theme = "A-";
        }else  if (e.getSource() == item2){
            theme = "B-";
        }else  if (e.getSource() == item3){
            theme = "c-";
        }
        //初始化界面
        paintView();
    }
}


