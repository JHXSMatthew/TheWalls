# The Walls 战墙

> [!WARNING]
>
> 该插件目前能且仅能运行在 Minecraft 1.8.x 的 bukkit/spigot/paper，请勿尝试用高版本服务端搭配本插件使用。

原 YC 使用版本在 wall_old 分支，master 为社区改进版。

表结构已经忘记了，有志之士可以复原一下。

稳定分支 master

开发分支 dev

正在尝试将插件所有依赖移除，清除与老 YC 框架的关系。

## 命令
管理员命令(OP)

/wall - 主命令，显示帮助信息
/wall start - 直接开始当前游戏（无视人数限制）
/wall percentage <0~1> - 设置游戏开始所需玩家比例

## 设置流程
1. 把地图名称改为 world，覆盖原 world
2. 启动服务器，在游戏内输入 `/wall` 开始设置场地
3. 输入 `/wall setup` 进入设置模式
4. 输入 `/wall create <名字> <显示名字>` 创建新场地
5. 输入 `/wall wand` 获取设置工具
6. 使用工具选择区域，然后输入 `/wall bound` 设置边界
7. 设置城墙、出生点等
8. 输入 `/wall lobby` 设置大厅位置
9. 输入 `/wall save` 保存设置

## 最近更新
- 修复了 `/wall start` 命令导致的内部错误
- 启用了之前被禁用的 `/wall percentage` 命令
- 修复了多个可能导致服务器崩溃的空指针异常
- 改进了配置文件，支持数据库连接配置
- 修复了游戏初始化相关问题
- 提高了插件稳定性
- 添加了数据库表自动创建功能
- 改进了错误处理和用户体验

## 注意事项
- 插件专为 Minecraft 1.8.x 版本设计
- 需要 Vault 插件支持权限和经济系统
- 推荐配合合适的地图大小和玩家数量使用


## 创建游戏流程
1. 把地图名称改为 world ,覆盖原 world
2. 启动服务器,在游戏内输入/wall 开始设置场地
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

