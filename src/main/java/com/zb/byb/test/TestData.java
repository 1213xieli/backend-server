package com.zb.byb.test;

import com.zb.byb.controller.TouMiaoController;
import com.zb.byb.entity.TouMiao;
import com.zb.byb.service.TouMiaoService;
import com.zb.byb.service.impl.TouMiaoServiceImpl;
import com.zb.byb.util.BeanLocator;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Date;

/**
 * 作者：谢李
 */
public class TestData {

    public static void main(String[] args) {
        try {
            TestData test = new TestData();

            test.touMiaoController();
        }
        catch (Exception e)
        {

        }
    }

    public static void touMiaoController() throws Exception {
//        TouMiao info = new TouMiao();
//        info.setId("xieli");
//        info.setApplyDate(new Date());
//        info.setApplyAmount(333);
//        BeanLocator.getT(TouMiaoService.class).saveInfo(info);
        BeanLocator.getT(TouMiaoService.class).queryListInitData("32");
    }

}
