package me.gabimariz.bot.commands.music;

import me.gabimariz.bot.audio.lavaplayer.PlayerManager;
import me.gabimariz.bot.utils.Embed;
import me.gabimariz.bot.utils.MemberMessage;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Pause extends ListenerAdapter {
  @Override
  public void onMessageReceived(MessageReceivedEvent event) {
    String[] command = event.getMessage().getContentRaw().split(" ");

    TextChannel channel = event.getChannel().asTextChannel();

    if(event.getAuthor().isBot()) return;

    if(command[0].equals("!pause")) {
      if(MemberMessage.role(event, "")
        || MemberMessage.permission(event, Permission.ADMINISTRATOR)) {
        channel.sendMessageEmbeds(Embed.error()).queue();

        return;
      }

      if(PlayerManager.getInstance().getMusicManager(event.getGuild()).SCHEDULER.AUDIO_PLAYER.isPaused()) {
        PlayerManager.getInstance().getMusicManager(event.getGuild()).SCHEDULER.AUDIO_PLAYER.setPaused(false);
      } else {
        PlayerManager.getInstance().getMusicManager(event.getGuild()).SCHEDULER.AUDIO_PLAYER.setPaused(true);
      }
    }
  }
}
