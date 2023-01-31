package me.gabimariz.bot.audio.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

public class GuildMusicManager {
  public final AudioPlayer AUDIO_PLAYER;
  public final TrackScheduler SCHEDULER;
  public final AudioPlayerSendHandler SEND_HANDLER;

  public GuildMusicManager(AudioPlayerManager manager) {
    this.AUDIO_PLAYER = manager.createPlayer();
    this.SCHEDULER = new TrackScheduler(this.AUDIO_PLAYER);
    this.AUDIO_PLAYER.addListener(this.SCHEDULER);
    this.SEND_HANDLER = new AudioPlayerSendHandler(this.AUDIO_PLAYER);
  }

  public AudioPlayerSendHandler getSendHandler() {
    return this.SEND_HANDLER;
  }
}
