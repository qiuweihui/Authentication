# Authentication
##Vehicle Module
小车客户端模块

1.GenerateSM2Key类
：初始化时执行一次，产生小车的公钥和私钥，用于对发送内容的加密。

2.HashCompute类
：小车端计算哈希有三次，一次是初始计算自己公钥的hash，一次是核验服务器身份时计算服务器公钥的hash，一次是计算图像数据的hash
  ，分别为步骤1、6、8‘。
  
3.ImageEncrypt类
：视频图片数据的加密，使用SM4Key对称密钥，步骤8。

4。KeyDecrypt类
：密钥解密，将服务器发来的加密内容解密，获得服务器公钥和SM4Key，步骤5。

5.Signature类
：签名，使用小车私钥对VID和Time签名，步骤2。

6.SignVerify类
：签名验证，对服务器发来的签名使用服务器公钥和原文信息进行验证，确保服务器身份
，步骤7、8间。

7.UpChain类
：上传VID和公钥hash到区块链，步骤1。

8.ChainCheck类
：到区块链核验服务器身份，步骤6、7。

##EdgeServer Module
边缘服务器模块

类似小车客户端模块
