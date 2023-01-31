package me.gabimariz.bot.commands;

import me.gabimariz.bot.utils.Embed;
import me.gabimariz.bot.utils.EmbedMessage;
import me.gabimariz.bot.utils.MemberSlash;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Live extends ListenerAdapter {
  @Override
  public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
    EmbedMessage embed = new EmbedMessage(
      "Estamos ao vivo",
      new Color(19, 102, 233),
      "@everyone chega mais a live vai ser massa",
      "https://avatars.githubusercontent.com/u/39862684?v=4",
      ""
    );

    if (event.getName().equals("live")) {
      if(!MemberSlash.memberRole(event, "1068641987579883663")
        || !MemberSlash.memberPermission(event, Permission.ADMINISTRATOR)) {
        event.replyEmbeds(Embed.error()).queue();

        return;
      }

      event.replyEmbeds(embed.message().build())
        .addActionRow(
          Button.link("https://twitch.com/edbbarros", "Ir para live")
        )
        .queue();

    }
  }
}
;
