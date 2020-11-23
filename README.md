# Authentication
小车客户端模块,执行顺序就是标号顺序。

1.GenerateSM2Key类 ：

只在初始化时执行一次，产生小车的公钥和私钥，用于对发送信息的加密和签名，步骤0。结果分别存到pubkey.json和prikey.json。

2.HashCompute类 ：

小车端计算哈希有三次，一次是初始计算自己公钥的hash，调用hashCompute方法返回hash，步骤1之前； 一次是核验服务器身份时计算服务器公钥的hash，调用hashCompute方法返回hash，步骤6之前； 一次是计算图像数据的hash，调用imageHashCompute方法返回hash，步骤8’之前。

3.UpChain类 ：

上传VID和公钥hash到区块链，步骤1。VID存放在VID.json文件中，可以自己指定，而且是不可变的，hash计算传入pubkey.json路径，调用hashCompute方法即可。

4.Signature类 ：

签名，使用小车私钥对VID和Time签名，步骤2之前。选取签名是的时间戳作为Time，和VID串接为VID_Time字符串后进行签名。

5.BroadcastSend类 ：

发送广播信息，初步以点对点连接，暂时未使用广播，步骤2。

6.ResponseReceive类 ：

接收边缘服务器认证成功后的回复信息，步骤5。存储为response_receive.json文件，其中包含sm2_sign、SID_Time 、encrypt，分别对应签名、签名原文和加密信息，

7.KeyDecrypt类 ：

将服务器发来的encrypt内容解密，获得服务器公钥和SM4Key，步骤5之后。分别存为pubkey_server.json和sm4key.json文件。

8.ChainCheck类 ：

到区块链核验服务器身份，步骤6、7。读入response_receive.json文件中的SID_Time对象，截取前四位为SID，读入pubkey_server.json中的pubkey对象为服务器公钥，调用hashCompute计算hash并上传核验。

9.SignVerify类 ：

在第7步接到区块链确认后，进行签名验证，对服务器发来的签名使用服务器公钥pubkey_server、签名内容sm2_sign和原文信息SID_Time进行验证，步骤7、8间。

10.ImageEncrypt类 ：

视频图片数据的加密，步骤8之前。使用SM4Key对称密钥，传入视频路径进行加密并输出。

11.UploadInfo类 ：

上传文本数据和hash数据到区块链，步骤8‘。海拔及经纬度等文本数据可以读取本地的文本文件text_data.json，VID读取本地VID.json文件，视频hash调用imageHashCompute方法计算并加入obj json对象中上传。

12.UpServer类 ：

将加密后的视频图像数据上传到边缘服务器，步骤8。需要传入本地加密信息路径和服务器IP地址两个参数。

13.TokenReceive ：

获取令牌信息并存储，以供后面使用，步骤11。
