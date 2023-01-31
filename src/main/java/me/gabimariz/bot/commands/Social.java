package me.gabimariz.bot.commands;

import me.gabimariz.bot.utils.EmbedMessage;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class Social extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
      EmbedMessage embed = new EmbedMessage(
        "Outras mídias",
        new Color(19, 102, 233),
        "**[instagram](https://instagram.com/edbbarros.dev)** \n" +
          "**[twitter](https://twitter.com/edbbarros)**\n" +
          "**[twitch](https://twitch.com/edbbarros)**\n" +
          "**[youtube](https://youtube.com/@edbbarros)**\n" +
          "**[linkedin](https://linkedin.com/in/edbbarros)**\n" +
          "**[github](https://github.com/edbbarros)**",
        "",
      "https://avatars.githubusercontent.com/u/39862684?v=4"
      );


      if(event.getName().equals("social")) {
        event.replyEmbeds(embed.thumbnail().build()).queue();
      }
    }
}
