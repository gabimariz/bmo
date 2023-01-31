package me.gabimariz.bot.commands.music;

import me.gabimariz.bot.audio.lavaplayer.PlayerManager;
import me.gabimariz.bot.utils.Embed;
import me.gabimariz.bot.utils.EmbedMessage;
import me.gabimariz.bot.utils.MemberMessage;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

public class Play extends ListenerAdapter {

  @Override
  public void onMessageReceived(MessageReceivedEvent event) {
    String[] command = event.getMessage().getContentRaw().split(" ");

    String sound = String.join(" ", Arrays.copyOfRange(command, 1, command.length));

    TextChannel channel = event.getChannel().asTextChannel();

    if(event.getAuthor().isBot()) return;

    if(command[0].equals("!play")) {
      if(!Objects.requireNonNull(Objects.requireNonNull(event.getMember()).getVoiceState()).inAudioChannel()) {
        EmbedMessage embed = new EmbedMessage(
          "Warning",
          new Color(240, 173, 78),
          "You are not on a voice channel"
        );

        channel.sendMessageEmbeds(embed.message().build()).queue();

        return;
      }

      final AudioManager AUDIO_MANAGER = event.getGuild().getAudioManager();
      final VoiceChannel MEMBER_CHANNEL = (VoiceChannel) event.getMember().getVoiceState().getChannel();

      if(MemberMessage.role(event, "")
        || MemberMessage.permission(event, Permission.ADMINISTRATOR)) {
        channel.sendMessageEmbeds(Embed.error()).queue();

        return;
      }

      AUDIO_MANAGER.openAudioConnection(MEMBER_CHANNEL);

      PlayerManager.getInstance().loadAndPlay(event.getChannel().asTextChannel(), "ytsearch: " + sound +  "audio");
    }

  }
}
