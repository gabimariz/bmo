package me.gabimariz.bot.commands.music;

import me.gabimariz.bot.audio.lavaplayer.PlayerManager;
import me.gabimariz.bot.utils.Embed;
import me.gabimariz.bot.utils.MemberMessage;
import me.gabimariz.bot.utils.Verify;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Pause extends ListenerAdapter {
  @Override
  public void onMessageReceived(@NotNull MessageReceivedEvent event) {
    TextChannel channel = event.getChannel().asTextChannel();

    if (Verify.isBot(event))
      return;

    if (Verify.command(event)[0].equals("!pause")) {
      if (MemberMessage.role(event, "")
          || MemberMessage.permission(event, Permission.ADMINISTRATOR)) {
        channel.sendMessageEmbeds(Embed.status(
            "Access denied",
            new Color(217, 83, 79),
            "You are not allowed to use the command!")).queue();

        return;
      }

      PlayerManager.getInstance().getMusicManager(event.getGuild()).SCHEDULER.AUDIO_PLAYER
          .setPaused(!PlayerManager.getInstance().getMusicManager(event.getGuild()).SCHEDULER.AUDIO_PLAYER.isPaused());
    }
  }
}
