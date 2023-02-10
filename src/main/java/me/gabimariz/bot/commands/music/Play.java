package me.gabimariz.bot.commands.music;

import me.gabimariz.bot.audio.lavaplayer.PlayerManager;
import me.gabimariz.bot.utils.Embed;
import me.gabimariz.bot.utils.EmbedMessage;
import me.gabimariz.bot.utils.MemberMessage;
import me.gabimariz.bot.utils.Verify;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Arrays;

public class Play extends ListenerAdapter {

  @Override
  public void onMessageReceived(@NotNull MessageReceivedEvent event) {
    String sound = String.join(" ", Arrays.copyOfRange(Verify.command(event), 1, Verify.command(event).length));
    TextChannel channel = event.getChannel().asTextChannel();

    if (Verify.isBot(event))
      return;

    if (Verify.command(event)[0].equals("!play")) {
      if (!Verify.nonNull(Verify.nonNull(event.getMember()).getVoiceState()).inAudioChannel()) {
        EmbedMessage embed = new EmbedMessage(
            "Warning",
            new Color(240, 173, 78),
            "You are not on a voice channel");

        channel.sendMessageEmbeds(embed.message().build()).queue();

        return;
      }

      final AudioManager AUDIO_MANAGER = event.getGuild().getAudioManager();
      final VoiceChannel MEMBER_CHANNEL = (VoiceChannel) event.getMember().getVoiceState().getChannel();

      if (MemberMessage.role(event, "")
          || MemberMessage.permission(event, Permission.ADMINISTRATOR)) {
        channel.sendMessageEmbeds(Embed.status(
            "Access denied",
            new Color(217, 83, 79),
            "You are not allowed to use the command!")).queue();

        return;
      }

      AUDIO_MANAGER.openAudioConnection(MEMBER_CHANNEL);

      PlayerManager.getInstance().loadAndPlay(event.getChannel().asTextChannel(), "ytsearch: " + sound + "audio");
    }

  }
}
