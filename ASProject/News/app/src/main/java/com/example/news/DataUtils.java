package com.example.news;

import java.util.ArrayList;
import java.util.List;

public class DataUtils {
    public static final String[] TITLES={"主页","推荐","科技","实事","娱乐"};
    public static final List<Integer> PICTURE = new ArrayList<Integer>(){{
        for (int i=0;i<100;i++){
            add(R.drawable.one);
            add(R.drawable.two);
            add(R.drawable.three);
        }

    }};
    public static final List<String> NEWSSTRING = new ArrayList<String>(){{

        for (int i=0;i<100;i++){
            add("李商隐因“抄袭”《夜雨寄北》被骂上热搜？年轻人不读书有多可怕");
            add("《后浪》为何让人愤怒？真实的90后：每天赚300元，结婚不敢想");
            add("为什么一开口就能知道我是广东人呢？看完这个真的忍不住了，哈哈哈哈哈哈！");
        }

    }};
}
