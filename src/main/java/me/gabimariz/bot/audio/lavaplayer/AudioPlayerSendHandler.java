package me.gabimariz.bot.audio.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.MutableAudioFrame;
import net.dv8tion.jda.api.audio.AudioSendHandler;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class AudioPlayerSendHandler implements AudioSendHandler {
  private final AudioPlayer AUDIO_PLAYER;
  private final ByteBuffer BUFFER;
  private final MutableAudioFrame FRAME;

  public AudioPlayerSendHandler(AudioPlayer audioPlayer) {
    this.AUDIO_PLAYER = audioPlayer;
    this.BUFFER = ByteBuffer.allocate(1024);
    this.FRAME = new MutableAudioFrame();

    this.FRAME.setBuffer(this.BUFFER);
  }

  @Override
  public boolean canProvide() {
    return this.AUDIO_PLAYER.provide(this.FRAME);

  }

  @Override
  public ByteBuffer provide20MsAudio() {
    final Buffer BUFFER = ((Buffer) this.BUFFER).flip();

    return (ByteBuffer) BUFFER;
  }

  @Override
  public boolean isOpus() {
    return true;
  }
}
