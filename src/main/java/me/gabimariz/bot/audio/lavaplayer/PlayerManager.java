package me.gabimariz.bot.audio.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerManager {
  private static PlayerManager instance;
  private final Map<Long, GuildMusicManager> MUSIC_MANAGERS;
  private final AudioPlayerManager AUDIO_PLAYER_MANAGER;

  public PlayerManager() {
    this.MUSIC_MANAGERS = new HashMap<>();
    this.AUDIO_PLAYER_MANAGER = new DefaultAudioPlayerManager();

    AudioSourceManagers.registerRemoteSources(this.AUDIO_PLAYER_MANAGER);
    AudioSourceManagers.registerLocalSource(this.AUDIO_PLAYER_MANAGER);
  }

  public GuildMusicManager getMusicManager(Guild guild) {
    return this.MUSIC_MANAGERS.computeIfAbsent(guild.getIdLong(), (guildId) -> {
      final GuildMusicManager GUILD_MUSIC_MANAGER = new GuildMusicManager(this.AUDIO_PLAYER_MANAGER);
      guild.getAudioManager().setSendingHandler(GUILD_MUSIC_MANAGER.getSendHandler());

      return GUILD_MUSIC_MANAGER;
    });
  }

  public void loadAndPlay(TextChannel textChannel, String trackUrl) {
    final GuildMusicManager MUSIC_MANAGER = this.getMusicManager(textChannel.getGuild());

    this.AUDIO_PLAYER_MANAGER.loadItemOrdered(MUSIC_MANAGER, trackUrl, new AudioLoadResultHandler() {
      @Override
      public void trackLoaded(AudioTrack track) {
        MUSIC_MANAGER.SCHEDULER.queue(track);

        textChannel.sendMessage(
          "Adding to queue **`" +
            track.getInfo().title +
            "`** feat **`" +
            track.getInfo().author +
            "`**"
        ).queue();
      }

      @Override
      public void playlistLoaded(AudioPlaylist playlist) {
        final List<AudioTrack> TRACKS = playlist.getTracks();

        if(!TRACKS.isEmpty()) {
          MUSIC_MANAGER.SCHEDULER.queue(TRACKS.get(0));

          textChannel.sendMessage(
            "adding to queue **" +
            TRACKS.get(0).getInfo().title +
            "** feat **" +
              TRACKS.get(0).getInfo().author +
              "**"
            ).queue();
        }
      }

      @Override
      public void noMatches() {

      }

      @Override
      public void loadFailed(FriendlyException exception) {

      }
    });
  }

  public static PlayerManager getInstance() {
    if(instance == null) {
      instance = new PlayerManager();
    }

    return instance;
  }

}
