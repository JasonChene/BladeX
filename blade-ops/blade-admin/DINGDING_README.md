## SDK下载

#### Java SDK 下载
    下载SDK: https://open-doc.dingtalk.com/microapp/faquestions/vzbp02
    
## 配置项

#### bootstrap.yml

```
    spring:
      boot:
        admin:
          notify:
            dingtalk:
              webhook-token: ${Your DingDing Robot Token}
```