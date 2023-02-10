package me.gabimariz.bot.commands;

import io.github.cdimascio.dotenv.Dotenv;
import me.gabimariz.bot.utils.EmbedMessage;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class Social extends ListenerAdapter {
  private final Dotenv ENV = Dotenv.load();

  @Override
  public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
    EmbedMessage embed = new EmbedMessage(
      "Other medias",
      new Color(19, 102, 233),
      String.format(
        "*[instagram](%s)**\n **[twitter](%s)**\n **[twitch](%s)**\n **[youtube](%s)**\n **[linkedin](%s)**\n **[github](%s)**",
        ENV.get("INSTAGRAM_URL"),
        ENV.get("TWITTER_URL"),
        ENV.get("TWITCH_URL"),
        ENV.get("YOUTUBE_URL"),
        ENV.get("LINKEDIN_URL"),
        ENV.get("GITHUB_URL")
      ),
      "",
      ""
      );

      if(event.getName().equals("social")) {
        event.replyEmbeds(embed.thumbnail().build()).queue();
      }
  }
}
