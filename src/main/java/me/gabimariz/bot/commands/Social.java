package me.gabimariz.bot.commands;

import me.gabimariz.bot.utils.EmbedMessage;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class Social extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
      EmbedMessage embed = new EmbedMessage(
        "Other medias",
        new Color(19, 102, 233),
        "**[instagram]()** \n" +
          "**[twitter]()**\n" +
          "**[twitch]()**\n" +
          "**[youtube]()**\n" +
          "**[linkedin]()**\n" +
          "**[github]()**",
        "",
      ""
      );


      if(event.getName().equals("social")) {
        event.replyEmbeds(embed.thumbnail().build()).queue();
      }
    }
}
