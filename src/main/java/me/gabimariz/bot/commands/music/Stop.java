package me.gabimariz.bot.commands.music;

import me.gabimariz.bot.Main;
import me.gabimariz.bot.audio.lavaplayer.PlayerManager;
import me.gabimariz.bot.utils.Embed;
import me.gabimariz.bot.utils.MemberMessage;
import me.gabimariz.bot.utils.Verify;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Objects;

public class Stop extends ListenerAdapter {
  @Override
  public void onMessageReceived(MessageReceivedEvent event) {
    TextChannel channel = event.getChannel().asTextChannel();

    if(Verify.isBot(event)) return;

    if(Verify.command(event)[0].equals("!stop")) {

      GuildVoiceState botVoiceState = Main.api.getGuilds().get(0).getSelfMember().getVoiceState();
      GuildVoiceState memberVoiceState = Objects.requireNonNull(event.getMember()).getVoiceState();

      if(MemberMessage.role(event, "")
        || MemberMessage.permission(event, Permission.ADMINISTRATOR)) {
        channel.sendMessageEmbeds(
          Embed.status(
            "Access denied",
            new Color(217, 83, 79),
            "You are not allowed to use the command!")
        ).queue();

        return;
      }

      if(!Verify.nonNull(Verify.nonNull(event.getMember()).getVoiceState()).inAudioChannel()) {
        channel.sendMessageEmbeds(
          Embed.status(
            "Warning",
            new Color(240, 173, 78),
            "You are not on a voice channel!")
        ).queue();

        return;
      }

      if(botVoiceState.getChannel().equals(memberVoiceState.getChannel())) {
        PlayerManager.getInstance().getMusicManager(event.getGuild()).SCHEDULER.AUDIO_PLAYER.stopTrack();
        PlayerManager.getInstance().getMusicManager(event.getGuild()).SCHEDULER.QUEUE.clear();

        event.getGuild().getAudioManager().closeAudioConnection();

        channel.sendMessageEmbeds(
          Embed.status(
            "Stopped",
            new Color(92, 184, 92),
            "")).queue();
      } else {
        channel.sendMessageEmbeds(
          Embed.status(
            "Warning",
            new Color(240, 173, 78),
            "You are not on the same voice channel!")
        ).queue();

        return;
      }
    }
  }
}
