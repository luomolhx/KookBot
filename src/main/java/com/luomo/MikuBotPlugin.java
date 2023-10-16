package com.luomo;

import lombok.Getter;
import snw.jkook.JKook;
import snw.jkook.command.JKookCommand;
import snw.jkook.entity.CustomEmoji;
import snw.jkook.message.component.MarkdownComponent;
import snw.jkook.plugin.BasePlugin;

/**
 * ClassName: MikuBotPlugin
 * Package: com.luomo
 * Description:
 *
 * @Author: luomo
 * @Create: 2023/10/16 - 22:47
 * @Version: v1.0
 */
public class MikuBotPlugin extends BasePlugin {

    @Getter
    public static MikuBotPlugin instance;

    @Override
    public void onLoad() {
        instance = this;
        getLogger().info("MikuBot 插件已加载");
    }

    @Override
    public void onEnable() {
        getLogger().info("MikuBot 插件已启用");
        new JKookCommand("info")
                .executesUser((sender, args, message) -> {
                    if (message == null) return;
                    CustomEmoji smile = JKook.getCore().getUnsafe().getEmoji("\ud84d\ude04");
                    message.sendReaction(smile);

                    StringBuilder replayContent = new StringBuilder();

                    String name = sender.getName();
                    String userId = sender.getId();
                    String fullName = name + "#" + sender.getIdentifyNumber();

                    replayContent.append("你的名字: ").append(name).append("\n");
                    replayContent.append("你的用户ID: ").append(userId).append("\n");
                    replayContent.append("你的完整用户名称: ").append(fullName).append("\n");

                    message.reply(new MarkdownComponent(replayContent.toString()));
                })
                .register(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("MikuBot 插件已禁用");
    }
}
