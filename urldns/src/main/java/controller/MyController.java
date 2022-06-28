package controller;

import entity.ControllersFactory;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import utils.Dnslog;
import utils.HttpUtils;
import utils.PayloadGenerator;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MyController implements Initializable {
    @FXML
    public Button button_run;

    @FXML
    public TextArea result;

    @FXML
    public ComboBox<String> httpMethod;

    @FXML
    public TextField attackUrl;

    @FXML
    public TextField dns_url;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initComboBox();
    }

    public void initComboBox() {
        ObservableList<String> methods = FXCollections.observableArrayList(new String[] { "GET", "POST" });
        httpMethod.setPromptText("GET");
        httpMethod.setValue("GET");
        httpMethod.setItems(methods);
        dns_url.setDisable(true);

//        button_gen.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                // 生成payload
//                try {
//                    boolean flag = PayloadGenerate();
//                    if (flag){
//                        // 显示url框
//                        attackUrl.setDisable(false);
//                        httpMethod.setDisable(false);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        // 将对象存进去
        ControllersFactory.controllers.put(MyController.class.getSimpleName(),this);
    }

    public void execResult() throws Exception {
        String attackurl = attackUrl.getText();
        if (attackurl.length() != 0){
            //获取请求方式
            String method = httpMethod.getValue();

            //dnslog获取domain
            Map<String, String> map = Dnslog.GetDnslogDomain();
            String domain = map.get("domain");
            this.dns_url.appendText(domain);

            //payload生成
            boolean flag = PayloadGenerator.PayloadGenerate(domain);
            if (flag){
                this.result.appendText("[!] Payload生成成功：payload.ser!\n");
            }else {
                this.result.appendText("[!] Payload生成失败!\n");
                return;
            }

            //payload发送
            this.result.appendText("[!] Payload准备发送......\n");
            Integer code = HttpUtils.UrlDNSRequest(method, attackurl);
            if (code.equals(200)){
                this.result.appendText("[!] Payload发送成功!\n");
            }else {
                this.result.appendText("[!] Payload发送失败!\n");
                return;
            }

            //检查dnslog记录
            String cookie = map.get("cookie");
            flag = Dnslog.GetDnslogRecords(cookie);
            if (flag){
                this.result.appendText("[!] 检测到dnslog记录，存在反序列化点!\n");
            }else {
                this.result.appendText("[!] 未检测到dnslog记录，不存在反序列化点!\n");
            }

        }else {
            this.result.appendText("[!] 目标地址为空，请重新输入!\n");
        }
    }






}
