# Authentication

##EdgeServer Module
边缘服务器模块

1.GenerateSM2Key类
：只在初始化时执行一次，产生服务器的公钥和私钥，用于对发送信息的加密和签名，步骤0。

2.GenerateSM4Key类
：只在初始化时执行一次，产生对称密钥，用于对视频图像的加密，步骤0。

3.HashCompute类
：服务器端计算哈希有两次，一次是初始计算自己公钥的hash，步骤1‘之前；
一次是核验小车身份时计算小车公钥的hash，步骤3之前。

4.UpChain类
：上传SID和公钥hash到区块链，步骤1’。

5.BroadcastReceive
：接收小车发送的广播信息，初步以点对点连接，后面使用广播，步骤2。

6.ChainCheck类
：到区块链核验服务器身份，步骤3、4。

7.SignVerify类
：签名验证，对小车发来的签名使用小车公钥和原文信息进行验证，确保小车身份
，步骤4之后。

8.KeyEncrypt类
：密钥加密，将服务器公钥和SM4Key加密，步骤5之前。

9.Signature类
：签名，使用服务器私钥对SID和Time签名，步骤5之前。

10.BroadcastResponse
：回复小车的广播，附带签名和加密信息返回，步骤5。

11.EncryptImageReceive
：接收小车发来的加密视频图像数据，并存储到边缘服务器上，步骤8。

12.ImageDecrypt类
：使用SM4Key对称密钥，解密视频图片数据的，步骤8，9之间。

13.DataTransmit类
：将解密后的视频图像数据上传到中心服务器上，步骤9。

14.TransmitResponse类
：接收中心服务器上传成功后的回复信息，步骤10。

15.TokenCreate类
：产生令牌，步骤11之前。

16.TokenTransmit
：转发令牌信息给小车，步骤11。


##Vehicle Module
小车客户端模块

1.GenerateSM2Key类
：只在初始化时执行一次，产生小车的公钥和私钥，用于对发送信息的加密和签名，步骤0。

2.HashCompute类
：小车端计算哈希有三次，一次是初始计算自己公钥的hash，步骤1之前；
一次是核验服务器身份时计算服务器公钥的hash，步骤6之前；
一次是计算图像数据的hash，步骤8’之前。

3.UpChain类
：上传VID和公钥hash到区块链，步骤1。
  
4.Signature类
：签名，使用小车私钥对VID和Time签名，步骤2之前。  

5.BroadcastSend类
：发送广播信息，初步以点对点连接，后面使用广播，步骤2。

6.ResponseReceive类
：接收边缘服务器认证成功后的回复信息，包括签名和加密信息，步骤5。

7.KeyDecrypt类
：密钥解密，将服务器发来的加密内容解密，获得服务器公钥和SM4Key，步骤5之后。

8.ChainCheck类
：到区块链核验服务器身份，步骤6、7。

9.SignVerify类
：签名验证，对服务器发来的签名使用服务器公钥和原文信息进行验证，确保服务器身份
，步骤7、8间。

10.ImageEncrypt类
：视频图片数据的加密，使用SM4Key对称密钥，步骤8之前。

11.UploadInfo类
：上传文本数据和hash数据到区块链，步骤8‘。

12.UpServer类
：将加密后的视频图像数据上传到边缘服务器，步骤8。

13.TokenGet
：获取令牌信息并存储，以供后面使用，步骤11。