package me.gabimariz.bot.commands.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import me.gabimariz.bot.Main;
import me.gabimariz.bot.audio.lavaplayer.GuildMusicManager;
import me.gabimariz.bot.audio.lavaplayer.PlayerManager;
import me.gabimariz.bot.utils.Embed;
import me.gabimariz.bot.utils.EmbedMessage;
import me.gabimariz.bot.utils.MemberMessage;
import me.gabimariz.bot.utils.Verify;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;

public class Skip extends ListenerAdapter {
  @Override
  public void onMessageReceived(@NotNull MessageReceivedEvent event) {
    TextChannel channel = event.getChannel().asTextChannel();

    GuildVoiceState botVoiceState = Main.api.getGuilds().get(0).getSelfMember().getVoiceState();
    GuildVoiceState memberVoiceState = Objects.requireNonNull(event.getMember()).getVoiceState();

    if (Verify.isBot(event))
      return;

    if (Verify.command(event)[0].equals("!skip")) {
      assert botVoiceState != null;
      if (!botVoiceState.inAudioChannel()) {
        channel.sendMessageEmbeds(
            Embed.status(
                "Warning",
                new Color(240, 173, 78),
                "I need to be on a voice channel!"))
            .queue();

        return;
      }

      if (MemberMessage.role(event, "")
          || MemberMessage.permission(event, Permission.ADMINISTRATOR)) {
        channel.sendMessageEmbeds(Embed.status(
            "Access denied",
            new Color(217, 83, 79),
            "You are not allowed to use the command!")).queue();

        return;
      }

      if (!memberVoiceState.inAudioChannel()) {
        channel.sendMessageEmbeds(
            Embed.status(
                "Warning",
                new Color(240, 173, 78),
                "You need to be on a voice channel!"))
            .queue();

        return;
      }

      if (!Verify.nonNull(Verify.nonNull(event.getMember()).getVoiceState()).inAudioChannel()) {
        channel.sendMessageEmbeds(
            Embed.status(
                "Warning",
                new Color(240, 173, 78),
                "You are not on a voice channel!"))
            .queue();

        return;
      }

      final GuildMusicManager MUSIC_MANAGER = PlayerManager.getInstance().getMusicManager(event.getGuild());
      final AudioPlayer AUDIO_PLAYER = MUSIC_MANAGER.AUDIO_PLAYER;

      if (AUDIO_PLAYER.getPlayingTrack() == null) {
        channel.sendMessageEmbeds(
            Embed.status(
                "Warning",
                new Color(240, 173, 78),
                "There is no track playing currently!"))
            .queue();

        return;
      }

      MUSIC_MANAGER.SCHEDULER.nextTrack();

      final String MUSIC_IDENTIFIER = AUDIO_PLAYER.getPlayingTrack().getInfo().identifier;
      final String MUSIC_TITLE = AUDIO_PLAYER.getPlayingTrack().getInfo().title;
      final String MUSIC_AUTHOR = AUDIO_PLAYER.getPlayingTrack().getInfo().author;

      EmbedMessage embed = new EmbedMessage(
          "Now playing",
          new Color(19, 102, 233),
          String.format(
              " **%s** \nby **%s**",
              MUSIC_TITLE,
              MUSIC_AUTHOR),
          "",
          String.format("https://img.youtube.com/vi/%s/0.jpg", MUSIC_IDENTIFIER));

      channel.sendMessageEmbeds(embed.thumbnail().build()).queue();
    }
  }
}
