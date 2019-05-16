package com.damon.test.util;

import java.io.FileOutputStream;

public class WriteToFile {


    public static void main(String[] args) {
        String f = "a,b,c\nd,e,f";
        String fileName = "test";
//        createCSV(f, fileName);

        System.out.println(getCategory());

    }

    public static void createCSV(String dataList, String fileName) {
        String path = "F://testData/" + fileName + ".csv";
        byte [] by = dataList.getBytes();
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(by, 0, by.length);
            System.out.printf("写入文件%s成功", fileName);
        } catch (Exception e) {
            System.out.printf("写入文件%s失败", fileName);
        }
    }

    public static String getCategory() {
        String category = "冰箱,嵌入式冰箱\n" +
                "冰箱,干湿分储冰箱\n" +
                "冰箱,单门冰箱\n" +
                "冰箱,两门冰箱\n" +
                "冰箱,冰箱\n" +
                "冰箱,三门冰箱\n" +
                "冰箱,多门冰箱\n" +
                "冰箱,对开门冰箱\n" +
                "冷柜,冷柜\n" +
                "冷柜,冰吧\n" +
                "冷柜,酒柜\n" +
                "冷柜,展示柜\n" +
                "厨房小家电,榨汁机\n" +
                "厨房小家电,电磁炉\n" +
                "厨房小家电,奶泡机\n" +
                "厨房小家电,电水壶\n" +
                "厨房小家电,搅拌机\n" +
                "厨房小家电,多士炉\n" +
                "厨房小家电,煮蛋器\n" +
                "厨房小家电,微波炉\n" +
                "厨房小家电,多功能料理机\n" +
                "厨房小家电,电压力锅\n" +
                "厨房小家电,破壁机\n" +
                "厨房小家电,电饭煲\n" +
                "厨房小家电,厨房小家电\n" +
                "厨房电器,净水器\n" +
                "厨房电器,蒸箱\n" +
                "厨房电器,厨房电器\n" +
                "厨房电器,饮水机\n" +
                "厨房电器,吸油烟机\n" +
                "厨房电器,燃气灶\n" +
                "厨房电器,消毒柜\n" +
                "厨房电器,烤箱\n" +
                "厨房电器,洗碗机\n" +
                "智能产品,智能洁身器\n" +
                "智能产品,无线智能充电\n" +
                "智能产品,智能扫地机\n" +
                "智能产品,智能产品\n" +
                "智能产品,智能私人影院\n" +
                "智能产品,智能烤箱\n" +
                "洗衣机,滚筒洗衣机\n" +
                "洗衣机,波轮洗衣机\n" +
                "洗衣机,双缸洗衣机\n" +
                "洗衣机,洗衣机\n" +
                "洗衣机,mini洗衣机\n" +
                "洗衣机,干衣机\n" +
                "热水器,热泵\n" +
                "热水器,小厨宝\n" +
                "热水器,热水器\n" +
                "热水器,电热水器\n" +
                "热水器,燃气热水器\n" +
                "生活小家电,吸尘器\n" +
                "生活小家电,挂烫机\n" +
                "生活小家电,空气净化器\n" +
                "生活小家电,除湿器\n" +
                "生活小家电,加湿器\n" +
                "生活小家电,电暖器\n" +
                "生活小家电,电风扇\n" +
                "生活小家电,生活小家电\n" +
                "空调,中央空调\n" +
                "空调,商用空调\n" +
                "空调,商用柜嵌\n" +
                "空调,空调\n" +
                "空调,壁挂式空调\n" +
                "空调,柜式空调\n";
        String brand = "海尔,格力,美的,志高,华凌,柯兰特,九阳,华帝,帅康,TCL,长虹,康佳,创维,格兰仕,万宝,海尔,三星,美的," +
                "飞利浦,格力,索尼,松下,三洋,西门子,海尔,美菱, 海信,TCL,长虹";
        String[] categorys = category.split("\n");
        String[] brands = brand.split(",");
        int categoryIndex = (int)(Math.random() * categorys.length);
        int brandIndex = (int)(Math.random() * brands.length);
        return categorys[categoryIndex] + "," + brands[brandIndex] + categorys[categoryIndex].split(",")[1] + "," + brands[brandIndex];
    }

}
