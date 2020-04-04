# The Walls 战墙

原版本在wall_old分支，master为社区改进版。

表结构已经忘记了，有志之士可以复原一下。

稳定分支 master

开发分支 dev

正在尝试将插件所有依赖移除，清除与老YC框架的关系。

## 命令
管理员命令(OP)

/wall start 直接开始当前游戏 无视人数限制


## 创建游戏流程
设置之前建议搞个多世界还是什么东西的，能帮你在不同世界传送，设置完了一定记得删了多世界。


首先拿一个斧头，这个斧头左键右键可以标记2个点。
/wall wand 

第一步是创建一个游戏模板，/wall create <地图文件夹/世界名> <地图显示名>

然后/wall builder <建筑师名> 设置建筑师的名字。

第二步是设置游戏区域，
拿斧子标记一个地图区域，指的是整个战墙的作战区域。
拿斧子左键对角线一端，右键对角线另一端。
我记得有个WE mod挺好用的 客户端能看到区域范围。

/wall bound

下一步是设置墙，同理斧头左右键之后，
/wall wall

总共很多墙，重复输入就好。

下一步是设置出生点 人到点后输/wall spawn
4个队伍4个出生点

然后是设置玩家数量，必须是4的倍数。
/wall player <玩家数量>

然后是墙倒塌时间
/wall walltime <墙倒时间>

/wall percentage <百分比> 鬼知道是什么 我忘记了，输个1.0准没错

然后人跑到大厅去，
/wall lobby 设置大厅。

最后/wall save 保存。




# 其他项目
其他项目可以在 www.mcndsj.com/projects 找到。 如您有所需的未开源，请通过任意相关项目的GitHub issue联系我，会将对应项目开源。

# NOTICE

It's really been a while since this project is done.
The code in this project may use untraceable third-party open source codes and may violate the license. 
Please contact me through email 68638023@qq.com or submit an issue in GitHub repo and I will remove any illegal code from this project.
I owe you my apologies.

# 注意

项目内可能含有无法找寻来源的第三方开源代码，如使用有违反开源协议，请您直接通过邮件 68638023@qq.com 或 GitHub 的 issue 联系我。 我将第一时间将违规代码
从项目中删除，深表歉意！

