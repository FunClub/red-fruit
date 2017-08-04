package com.taomei.service.share.utils;

import java.util.*;

/**
 * 注册工具类
 */
public class RegisterUtil {
    private static List<String> firstNames=new ArrayList<>();
    private static List<String> lastNames=new ArrayList<>();;

    static {
        /*形容词*/
        firstNames.add("美丽的");
        firstNames.add("大方的");
        firstNames.add("矜持的");
        firstNames.add("可爱的");
        firstNames.add("害羞的");
        firstNames.add("贪玩的");
        firstNames.add("热情的");
        firstNames.add("好客的");
        firstNames.add("顾家的");
        firstNames.add("机灵的");
        firstNames.add("笨笨的");
        firstNames.add("妩媚的");
        firstNames.add("开心的");
        firstNames.add("爱哭的");
        firstNames.add("傻傻的");
        firstNames.add("胆大的");
        firstNames.add("胖胖的");
        firstNames.add("瘦瘦的");
        firstNames.add("小学的");
        firstNames.add("初中的");
        firstNames.add("高中的");
        firstNames.add("大学的");

        /*行为型*/
        firstNames.add("睡觉的");
        firstNames.add("看书的");
        firstNames.add("逛街的");
        firstNames.add("上网的");
        firstNames.add("贪吃的");
        firstNames.add("打盹的");
        firstNames.add("唱歌的");
        firstNames.add("游泳的");
        firstNames.add("大笑的");
        firstNames.add("唱歌的");
        firstNames.add("打牌的");
        firstNames.add("上课的");

         /*动物*/
        lastNames.add("猫咪");
        lastNames.add("企鹅");
        lastNames.add("蜻蜓");
        lastNames.add("海豚");
        lastNames.add("小龙虾");
        lastNames.add("咕咕鱼");

        /*菜品*/
        lastNames.add("包包菜");
        lastNames.add("汉堡包");
        lastNames.add("西红柿");
        lastNames.add("空心菜");
        lastNames.add("烧鹅");
        lastNames.add("哈密瓜");

        /*水果*/
        lastNames.add("葡萄");
        lastNames.add("西瓜");
        lastNames.add("哈密瓜");
        lastNames.add("橘子");
        lastNames.add("苹果");
        lastNames.add("水蜜桃");
        lastNames.add("八月瓜");
        lastNames.add("饼干");
    }



    /**
     * 随机生成昵称
     * @return 生成的昵称
     */
    public static String generateRandomNickName(){
        Random random = new Random();
        String firstName = firstNames.get(random.nextInt(firstNames.size()));
        String lastName = lastNames.get(random.nextInt(lastNames.size()));
        return firstName+lastName;

    }


}
