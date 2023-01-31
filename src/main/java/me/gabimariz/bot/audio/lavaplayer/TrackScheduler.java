package me.gabimariz.bot.audio.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrackScheduler extends AudioEventAdapter {
  public final AudioPlayer AUDIO_PLAYER;
  public final BlockingQueue<AudioTrack> QUEUE;

  public TrackScheduler(AudioPlayer audioPlayer) {
    this.AUDIO_PLAYER = audioPlayer;
    this.QUEUE = new LinkedBlockingQueue<>();
  }

  public void queue(AudioTrack track) {
    if(!this.AUDIO_PLAYER.startTrack(track, true)) {
      this.QUEUE.offer(track);
    }
  }

  public void nextTrack() {
    this.AUDIO_PLAYER.startTrack(this.QUEUE.poll(), false);
  }

  @Override
  public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
    if(endReason.mayStartNext) {
      nextTrack();
    }
  }
}
