# ShareDemo
分享工具module，支持分享qq，微信等等..

#架包导入方式
1.将ShareUtil 这个module导出为aar格式，然后加入新项目中

    compile(name: 'shareutil-0.0.1', ext: 'aar')

2.在新项目中直接引用该module

#此分享工具类的使用
1.首先需要在自定义的Application中初始化该对象，初始化过程中添加各平台的appid，具体appid需要去各平台注册申请。

     ShareUtil.getInstance(getApplicationContext()).initQQ("YOUR APPID");

2.在需要展示分享菜单的地方

     ShareUtil.getInstance(getApplicationContext()).show(MainActivity.this, new ShareVo("分享", "什么", "http://www.baidu.com", "http://d.hiphotos.baidu.com/image/h%3D200/sign=80d7f3fbc8fc1e17e2bf8b317a91f67c/6c224f4a20a44623ca89e18f9f22720e0df3d798.jpg", "wtf", 0));


